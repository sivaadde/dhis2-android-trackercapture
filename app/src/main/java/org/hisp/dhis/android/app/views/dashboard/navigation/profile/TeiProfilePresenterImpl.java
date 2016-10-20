package org.hisp.dhis.android.app.views.dashboard.navigation.profile;

import org.hisp.dhis.client.sdk.ui.bindings.views.View;

/**
 * Created by thomaslindsjorn on 19/10/16.
 */

public class TeiProfilePresenterImpl implements TeiProfilePresenter {

    private TeiProfileView teiProfileView;

    @Override
    public void attachView(View view) {
        teiProfileView = (TeiProfileView) view;
    }

    @Override
    public void detachView() {
        teiProfileView = null;
    }


    @Override
    public void drawProfile(String enrollmentUid) {
        teiProfileView.drawProfileItems(null);
    }
}
