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

package org.maxgamer.quickshop.util.text;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.util.MsgUtil;

@AllArgsConstructor
@Getter
@Setter
/*
 A utils for print sheet on chat.
*/
public class ChatSheetPrinter {
    private final ChatColor chatColor = ChatColor.DARK_PURPLE;
    private CommandSender p;

    public void printCenterLine(@NotNull String text) {
        if (!text.isEmpty()) {
            MsgUtil.sendMessage(p,
                    chatColor
                            + MsgUtil.getMessage("tableformat.left_half_line", p)
                            + text
                            + MsgUtil.getMessage("tableformat.right_half_line", p));
        }
    }

    public void printExecuteableCmdLine(
            @NotNull String text, @NotNull String hoverText, @NotNull String executeCmd) {
        TextComponent message =
                new TextComponent(chatColor + MsgUtil.getMessage("tableformat.left_begin", p) + text);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, executeCmd));
        message.setHoverEvent(
                new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverText).create())); //FIXME: Update this when drop 1.15 supports
        p.spigot().sendMessage(message);
    }

    public void printFooter() {
        MsgUtil.sendColoredMessage(p, chatColor, MsgUtil.getMessage("tableformat.full_line", p));
    }

    public void printHeader() {
        MsgUtil.sendColoredMessage(p, chatColor, MsgUtil.getMessage("tableformat.full_line", p));
    }

    public void printLine(@NotNull String text) {
        String[] texts = text.split("\n");
        for (String str : texts) {
            if (!str.isEmpty()) {
                MsgUtil.sendMessage(p, chatColor + MsgUtil.getMessage("tableformat.left_begin", p) + str);
            }
        }
    }

    public void printSuggestableCmdLine(
            @NotNull String text, @NotNull String hoverText, @NotNull String suggestCmd, TextComponent... additionText) {
        TextComponent message =
                new TextComponent(chatColor + MsgUtil.getMessage("tableformat.left_begin", p) + text);
        message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, suggestCmd));
        message.setHoverEvent(
                new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverText).create())); //FIXME: Update this when drop 1.15 supports
        if (additionText.length >= 1) {
            p.spigot().sendMessage(new ComponentBuilder().append(message).append(additionText).create());
        } else {
            p.spigot().sendMessage(message);
        }
    }

}
