/*
 * Copyright 2015 Jamie Mansfield <https://github.com/jamierocks>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.jamierocks.bukkit.enderbow;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.jamierocks.bukkit.enderbow.data.locale.Language;

/**
 * Created by jamie on 11/01/15.
 */
public class EnderBowCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("enderbow.give") || sender.hasPermission("enderbow.spawn")) {
            if(sender instanceof Player) {
                ((Player) sender).getInventory().addItem(EnderBowPlugin.getEnderBow());
            } else {
                sender.sendMessage(Language.localize("server.give"));
            }
        } else {
            sender.sendMessage(Language.localize("permission.insufficient"));
        }
        return true;
    }
}
