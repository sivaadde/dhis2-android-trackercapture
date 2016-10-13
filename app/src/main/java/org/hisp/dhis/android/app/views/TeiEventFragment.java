package org.hisp.dhis.android.app.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.hisp.dhis.android.app.R;

/**
 * Created by thomaslindsjorn on 13/10/16.
 */
public class TeiEventFragment extends TeiFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup contentContainer = (ViewGroup) rootView.findViewById(R.id.content_container);
        drawProgramStage(inflater, contentContainer, "Immunization");
        drawProgramStage(inflater, contentContainer, "Back Entry");
        drawProgramStage(inflater, contentContainer, "Back Entry");
        drawProgramStage(inflater, contentContainer, "Back Entry");
        drawProgramStage(inflater, contentContainer, "Back Entry");
        return rootView;
    }

    private void drawProgramStage(LayoutInflater inflater, ViewGroup contentContainer, final String programStageTitle) {
        final View programStageCard = inflater.inflate(R.layout.dashboard_program_stage, contentContainer, false);
        ((TextView) programStageCard.findViewById(R.id.title)).setText(programStageTitle);

        programStageCard.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "New " + programStageTitle, Toast.LENGTH_SHORT).show();
            }
        });

        ViewGroup eventContainer = (ViewGroup) programStageCard.findViewById(R.id.event_container);
        drawEvent(inflater, eventContainer, "Event numero 1", false);
        drawEvent(inflater, eventContainer, "Ev dos", true);
        drawEvent(inflater, eventContainer, "Este es el evento mas grande señor, si si", false);

        programStageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ProgramStageActivity.class);
                startActivity(i);
            }
        });

        contentContainer.addView(programStageCard);
    }

    private void drawEvent(LayoutInflater inflater, ViewGroup eventContainer, final String eventName, boolean drawRefreshButton) {
        View event = inflater.inflate(R.layout.dashboard_event, eventContainer, false);
        ((TextView) event.findViewById(R.id.event_name)).setText(eventName);
        if (drawRefreshButton) {
            event.findViewById(R.id.refresh_button).setVisibility(View.VISIBLE);
            event.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        }
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), eventName, Toast.LENGTH_SHORT).show();
            }
        });

        eventContainer.addView(event);
    }

}
