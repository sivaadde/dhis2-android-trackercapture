package org.hisp.dhis.android.app.views.dashboard.navigation.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.hisp.dhis.android.app.R;
import org.hisp.dhis.android.app.TrackerCaptureApp;
import org.hisp.dhis.android.app.views.dashboard.navigation.AbsTeiNavigationSectionFragment;
import org.hisp.dhis.client.sdk.ui.models.FormEntity;
import org.hisp.dhis.client.sdk.ui.models.FormExpansionPanel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by thomaslindsjorn on 13/10/16.
 */
public class TeiProgramStageFragment extends AbsTeiNavigationSectionFragment implements TeiProgramStageView {

    @Inject
    TeiProgramStagePresenter teiProgramStagePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
        //drawDummyData(inflater, rootView);

        try {
            ((TrackerCaptureApp) getActivity().getApplication())
                    .getActivityComponent().inject(this);
        } catch (Exception e) {
            Log.e("DataEntryFragment", "Activity or Application is null. Vital resources have been killed.", e);
        }

        teiProgramStagePresenter.attachView(this);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        teiProgramStagePresenter.drawProgramStages("Enrollment uid");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        teiProgramStagePresenter.detachView();
    }

    @Override
    public void drawProgramStages(List<FormEntity> programStages) {
        ViewGroup contentContainer = (ViewGroup) getView().findViewById(R.id.content_container);
        contentContainer.removeAllViews();
        for (FormEntity programStage : programStages) {
            contentContainer.addView(getViewForProgramStage(programStage, contentContainer));
        }
    }

    @Override
    public void onEventClicked(FormEntity event) {
        teiProgramStagePresenter.onEventClicked(event.getId());
    }

    private View getViewForProgramStage(FormEntity programStage, ViewGroup container) {
        final View itemView = LayoutInflater.from(getContext()).inflate(
                R.layout.recyclerview_row_expansion_panel, container, false);

        final TextView textViewLabel = ((TextView) itemView
                .findViewById(R.id.title));
        textViewLabel.setText(programStage.getLabel());
        itemView.findViewById(R.id.action_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), ((TextView) itemView
                        .findViewById(R.id.title)).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        final ImageButton expandCollapseButton = (ImageButton) itemView.findViewById(R.id.expand_collapse_button);
        View.OnClickListener expandCollapseClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded()) {
                    collapse(v);
                } else {
                    expand(v);
                    onEventClicked(new FormExpansionPanel(textViewLabel.getText().toString(), "label"));
                }
            }

            private void expand(View v) {
                expandCollapseButton.setTag(true);
                expandCollapseButton.setImageResource(org.hisp.dhis.client.sdk.ui.R.drawable.ic_expand);
                Toast.makeText(v.getContext(), "Collapse " + textViewLabel.getText(), Toast.LENGTH_SHORT).show();
            }

            private void collapse(View v) {
                expandCollapseButton.setTag(false);
                expandCollapseButton.setImageResource(org.hisp.dhis.client.sdk.ui.R.drawable.ic_collapse);
                Toast.makeText(v.getContext(), "Expand " + textViewLabel.getText(), Toast.LENGTH_SHORT).show();
            }

            private boolean isExpanded() {
                return expandCollapseButton.getTag() != null && expandCollapseButton.getTag() instanceof Boolean && (Boolean) expandCollapseButton.getTag();
            }
        };

        expandCollapseButton.setOnClickListener(expandCollapseClickListener);
        itemView.setOnClickListener(expandCollapseClickListener);

        return itemView;
    }
}
