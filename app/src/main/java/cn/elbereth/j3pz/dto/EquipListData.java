package cn.elbereth.j3pz.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/21.
 */

public class EquipListData {
    private long id;
    private int quality;
    private String name;
    @SerializedName("class")
    private int clazz;
    private List<String> filter;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public List<String> getFilter() {
        return filter;
    }

    public void setFilter(List<String> filter) {
        this.filter = filter;
    }
}
