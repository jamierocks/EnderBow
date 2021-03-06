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
