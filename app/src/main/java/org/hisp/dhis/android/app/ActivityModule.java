package org.hisp.dhis.android.app;

import android.util.Log;

import org.hisp.dhis.android.app.models.RxRulesEngine;
import org.hisp.dhis.android.app.presenters.DataEntryPresenter;
import org.hisp.dhis.android.app.presenters.DataEntryPresenterImpl;
import org.hisp.dhis.android.app.presenters.EnrollmentFormPresenter;
import org.hisp.dhis.android.app.presenters.EnrollmentFormPresenterImpl;
import org.hisp.dhis.android.app.presenters.TrackedEntityInstanceDashboardPresenter;
import org.hisp.dhis.android.app.presenters.TrackedEntityInstanceDashboardPresenterImpl;
import org.hisp.dhis.android.app.views.dashboard.TeiDashboardPresenter;
import org.hisp.dhis.android.app.views.dashboard.TeiDashboardPresenterImpl;
import org.hisp.dhis.android.app.views.dashboard.navigation.TeiNavigationPresenter;
import org.hisp.dhis.android.app.views.dashboard.navigation.TeiNavigationPresenterImpl;
import org.hisp.dhis.android.app.views.dashboard.navigation.event.TeiProgramStagePresenter;
import org.hisp.dhis.android.app.views.dashboard.navigation.event.TeiProgramStagePresenterImpl;
import org.hisp.dhis.android.app.views.dashboard.navigation.profile.TeiProfilePresenter;
import org.hisp.dhis.android.app.views.dashboard.navigation.profile.TeiProfilePresenterImpl;
import org.hisp.dhis.android.app.views.dashboard.navigation.widget.TeiWidgetPresenter;
import org.hisp.dhis.android.app.views.dashboard.navigation.widget.TeiWidgetPresenterImpl;
import org.hisp.dhis.android.app.views.dataentry.FormSectionPresenter;
import org.hisp.dhis.client.sdk.android.enrollment.EnrollmentInteractor;
import org.hisp.dhis.client.sdk.android.event.EventInteractor;
import org.hisp.dhis.client.sdk.android.optionset.OptionSetInteractor;
import org.hisp.dhis.client.sdk.android.program.ProgramInteractor;
import org.hisp.dhis.client.sdk.android.program.ProgramRuleActionInteractor;
import org.hisp.dhis.client.sdk.android.program.ProgramRuleInteractor;
import org.hisp.dhis.client.sdk.android.program.ProgramRuleVariableInteractor;
import org.hisp.dhis.client.sdk.android.program.ProgramStageInteractor;
import org.hisp.dhis.client.sdk.android.program.ProgramStageSectionInteractor;
import org.hisp.dhis.client.sdk.android.program.ProgramTrackedEntityAttributeInteractor;
import org.hisp.dhis.client.sdk.android.trackedentity.TrackedEntityAttributeValueInteractor;
import org.hisp.dhis.client.sdk.android.trackedentity.TrackedEntityInstanceInteractor;
import org.hisp.dhis.client.sdk.android.user.CurrentUserInteractor;
import org.hisp.dhis.client.sdk.models.event.Event;
import org.hisp.dhis.client.sdk.ui.bindings.views.View;
import org.hisp.dhis.client.sdk.utils.Logger;

import java.util.Date;

import javax.annotation.Nullable;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    public ActivityModule() {
        // explicit empty constructor
    }

    @Provides
    @PerActivity
    public RxRulesEngine providesRuleEngine(
            @Nullable ProgramRuleInteractor programRuleInteractor,
            @Nullable ProgramRuleActionInteractor programRuleActionInteractor,
            @Nullable ProgramRuleVariableInteractor programRuleVariableInteractor,
            @Nullable EnrollmentInteractor enrollmentInteractor,
            @Nullable EventInteractor eventInteractor, Logger logger) {
        return new RxRulesEngine(programRuleInteractor, programRuleActionInteractor,
                programRuleVariableInteractor, eventInteractor, enrollmentInteractor, logger);
    }

    @Provides
    @PerActivity
    public TeiWidgetPresenter providesTeiWidgetPresenter() {
        return new TeiWidgetPresenterImpl();
    }

    @Provides
    @PerActivity
    public TeiProfilePresenter providesTeiProfilePresenter() {
        return new TeiProfilePresenterImpl();
    }

    @Provides
    @PerActivity
    public TeiProgramStagePresenter providesTeiProgramStagePresenter(TeiDashboardPresenter teiDashboardPresenter) {
        return new TeiProgramStagePresenterImpl(teiDashboardPresenter);
    }

    @Provides
    @PerActivity
    public TeiNavigationPresenter providesTeiNavigationPresenter() {
        return new TeiNavigationPresenterImpl();
    }

    @Provides
    @PerActivity
    public TeiDashboardPresenter providesTeiDashboardPresenter(
            @Nullable FormSectionPresenter formSectionPresenter) {
        return new TeiDashboardPresenterImpl(formSectionPresenter);
    }

    @Provides
    @PerActivity
    public TrackedEntityInstanceDashboardPresenter providesTrackedEntityInstanceDashboardPresenter(
            @Nullable EnrollmentInteractor enrollmentInteractor,
            @Nullable TrackedEntityInstanceInteractor trackedEntityInstanceInteractor,
            @Nullable TrackedEntityAttributeValueInteractor trackedEntityAttributeValueInteractor,
            Logger logger) {
        return new TrackedEntityInstanceDashboardPresenterImpl(
                enrollmentInteractor, trackedEntityInstanceInteractor,
                trackedEntityAttributeValueInteractor, logger);
    }

    @Provides
    @PerActivity
    public EnrollmentFormPresenter providesEnrollmentFormPresenter(
            @Nullable ProgramInteractor programInteractor,
            @Nullable ProgramStageInteractor programStageInteractor,
            @Nullable ProgramStageSectionInteractor stageSectionInteractor,
            @Nullable EventInteractor eventInteractor,
            @Nullable EnrollmentInteractor enrollmentInteractor,
            RxRulesEngine rxRulesEngine,
            LocationProvider locationProvider, Logger logger) {
        return new EnrollmentFormPresenterImpl(programInteractor, programStageInteractor,
                stageSectionInteractor, eventInteractor, enrollmentInteractor, rxRulesEngine, locationProvider, logger);
    }

    @Provides
    public DataEntryPresenter providesDataEntryPresenter(
            @Nullable CurrentUserInteractor currentUserInteractor,
            @Nullable EnrollmentInteractor enrollmentInteractor,
            @Nullable ProgramInteractor programInteractor,
            @Nullable TrackedEntityAttributeValueInteractor trackedEntityAttributeValueInteractor,
            @Nullable ProgramTrackedEntityAttributeInteractor programTrackedEntityAttributeInteractor,
            @Nullable OptionSetInteractor optionSetInteractor,
            RxRulesEngine rxRulesEngine, Logger logger) {
        return new DataEntryPresenterImpl(currentUserInteractor, optionSetInteractor,
                enrollmentInteractor, programInteractor,
                programTrackedEntityAttributeInteractor, trackedEntityAttributeValueInteractor,
                rxRulesEngine, logger);
    }

    @Provides
    public FormSectionPresenter providesFormSectionPresenter() {
        return new FormSectionPresenter() {
            @Override
            public void createDataEntryForm(String eventUid) {
                Log.d("Data Entry", "for event: " + eventUid);
            }

            @Override
            public void saveEventDate(String eventUid, Date eventDate) {

            }

            @Override
            public void saveEventStatus(String eventUid, Event.EventStatus eventStatus) {

            }

            @Override
            public void subscribeToLocations() {

            }

            @Override
            public void stopLocationUpdates() {

            }

            @Override
            public void attachView(View view) {

            }

            @Override
            public void detachView() {

            }
        };
    }
}
