package projects.tpokora.com.boxingintervals.fragments;

import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import projects.tpokora.com.boxingintervals.PropertyReader;
import projects.tpokora.com.boxingintervals.R;

/**
 * Created by pokor on 24.09.2016.
 */
public abstract class AbstractFragment extends Fragment {

    private TextView activityTitleTextView;
    protected Button settingsButton;
    protected Button timmerButton;

    protected PropertyReader propertyReader;

    protected HashMap<String, Integer> timerProperties;

    public AbstractFragment() {
        propertyReader = new PropertyReader();
        timerProperties = new HashMap<String, Integer>();
    }

    protected void setActivityTitle(String activityTitle) {
        activityTitleTextView = (TextView) this.getActivity().findViewById(R.id.activity_title);
        activityTitleTextView.setText(activityTitle);
    }

    protected void setSettingsButtonString(String settingsButtonString) {
        settingsButton = (Button) this.getActivity().findViewById(R.id.settings_button);
        settingsButton.setText(settingsButtonString);
    }

    protected void setTimerButtonString(String timerButtonString) {
        timmerButton = (Button) this.getActivity().findViewById(R.id.start_button);
        timmerButton.setText(timerButtonString);
    }

    protected void changeFragment(AbstractFragment newFragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newFragment).commit();
    }
}
