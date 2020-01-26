package winarwahyuw.winw.daftarbelanja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setTitle("About Creator");

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            navigationView.setSelectedItemId(R.id.nav_winar);
        }
    }

        protected BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment fragment;
                        switch (menuItem.getItemId()) {
                            case R.id.nav_zidane:
                                fragment = new ZidaneFragment();
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.frame, fragment, fragment.getClass().getSimpleName())
                                        .commit();
                                return true;
                            case R.id.nav_winar:
                                fragment = new WinarFragment();
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.frame, fragment, fragment.getClass().getSimpleName())
                                        .commit();
                                return true;
                        }return false;
                    }
                };
}

