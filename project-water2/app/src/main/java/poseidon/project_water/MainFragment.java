package poseidon.project_water;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by younggun on 2017-05-22.
 */

public class MainFragment extends Fragment {

    ArrayList<todayWater> waterList;
    TodayAdapter todayAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ListView lv_today = (ListView)v.findViewById(R.id.lv_today);
        waterList = new ArrayList<todayWater>();
        todayAdapter = new TodayAdapter(getActivity(), waterList);
        lv_today.setAdapter(todayAdapter);
        v.findViewById(R.id.iv_ccup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다이얼로그 띄우기
            }
        });

        v.findViewById(R.id.iv_water).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //마신 물 추가
                waterList.add(new todayWater(R.drawable.ic_ccup, 300,
                        new SimpleDateFormat("HH시mm분").format(new Date(System.currentTimeMillis()))));
                todayAdapter.notifyDataSetChanged();
            }
        });

        return v;
    }

    class todayWater {
        private int resource_img;
        private int drinkWater;
        private String time;

        todayWater(int $resource_img, int $drinkWater, String $time) {
            resource_img = $resource_img;
            drinkWater = $drinkWater;
            time = $time;
        }

        public int getResource_img() {
            return resource_img;
        }

        public int getDrinkWater() {
            return drinkWater;
        }

        public String getTime() {
            return time;
        }
    }

    class TodayAdapter extends BaseAdapter {

        Context _context;
        ArrayList<todayWater> _list;

        TodayAdapter(Context $context, ArrayList<todayWater> $list) {
            _context = $context;
            _list = $list;
        }

        @Override
        public int getCount() {
            return _list.size();
        }

        @Override
        public Object getItem(int position) {
            return _list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(_context);
            View v;
            if(convertView == null) {
                v = inflater.inflate(R.layout.layout_todaylist_item, null);
            } else {
                v = convertView;
            }
            ImageView iv_todayList_cup = (ImageView)v.findViewById(R.id.iv_todaylist_cup);
            TextView tv_todayList_drink = (TextView)v.findViewById(R.id.tv_todaylist_drink);
            TextView tv_todayList_time = (TextView)v.findViewById(R.id.tv_todaylist_time);
            iv_todayList_cup.setImageResource(_list.get(position).getResource_img());
            tv_todayList_drink.setText(Integer.toString(_list.get(position).getDrinkWater()) + "ml");
            tv_todayList_time.setText(_list.get(position).getTime());
            return v;
        }
    }
}
