package cn.elbereth.j3pz.event;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.elbereth.j3pz.dto.BuffData;
import okhttp3.ResponseBody;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 *         Created on 2017/1/21.
 */

public class BuffEvent extends PzJsonEvent {
    private static final String TAG = "BuffEvent";

    private List<BuffData> data;

    public BuffEvent(Exception error) {
        super(error);
    }

    public BuffEvent(int code, String msg, ResponseBody body) {
        super(code, msg, body);
        if (json != null) {
            Gson gson = new Gson();
            JsonElement element = json.get("data");
            if (element != null && element.isJsonArray()) {
                try {
                    data = gson.fromJson(element, new TypeToken<List<BuffData>>(){}.getType());
                } catch (JsonSyntaxException e) {
                    Log.e(TAG, "LoginEvent: failed to get data", e);
                }
            }
        }
    }

    public List<BuffData> getData() {
        return data;
    }
}
