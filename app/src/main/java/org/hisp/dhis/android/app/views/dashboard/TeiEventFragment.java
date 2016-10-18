package org.hisp.dhis.android.app.views.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.hisp.dhis.android.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomaslindsjorn on 13/10/16.
 */
public class TeiEventFragment extends TeiFragment {

    private OnFragmentInteractionListener onFragmentInteractionListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup contentContainer = (ViewGroup) rootView.findViewById(R.id.content_container);
        drawProgramStage(inflater, contentContainer, "Immunization", true, true);
        drawProgramStage(inflater, contentContainer, "Very long program stage name bro, too long for one line do people" +
                " even use so long names", true, true);
        drawProgramStage(inflater, contentContainer, "This is a repeatable program stage", true, true);
        drawProgramStage(inflater, contentContainer, "This is non-repeatable", true, false);
        for (int i = 6; i < 10; i++) {
            drawProgramStage(inflater, contentContainer, "Program Stage " + i, true, true);
        }
        drawProgramStage(inflater, contentContainer, "Last Program Stage", false, true);
        return rootView;
    }

    private View drawProgramStage(LayoutInflater inflater, ViewGroup contentContainer,
                                  final String programStageTitle, boolean showDivider, final boolean isRepeatable) {
        final View programStageCard = inflater.inflate(R.layout.dashboard_program_stage, contentContainer, false);
        final TextView name = ((TextView) programStageCard.findViewById(R.id.name));
        name.setText(programStageTitle);

        final ImageButton actionButton = (ImageButton) programStageCard.findViewById(R.id.action_button);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
                if (isRepeatable) {
                    message = "New " + programStageTitle;
                } else {
                    message = "Open Event";
                }
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
        if (!isRepeatable) {
            actionButton.setImageResource(R.drawable.ic_edit_black);
        }

        final ViewGroup eventContainer = (ViewGroup) programStageCard.findViewById(R.id.event_container);
        final ImageButton expandCollapseButton = (ImageButton) programStageCard.findViewById(R.id.expand_collapse_button);
        final RelativeLayout headerBar = (RelativeLayout) programStageCard.findViewById(R.id.header_bar);

        expandCollapseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventContainer.getVisibility() == View.GONE) {
                    expandCollapseButton.setImageResource(R.drawable.ic_collapse);
                    expandHeader();
                    eventContainer.setVisibility(View.VISIBLE);
                    final List<View> events = new ArrayList<>();
                    events.add(
                            drawEvent(LayoutInflater.from(getContext()), eventContainer, "Event", false));
                    if (isRepeatable) {
                        events.add(drawEvent(LayoutInflater.from(getContext()), eventContainer, "Ev dos", true));
                        events.add(drawEvent(LayoutInflater.from(getContext()), eventContainer, "Este es el evento mas grande señor, si si", true));
                    }

                    for (final View event : events) {
                        event.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ((TextView) event.findViewById(R.id.event_name)).setTextColor(ContextCompat.getColor(getContext(), R.color.color_primary_default));
                                Toast.makeText(getContext(), ((TextView) event.findViewById(R.id.event_name)).getText(), Toast.LENGTH_SHORT).show();
                                if (onFragmentInteractionListener != null) {
                                    onFragmentInteractionListener.onHideMenu();
                                }
                                for (final View event2 : events) {
                                    if (event2 != event) {
                                        ((TextView) event.findViewById(R.id.event_name)).setTextColor(ContextCompat.getColor(getContext(), android.R.color.black));
                                    }
                                }
                            }
                        });
                    }

                } else {
                    expandCollapseButton.setImageResource(R.drawable.ic_expand);
                    collapseHeader();
                    eventContainer.setVisibility(View.GONE);
                    eventContainer.removeAllViews();
                }
            }

            private void collapseHeader() {
                ViewGroup.LayoutParams headerParams = headerBar.getLayoutParams();
                headerParams.height = (int) (headerParams.height / (4.0 / 3.0));
                headerBar.setLayoutParams(headerParams);
                name.setMaxLines(2);
            }

            private void expandHeader() {
                ViewGroup.LayoutParams headerParams = headerBar.getLayoutParams();
                headerParams.height = (int) (headerParams.height * (4.0 / 3.0));
                headerBar.setLayoutParams(headerParams);
                name.setMaxLines(3);
            }
        });

        programStageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandCollapseButton.performClick();
            }
        });

        if (!showDivider) {
            programStageCard.findViewById(R.id.divider).setVisibility(View.GONE);
        }

        contentContainer.addView(programStageCard);

        return programStageCard;
    }

    private View drawEvent(LayoutInflater inflater, ViewGroup eventContainer, final String eventName, boolean drawRefreshButton) {
        View event = inflater.inflate(R.layout.dashboard_event, eventContainer, false);
        ((TextView) event.findViewById(R.id.event_name)).setText(eventName);
        if (drawRefreshButton) {
            event.findViewById(R.id.refresh_button).setVisibility(View.VISIBLE);
            event.findViewById(R.id.error_text).setVisibility(View.VISIBLE);
        }
        event.findViewById(R.id.error_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setPositiveButton("Retry", null).setNegativeButton("Cancel", null).
                        setIcon(R.drawable.ic_error_black);
                AlertDialog alertDialog = builder.create();

                alertDialog.setTitle("SYNC ERROR");
                alertDialog.setMessage("Error syncing item. Error message: 501 Internal Server Error.");
                alertDialog.show();
            }
        });
        eventContainer.addView(event);
        return event;
    }

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener onFragmentInteractionListener) {
        this.onFragmentInteractionListener = onFragmentInteractionListener;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onHideMenu();
    }
}
