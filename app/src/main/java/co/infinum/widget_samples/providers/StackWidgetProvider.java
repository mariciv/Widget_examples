package co.infinum.widget_samples.providers;

import android.annotation.TargetApi;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import co.infinum.widget_samples.R;
import co.infinum.widget_samples.services.StackWidgetService;

/**
 * Created by Ivan on 9/10/14.
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class StackWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.stack_widget);

        Intent intent = new Intent(context, StackWidgetService.class);
        remoteViews.setRemoteAdapter(R.id.stack_view, intent);

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }
}
