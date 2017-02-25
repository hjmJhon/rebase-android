/*
 * Copyright (C) 2017 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of rebase-android
 *
 * rebase-android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * rebase-android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with rebase-android. If not, see <http://www.gnu.org/licenses/>.
 */

package com.drakeet.rebase.tool;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.StyleRes;
import android.util.Log;
import com.drakeet.rebase.R;

/**
 * @author drakeet
 */
public class Colorful {

    private static final String KEY_STYLE = "key_style";
    // TODO: 2017/2/25 to be flexible
    private final int[] styles = {
        R.style.Rebase_Colorful_DarkMagenta,
        R.style.Rebase
    };
    // TODO: 2017/2/25 static
    private static int styleIndex;
    private Activity activity;


    private Colorful(Activity activity) {
        this.activity = activity;
    }


    public class Theme {

        @StyleRes int styleId;


        public Theme(int styleId) {
            this.styleId = styleId;
        }


        public void apply() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
            preferences.edit().putInt(KEY_STYLE, styleId).apply();
            activity.recreate();
        }
    }


    public static void init(Activity activity) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        int styleId = preferences.getInt(KEY_STYLE, R.style.Rebase);
        Log.d(KEY_STYLE, styleId + "");
        activity.setTheme(styleId);
    }


    public static Colorful of(Activity activity) {
        return new Colorful(activity);
    }


    public Theme changeOne() {
        int styleId = styles[styleIndex % styles.length];
        styleIndex++;
        return new Theme(styleId);
    }
}
