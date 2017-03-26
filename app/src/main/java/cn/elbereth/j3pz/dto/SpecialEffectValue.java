package cn.elbereth.j3pz.dto;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/22.
 */

public class SpecialEffectValue {
    private int conditionNum;
    private String desc;
    private String effectIndex;
    private int effectNum;

    public int getConditionNum() {
        return conditionNum;
    }

    public void setConditionNum(int conditionNum) {
        this.conditionNum = conditionNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEffectIndex() {
        return effectIndex;
    }

    public void setEffectIndex(String effectIndex) {
        this.effectIndex = effectIndex;
    }

    public int getEffectNum() {
        return effectNum;
    }

    public void setEffectNum(int effectNum) {
        this.effectNum = effectNum;
    }
}
