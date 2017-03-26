package cn.elbereth.j3pz.dto;

import com.google.gson.annotations.SerializedName;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/22.
 */

public class EquipDetailData {
    @SerializedName("P_ID")
    private long pid;
    private int uiID;
    private long iconID; //装备图标ID
    private String name; //装备名称
    private int menpai; //推荐门派
    private int xinfa; //推荐心法
    private int type; //装备类型
    private int quality; //装品
    private int score; //装分
    private int body; //体质
    private int spirit; //根骨
    private int strength; //力道
    private int agility; //身法
    private int spunk; //元气
    private int basicPhysicsShield; //基础外防
    private int basicMagicShield; //基础内防
    private int physicsShield; //外防
    private int magicShield; //内防
    private int dodge; //闪避
    private int parryBase;
    private int parryValue;
    private int toughness; //御劲
    private int attack; //攻击
    private int heal; //治疗
    private int crit; //会心
    private int critEffect; //会效
    private int overcome; //破防
    private int acce; //加速
    private int hit; //命中
    private int strain; //无双
    private int huajing; //化劲
    private int threat; //威胁值
    private SpecialEffect texiao; //特效
    private String xiangqian; //镶嵌
    private int strengthen; //精炼
    private String dropSource; //装备来源
    private long xinfatype;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public int getUiID() {
        return uiID;
    }

    public void setUiID(int uiID) {
        this.uiID = uiID;
    }

    public long getIconID() {
        return iconID;
    }

    public void setIconID(long iconID) {
        this.iconID = iconID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 门派
     *          0 根骨职业 七秀(冰心诀)、五毒(毒经)、纯阳(紫霞功)、长歌(莫问)
     *          1 内功职业
     *          6 七秀
     *          7 五毒
     *          8 纯阳
     *          13 长歌
     */
    public int getMenpai() {
        return menpai;
    }

    public void setMenpai(int menpai) {
        this.menpai = menpai;
    }

    /**
     * @return 心法类型
     *          0 内功DPS
     *          3 根骨内功DPS
     */
    public int getXinfa() {
        return xinfa;
    }

    public void setXinfa(int xinfa) {
        this.xinfa = xinfa;
    }

    /**
     * @return 装备类型
     *          9 帽子 8 上衣 10 腰带 6 护腕
     *          7 下装 5 鞋子 0 项链 1 腰坠
     *          2 戒指 4 暗器 11 武器
     */
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getSpunk() {
        return spunk;
    }

    public void setSpunk(int spunk) {
        this.spunk = spunk;
    }

    public int getBasicPhysicsShield() {
        return basicPhysicsShield;
    }

    public void setBasicPhysicsShield(int basicPhysicsShield) {
        this.basicPhysicsShield = basicPhysicsShield;
    }

    public int getBasicMagicShield() {
        return basicMagicShield;
    }

    public void setBasicMagicShield(int basicMagicShield) {
        this.basicMagicShield = basicMagicShield;
    }

    public int getPhysicsShield() {
        return physicsShield;
    }

    public void setPhysicsShield(int physicsShield) {
        this.physicsShield = physicsShield;
    }

    public int getMagicShield() {
        return magicShield;
    }

    public void setMagicShield(int magicShield) {
        this.magicShield = magicShield;
    }

    public int getDodge() {
        return dodge;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public int getParryBase() {
        return parryBase;
    }

    public void setParryBase(int parryBase) {
        this.parryBase = parryBase;
    }

    public int getParryValue() {
        return parryValue;
    }

    public void setParryValue(int parryValue) {
        this.parryValue = parryValue;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getCrit() {
        return crit;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }

    public int getCritEffect() {
        return critEffect;
    }

    public void setCritEffect(int critEffect) {
        this.critEffect = critEffect;
    }

    public int getOvercome() {
        return overcome;
    }

    public void setOvercome(int overcome) {
        this.overcome = overcome;
    }

    public int getAcce() {
        return acce;
    }

    public void setAcce(int acce) {
        this.acce = acce;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getStrain() {
        return strain;
    }

    public void setStrain(int strain) {
        this.strain = strain;
    }

    public int getHuajing() {
        return huajing;
    }

    public void setHuajing(int huajing) {
        this.huajing = huajing;
    }

    public int getThreat() {
        return threat;
    }

    public void setThreat(int threat) {
        this.threat = threat;
    }

    public SpecialEffect getTexiao() {
        return texiao;
    }

    public void setTexiao(SpecialEffect texiao) {
        this.texiao = texiao;
    }

    public String getXiangqian() {
        return xiangqian;
    }

    public void setXiangqian(String xiangqian) {
        this.xiangqian = xiangqian;
    }

    public int getStrengthen() {
        return strengthen;
    }

    public void setStrengthen(int strengthen) {
        this.strengthen = strengthen;
    }

    public String getDropSource() {
        return dropSource;
    }

    public void setDropSource(String dropSource) {
        this.dropSource = dropSource;
    }

    public long getXinfatype() {
        return xinfatype;
    }

    public void setXinfatype(long xinfatype) {
        this.xinfatype = xinfatype;
    }
}
