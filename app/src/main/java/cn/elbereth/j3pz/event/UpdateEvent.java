package cn.elbereth.j3pz.event;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

import cn.elbereth.j3pz.dto.UpdateData;
import okhttp3.ResponseBody;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/21.
 */

public class UpdateEvent extends PzJsonEvent {
    private static final String TAG = "UpdateEvent";

    private UpdateData data;

    public UpdateEvent(Exception error) {
        super(error);
    }

    public UpdateEvent(int code, String msg, ResponseBody body) {
        super(code, msg, body);
        if (json != null) {
            Gson gson = new Gson();
            JsonElement element = json.get("data");
            if (element != null && element.isJsonObject()) {
                try {
                    data = gson.fromJson(element, UpdateData.class);
                } catch (JsonSyntaxException e) {
                    Log.e(TAG, "LoginEvent: failed to get data", e);
                }
            }
        }
    }

    public UpdateData getData() {
        return data;
    }
}
