package april.lesincs.rxjava

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.github.kbiakov.codeview.classifier.CodeProcessor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

const val ARGUMENT_OPERATOR = "ARGUMENT_OPERATOR"

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mCurrentFrag: Fragment

    private val mOperatorCreateFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR, OperatorFactory.OPERATOR_CREATE) } }
    private val mOperatorDeferFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR, OperatorFactory.OPERATOR_DEFER) } }
    private val mOperatorEmptyFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR, OperatorFactory.OPERTOR_EMPTY) } }
    private val mOperatorNeverFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR, OperatorFactory.OPERTOR_NEVER) } }
    private val mOperatorErrorFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR, OperatorFactory.OPERTOR_ERROR) } }
    private val mOperatorFromFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR, OperatorFactory.OPERTOR_FROM) } }
    private val mOperatorIntervalFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR, OperatorFactory.OPERTOR_INTERVAL) } }
    private val mOperatorJustFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR, OperatorFactory.OPERTOR_JUST) } }
    private val mOperatorRangeFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR, OperatorFactory.OPERTOR_RANGE) } }
    private val mOperatorTimerFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR, OperatorFactory.OPERTOR_TIMER) } }

    private val mOperatorBufferFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_BUFFER) } }
    private val mOperatorFlatMapFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_FLATMAP) } }
    private val mOperatorGroupByFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_GROUPBY) } }
    private val mOperatorScanFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_SCAN) } }
    private val mOperatorMapFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_MAP) } }
    private val mOperatorWindowFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_WINDOW) } }

    private val mOperatorDebounceFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_DEBOUNCE) } }
    private val mOperatorDistinctFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_DISTINCT) } }
    private val mOperatorElementAtFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_ELEMENT_AT) } }
    private val mOperatorFilterFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_FILTER) } }
    private val mOperatorFirstFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_FIRST) } }
    private val mOperatorIgnoreElementsFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_IGNORE_ELEMENTS) } }
    private val mOperatorLastFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_LAST) } }
    private val mOperatorSampleFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_SAMPLE) } }
    private val mOperatorSkipFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_SKIP) } }
    private val mOperatorSkipLastFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_SKIP_LAST) } }
    private val mOperatorTakeFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_TAKE) } }
    private val mOperatorTakeLastFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_TAKE_LAST) } }

    private val mOperatorCombineLatestFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_COMBINLATEST) } }
    private val mOperatorJoinFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_JOIN) } }
    private val mOperatorMergeFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_MERGE) } }
    private val mOperatorStartWithFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_START_WITH) } }
    private val mOperatorSwitchFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_SWITCH) } }
    private val mOperatorZipFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_ZIP) } }

    private val mOperatorCatchFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_CATCH) } }
    private val mOperatorRetryFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_RETRY) } }

    private val mOperatorDelayFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_DELAY) } }
    private val mOperatorDoFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_DO) } }
    private val mOperatorMaterializeFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_MATERIALIZE) } }
    private val mOperatorDeMaterializeFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_DEMATERIALIZE) } }
    private val mOperatorSerializeFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_SERIALIZE) } }
    private val mOperatorObserveOnFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_OBSERVE_ON) } }
    private val mOperatorSubscribeOnFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_SUBSCRIBE_ON) } }
    private val mOperatorTimeIntervalFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_TIME_INTERVAL) } }
    private val mOperatorTimeOutFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_TIME_OUT) } }
    private val mOperatorTimeStampFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_TIME_STAMP) } }
    private val mOperatorUsingFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_USING) } }

    private val mOperatorAllFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_ALL) } }
    private val mOperatorAmbFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_AMB) } }
    private val mOperatorContainsFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_CONTAINS) } }
    private val mOperatorDefaultIfEmptyFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_DEFAULT_IF_EMPTY) } }
    private val mOperatorSequenceEqualFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_SEQUENCE_EQUAL) } }
    private val mOperatorSkipUntilFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_SKIP_UNTIL) } }
    private val mOperatorSkipWhileFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_SKIP_WHILE) } }
    private val mOperatorTakeUntilFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_TAKE_UNTIL) } }
    private val mOperatorTakeWhileFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_TAKE_WHILE) } }

    private val mOperatorConcatFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_CONCAT) } }
    private val mOperatorReduceFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_REDUCE) } }

    private val mOperatorConnectFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_CONNECT) } }
    private val mOperatorPublishFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_PUBLISH) } }
    private val mOperatorRefCountFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_REFCOUNT) } }
    private val mOperatorReplayFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_REPLAY) } }

    private val mOperatorToFrag: Fragment = OperatorFragment().apply { arguments = Bundle().apply { putParcelable(ARGUMENT_OPERATOR,OperatorFactory.OPERATOR_TO) } }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFrag()

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun initFrag() {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, mOperatorCreateFrag).commit()
        nav_view.setCheckedItem(R.id.menu_operator_create)
        mCurrentFrag = mOperatorCreateFrag
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.menu_operator_create -> switch(mOperatorCreateFrag)
            R.id.menu_operator_defer -> switch(mOperatorDeferFrag)
            R.id.menu_operator_empty -> switch(mOperatorEmptyFrag)
            R.id.menu_operator_never -> switch(mOperatorNeverFrag)
            R.id.menu_operator_error -> switch(mOperatorErrorFrag)
            R.id.menu_operator_from -> switch(mOperatorFromFrag)
            R.id.menu_operator_interval -> switch(mOperatorIntervalFrag)
            R.id.menu_operator_just -> switch(mOperatorJustFrag)
            R.id.menu_operator_range -> switch(mOperatorRangeFrag)
            R.id.menu_operator_timer -> switch(mOperatorTimerFrag)

            R.id.menu_operator_buffer->switch(mOperatorBufferFrag)
            R.id.menu_operator_flatMap->switch(mOperatorFlatMapFrag)
            R.id.menu_operator_groupBy->switch(mOperatorGroupByFrag)
            R.id.menu_operator_map->switch(mOperatorMapFrag)
            R.id.menu_operator_scan->switch(mOperatorScanFrag)
            R.id.menu_operator_window->switch(mOperatorWindowFrag)

            R.id.menu_operator_debounce->switch(mOperatorDebounceFrag)
            R.id.menu_operator_distinct->switch(mOperatorDistinctFrag)
            R.id.menu_operator_elementAt->switch(mOperatorElementAtFrag)
            R.id.menu_operator_filter->switch(mOperatorFilterFrag)
            R.id.menu_operator_first->switch(mOperatorFirstFrag)
            R.id.menu_operator_ignoreElements->switch(mOperatorIgnoreElementsFrag)
            R.id.menu_operator_last->switch(mOperatorLastFrag)
            R.id.menu_operator_sample->switch(mOperatorSampleFrag)
            R.id.menu_operator_skip->switch(mOperatorSkipFrag)
            R.id.menu_operator_skipLast->switch(mOperatorSkipLastFrag)
            R.id.menu_operator_take->switch(mOperatorTakeFrag)
            R.id.menu_operator_takeLast->switch(mOperatorTakeLastFrag)

            R.id.menu_operator_combineLatest->switch(mOperatorCombineLatestFrag)
            R.id.menu_operator_join->switch(mOperatorJoinFrag)
            R.id.menu_operator_merge->switch(mOperatorMergeFrag)
            R.id.menu_operator_start_with->switch(mOperatorStartWithFrag)
            R.id.menu_operator_switch->switch(mOperatorSwitchFrag)
            R.id.menu_operator_zip->switch(mOperatorZipFrag)

            R.id.menu_operator_catch->switch(mOperatorCatchFrag)
            R.id.menu_operator_retry->switch(mOperatorRetryFrag)

            R.id.menu_operator_delay->switch(mOperatorDelayFrag)
            R.id.menu_operator_do->switch(mOperatorDoFrag)
            R.id.menu_operator_materialize->switch(mOperatorMaterializeFrag)
            R.id.menu_operator_dematerialize->switch(mOperatorDeMaterializeFrag)
            R.id.menu_operator_serialize->switch(mOperatorSerializeFrag)
            R.id.menu_operator_observeOn->switch(mOperatorObserveOnFrag)
            R.id.menu_operator_subscribeOn->switch(mOperatorSubscribeOnFrag)
            R.id.menu_operator_timeInterval->switch(mOperatorTimeIntervalFrag)
            R.id.menu_operator_timeOut->switch(mOperatorTimeOutFrag)
            R.id.menu_operator_timeStamp->switch(mOperatorTimeStampFrag)
            R.id.menu_operator_using->switch(mOperatorUsingFrag)

            R.id.menu_operator_all->switch(mOperatorAllFrag)
            R.id.menu_operator_amb->switch(mOperatorAmbFrag)
            R.id.menu_operator_contains->switch(mOperatorContainsFrag)
            R.id.menu_operator_defaultIfEmpty->switch(mOperatorDefaultIfEmptyFrag)
            R.id.menu_operator_sequenceEqual->switch(mOperatorSequenceEqualFrag)
            R.id.menu_operator_skipUntil->switch(mOperatorSkipUntilFrag)
            R.id.menu_operator_skipWhile->switch(mOperatorSkipWhileFrag)
            R.id.menu_operator_takeUntil->switch(mOperatorTakeUntilFrag)
            R.id.menu_operator_takeWhile->switch(mOperatorTakeWhileFrag)

            R.id.menu_operator_concat->switch(mOperatorConcatFrag)
            R.id.menu_operator_reduce->switch(mOperatorReduceFrag)

            R.id.menu_operator_connect->switch(mOperatorConnectFrag)
            R.id.menu_operator_publish->switch(mOperatorPublishFrag)
            R.id.menu_operator_refCount->switch(mOperatorRefCountFrag)
            R.id.menu_operator_replay->switch(mOperatorReplayFrag)

            R.id.menu_operator_to ->switch(mOperatorToFrag)
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun switch(toFragment: Fragment) {
        if (toFragment === mCurrentFrag)
            return
        if (!toFragment.isAdded) {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, toFragment).commit()
        }
        supportFragmentManager.beginTransaction().hide(mCurrentFrag).show(toFragment).commit()
        mCurrentFrag = toFragment
    }
}
