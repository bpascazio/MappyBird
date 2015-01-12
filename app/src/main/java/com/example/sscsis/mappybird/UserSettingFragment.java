package com.example.sscsis.mappybird;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class UserSettingFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.settings);
	}
	
}
