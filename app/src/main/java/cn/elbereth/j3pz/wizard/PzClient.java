package cn.elbereth.j3pz.wizard;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.elbereth.j3pz.base.EventBusProvider;
import cn.elbereth.j3pz.event.BuffEvent;
import cn.elbereth.j3pz.event.EquipDetailEvent;
import cn.elbereth.j3pz.event.EquipListEvent;
import cn.elbereth.j3pz.event.LoginEvent;
import cn.elbereth.j3pz.event.StoneEvent;
import cn.elbereth.j3pz.event.UpdateEvent;
import cn.elbereth.j3pz.event.UserEvent;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/20.
 */

public class PzClient {
    private static final String TAG = "PzClient";
    public static final PzClient INSTANCE = new PzClient();

    private static final String DOMAIN = "https://www.j3pz.com/";
    private static final String URL_LOGIN = DOMAIN + "api/user/auth";
    private static final String URL_USER = DOMAIN + "api/user";
    private static final String URL_UPDATE = DOMAIN + "api/update";
    private static final String URL_BUFF = DOMAIN + "api/buff";
    private static final String URL_EQUIP = DOMAIN + "api/equip";
    private static final String URL_STONE = DOMAIN + "api/stone";
    private static final String URL_ENHANCE = DOMAIN + "api/enhance";

    private final HttpHelper helper;
    private final EventBus eventBus;

    private PzClient() {
        this.helper = new HttpHelper();
        eventBus = EventBusProvider.GLOBAL_EVENT_BUS;
    }

    public void login(String username, String password) {
        JsonObject obj = new JsonObject();
        obj.addProperty("user", username);
        obj.addProperty("pass", password);
        helper.post(URL_LOGIN, helper.getJsonBody(obj), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                eventBus.post(new LoginEvent(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                eventBus.post(new LoginEvent(response.code(), response.message(), response.body()));
            }
        });
    }

    public void user(String token) {
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Bearer " + token);
        helper.get(URL_USER, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                eventBus.post(new UserEvent(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                eventBus.post(new UserEvent(response.code(), response.message(), response.body()));
            }
        });
    }

    public void update() {
        helper.get(URL_UPDATE, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                eventBus.post(new UpdateEvent(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                eventBus.post(new UpdateEvent(response.code(), response.message(), response.body()));
            }
        });
    }

    public void buff(@NonNull String school) {
        Map<String, String> map = new HashMap<>();
        map.put("school", school);
        helper.pramGet(URL_BUFF, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                eventBus.post(new BuffEvent(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                eventBus.post(new BuffEvent(response.code(), response.message(), response.body()));
            }
        });
    }


    /**
     * @param school 心法
     * @param position 装备位置
     *                 0 帽子 1 上衣 2 腰带 3 护腕
     *                 4 下装 5 鞋子 6 项链 7 腰坠
     *                 8 戒指1 9 戒指2 a 暗器 b 武器
     */
    public void equip(@NonNull String school, int position) {
        Map<String, String> map = new HashMap<>();
        map.put("school", school);
        map.put("position", Integer.toHexString(position));
        helper.pramGet(URL_EQUIP, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                eventBus.post(new EquipListEvent(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                eventBus.post(new EquipListEvent(response.code(), response.message(), response.body()));
            }
        });
    }

    public void enhance(@NonNull String school, int position) {
        Map<String, String> map = new HashMap<>();
        map.put("school", school);
        map.put("position", Integer.toHexString(position));
        helper.pramGet(URL_EQUIP, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO: 2017/1/22
//                eventBus.post(new EquipListEvent(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO: 2017/1/22
//                eventBus.post(new EquipListEvent(response.code(), response.message(), response.body()));
            }
        });
    }

    public void stone(@NonNull String school) {
        Map<String, String> map = new HashMap<>();
        map.put("school", school);
        helper.pramGet(URL_STONE, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                eventBus.post(new StoneEvent(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                eventBus.post(new StoneEvent(response.code(), response.message(), response.body()));
            }
        });
    }

    public void equipDetail(long pid) {
        helper.get(URL_EQUIP + "/" + pid, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                eventBus.post(new EquipDetailEvent(e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                eventBus.post(new EquipDetailEvent(response.code(), response.message(), response.body()));
            }
        });
    }

    public void test() {
        helper.get("https://www.j3pz.com/api/update", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + response.body().toString());
            }
        });
    }
}
