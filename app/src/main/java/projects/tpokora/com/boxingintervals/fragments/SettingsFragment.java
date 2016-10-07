package projects.tpokora.com.boxingintervals.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import projects.tpokora.com.boxingintervals.PropertyReader;
import projects.tpokora.com.boxingintervals.R;

/**
 * Created by pokor on 24.09.2016.
 */
public class SettingsFragment extends AbstractFragment {

    private static final String INCREASE = "increase";
    private static final String DECREASE = "decrease";

    private TextView bufforTextView;
    private TextView intervalsAmountTextView;
    private TextView intervalsDurationTextView;
    private TextView breakDurationTextView;

    private Button bufforDecreaseButton;
    private Button bufforIncreaseButton;
    private Button intervalsDecreaseButton;
    private Button intervalsIncreaseButton;
    private Button intervalDurationDecreaseButton;
    private Button intervalDurationIncreaseButton;
    private Button breakDurationDecreaseButton;
    private Button breakDurationIncreaseButton;

    private Button saveSettingsButton;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState) {
        setActivityTitle(getResources().getString(R.string.settings_fragment_header_string));
        setSettingsButtonString(getResources().getString(R.string.settings_save_button_string));

        return layoutInflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadTimerProperties(getActivity().getApplicationContext());
        setActivityTitle(getResources().getString(R.string.timer_fragment_header_string));
        initSettingsUI();
    }

    private void initSettingsUI() {
        bufforTextView = (TextView) getActivity().findViewById(R.id.buffor_amount);
        setBufforTextViewValue();

        intervalsAmountTextView = (TextView) getActivity().findViewById(R.id.interval_amount);
        setIntervalsTextViewValue();

        intervalsDurationTextView = (TextView) getActivity().findViewById(R.id.interval_duration_amount);
        setIntervalDurationTextViewValue();

        breakDurationTextView = (TextView) getActivity().findViewById(R.id.break_duration);
        setBreakDurationTextViewValue();

        // Change timer properties buttons
        bufforDecreaseButton = (Button) getActivity().findViewById(R.id.buffor_decrease);
        bufforDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAmount(DECREASE, PropertyReader.BUFFOR);
                setBufforTextViewValue();
            }
        });

        bufforIncreaseButton = (Button) getActivity().findViewById(R.id.buffor_increase);
        bufforIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAmount(INCREASE, PropertyReader.BUFFOR);
                setBufforTextViewValue();
            }
        });

        intervalsDecreaseButton = (Button) getActivity().findViewById(R.id.interval_decrease);
        intervalsDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAmount(DECREASE, PropertyReader.INTERVALS);
                setIntervalsTextViewValue();
            }
        });

        intervalsIncreaseButton = (Button) getActivity().findViewById(R.id.interval_increase);
        intervalsIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAmount(INCREASE, PropertyReader.INTERVALS);
                setIntervalsTextViewValue();
            }
        });

        intervalDurationDecreaseButton = (Button) getActivity().findViewById(R.id.interval_duration_decrease);
        intervalDurationDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAmount(DECREASE, PropertyReader.INTERVAL_DURATION);
                setIntervalDurationTextViewValue();
            }
        });

        intervalDurationIncreaseButton = (Button) getActivity().findViewById(R.id.interval_duration_increase);
        intervalDurationIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAmount(INCREASE, PropertyReader.INTERVAL_DURATION);
                setIntervalDurationTextViewValue();
            }
        });

        breakDurationDecreaseButton = (Button) getActivity().findViewById(R.id.break_duration_decrease);
        breakDurationDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAmount(DECREASE, PropertyReader.BREAK_DURATION);
                setBreakDurationTextViewValue();
            }
        });

        breakDurationIncreaseButton = (Button) getActivity().findViewById(R.id.break_duration_increase);
        breakDurationIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAmount(INCREASE, PropertyReader.BREAK_DURATION);
                setBreakDurationTextViewValue();
            }
        });

        saveSettingsButton = (Button) getActivity().findViewById(R.id.save_settings);
        saveSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Save settings to file
                //propertyReader.savePropertiesToFile(timerProperties);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void changeAmount(String action, String property) {
        switch (action) {
            case INCREASE:
                timerProperties.put(property, (timerProperties.get(property) + 1));
                break;
            case DECREASE:
                if (timerProperties.get(property) > 0) {
                    timerProperties.put(property, (timerProperties.get(property) - 1));
                }
                break;
            default:
                break;
        }
    }

    private void setBufforTextViewValue() {
        bufforTextView.setText(Integer.toString(timerProperties.get(PropertyReader.BUFFOR)));
    }

    private void setIntervalsTextViewValue() {
        intervalsAmountTextView.setText(Integer.toString(timerProperties.get(PropertyReader.INTERVALS)));
    }

    private void setIntervalDurationTextViewValue() {
        intervalsDurationTextView.setText(Integer.toString(timerProperties.get(PropertyReader.INTERVAL_DURATION)));
    }

    private void setBreakDurationTextViewValue() {
        breakDurationTextView.setText(Integer.toString(timerProperties.get(PropertyReader.BREAK_DURATION)));
    }
}
