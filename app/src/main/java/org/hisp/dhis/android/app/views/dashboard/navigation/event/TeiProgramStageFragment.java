package org.hisp.dhis.android.app.views.dashboard.navigation.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import org.hisp.dhis.android.app.R;
import org.hisp.dhis.android.app.TrackerCaptureApp;
import org.hisp.dhis.android.app.views.dashboard.navigation.AbsTeiNavigationSectionFragment;
import org.hisp.dhis.client.sdk.ui.adapters.expandable.ExpandableAdapter;
import org.hisp.dhis.client.sdk.ui.models.ExpansionPanel;
import org.hisp.dhis.client.sdk.ui.models.FormEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TeiProgramStageFragment extends AbsTeiNavigationSectionFragment implements TeiProgramStageView {

    @Inject
    TeiProgramStagePresenter teiProgramStagePresenter;
    private ExpandableAdapter adapter;
    private ArrayList<ExpansionPanel> programStages;

    public static TeiProgramStageFragment newInstance(String enrollmentUid) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_ENROLLMENT_UID, enrollmentUid);

        TeiProgramStageFragment teiProgramStageFragment = new TeiProgramStageFragment();
        teiProgramStageFragment.setArguments(bundle);
        return teiProgramStageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initViews(inflater, container);

        try {
            ((TrackerCaptureApp) getActivity().getApplication())
                    .getActivityComponent().inject(this);
        } catch (Exception e) {
            Log.e("DataEntryFragment", "Activity or Application is null. Vital resources have been killed.", e);
        }

        teiProgramStagePresenter.attachView(this);
        return recyclerView;
    }

    private void initViews(LayoutInflater inflater, @Nullable ViewGroup container) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_tei_navigation, container, false);
        programStages = new ArrayList<>();
        adapter = new ExpandableAdapter(programStages);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        teiProgramStagePresenter.drawProgramStages(getEnrollmentUid());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        teiProgramStagePresenter.detachView();
    }

    @Override
    public void drawProgramStages(final List<ExpansionPanel> programStages) {
        adapter.swap(programStages);
    }

    @Override
    public void onEventClicked(FormEntity event) {
        teiProgramStagePresenter.onEventClicked(event.getId());
    }

    @Override
    protected ExpandableRecyclerAdapter getAdapter() {
        return adapter;
    }
}
