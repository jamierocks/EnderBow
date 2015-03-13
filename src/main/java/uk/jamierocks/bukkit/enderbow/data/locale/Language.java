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
package uk.jamierocks.bukkit.enderbow.data.locale;

import uk.jamierocks.bukkit.enderbow.EnderBowPlugin;
import uk.jamierocks.bukkit.enderbow.data.Settings;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jamie on 05/02/15.
 * Adapted for EnderBow on 18/02/2015
 */
public final class Language {

    private static final Map<String, Properties> langs = new HashMap<String, Properties>();
    private static volatile String current;
    private static final String fallback = "enUS";

    static {
        try {
            load(Settings.getLanguage());
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static void load(String lang) throws IOException {
        if (!langs.containsKey(lang)) {
            Properties props = new Properties();
            props.load(EnderBowPlugin.class.getResourceAsStream("/locale/" + lang + ".lang"));
            langs.put(lang, props);
            EnderBowPlugin.getInstance().getLogger().info("Loading Language: " + localize("locale.name"));
        }

        current = lang;
    }

    public static void reload(String lang) throws IOException {
        if (langs.containsKey(lang)) {
            langs.remove(lang);
        }

        load(lang);
    }

    public static String localize(String lang, String tag) {
        if (langs.containsKey(lang)) {
            Properties props = langs.get(lang);
            if (props.containsKey(tag)) {
                return props.getProperty(tag, tag);
            } else {
                if (lang.equalsIgnoreCase(fallback)) {
                    return "Unknown";
                } else {
                    return localize(fallback, tag);
                }
            }
        } else {
            return localize(fallback, tag);
        }
    }

    public static String localize(String tag) {
        return localize(current, tag);
    }

    public static String localizeWithReplace(String tag, String replaceWith) {
        return localize(current, tag).replace("%s", replaceWith);
    }

    public static String getCurrent() {
        return current;
    }
}