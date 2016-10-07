package projects.tpokora.com.boxingintervals;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by pokor on 06.10.2016.
 */

public class PropertyReader {

    private static final String DEBUG_TAG = "PropertyReader";
    private static final String PROPERTIES_FILE = "timer.properties";

    public final static String BUFFOR = "buffor";
    public final static String INTERVALS = "intervals";
    public final static String INTERVAL_DURATION= "interval.duration";
    public final static String BREAK_DURATION = "break.duration";

    private Context context;
    private Properties properties;

    public PropertyReader(Context context) {
        this.context = context;

        properties = new Properties();
    }

    public Properties getProperties() {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(PROPERTIES_FILE);
            properties.load(inputStream);
        } catch (IOException ioe) {
            Log.e(PropertyReader.this.getClass().getName(), "Could not read " + PROPERTIES_FILE);
        }

        return properties;
    }

    public void savePropertiesToFile(HashMap<String, Integer> timerProperties) {
        // TODO: impossible to save file to assets
        Iterator iterator = timerProperties.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            properties.put(entry.getKey(), entry.getValue());
        }

        try {
            properties.store(new FileOutputStream(PROPERTIES_FILE), null);
            Log.d(DEBUG_TAG, "Properties saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
