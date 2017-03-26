package cn.elbereth.j3pz.dto;

import java.util.List;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/20.
 */

public class LoginData {
    private String uid;
    private String token;
    private String name;
    private int maxSave;
    private UserPrefer prefer;
    private List<UserCase> cases;
    private String picture;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSave() {
        return maxSave;
    }

    public void setMaxSave(int maxSave) {
        this.maxSave = maxSave;
    }

    public UserPrefer getPrefer() {
        return prefer;
    }

    public void setPrefer(UserPrefer prefer) {
        this.prefer = prefer;
    }

    public List<UserCase> getCases() {
        return cases;
    }

    public void setCases(List<UserCase> cases) {
        this.cases = cases;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
