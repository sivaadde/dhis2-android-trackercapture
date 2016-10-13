package org.hisp.dhis.android.app.views;

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
public class TeiWidgetFragment extends TeiFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup contentContainer = (ViewGroup) rootView.findViewById(R.id.content_container);
        drawIndicatorsCard(inflater, contentContainer);
        drawRelationshipCard(inflater, contentContainer);
        return rootView;
    }

    private void drawRelationshipCard(LayoutInflater inflater, ViewGroup contentContainer) {
        View relationshipCard = inflater.inflate(R.layout.dashboard_data_card, contentContainer, false);
        ((TextView) relationshipCard.findViewById(R.id.title)).setText("Relationships");
        relationshipCard.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "New Relationship", Toast.LENGTH_SHORT).show();
            }
        });
        ViewGroup dataItemContainer = (ViewGroup) relationshipCard.findViewById(R.id.data_item_container);
        drawDataItem(dataItemContainer, "Mother of", "Arne Carlsen", true);
        drawDataItem(dataItemContainer, "Brother of", "Jan Carlsen", false);
        contentContainer.addView(relationshipCard);
    }

    private void drawIndicatorsCard(LayoutInflater inflater, ViewGroup contentContainer) {
        View indicatorsCard = inflater.inflate(R.layout.dashboard_data_card, contentContainer, false);
        ((TextView) indicatorsCard.findViewById(R.id.title)).setText("Indicators");
        indicatorsCard.findViewById(R.id.fab).setVisibility(View.GONE);

        ViewGroup dataItemContainer = (ViewGroup) indicatorsCard.findViewById(R.id.data_item_container);
        drawDataItem(dataItemContainer, "Age", "47", false);
        contentContainer.addView(indicatorsCard);
    }
}
