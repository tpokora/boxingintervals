package projects.tpokora.com.boxingintervals.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projects.tpokora.com.boxingintervals.R;

/**
 * Created by pokor on 24.09.2016.
 */
public class SettingsFragment extends AbstractFragment {

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState) {
        setActivityTitle(getResources().getString(R.string.settings_fragment_header_string));
        setSettingsButtonString(getResources().getString(R.string.settings_done_button_string));

        return layoutInflater.inflate(R.layout.fragment_settings, container, false);
    }
}
