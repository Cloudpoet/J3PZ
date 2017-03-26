package cn.elbereth.j3pz.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.elbereth.j3pz.R;
import cn.elbereth.j3pz.base.BaseActivity;
import cn.elbereth.j3pz.base.EventBusProvider;
import cn.elbereth.j3pz.dto.EquipDetailData;
import cn.elbereth.j3pz.event.EquipDetailEvent;
import cn.elbereth.j3pz.utils.TranslateUtils;
import cn.elbereth.j3pz.wizard.PzClient;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/22.
 */

public class EquipActivity extends BaseActivity {
    private static final String TAG = "EquipActivity";
    public static final String EXTRA_PID = "pid";

    private TextView nameTv;
    private TextView strengthenTv;
    private TextView typeTv;
    private TextView basicPropTv;
    private TextView magicPropTv;
    private TextView specialEffectTv;
    private TextView componentTv;
    private TextView componentValueTv;
    private TextView qualityTv;
    private TextView scoreTv;
    private TextView schoolTv;
    private TextView sourceTv;

    private long pid;
    private EquipDetailData data;
    private EventBus eventBus;
    private PzClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);

        pid = getIntent().getLongExtra(EXTRA_PID, -1);
        Log.i(TAG, "onCreate pid: " + pid);

        if (pid < 0) {
            showToast("数据错误");
            finish();
        }

        eventBus = EventBusProvider.GLOBAL_EVENT_BUS;
        client = PzClient.INSTANCE;

        nameTv = (TextView) findViewById(R.id.equip_tv_name);
        strengthenTv = (TextView) findViewById(R.id.equip_tv_strengthen);
        typeTv = (TextView) findViewById(R.id.equip_tv_type);
        basicPropTv = (TextView) findViewById(R.id.equip_tv_prop_basic);
        magicPropTv = (TextView) findViewById(R.id.equip_tv_prop_magic);
        specialEffectTv = (TextView) findViewById(R.id.equip_tv_special_effect);
        componentTv = (TextView) findViewById(R.id.equip_tv_component);
        componentValueTv = (TextView) findViewById(R.id.equip_tv_component_value);
        qualityTv = (TextView) findViewById(R.id.equip_tv_quality);
        scoreTv = (TextView) findViewById(R.id.equip_tv_score);
        schoolTv = (TextView) findViewById(R.id.equip_tv_school);
        sourceTv = (TextView) findViewById(R.id.equip_tv_drop_source);
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
        if (data == null) {
            client.equipDetail(pid);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    private void initView() {
        nameTv.setText(data.getName());
        strengthenTv.setText("精炼等级:0/" + data.getStrengthen());
        typeTv.setText(TranslateUtils.getEquipTypeName(data.getType()));
        StringBuilder basicBuilder = new StringBuilder();
        if (data.getBody() > 0) {
            basicBuilder.append("\n").append("体质+").append(data.getBody());
        }
        if (data.getSpirit() > 0) {
            basicBuilder.append("\n").append("根骨+").append(data.getSpirit());
        }
        if (data.getAgility() > 0) {
            basicBuilder.append("\n").append("身法+").append(data.getAgility());
        }
        if (data.getStrength() > 0) {
            basicBuilder.append("\n").append("力道+").append(data.getStrength());
        }
        if (basicBuilder.length() == 0) {
            basicPropTv.setVisibility(View.GONE);
        } else {
            basicPropTv.setVisibility(View.VISIBLE);
            basicPropTv.setText(basicBuilder.substring(1));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void  getEquipDetailEvent(EquipDetailEvent event) {
        if (event.success) {
            EquipDetailData data = event.getData();
            if (data != null) {
                if (data.getPid() == pid) {
                    this.data = data;
                    initView();
                }
            } else {
                showToast("数据错误");
            }
        } else {
            showToast("连接失败，请检查网络");
        }
    }
}
