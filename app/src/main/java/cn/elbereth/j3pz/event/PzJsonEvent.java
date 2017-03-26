package cn.elbereth.j3pz.event;

import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 *         Created on 2017/1/21.
 */

public class PzJsonEvent extends PzEvent {
    private static final String TAG = "PzJsonEvent";

    protected final JsonObject json;

    protected PzJsonEvent(Exception error) {
        super(error);
        json = null;
    }

    protected PzJsonEvent(int code, String msg, ResponseBody body) {
        super(code, msg, body);
        JsonObject object = null;
        if (body != null) {
            try {
                String s = body.string();
                Log.d(TAG, "PzJsonEvent get json: " + s);
                JsonParser parser = new JsonParser();
                try {
                    object = parser.parse(s).getAsJsonObject();
                } catch (JsonParseException | IllegalStateException e) {
                    Log.e(TAG, "PzJsonEvent: failed to parse json", e);
                }
            } catch (IOException e) {
                Log.e(TAG, "PzJsonEvent: failed to get json", e);
            }
        }
        json = object;
    }
}
