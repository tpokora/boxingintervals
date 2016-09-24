package projects.tpokora.com.boxingintervals;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import projects.tpokora.com.boxingintervals.fragments.SettingsFragment;
import projects.tpokora.com.boxingintervals.fragments.TimerFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;

    private TimerFragment timerFragment;
    private SettingsFragment settingsFragment;

    private Button startButton;
    private Button settingsButton;

    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIElements();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            fragmentTransaction = getSupportFragmentManager().beginTransaction();

            timerFragment = new TimerFragment();
            state = 0;
            fragmentTransaction.add(R.id.fragment_container, timerFragment).commit();
        }
    }

    private void initUIElements() {
        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
            }
        });

        settingsButton = (Button) findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (state == 0) {
                    settingsFragment = new SettingsFragment();
                    state = 1;
                    fragmentTransaction.replace(R.id.fragment_container, settingsFragment).addToBackStack(null).commit();
                } else {
                    timerFragment = new TimerFragment();
                    state = 0;
                    fragmentTransaction.replace(R.id.fragment_container, timerFragment).addToBackStack(null).commit();
                }
            }
        });
    }

    private void startTimer() {

    }
}
