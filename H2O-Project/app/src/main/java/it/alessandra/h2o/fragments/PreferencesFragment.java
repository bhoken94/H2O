package it.alessandra.h2o.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.util.Objects;

import it.alessandra.h2o.R;


public class PreferencesFragment extends PreferenceFragmentCompat {

    public final String KEY_UNIT = "units";
    public final String KEY_WEIGHT = "weight";

    private ListPreference units;
    private EditTextPreference weight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        //Carico le preference che posso condividere in tutte le activity, e setto il sommario col valore preso
        setPreferencesFromResource(R.xml.root_preferences,rootKey);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getActivity()));
        units = (ListPreference) findPreference(KEY_UNIT);
        weight = (EditTextPreference) findPreference(KEY_WEIGHT);

        assert weight != null;
        weight.setSummary(weight.getText());
        weight.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                weight.setSummary(newValue.toString());
                return true;
            }
        });
        units.setSummary(units.getValue());
        units.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                units.setSummary(newValue.toString());
                return true;
            }
        });
    }


}
