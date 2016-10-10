package projects.tpokora.com.boxingintervals.fragments;

import android.media.MediaPlayer;
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

import projects.tpokora.com.boxingintervals.PropertyReader;
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
    private int startTimerStatus = 0;

    private Timer timer;
    private Handler timerHandler;
    private Runnable timerRunnable;

    private long startTime = 0L;

    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState) {
        return layoutInflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        timerProperties = propertyReader.loadProperties(this);
        setActivityTitle(getResources().getString(R.string.timer_fragment_header_string));
        setTimer();
        initTimerUI();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        timerProperties = propertyReader.loadProperties(this);
        setTimer();
    }

    public void setTimer() {
        int intervalDuration = timerProperties.get(PropertyReader.INTERVAL_DURATION);
        int minutes = intervalDuration / 60;
        int seconds = intervalDuration % 60;
        timer = new Timer(timerProperties.get(PropertyReader.BUFFOR), timerProperties.get(PropertyReader.INTERVALS), minutes, seconds, timerProperties.get(PropertyReader.BREAK_DURATION));
    }

    private void initTimerUI() {
        mediaPlayer = MediaPlayer.create(getActivity().getBaseContext(), R.raw.boxingbellsound);

        bufforTextView = (TextView) getActivity().findViewById(R.id.buffor_title);
        bufforTextView.setText(String.format("%02d", timer.getBuffor()));

        intervalsTextView = (TextView) getActivity().findViewById(R.id.interval_counter_title);
        intervalsTextView.setText(setIntervalsTextViewText(INTERVAL_NUMBER_STRING, 1, timer.getIntervals()));

        intervalTimerTextView = (TextView) getActivity().findViewById(R.id.interval_counter);
        intervalTimerTextView.setText(setIntervalTimerTextViewText(timer.getIntervalMinute(), timer.getIntervalSeconds()));

        breakTimerTextView = (TextView) getActivity().findViewById(R.id.break_timer);
        breakTimerTextView.setText(String.format("%02d s", timer.getBreakDuration()));

        // UI for timer
        timerHandler = new Handler();
        timerRunnable = new Runnable() {

            private boolean buffor = true;
            private boolean interval = false;
            private boolean rest = false;

            private long timeInMilliseconds = 0L;
            private long currentTime = 0L;
            private int currentTimeSecs = 0;
            private int totalLeftSeconds = 0;
            private int secondsToShow = 0;

            private int intervalCounter = 1;

            @Override
            public void run() {
                // start buffor
                if (buffor) {
                    currentTimeSecs = calculateCurrentTimeSecs(SystemClock.uptimeMillis() - startTime);
                    bufforTextView.setText(String.format("%02d", timer.getBuffor() - currentTimeSecs));

                    if (currentTimeSecs >= timer.getBuffor()) {
                        buffor = false;
                        interval = true;
                        currentTime = 0;
                        currentTimeSecs = 0;
                        resetStartTime();
                        playRingBellSound();
                    }
                }

                // start interval
                if (interval) {
                    currentTimeSecs = calculateCurrentTimeSecs(SystemClock.uptimeMillis() - startTime);
                    totalLeftSeconds = calculateTotalSecondsLeft(timer.getIntervalDuration(), currentTimeSecs);
                    secondsToShow = totalLeftSeconds % 60;

                    intervalTimerTextView.setText(setIntervalTimerTextViewText(calculateMinutes(totalLeftSeconds), secondsToShow));

                    // intervals check
                    if (totalLeftSeconds == 0) {
                        if (intervalCounter > timer.getIntervals() - 1) {
                            interval = false;
                            resetStartTime();
                        } else {
                            Log.d(DEBUG_TAG, "Interval counter increase");
                            intervalCounter++;
                            intervalsTextView.setText(String.format("%s %d/%d", INTERVAL_NUMBER_STRING, intervalCounter, timer.getIntervals()));
                            currentTime = 0;
                            resetStartTime();
                            interval = false;
                            if (intervalCounter <= timer.getIntervals()) {
                                rest = true;
                                playRingBellSound();
                            }
                        }
                    }
                }

                // start rest
                if (rest) {
                    intervalTimerTextView.setText(setIntervalTimerTextViewText(timer.getIntervalMinute(), timer.getIntervalSeconds()));
                    currentTimeSecs = calculateCurrentTimeSecs(SystemClock.uptimeMillis() - startTime);
                    totalLeftSeconds = calculateTotalSecondsLeft(timer.getBreakDuration(), currentTimeSecs);

                    breakTimerTextView.setText(String.format("%02d s", totalLeftSeconds));

                    if (totalLeftSeconds == 0) {
                        breakTimerTextView.setText(String.format("%02d s", timer.getBreakDuration()));
                        rest = false;
                        interval = true;
                        resetStartTime();
                        playRingBellSound();
                    }
                }

                timerHandler.postDelayed(this, 1000);

                if (!buffor && !interval && !rest) {
                    Log.d(DEBUG_TAG, "Stopping timer");
                    timerHandler.removeMessages(0);
                    startTimerButton.setText(getResources().getString(R.string.start_button_string));
                    playRingBellSound();
                }
            }

            private void resetStartTime() {
                startTime = SystemClock.uptimeMillis();
            }

            private int calculateCurrentTimeSecs(long timeInMilliseconds) {
                currentTimeSecs = (int) (timeInMilliseconds / 1000);
                return currentTimeSecs;
            }

            private int calculateTotalSecondsLeft(int totalTime, int deductTime) {
                return totalTime - deductTime;
            }

            private int calculateMinutes(int seconds) {
                return seconds / 60;
            }

        };

        startTimerButton = (Button) getActivity().findViewById(R.id.start_button);
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startTimerStatus == 0) {
                    setTimer();
                    startTime = SystemClock.uptimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    Log.d(DEBUG_TAG, "Start timer");
                    startTimerButton.setText(getResources().getString(R.string.start_button_stop_string));
                    startTimerStatus = 1;
                    initTimerUI();
                } else {
                    setTimer();
                    timerHandler.removeMessages(0);
                    startTimerButton.setText(getResources().getString(R.string.start_button_string));
                    startTimerStatus = 0;
                    initTimerUI();
                }

            }
        });
    }

    private String setIntervalsTextViewText(String x, int currentInterval, int totalIntervals) {
        return String.format("%s %d/%d", x, currentInterval, totalIntervals);
    }

    private String setIntervalTimerTextViewText(int minutes, int seconds) {
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void playRingBellSound() {
        mediaPlayer.start();
        Toast.makeText(getContext(), "BELL RING", Toast.LENGTH_LONG);
    }

}
