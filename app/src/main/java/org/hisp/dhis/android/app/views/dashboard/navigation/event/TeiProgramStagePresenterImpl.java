package org.hisp.dhis.android.app.views.dashboard.navigation.event;

import org.hisp.dhis.android.app.views.dashboard.TeiDashboardPresenter;
import org.hisp.dhis.client.sdk.ui.bindings.views.View;
import org.hisp.dhis.client.sdk.ui.models.FormEntity;
import org.hisp.dhis.client.sdk.ui.models.FormExpansionPanel;

import java.util.ArrayList;
import java.util.List;

public class TeiProgramStagePresenterImpl implements TeiProgramStagePresenter {

    private final TeiDashboardPresenter teiDashboardPresenter;
    private TeiProgramStageView teiProgramStageView;

    public TeiProgramStagePresenterImpl(TeiDashboardPresenter teiDashboardPresenter) {
        this.teiDashboardPresenter = teiDashboardPresenter;
    }

    @Override
    public void drawProgramStages(String enrollmentUid) {
        teiProgramStageView.drawProgramStages(getDummyProgramStages());
    }

    @Override
    public void onEventClicked(String eventUid) {
        teiDashboardPresenter.showDataEntryForEvent(eventUid);
        teiDashboardPresenter.hideMenu();
    }

    @Override
    public void attachView(View view) {
        teiProgramStageView = (TeiProgramStageView) view;
    }

    @Override
    public void detachView() {
        teiProgramStageView = null;
    }

    List<FormEntity> getDummyProgramStages() {
        List<FormEntity> formEntities = new ArrayList<>();
        formEntities.add(new FormExpansionPanel("123", "Immunization"));
        formEntities.add(new FormExpansionPanel("124", "Very long program stage name bro, too long for one line do people"));
        /*
        drawProgramStage(inflater, contentContainer, "Immunization", true, true);
        drawProgramStage(inflater, contentContainer, "Very long program stage name bro, too long for one line do people" +
                " even use so long names", true, true);
        drawProgramStage(inflater, contentContainer, "This is a repeatable program stage", true, true);
        drawProgramStage(inflater, contentContainer, "This is non-repeatable", true, false);
        for (int i = 6; i < 10; i++) {
            drawProgramStage(inflater, contentContainer, "Program Stage " + i, true, true);
        }
        drawProgramStage(inflater, contentContainer, "Last Program Stage", false, true);*/
        return formEntities;
    }
}
