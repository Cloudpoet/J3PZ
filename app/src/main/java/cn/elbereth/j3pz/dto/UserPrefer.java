package cn.elbereth.j3pz.dto;

import java.util.List;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/20.
 */

public class UserPrefer {
    private List<Integer> quality;
    private int strengthen;
    private int magicStoneLevel;
    private List<String> dps;
    private List<String> hps;
    private List<String> t;

    public List<Integer> getQuality() {
        return quality;
    }

    public void setQuality(List<Integer> quality) {
        this.quality = quality;
    }

    public int getStrengthen() {
        return strengthen;
    }

    public void setStrengthen(int strengthen) {
        this.strengthen = strengthen;
    }

    public int getMagicStoneLevel() {
        return magicStoneLevel;
    }

    public void setMagicStoneLevel(int magicStoneLevel) {
        this.magicStoneLevel = magicStoneLevel;
    }

    public List<String> getDps() {
        return dps;
    }

    public void setDps(List<String> dps) {
        this.dps = dps;
    }

    public List<String> getHps() {
        return hps;
    }

    public void setHps(List<String> hps) {
        this.hps = hps;
    }

    public List<String> getT() {
        return t;
    }

    public void setT(List<String> t) {
        this.t = t;
    }
}
