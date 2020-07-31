/*
 *     Copyright (c) 2020, Bukkit Commons Studio.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>
 */

package org.maxgamer.quickshop.economy;

import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.util.Util;
import org.maxgamer.quickshop.util.math.CalculateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class EconomyTransaction {
    @Nullable
    private final UUID from;
    @Nullable
    private final UUID to;
    private final double amount;
    @NotNull
    private final EconomyCore core;
    private final double total; //
    private final double tax;
    private final UUID taxAccount;
    private final boolean allowLoan;
    private TransactionSteps steps; //For rollback
    @Nullable
    private String lastError = null;


    /**
     * Create a transaction
     *
     * @param from The account that money from, be null is ignored.
     * @param to   The account that money to, be null is ignored.
     * @param core The economy core
     */
    @Builder
    public EconomyTransaction(@Nullable UUID from, @Nullable UUID to, double amount, double taxModifier, @Nullable UUID taxAccount, @NotNull EconomyCore core, boolean allowLoan) {
        this.from = from;
        this.to = to;
        this.core = core;
        this.amount = amount;
        this.steps = TransactionSteps.WAIT;
        this.taxAccount = taxAccount;
        this.allowLoan = allowLoan;
        if (taxModifier != 0.0d) { //Calc total money and apply tax
            this.total = CalculateUtil.multiply(taxModifier, amount);
        } else {
            this.total = amount;
        }
        this.tax = CalculateUtil.subtract(amount, total); //Calc total tax
        if (from == null && to == null) {
            lastError = "From and To cannot be null in same time.";
            throw new IllegalArgumentException("From and To cannot be null in same time.");
        }
        //Fetch some stupid plugin caching
        if (from != null) {
            core.getBalance(from);
        }
        if (to != null) {
            core.getBalance(to);
        }
    }

    /**
     * Commit the transaction by the Fail-Safe way
     * Automatic rollback when commit failed
     *
     * @return The transaction success.
     */
    public boolean failSafeCommit() {
        Util.debugLog("Transaction begin: FailSafe Commit --> " + from + " => " + to + "; Amount: " + amount + ", EconomyCore: " + core.getName());
        boolean result = commit();
        if (!result) {
            rollback(true);
        }
        return result;
    }

    /**
     * Commit the transaction
     *
     * @return The transaction success.
     */
    public boolean commit() {
        return this.commit(new TransactionCallback() {
        });
    }

    /**
     * Commit the transaction with callback
     *
     * @param callback The result callback
     * @return The transaction success.
     */
    public boolean commit(@NotNull TransactionCallback callback) {
        Util.debugLog("Transaction begin: Regular Commit --> " + from + " => " + to + "; Amount: " + amount + " Total(include tax): " + total + " Tax: " + tax + ", EconomyCore: " + core.getName());
        if (from != null && core.getBalance(from) < total && !allowLoan) {
            this.lastError = "From hadn't enough money";
            callback.onFailed(this);
            return false;
        }
        steps = TransactionSteps.WITHDRAW;
        if (from != null && !core.withdraw(from, amount)) {
            this.lastError = "Failed to withdraw " + amount + " from player " + from.toString() + " account";
            callback.onFailed(this);
            return false;
        }
        steps = TransactionSteps.DEPOSIT;
        if (to != null && !core.deposit(to, total)) {
            this.lastError = "Failed to deposit " + total + " to player " + to.toString() + " account";
            callback.onFailed(this);
            return false;
        }
        steps = TransactionSteps.TAX;
        if (taxAccount != null && !core.deposit(taxAccount, tax)) {
            this.lastError = "Failed to deposit tax account: " + tax;
        }
        steps = TransactionSteps.DONE;
        callback.onSuccess(this);
        return true;
    }

    /**
     * Rolling back the transaction
     *
     * @param continueWhenFailed Continue when some parts of the rollback fails.
     * @return A list contains all steps executed. If "continueWhenFailed" is false, it only contains all success steps before hit the error. Else all.
     */
    @SuppressWarnings("UnusedReturnValue")
    @NotNull
    public List<RollbackSteps> rollback(boolean continueWhenFailed) {
        List<RollbackSteps> rollbackSteps = new ArrayList<>(3);
        if (steps == TransactionSteps.WAIT) {
            return rollbackSteps; //We did nothing, just checks balance
        }
        if (steps == TransactionSteps.DEPOSIT || steps == TransactionSteps.WITHDRAW || steps == TransactionSteps.TAX) {
            if (from != null && !core.deposit(from, amount)) { //Rollback withdraw
                if (!continueWhenFailed) {
                    rollbackSteps.add(RollbackSteps.ROLLBACK_WITHDRAW);
                    return rollbackSteps;
                }
            }
        }
        if (steps == TransactionSteps.DEPOSIT || steps == TransactionSteps.TAX) {
            if (to != null && !core.withdraw(to, total)) { //Rollback deposit
                if (!continueWhenFailed) {
                    rollbackSteps.add(RollbackSteps.ROLLBACK_DEPOSIT);
                    return rollbackSteps;
                }
            }
        }

        if (steps == TransactionSteps.TAX) {
            if (taxAccount != null && !core.withdraw(taxAccount, tax)) {
                this.lastError = "Failed to withdraw tax account when rollback: " + tax;
            }
        }

        if (taxAccount != null && !core.deposit(taxAccount, tax)) { //Rollback withdraw
            rollbackSteps.add(RollbackSteps.ROLLBACK_TAX); //Ignore fails
        }
        rollbackSteps.add(RollbackSteps.ROLLBACK_DONE);
        return rollbackSteps;
    }

    private enum RollbackSteps {
        ROLLBACK_WITHDRAW,
        ROLLBACK_DEPOSIT,
        ROLLBACK_TAX,
        ROLLBACK_DONE
    }

    private enum TransactionSteps {
        WAIT,
        WITHDRAW,
        DEPOSIT,
        TAX,
        DONE
    }

    interface TransactionCallback {
        default void onSuccess(@NotNull EconomyTransaction economyTransaction) {
            Util.debugLog("Transaction succeed.");
        }

        default void onFailed(@NotNull EconomyTransaction economyTransaction) {
            Util.debugLog("Transaction failed: " + economyTransaction.getLastError() + ".");
        }

    }

}
