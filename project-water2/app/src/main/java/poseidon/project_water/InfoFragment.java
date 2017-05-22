package poseidon.project_water;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by younggun on 2017-05-22.
 */

public class InfoFragment extends Fragment {

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info, container, false);

        view.findViewById(R.id.bt_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_tall = (EditText)view.findViewById(R.id.et_tall);
                EditText et_weight = (EditText)view.findViewById(R.id.et_weight);
                EditText et_cup = (EditText)view.findViewById(R.id.et_cup);
                if(!et_tall.getText().toString().equals("") &&
                        !et_weight.getText().toString().equals("") &&
                        !et_cup.getText().toString().equals("")) {
                    int need = Integer.parseInt(et_weight.getText().toString()) * 30;
                    int cup = Integer.parseInt(et_cup.getText().toString());
                    //데이터베이스에 need와 cup 저장하기
                }
            }
        });
        return view;
    }
}
