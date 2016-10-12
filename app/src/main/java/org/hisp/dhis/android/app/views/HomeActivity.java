package org.hisp.dhis.android.app.views;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.hisp.dhis.android.app.R;
import org.hisp.dhis.client.sdk.models.enrollment.Enrollment;
import org.hisp.dhis.client.sdk.ui.bindings.views.DefaultHomeActivity;
import org.hisp.dhis.client.sdk.ui.fragments.EventListFragment;
import org.hisp.dhis.client.sdk.ui.fragments.WrapperFragment;

public class HomeActivity extends DefaultHomeActivity {

    @IdRes
    private static final int DRAWER_ITEM_PLACEHOLDER_ID = 324342;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addMenuItem(DRAWER_ITEM_PLACEHOLDER_ID, R.drawable.ic_add,
                R.string.drawer_item_placeholder);
        if (savedInstanceState == null) {
            onNavigationItemSelected(getNavigationView().getMenu()
                    .findItem(DRAWER_ITEM_PLACEHOLDER_ID));
        }

        drawProfileCard();

        ViewGroup programStageContainer = ((ViewGroup) findViewById(R.id.content_container));
        drawProgramStage(programStageContainer, "Immunization");
        drawProgramStage(programStageContainer, "Back Entry");
    }

    private void drawProgramStage(ViewGroup contentContainer, final String programStageTitle) {
        final View programStageCard = LayoutInflater.from(this).inflate(R.layout.dashboard_program_stage, contentContainer, false);
        ((TextView) programStageCard.findViewById(R.id.title)).setText(programStageTitle);

        final ViewPager viewPager = ((ViewPager) programStageCard.findViewById(R.id.view_pager));
        viewPager.setAdapter(new ProgramStageEventAdapter(
                getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) programStageCard.findViewById(R.id.tab_layout);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                (tabLayout)));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        programStageCard.findViewById(R.id.new_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "New " + programStageTitle, Toast.LENGTH_SHORT).show();
            }
        });

        contentContainer.addView(programStageCard);
    }

    private void drawEvent(ViewGroup eventContainer, final String eventName, boolean drawRefreshButton) {
        View event = LayoutInflater.from(this).inflate(R.layout.dashboard_event, eventContainer, false);
        ((TextView) event.findViewById(R.id.event_name)).setText(eventName);
        if (drawRefreshButton) {
            event.findViewById(R.id.refresh_button).setVisibility(View.VISIBLE);
            event.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        }
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, eventName, Toast.LENGTH_SHORT).show();
            }
        });

        eventContainer.addView(event);
    }

    private void drawProfileCard() {
        Spinner statusSpinner = (Spinner) findViewById(R.id.status_spinner);
        String[] statuses = new String[Enrollment.EnrollmentStatus.values().length];
        for (int i = 0; i < Enrollment.EnrollmentStatus.values().length; i++) {
            statuses[i] = Enrollment.EnrollmentStatus.values()[i].toString();
        }
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, statuses);
        statusSpinner.setAdapter(spinnerAdapter);
        statusSpinner.setSelection(0);

        final ViewGroup dataItemContainer = (ViewGroup) findViewById(R.id.data_item_container);

        View profileCard = findViewById(R.id.profile_card);
        profileCard.findViewById(R.id.expand_collapse_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataItemContainer.getVisibility() == View.VISIBLE) {
                    dataItemContainer.setVisibility(View.GONE);
                    ((ImageButton) findViewById(R.id.expand_collapse_button)).setImageResource(R.drawable.ic_expand);
                } else {
                    dataItemContainer.setVisibility(View.VISIBLE);
                    ((ImageButton) findViewById(R.id.expand_collapse_button)).setImageResource(R.drawable.ic_collapse);
                }
            }
        });

        addDataItem(dataItemContainer, "First name", "Magnus");
        addDataItem(dataItemContainer, "Last name", "Carlsen");

    }

    private void addDataItem(ViewGroup dataItemContainer, String label, String value) {
        View dataItemView = LayoutInflater.from(this).inflate(R.layout.dashboard_data_item, dataItemContainer, false);
        ((TextView) dataItemView.findViewById(R.id.label)).setText(label);
        ((TextView) dataItemView.findViewById(R.id.value)).setText(value);
        dataItemContainer.addView(dataItemView);
    }

    @Override
    protected boolean onItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case DRAWER_ITEM_PLACEHOLDER_ID: {
                attachFragment(WrapperFragment.newInstance(
                        SelectorFragment.class, getString(R.string.drawer_item_placeholder)));
                break;
            }
        }
        return true;
    }

    private class ProgramStageEventAdapter extends FragmentPagerAdapter {
        public ProgramStageEventAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new EventListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("FILTER", getPageTitle(position).toString());
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SCHEDULED";
                case 1:
                    return "ACTIVE";
                case 2:
                    return "COMPLETED";
            }
            return "";
        }
    }
}


