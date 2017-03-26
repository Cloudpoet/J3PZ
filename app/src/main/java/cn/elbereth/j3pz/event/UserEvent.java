package cn.elbereth.j3pz.event;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import cn.elbereth.j3pz.dto.LoginError;
import cn.elbereth.j3pz.dto.UserData;
import okhttp3.ResponseBody;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 *         Created on 2017/1/21.
 */

public class UserEvent extends PzJsonEvent {
    private static final String TAG = "UserEvent";

    private UserData data;
    private List<LoginError> errors;

    public UserEvent(Exception error) {
        super(error);
    }

    public UserEvent(int code, String msg, ResponseBody body) {
        super(code, msg, body);
        if (json != null) {
            Gson gson = new Gson();
            JsonElement element = json.get("data");
            if (element != null && element.isJsonObject()) {
                try {
                    data = gson.fromJson(element, UserData.class);
                } catch (JsonSyntaxException e) {
                    Log.e(TAG, "LoginEvent: failed to get data", e);
                }
            } else {
                element = json.get("errors");
                if (element != null && element.isJsonArray()) {
                    try {
                        errors = gson.fromJson(element, new TypeToken<List<LoginError>>() {}.getType());
                    } catch (JsonSyntaxException e) {
                        Log.e(TAG, "LoginEvent: failed to get data", e);
                    }
                }
            }
        }
    }

    public UserData getData() {
        return data;
    }

    public List<LoginError> getErrors() {
        return errors;
    }
}
