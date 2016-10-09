package projects.tpokora.com.boxingintervals;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by pokor on 06.10.2016.
 */

public class PropertyReader {
    private static final String DEBUG_TAG = "PropertyReader";

    protected SharedPreferences sharedPreferences;
    protected SharedPreferences.Editor editor;

    private static final int PREFERENCE_MODE_PRIVATE = 0;

    public final static String BUFFOR = "buffor";
    public final static String INTERVALS = "intervals";
    public final static String INTERVAL_DURATION= "interval.duration";
    public final static String BREAK_DURATION = "break.duration";
    private HashMap<String, Integer> timerProperties;

    public PropertyReader() {
        timerProperties = new HashMap<>();
    }

    public void saveProperties(Fragment fragment, HashMap<String, Integer> propertiesMap) {
        sharedPreferences = fragment.getActivity().getPreferences(PREFERENCE_MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putInt(BUFFOR, propertiesMap.get(BUFFOR));
        editor.putInt(INTERVALS, propertiesMap.get(INTERVALS));
        editor.putInt(INTERVAL_DURATION, propertiesMap.get(INTERVAL_DURATION));
        editor.putInt(BREAK_DURATION, propertiesMap.get(BREAK_DURATION));
        editor.commit();
    }

    public HashMap<String, Integer> loadProperties(Fragment fragment) {
        sharedPreferences = fragment.getActivity().getPreferences(PREFERENCE_MODE_PRIVATE);

        timerProperties.put(BUFFOR, sharedPreferences.getInt(BUFFOR, 0));
        timerProperties.put(INTERVALS, sharedPreferences.getInt(INTERVALS, 0));
        timerProperties.put(INTERVAL_DURATION, sharedPreferences.getInt(INTERVAL_DURATION, 0));
        timerProperties.put(BREAK_DURATION, sharedPreferences.getInt(BREAK_DURATION, 0));

        return timerProperties;
    }
}
