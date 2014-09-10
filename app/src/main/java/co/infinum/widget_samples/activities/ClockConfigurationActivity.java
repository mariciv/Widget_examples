package co.infinum.widget_samples.activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import co.infinum.widget_samples.R;
import co.infinum.widget_samples.services.UpdateConfigService;
import co.infinum.widget_samples.services.UpdateService;

/**
 * Created by Ivan on 9/7/14.
 */
public class ClockConfigurationActivity extends Activity {

    private int mAppWidgetId;

    private PendingIntent service;

    private Intent resultValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_CANCELED, resultValue);

    }

    @OnClick(R.id.light_theme)
    public void lightTheme() {

        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("Theme", "light").apply();
        setService();
        setResult(RESULT_OK, resultValue);
        finish();

    }

    @OnClick(R.id.dark_theme)
    public void darkTheme() {

        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("Theme", "dark").apply();
        setService();
        setResult(RESULT_OK, resultValue);
        finish();

    }

    private void setService() {
        final AlarmManager m = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        final Calendar TIME = Calendar.getInstance();
        TIME.set(Calendar.MINUTE, 0);
        TIME.set(Calendar.SECOND, 0);
        TIME.set(Calendar.MILLISECOND, 0);

        final Intent i = new Intent(this, UpdateConfigService.class);

        if (service == null)
        {
            service = PendingIntent.getService(this, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        }

        m.setRepeating(AlarmManager.RTC, TIME.getTime().getTime(), 1000 * 60, service);
    }

}
