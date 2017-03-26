package cn.elbereth.j3pz.event;

import okhttp3.ResponseBody;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/20.
 */

public class PzEvent {
    public final boolean success;
    public final Exception error;
    public final int code;
    public final String msg;
    protected final ResponseBody body;

    protected PzEvent(Exception error) {
        this(false, error, -1, null, null);
    }

    protected PzEvent(int code, String msg, ResponseBody body) {
        this(true, null, code, msg, body);
    }

    private PzEvent(boolean success, Exception error, int code, String msg, ResponseBody body) {
        this.success = success;
        this.error = error;
        this.code = code;
        this.msg = msg;
        this.body = body;
    }
}
