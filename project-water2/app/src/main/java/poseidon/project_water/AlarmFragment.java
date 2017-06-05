package poseidon.project_water;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;

/**
 * Created by younggun on 2017-05-22.
 */

public class AlarmFragment extends Fragment implements View.OnClickListener{

    LinearLayout layout_alarm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alram, container, false);
        layout_alarm = (LinearLayout)v.findViewById(R.id.layout_alarm);
        ((Switch)v.findViewById(R.id.switch_alarm)).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if(((Switch)v).isChecked()) {
            layout_alarm.setVisibility(View.VISIBLE);
        } else {
            layout_alarm.setVisibility(View.GONE);
        }
    }
}
