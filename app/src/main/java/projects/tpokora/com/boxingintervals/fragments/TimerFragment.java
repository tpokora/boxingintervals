package projects.tpokora.com.boxingintervals.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import projects.tpokora.com.boxingintervals.R;

/**
 * Created by pokor on 24.09.2016.
 */
public class TimerFragment extends Fragment {

    private TextView activityTitle;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState) {
        activityTitle = (TextView) getActivity().findViewById(R.id.activity_title);
        activityTitle.setText(getResources().getString(R.string.timer_fragment_header_string));

        return layoutInflater.inflate(R.layout.fragment_timer, container, false);
    }
}
