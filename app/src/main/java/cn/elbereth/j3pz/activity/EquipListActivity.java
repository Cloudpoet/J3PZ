package cn.elbereth.j3pz.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.elbereth.j3pz.R;
import cn.elbereth.j3pz.base.BaseActivity;
import cn.elbereth.j3pz.base.EventBusProvider;
import cn.elbereth.j3pz.domain.School;
import cn.elbereth.j3pz.dto.EquipListData;
import cn.elbereth.j3pz.event.EquipListEvent;
import cn.elbereth.j3pz.utils.TranslateUtils;
import cn.elbereth.j3pz.wizard.PzClient;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/21.
 */

public class EquipListActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "EquipListActivity";

    private List<EquipListData> list;
    private ArrayAdapter adapter;
    private EventBus eventBus;
    private PzClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_list);

        client = PzClient.INSTANCE;
        eventBus = EventBusProvider.GLOBAL_EVENT_BUS;

        list = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.equip_list_lv);
        adapter = new EquipAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        eventBus.register(this);
        client.equip(School.SCHOOL_BINGXIN, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
        list.clear();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEquipListEvent(EquipListEvent event) {
        if (event.success) {
            List<EquipListData> data = event.getData();
            if (data != null) {
                list.clear();
                list.addAll(data);
                adapter.notifyDataSetChanged();
            } else {
                showToast("数据错误");
            }
        } else {
            showToast("连接失败，请检查网络");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, EquipActivity.class).putExtra(EquipActivity.EXTRA_PID, list.get(position).getId()));
    }

    private static class EquipAdapter extends ArrayAdapter<EquipListData> {

        private EquipAdapter(Context context, List<EquipListData> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, android.view.View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.equit_item, parent, false);
                holder = new ViewHolder();
                holder.qualityTv = (TextView) convertView.findViewById(R.id.equip_item_tv_quality);
                holder.nameTv = (TextView) convertView.findViewById(R.id.equip_item_tv_name);
                holder.filterTv = (TextView) convertView.findViewById(R.id.equip_item_tv_filter);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            EquipListData data = getItem(position);
            holder.qualityTv.setText(Integer.toString(data.getQuality()));
            holder.nameTv.setText(data.getName());
            StringBuilder builder = new StringBuilder();
            for (String s : data.getFilter()) {
                String name = TranslateUtils.getFilterName(s);
                builder.append(" ").append(name == null ? s : name);
            }
            holder.filterTv.setText(builder.substring(1));
            return convertView;
        }
    }

    private static class ViewHolder {
        private TextView qualityTv;
        private TextView nameTv;
        private TextView filterTv;
    }
}
