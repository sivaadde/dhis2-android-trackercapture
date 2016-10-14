package org.hisp.dhis.android.app.views.dashboard;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import org.hisp.dhis.android.app.R;

public class TeiDashboardActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tei_dashboard);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.navigation_pane, new TeiDashboardOverviewFragment())
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.data_entry_pane, new DataEntryContainerFragment())
                .commit();

    }
}
