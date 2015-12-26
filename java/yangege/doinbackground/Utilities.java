package yangege.doinbackground;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;

public class Utilities {
    private static boolean isServiceRunning(Context context, String serviceName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningServices = (ArrayList<ActivityManager.RunningServiceInfo>) activityManager.getRunningServices(Integer.MAX_VALUE);
        for (int i = 0; i < runningServices.size(); i++) {
            if (runningServices.get(i).service.getClassName().toString().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    public static void KeepDaemonServiceAlive(Context context) {
        if (!Utilities.isServiceRunning(context, "yangege.doinbackground.JobService")) {
            Intent jobService = new Intent(context, DaemonService.class);
            context.startService(jobService);
        }
    }

    public static void KeepJobServiceAlive(Context context) {
        if (!Utilities.isServiceRunning(context, "yangege.doinbackground.JobService")) {
            Intent jobService = new Intent(context, JobService.class);
            context.startService(jobService);
        }
    }

    public static  void registerTimeTickReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_TIME_TICK);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                KeepDaemonServiceAlive(context);
                KeepJobServiceAlive(context);
            }
        };
        context.registerReceiver(broadcastReceiver, intentFilter);
    }
}
