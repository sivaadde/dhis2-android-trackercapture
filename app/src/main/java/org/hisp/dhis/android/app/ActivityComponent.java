package org.hisp.dhis.android.app;

import org.hisp.dhis.android.app.views.DataEntryFragment;
import org.hisp.dhis.android.app.views.EnrollmentFormActivity;
import org.hisp.dhis.android.app.views.TrackedEntityInstanceDashboardActivity;
import org.hisp.dhis.android.app.views.dashboard.TeiDashboardActivity;
import org.hisp.dhis.android.app.views.dashboard.TeiDashboardPresenter;
import org.hisp.dhis.android.app.views.dashboard.navigation.event.TeiProgramStageFragment;
import org.hisp.dhis.android.app.views.dashboard.navigation.profile.TeiProfileFragment;
import org.hisp.dhis.android.app.views.dashboard.navigation.widget.TeiWidgetFragment;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(
        modules = {
                ActivityModule.class
        }
)
public interface ActivityComponent {

    //------------------------------------------------------------------------
    // Injection targets
    //------------------------------------------------------------------------

    void inject(EnrollmentFormActivity enrollmentFormActivity);

    void inject(DataEntryFragment dataEntryFragment);

    void inject(TrackedEntityInstanceDashboardActivity trackedEntityInstanceDashboardActivity);

    void inject(TeiDashboardActivity teiDashboardActivity);

    void inject(TeiProgramStageFragment teiProgramStageFragment);

    void inject(TeiProfileFragment teiProfileFragment);

    void inject(TeiWidgetFragment teiWidgetFragment);

    void inject(TeiDashboardPresenter teiDashboardPresenter);
}
