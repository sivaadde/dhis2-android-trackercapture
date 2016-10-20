package org.hisp.dhis.android.app.views.dashboard;

import org.hisp.dhis.client.sdk.ui.bindings.views.View;
import org.hisp.dhis.client.sdk.ui.models.FormEntity;

import java.util.List;

public interface TeiDashboardView extends View {

    void closeDrawer();

    void openDrawer();
}
