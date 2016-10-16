package projects.tpokora.com.boxingintervals;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import projects.tpokora.com.boxingintervals.fragments.TimerFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;

    private TimerFragment timerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            setupFragment();
        }
    }

    private void setupFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        timerFragment = new TimerFragment();
        fragmentTransaction.add(R.id.fragment_container, timerFragment).commit();
    }
}
