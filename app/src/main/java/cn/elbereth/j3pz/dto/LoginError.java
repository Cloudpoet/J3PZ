package cn.elbereth.j3pz.dto;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/20.
 */

public class LoginError {
    private int status;
    private String title;
    private String detail;
    private String code;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
