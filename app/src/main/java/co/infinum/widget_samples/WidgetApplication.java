package co.infinum.widget_samples;

import android.app.Application;

/**
 * Created by Ivan on 9/10/14.
 */
public class WidgetApplication extends Application {

    private static WidgetApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static WidgetApplication getInstance() {
        return mInstance;
    }
}
