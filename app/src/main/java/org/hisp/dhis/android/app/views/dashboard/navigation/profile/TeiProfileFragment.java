package org.hisp.dhis.android.app.views.dashboard.navigation.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.hisp.dhis.android.app.TrackerCaptureApp;
import org.hisp.dhis.android.app.views.dashboard.navigation.AbsTeiNavigationSectionFragment;
import org.hisp.dhis.client.sdk.ui.models.FormEntity;

import java.util.List;

import javax.inject.Inject;

public class TeiProfileFragment extends AbsTeiNavigationSectionFragment implements TeiProfileView {

    @Inject
    TeiProfilePresenter teiProfilePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        /*ViewGroup contentContainer = (ViewGroup) rootView.findViewById(R.id.content_container);
        drawIndicatorsCard(inflater, contentContainer);
        drawRelationshipCard(inflater, contentContainer);*/

        try {
            ((TrackerCaptureApp) getActivity().getApplication())
                    .getActivityComponent().inject(this);
        } catch (Exception e) {
            Log.e("DataEntryFragment", "Activity or Application is null. Vital resources have been killed.", e);
        }

        teiProfilePresenter.attachView(this);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        teiProfilePresenter.drawProfile("Enrollment uid");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        teiProfilePresenter.detachView();
    }

    @Override
    public void drawProfileItems(List<FormEntity> formEntities) {
        Log.d("TeiProfileFragment", "Draw profile");
    }
}
