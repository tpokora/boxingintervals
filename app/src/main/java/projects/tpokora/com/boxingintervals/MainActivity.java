package projects.tpokora.com.boxingintervals;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import projects.tpokora.com.boxingintervals.fragments.AbstractFragment;
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

            setupFragment();
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

                if (settingsButton.getText().equals(getResources().getString(R.string.settings_button_string))) {
                    changeFragment(new SettingsFragment());
                } else {
                    changeFragment(new TimerFragment());
                    settingsButton.setText(getResources().getString(R.string.settings_button_string));
                }
            }
        });
    }

    private void setupFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        timerFragment = new TimerFragment();
        state = 0;
        fragmentTransaction.add(R.id.fragment_container, timerFragment).commit();
    }

    private void startTimer() {

    }

    private void changeFragment(AbstractFragment newFragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, newFragment).addToBackStack(null).commit();
    }
}
