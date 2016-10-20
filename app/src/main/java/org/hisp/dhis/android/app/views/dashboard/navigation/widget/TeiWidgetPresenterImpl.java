package org.hisp.dhis.android.app.views.dashboard.navigation.widget;

import org.hisp.dhis.client.sdk.ui.bindings.views.View;

/**
 * Created by thomaslindsjorn on 19/10/16.
 */

public class TeiWidgetPresenterImpl implements TeiWidgetPresenter {

    private TeiWidgetsView teiWidgetsView;

    @Override
    public void attachView(View view) {
        teiWidgetsView = (TeiWidgetsView) view;
    }

    @Override
    public void detachView() {
        teiWidgetsView = null;
    }

    @Override
    public void drawWidgets(String enrollmentUid) {
        teiWidgetsView.drawWidgets(null);
    }
}
