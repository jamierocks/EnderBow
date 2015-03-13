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
package uk.jamierocks.bukkit.enderbow.data;

import static uk.jamierocks.bukkit.enderbow.EnderBowPlugin.getInstance;

/**
 * Created by jamie on 18/02/15.
 */
public class Settings {

    /**
     * Gets the set language, defaults to 'enUS'
     * 
     * @return the set language
     */
    public static String getLanguage() {
        return getInstance().getConfig().getString("language", "enUS");
    }

    /**
     * Gets if auto updating is enabled, default to false
     *
     * @return true if auto updating is enabled, false otherwise
     */
    public static boolean isAutoUpdateEnabled() {
        return getInstance().getConfig().getBoolean("enable.auto.updater", false);
    }
}
