package cn.elbereth.j3pz.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.elbereth.j3pz.R;
import cn.elbereth.j3pz.base.BaseActivity;
import cn.elbereth.j3pz.base.EventBusProvider;
import cn.elbereth.j3pz.base.PzApplication;
import cn.elbereth.j3pz.domain.School;
import cn.elbereth.j3pz.dto.BuffData;
import cn.elbereth.j3pz.dto.LoginData;
import cn.elbereth.j3pz.dto.LoginError;
import cn.elbereth.j3pz.dto.UpdateData;
import cn.elbereth.j3pz.dto.UserData;
import cn.elbereth.j3pz.event.BuffEvent;
import cn.elbereth.j3pz.event.LoginEvent;
import cn.elbereth.j3pz.event.StoneEvent;
import cn.elbereth.j3pz.event.UpdateEvent;
import cn.elbereth.j3pz.event.UserEvent;
import cn.elbereth.j3pz.utils.StringUtils;
import cn.elbereth.j3pz.wizard.PzClient;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/19.
 */

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private EditText nameEt;
    private EditText pwdEt;
    private TextView msgTv;
    private EventBus eventBus;
    private PzClient client;
    private SharedPreferences pref;

    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventBus = EventBusProvider.GLOBAL_EVENT_BUS;
        client = PzClient.INSTANCE;
        pref = ((PzApplication) getApplication()).getDefaultPref();
        token = pref.getString("token", "");

        nameEt = (EditText) findViewById(R.id.main_et_name);
        pwdEt = (EditText) findViewById(R.id.main_et_password);
        msgTv = (TextView) findViewById(R.id.main_tv_msg);
        findViewById(R.id.main_btn_login).setOnClickListener(this);
        findViewById(R.id.main_btn_user).setOnClickListener(this);
        findViewById(R.id.main_btn_update).setOnClickListener(this);
        findViewById(R.id.main_btn_buff).setOnClickListener(this);
        findViewById(R.id.main_btn_equip).setOnClickListener(this);
        findViewById(R.id.main_btn_stone).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        int id = v.getId();
        if (id == R.id.main_btn_login) {
            String name = nameEt.getText().toString();
            String pwd = pwdEt.getText().toString();
            if (StringUtils.isEmpty(name)) {
                showToast(R.string.username_can_not_be_empty);
                return;
            }
            if (StringUtils.isEmpty(pwd)) {
                showToast(R.string.password_can_not_be_empty);
                return;
            }
            client.login(name, pwd);
        } else if (id == R.id.main_btn_user) {
            Log.i(TAG, "onClick token: " + token);
            client.user(token);
        } else if (id == R.id.main_btn_update) {
            client.update();
        } else if (id == R.id.main_btn_buff) {
            client.buff(School.SCHOOL_BINGXIN);
        } else if (id == R.id.main_btn_equip) {
            startActivity(new Intent(this, EquipListActivity.class));
        } else if (id == R.id.main_btn_stone) {
//            client.stone(School.SCHOOL_BINGXIN);
            client.test();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLoginEvent(LoginEvent event) {
        if (event.success) {
            LoginData data = event.getData();
            if (data == null) {
                msgTv.setTextColor(Color.RED);
                List<LoginError> errors = event.getErrors();
                if (errors == null || errors.isEmpty()) {
                    msgTv.setText("未知错误");
                } else {
                    LoginError error = errors.get(0);
                    msgTv.setText(error.getTitle() + ":" + error.getDetail());
                }
            } else {
                token = data.getToken();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("token", token);
                editor.apply();
                msgTv.setTextColor(Color.GREEN);
                msgTv.setText("登陆成功");
            }
        } else {
            showToast("连接失败，请检查网络");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getUpdateEvent(UpdateEvent event) {
        if (event.success) {
            UpdateData data = event.getData();
            if (data != null) {
                showToast(data.getDesc());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getUserEvent(UserEvent event) {
        if (event.success) {
            UserData data = event.getData();
            if (data == null) {
                msgTv.setTextColor(Color.RED);
                List<LoginError> errors = event.getErrors();
                if (errors == null || errors.isEmpty()) {
                    msgTv.setText("未知错误");
                } else {
                    if (StringUtils.isEmpty(token)) {
                        msgTv.setText("尚未登录，请先登录");
                    } else {
                        msgTv.setText("登陆超时，请重新登录");
                    }
                }
            } else {
                msgTv.setTextColor(Color.GREEN);
                msgTv.setText("Hello! " + data.getName());
            }
        } else {
            showToast("连接失败，请检查网络");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBuffEvent(BuffEvent event) {
        if (event.success) {
            List<BuffData> list = event.getData();
            if (list != null) {
                Log.i(TAG, "getBuffEvent: " + list.size());
            } else {
                showToast("数据错误");
            }
        } else {
            showToast("连接失败，请检查网络");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getStoneEvent(StoneEvent event) {
        if (event.success) {
            List<String> list = event.getData();
            if (list != null) {
                for (String s : list) {
                    Log.i(TAG, "getStoneEvent: " + s);
                }
            } else {
                showToast("数据错误");
            }
        } else {
            showToast("连接失败，请检查网络");
        }
    }
}
