package org.hisp.dhis.android.app.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.hisp.dhis.android.app.R;

public class ProgramStageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_stage);
        ((Toolbar) findViewById(R.id.toolbar)).setTitle("Immunization");

        ViewGroup contentContainer = (ViewGroup) findViewById(R.id.content_container);

        drawEvent(contentContainer, "Event numero 1", false);
        drawEvent(contentContainer, "Ev dos", true);
        drawEvent(contentContainer, "Este es el evento mas grande señor, si si", false);
        drawEvent(contentContainer, "Event numero quatro", false);
        drawEvent(contentContainer, "Cinco", false);
        drawEvent(contentContainer, "Cinco", false);
        drawEvent(contentContainer, "Event 6", false);

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
                Toast.makeText(ProgramStageActivity.this, eventName, Toast.LENGTH_SHORT).show();
            }
        });

        eventContainer.addView(event);
    }
}
