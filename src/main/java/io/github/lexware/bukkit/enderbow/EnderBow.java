/**
 * Copyright 2015 Jamie Mansfield <https://github.com/lexware>
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
package io.github.lexware.bukkit.enderbow;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by jamie on 11/01/15.
 */
public class EnderBow extends ItemStack {
    public EnderBow() {
        super(Material.BOW);
        
        // Set ItemMeta (displayname etc)
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName("Ender bow");
        this.setItemMeta(itemMeta);
    }
}
