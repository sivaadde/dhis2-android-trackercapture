package org.hisp.dhis.android.app.views.dashboard.navigation.event;

import org.hisp.dhis.android.app.views.dashboard.TeiDashboardPresenter;
import org.hisp.dhis.client.sdk.ui.bindings.views.View;
import org.hisp.dhis.client.sdk.ui.models.ExpansionPanel;
import org.hisp.dhis.client.sdk.ui.models.ReportEntity;

import java.util.ArrayList;
import java.util.HashMap;
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

    List<ExpansionPanel> getDummyProgramStages() {
        List<ExpansionPanel> expansionPanels = new ArrayList<>();

        List<ReportEntity> events = new ArrayList<>();
        ReportEntity firstEvent = new ReportEntity("123", ReportEntity.Status.SENT, new HashMap<String, String>());
        events.add(firstEvent);
        ReportEntity secondEvent = new ReportEntity("123", ReportEntity.Status.SENT, new HashMap<String, String>());
        events.add(secondEvent);
        ReportEntity thirdEvent = new ReportEntity("123", ReportEntity.Status.SENT, new HashMap<String, String>());
        events.add(thirdEvent);

        ExpansionPanel expansionPanel1 = new ExpansionPanel("123", "Immunization", ExpansionPanel.Type.ACTION_ADD);
        expansionPanel1.setChildren(events);
        expansionPanels.add(expansionPanel1);

        ExpansionPanel expansionPanel2 = new ExpansionPanel("124", "Very long program stage name bro, too long for one line do people even use so long names?", ExpansionPanel.Type.ACTION_ADD);
        expansionPanel2.setChildren(events);
        expansionPanels.add(expansionPanel2);

        ExpansionPanel expansionPanel3 = new ExpansionPanel("125", "WAT", ExpansionPanel.Type.ACTION_ADD);
        expansionPanel3.setChildren(events);
        expansionPanels.add(expansionPanel3);

        ExpansionPanel expansionPanel4 = new ExpansionPanel("125", "NON-Repeatable", ExpansionPanel.Type.ACTION_EDIT);
        expansionPanel4.setChildren(events.subList(0, 1));
        expansionPanels.add(expansionPanel4);

        return expansionPanels;
    }
}
