package org.hisp.dhis.android.app.views.dashboard.navigation.widget;

import org.hisp.dhis.client.sdk.ui.bindings.views.View;
import org.hisp.dhis.client.sdk.ui.models.FormEntity;

import java.util.List;

/**
 * Created by thomaslindsjorn on 18/10/16.
 */

public interface TeiWidgetsView extends View {

    void drawWidgets(List<FormEntity> widgets);
}
