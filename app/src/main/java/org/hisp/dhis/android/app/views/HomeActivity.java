package org.hisp.dhis.android.app.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.hisp.dhis.android.app.R;
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

        ViewGroup contentContainer = ((ViewGroup) findViewById(R.id.content_container));

        drawProfileCard(contentContainer);

        drawIndicatorsCard(contentContainer);

        drawProgramStage(contentContainer, "Immunization");
        drawProgramStage(contentContainer, "Back Entry");

        drawRelationshipCard(contentContainer);
    }

    private void drawRelationshipCard(ViewGroup contentContainer) {
        View relationshipCard = LayoutInflater.from(this).inflate(R.layout.dashboard_data_card, contentContainer, false);
        ((TextView) relationshipCard.findViewById(R.id.title)).setText("Relationships");
        relationshipCard.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "New Relationship", Toast.LENGTH_SHORT).show();
            }
        });
        ViewGroup dataItemContainer = (ViewGroup) relationshipCard.findViewById(R.id.data_item_container);
        drawDataItem(dataItemContainer, "Mother of", "Arne Carlsen");
        drawDataItem(dataItemContainer, "Brother of", "Jan Carlsen");
        contentContainer.addView(relationshipCard);
    }

    private void drawIndicatorsCard(ViewGroup contentContainer) {
        View indicatorsCard = LayoutInflater.from(this).inflate(R.layout.dashboard_data_card, contentContainer, false);
        ((TextView) indicatorsCard.findViewById(R.id.title)).setText("Indicators");
        indicatorsCard.findViewById(R.id.fab).setVisibility(View.GONE);

        ViewGroup dataItemContainer = (ViewGroup) indicatorsCard.findViewById(R.id.data_item_container);
        drawDataItem(dataItemContainer, "Age", "47");
        contentContainer.addView(indicatorsCard);
    }

    private void drawDataItem(ViewGroup dataItemContainer, String label, String value) {
        View dataItemView = LayoutInflater.from(this).inflate(R.layout.dashboard_data_item, dataItemContainer, false);
        ((TextView) dataItemView.findViewById(R.id.label)).setText(label);
        ((TextView) dataItemView.findViewById(R.id.value)).setText(value);
        dataItemContainer.addView(dataItemView);
    }

    private void drawProgramStage(ViewGroup contentContainer, final String programStageTitle) {
        final View programStageCard = LayoutInflater.from(this).inflate(R.layout.dashboard_program_stage, contentContainer, false);
        ((TextView) programStageCard.findViewById(R.id.title)).setText(programStageTitle);

        programStageCard.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "New " + programStageTitle, Toast.LENGTH_SHORT).show();
            }
        });

        ViewGroup eventContainer = (ViewGroup) programStageCard.findViewById(R.id.event_container);
        drawEvent(eventContainer, "Event numero 1", false);
        drawEvent(eventContainer, "Ev dos", true);
        drawEvent(eventContainer, "Este es el evento mas grande señor, si si", false);

        programStageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ProgramStageActivity.class);
                startActivity(i);
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

    private void drawProfileCard(ViewGroup contentContainer) {
        View profileCard = LayoutInflater.from(this).inflate(R.layout.dashboard_data_card, contentContainer, false);

        ((TextView) profileCard.findViewById(R.id.title)).setText("Profile");
        FloatingActionButton editButton = (FloatingActionButton) profileCard.findViewById(R.id.fab);
        editButton.setImageResource(R.drawable.ic_edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Edit Profile", Toast.LENGTH_SHORT).show();
            }
        });

        ViewGroup dataItemContainer = (ViewGroup) profileCard.findViewById(R.id.data_item_container);
        drawDataItem(dataItemContainer, "First name", "Magnus");
        drawDataItem(dataItemContainer, "Last name", "Carlsen");
        drawDataItem(dataItemContainer, "Date of birth", "1998-01-01");
        contentContainer.addView(profileCard);
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


