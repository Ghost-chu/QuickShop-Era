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

package org.maxgamer.quickshop.util.economy;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.economy.Economy;
import org.maxgamer.quickshop.util.MsgUtil;
import org.maxgamer.quickshop.util.Util;

@AllArgsConstructor
public class EconomyUtil {
    @NotNull
    private final Economy economy;

    private final boolean disableVaultFormat;

    private final boolean useDecimalFormat;

    private final boolean currencySymbolOnRight;

    @NotNull
    private final String alternateCurrencySymbol;


    /**
     * Formats the given number according to how vault would like it. E.g. $50 or 5 dollars.
     *
     * @param n price
     * @return The formatted string.
     */
    @NotNull
    public String format(double n) {
        return format(n, disableVaultFormat);
    }

    @NotNull
    public String format(double n, boolean internalFormat) {
        if (internalFormat) {
            return getInternalFormat(n);
        }

        try {
            String formatted = economy.format(n);
            if (formatted == null || formatted.isEmpty()) {
                Util.debugLog(
                        "Use alternate-currency-symbol to formatting, Cause economy plugin returned null");
                return this.getInternalFormat(n);
            } else {
                return formatted;
            }
        } catch (Exception e) {
            Util.debugLog("format", e.getMessage());
            Util.debugLog(
                    "format", "Use alternate-currency-symbol to formatting, Cause NumberFormatException");
            return getInternalFormat(n);
        }
    }

    private String getInternalFormat(double amount) {
        String formatted = useDecimalFormat ? MsgUtil.decimalFormat(amount) : Double.toString(amount);
        return currencySymbolOnRight ? formatted + alternateCurrencySymbol : alternateCurrencySymbol + formatted;
    }

}
