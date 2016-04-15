/*
 * This file is part of EnderBow, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015-2016, Jamie Mansfield <https://www.jamierocks.uk/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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