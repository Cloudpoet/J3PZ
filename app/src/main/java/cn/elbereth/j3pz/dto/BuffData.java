package cn.elbereth.j3pz.dto;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/21.
 */

public class BuffData {
    private int id;
    private String name;
    private int type;
    private int iconId;
    private String desc;
    private int isPercent;
    private int isFinal;
    private int conflict;
    private BuffScaling dataB;
    private BuffScaling dataP;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIsPercent() {
        return isPercent;
    }

    public void setIsPercent(int isPercent) {
        this.isPercent = isPercent;
    }

    public int getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(int isFinal) {
        this.isFinal = isFinal;
    }

    public int getConflict() {
        return conflict;
    }

    public void setConflict(int conflict) {
        this.conflict = conflict;
    }

    public BuffScaling getDataB() {
        return dataB;
    }

    public void setDataB(BuffScaling dataB) {
        this.dataB = dataB;
    }

    public BuffScaling getDataP() {
        return dataP;
    }

    public void setDataP(BuffScaling dataP) {
        this.dataP = dataP;
    }
}
