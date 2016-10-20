package org.hisp.dhis.android.app.views.dashboard.navigation.profile;

import org.hisp.dhis.client.sdk.ui.bindings.presenters.Presenter;

/**
 * Created by thomaslindsjorn on 19/10/16.
 */

public interface TeiProfilePresenter extends Presenter {

    void drawProfile(String enrollmentUid);
}
