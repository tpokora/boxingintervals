package projects.tpokora.com.boxingintervals.fragments;

import android.os.Bundle;
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

    private TextView bufforTextView;
    private TextView intervalsAmountTextView;
    private TextView intervalsDurationTextView;
    private TextView breakDurationTextView;

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
        bufforTextView.setText(Integer.toString(timerProperties.get(PropertyReader.BUFFOR)));

        intervalsAmountTextView = (TextView) getActivity().findViewById(R.id.interval_amount);
        intervalsAmountTextView.setText(Integer.toString(timerProperties.get(PropertyReader.INTERVALS)));

        intervalsDurationTextView = (TextView) getActivity().findViewById(R.id.interval_duration_amount);
        intervalsDurationTextView.setText(Integer.toString(timerProperties.get(PropertyReader.INTERVAL_DURATION)));

        breakDurationTextView = (TextView) getActivity().findViewById(R.id.break_duration);
        breakDurationTextView.setText(Integer.toString(timerProperties.get(PropertyReader.BREAK_DURATION)));

        saveSettingsButton = (Button) getActivity().findViewById(R.id.settings_button);
        saveSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
