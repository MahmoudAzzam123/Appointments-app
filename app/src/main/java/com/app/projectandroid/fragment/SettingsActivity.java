package com.app.projectandroid.fragment;

        import android.os.Bundle;
        import android.widget.Toast;

        import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.preference.PreferenceFragmentCompat;
        import androidx.preference.SwitchPreference;
        import androidx.preference.SwitchPreferenceCompat;

        import com.app.projectandroid.R;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            DatabaseReference myRef = database.getReference("myData");

            SwitchPreferenceCompat spc = findPreference("switch");

           /* if (spc.isChecked()) {
                FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            }*/
//            else {
////                FirebaseDatabase.getInstance().setPersistenceEnabled(false);
//            }
        }
    }
}