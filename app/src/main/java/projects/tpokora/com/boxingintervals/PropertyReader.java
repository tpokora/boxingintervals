package projects.tpokora.com.boxingintervals;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by pokor on 06.10.2016.
 */

public class PropertyReader {

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

    public Properties getProperties(String filename) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(filename);
            properties.load(inputStream);
        } catch (IOException ioe) {
            Log.e(PropertyReader.this.getClass().getName(), "Could no read " + filename);
        }

        return properties;
    }
}
