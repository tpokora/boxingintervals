package projects.tpokora.com.boxingintervals.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import projects.tpokora.com.boxingintervals.R;
import projects.tpokora.com.boxingintervals.Timer;

/**
 * Created by pokor on 24.09.2016.
 */
public class TimerFragment extends AbstractFragment {

    private static final String DEBUG_TAG = "TimerFragment";
    private static final String INTERVAL_NUMBER_STRING = "Interval";

    private TextView bufforTextView;
    private TextView intervalsTextView;
    private TextView intervalTimerTextView;
    private TextView breakTimerTextView;

    private Button startTimerButton;

    private Timer timer;
    private Handler timerHandler;
    private Runnable timerRunnable;

    private boolean runTimer = false;
    private long startTime = 0L;
    private long startIntervalTime = 0L;
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long currentTime = 0L;
    private long currentTimeSecs = 0L;
    private long mins = 0L;

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
        timer = new Timer(10, 2, 1, 10, 20);
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

        // UI for timer
        timerHandler = new Handler();
        timerRunnable = new Runnable() {

            boolean buffor = true;
            boolean interval = false;
            boolean rest = false;

            int intervalCounter = 0;

            @Override
            public void run() {
                // start buffor
                if (buffor) {
                    timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
                    currentTime = timeSwapBuff + timeInMilliseconds;
                    currentTimeSecs = (int) (currentTime / 1000);
                    mins = currentTimeSecs / 60;

                    currentTimeSecs = currentTimeSecs % 60;
                    Log.d(DEBUG_TAG, "Buffor second: " + currentTimeSecs);
                    bufforTextView.setText(String.format("%02d", timer.getBuffor() - currentTimeSecs));

                    if (currentTimeSecs >= timer.getBuffor()) {
                        buffor = false;
                        interval = true;
                        currentTime = 0;
                        timeSwapBuff = 0;
                        currentTimeSecs = 0;
                        startTime = SystemClock.uptimeMillis();
                    }
                }

                timerHandler.postDelayed(this, 1000);

                if (!buffor && !interval && !rest) {
                    Log.d(DEBUG_TAG, "Stopping timer");
                    timerHandler.removeCallbacks(timerRunnable);
                }
            }
        };

        startTimerButton = (Button) getActivity().findViewById(R.id.start_button);
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(DEBUG_TAG, "Timer");
                startTime = SystemClock.uptimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);
                Log.d(DEBUG_TAG, "Start timer");
            }
        });
    }
}
