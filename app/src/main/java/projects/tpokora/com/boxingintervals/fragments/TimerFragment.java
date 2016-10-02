package projects.tpokora.com.boxingintervals.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import projects.tpokora.com.boxingintervals.R;
import projects.tpokora.com.boxingintervals.Timer;

/**
 * Created by pokor on 24.09.2016.
 */
public class TimerFragment extends AbstractFragment {

    private Timer timer;

    private TextView bufforTextView;
    private TextView intervalsTextView;
    private TextView intervalTimerTextView;
    private TextView breakTimerTextView;

    private static final String INTERVAL_NUMBER_STRING = "Interval";

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState) {
        return layoutInflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setActivityTitle(getResources().getString(R.string.timer_fragment_header_string));
        setTimer();
        initTimerUI();
    }

    public void setTimer() {
        timer = new Timer(10, 2, 150, 20);
    }

    private void initTimerUI() {
        bufforTextView = (TextView) getActivity().findViewById(R.id.buffor_title);
        bufforTextView.setText(Integer.toString(timer.getBuffor()));

        intervalsTextView = (TextView) getActivity().findViewById(R.id.interval_counter_title);
        intervalsTextView.setText(INTERVAL_NUMBER_STRING + " 1/" + timer.getIntervals());

        intervalTimerTextView = (TextView) getActivity().findViewById(R.id.interval_counter);
        intervalTimerTextView.setText(timer.getTimerTime());

        breakTimerTextView = (TextView) getActivity().findViewById(R.id.break_timer);
        breakTimerTextView.setText(timer.getBreakDurationString() + " s");
    }
}
