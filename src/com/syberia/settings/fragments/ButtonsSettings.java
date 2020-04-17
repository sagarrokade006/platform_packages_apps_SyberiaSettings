/*
 * Copyright © 2018 Syberia Project
 * Date: 22.08.2018
 * Time: 21:21
 * Author: @alexxxdev <alexxxdev@ya.ru>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.syberia.settings.fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.content.res.Resources;

import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.SwitchPreference;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;

import com.android.internal.logging.nano.MetricsProto;

import com.syberia.settings.preference.CustomSeekBarPreference;

import com.syberia.settings.preference.ActionFragment;
import com.syberia.settings.preference.CustomSeekBarPreference;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Buttons extends SettingsPreferenceFragment
        implements Preference.OnPreferenceChangeListener {

    public static final String TAG = "Buttons";

    private static final String NAV_BAR_LAYOUT = "nav_bar_layout";
    private static final String SYSUI_NAV_BAR = "sysui_nav_bar";

    private ContentResolver resolver;
    private ListPreference mNavBarLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.buttons_settings);
        resolver = getActivity().getContentResolver();

        mNavBarLayout = (ListPreference) findPreference(NAV_BAR_LAYOUT);
        mNavBarLayout.setOnPreferenceChangeListener(this);
        String navBarLayoutValue = Settings.Secure.getString(resolver, SYSUI_NAV_BAR);
        if (navBarLayoutValue != null) {
            mNavBarLayout.setValue(navBarLayoutValue);
        } else {
            mNavBarLayout.setValueIndex(0);
        }

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object objValue) {
        resolver = getActivity().getContentResolver();
        if (preference == mNavBarLayout) {
            Settings.Secure.putString(resolver, SYSUI_NAV_BAR, (String) objValue);
            return true;
        }
            return false;
    }


    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.SYBERIA;
    }
}
