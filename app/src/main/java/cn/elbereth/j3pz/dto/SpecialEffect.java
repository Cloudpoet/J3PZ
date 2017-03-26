package cn.elbereth.j3pz.dto;

import java.util.List;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/22.
 */

public class SpecialEffect {
    private int id;
    private String type; //"Collection"
    private List<String> desc;
    private String name; //套装名称
    private List<SpecialEffectComponent> components; //套装组件
    private List<SpecialEffectValue> effects; //套装效果

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getDesc() {
        return desc;
    }

    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpecialEffectComponent> getComponents() {
        return components;
    }

    public void setComponents(List<SpecialEffectComponent> components) {
        this.components = components;
    }

    public List<SpecialEffectValue> getEffects() {
        return effects;
    }

    public void setEffects(List<SpecialEffectValue> effects) {
        this.effects = effects;
    }
}
