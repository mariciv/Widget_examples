package co.infinum.widget_samples.services;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import co.infinum.widget_samples.R;
import co.infinum.widget_samples.WidgetApplication;

/**
 * Created by Ivan on 9/10/14.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB) // To disable error because api level incompatibility
public class StackWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory();
    }

    private class StackRemoteViewsFactory implements RemoteViewsFactory {

        private Integer[] imageResIds = new Integer[]{
                R.drawable.nixa,
                R.drawable.kust,
                R.drawable.vitas,
                R.drawable.koc,
                R.drawable.penic,
                R.drawable.maric,
                R.drawable.kovac,
                R.drawable.jurkovic,
                R.drawable.rumac,
                R.drawable.ana,
                R.drawable.grbac,
                R.drawable.plesac };

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return imageResIds.length;
        }

        @Override
        public RemoteViews getViewAt(int i) {
            RemoteViews remoteViews = null;

            if (i < imageResIds.length) {
                remoteViews = new RemoteViews(WidgetApplication.getInstance().getPackageName(), R.layout.stack_widget_image);
                remoteViews.setImageViewResource(R.id.stack_image, imageResIds[i]);
            }

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
