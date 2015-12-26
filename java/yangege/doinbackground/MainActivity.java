package yangege.doinbackground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent daemonService = new Intent(MainActivity.this, DaemonService.class);
        startService(daemonService);
        Intent jobService = new Intent(MainActivity.this, JobService.class);
        startService(jobService);
        /* EXAMPLE: You can use this function to make one context(activity, service etc.) check and restart JobService and DaemonService periodically*/
        Utilities.registerTimeTickReceiver(MainActivity.this);
    }

}
