package ca.bcit.soilmonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;

    AccountFragment accountFragment = new AccountFragment();
    DevicesFragment devicesFragment = new DevicesFragment();
    HomeFragment homeFragment = new HomeFragment();
    SettingsFragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        addFragments();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();

                if (itemId == R.id.account) {
                    switchFragment(accountFragment);
                    return true;
                } else if (itemId == R.id.devices) {
                    switchFragment(devicesFragment);
                    return true;
                } else if (itemId == R.id.home) {
                    switchFragment(homeFragment);
                    return true;
                } else if (itemId == R.id.settings) {
                    switchFragment(settingsFragment);
                    return true;
                }
                return false;
            }
        });
    }

    private void addFragments() {
//        Log.i("addFragmentsI", fragmentManager.getFragments().toString());
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.nav_fragment, accountFragment);
        fragmentTransaction.add(R.id.nav_fragment, devicesFragment);
        fragmentTransaction.add(R.id.nav_fragment, homeFragment);
        fragmentTransaction.add(R.id.nav_fragment, settingsFragment);

        fragmentTransaction.hide(accountFragment);
        fragmentTransaction.hide(homeFragment);
        fragmentTransaction.hide(devicesFragment);
        fragmentTransaction.hide(settingsFragment);

        fragmentTransaction.commit();
    }

    private void switchFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();

        for (Fragment fragmentIterator : fragmentManager.getFragments()) {
            if (fragmentIterator == fragment) {
                fragmentTransaction.show(fragmentIterator);
            } else {
                fragmentTransaction.hide(fragmentIterator);
            }
        }

        fragmentTransaction.commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.nav_fragment, fragment).commit();
    }
}