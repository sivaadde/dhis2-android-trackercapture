package org.hisp.dhis.android.app.views.dashboard;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import org.hisp.dhis.android.app.R;

public class TeiDashboardActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tei_dashboard);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.data_entry_pane, new DataEntryContainerFragment())
                .commit();

        if (getResources().getBoolean(R.bool.use_two_pane_layout)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.navigation_pane, new TeiDashboardOverviewFragment())
                    .commit();
        } else {
            final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawerLayout.openDrawer(GravityCompat.END);

            TeiDashboardOverviewFragment menuFragment = new TeiDashboardOverviewFragment();
            menuFragment.setOnFragmentInteractionListener(new TeiEventFragment.OnFragmentInteractionListener() {
                @Override
                public void onHideMenu() {
                    drawerLayout.closeDrawers();
                }
            });
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.drawer_content, menuFragment)
                    .commit();
        }

    }
}
