package cn.elbereth.j3pz.event;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import cn.elbereth.j3pz.dto.EquipDetailData;
import okhttp3.ResponseBody;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/22.
 */

public class EquipDetailEvent extends PzJsonEvent {
    private static final String TAG = "EquipDetailEvent";

    private EquipDetailData data;

    public EquipDetailEvent(Exception error) {
        super(error);
    }

    public EquipDetailEvent(int code, String msg, ResponseBody body) {
        super(code, msg, body);
        if (json != null) {
            Gson gson = new Gson();
            JsonElement element = json.get("data");
            if (element != null && element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                JsonElement texiao = obj.get("texiao");
                if (texiao != null && !texiao.isJsonObject()) {
                    obj.remove("texiao");
                }
                try {
                    data = gson.fromJson(obj, EquipDetailData.class);
                } catch (JsonSyntaxException e) {
                    Log.e(TAG, "LoginEvent: failed to get data", e);
                }
            }
        }
    }

    public EquipDetailData getData() {
        return data;
    }
}
