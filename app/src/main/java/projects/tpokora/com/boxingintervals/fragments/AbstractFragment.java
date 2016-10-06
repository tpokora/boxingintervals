package projects.tpokora.com.boxingintervals.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import projects.tpokora.com.boxingintervals.PropertyReader;
import projects.tpokora.com.boxingintervals.R;

/**
 * Created by pokor on 24.09.2016.
 */
public abstract class AbstractFragment extends Fragment {

    protected static final String PROPERTIES_FILE = "timer.properties";

    private TextView activityTitleTextView;
    private Button settingsButton;

    private PropertyReader propertyReader;

    protected HashMap<String, Integer> timerProperties;

    public AbstractFragment() {
        timerProperties = new HashMap<String, Integer>();
    }

    protected void setActivityTitle(String activityTitle) {
        activityTitleTextView = (TextView) this.getActivity().findViewById(R.id.activity_title);
        activityTitleTextView.setText(activityTitle);
    }

    protected void setSettingsButtonString(String buttonString) {
        settingsButton = (Button) this.getActivity().findViewById(R.id.settings_button);
        settingsButton.setText(buttonString);
    }

    protected void loadTimerProperties(Context context) {
        propertyReader = new PropertyReader(context);
        Properties properties = propertyReader.getProperties(PROPERTIES_FILE);

        for (final Map.Entry<Object, Object> entry : properties.entrySet()) {
            timerProperties.put((String) entry.getKey(), Integer.valueOf((String) entry.getValue()));
        }
    }
}
