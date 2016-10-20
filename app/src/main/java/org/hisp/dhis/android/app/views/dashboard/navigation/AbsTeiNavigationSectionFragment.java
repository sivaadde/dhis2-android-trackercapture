package org.hisp.dhis.android.app.views.dashboard.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.hisp.dhis.android.app.R;

/**
 * Parent fragment for fragments within the dashboard navigation layout
 */

public abstract class AbsTeiNavigationSectionFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tei_navigation, container, false);
        return rootView;
    }

    protected void drawDataItem(ViewGroup dataItemContainer, String label, String value, boolean showDivider) {
        View dataItemView = LayoutInflater.from(getContext()).inflate(R.layout.dashboard_data_item, dataItemContainer, false);
        ((TextView) dataItemView.findViewById(R.id.label)).setText(label);
        ((TextView) dataItemView.findViewById(R.id.value)).setText(value);
        if (!showDivider) {
            dataItemView.findViewById(R.id.divider).setVisibility(View.GONE);
        }
        dataItemContainer.addView(dataItemView);
    }
}
