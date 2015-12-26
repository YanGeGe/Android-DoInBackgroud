package yangege.doinbackground;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;


public class JobService extends Service {
    public JobService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID) {
        Timer timer = new Timer();
        TimerTask timerTask  = new TimerTask() {
            @Override
            public void run() {
                Utilities.KeepDaemonServiceAlive(JobService.this);
            }
        };
        timer.schedule(timerTask, 0, 1000);
        BackgroundJob backgroundJob = new BackgroundJob();
        backgroundJob.start();
        return START_STICKY;
    }
}
