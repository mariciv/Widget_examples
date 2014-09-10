package co.infinum.widget_samples.services;

import org.joda.time.DateTime;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import co.infinum.widget_samples.R;
import co.infinum.widget_samples.activities.ClockConfigurationActivity;
import co.infinum.widget_samples.providers.ClockConfigWidgetProvider;

/**
 * Created by Ivan on 9/7/14.
 */
public class UpdateConfigService extends Service {

    @Override
    public IBinder onBind(Intent intent) {

        throw null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String theme = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("Theme", "light");
        RemoteViews views;
        if (theme.equals("light")) {
            views = new RemoteViews(getPackageName(), R.layout.clock_widget_light);
        } else {
            views = new RemoteViews(getPackageName(), R.layout.clock_widget_dark);
        }

        Intent i = new Intent(this, ClockConfigurationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, 0);

        views.setOnClickPendingIntent(R.id.widget_root, pendingIntent);

        DateTime time = new DateTime();

        int hour = time.getHourOfDay();
        int min = time.getMinuteOfHour();

        views.setTextViewText(R.id.clock_hours, String.format("%02d", hour));
        views.setTextViewText(R.id.clock_minutes, String.format("%02d", min));

        ComponentName thisWidget = new ComponentName(this, ClockConfigWidgetProvider.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        appWidgetManager.updateAppWidget(thisWidget, views);

        return super.onStartCommand(intent, flags, startId);
    }
}
