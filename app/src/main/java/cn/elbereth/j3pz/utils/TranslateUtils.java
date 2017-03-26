package cn.elbereth.j3pz.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/22.
 */

public class TranslateUtils {
    private static final Map<String, String> FILTER_MAP = new HashMap<>();
    private static final String[] EQUIP_TYPE_ARRAY = {"项链", "腰坠", "戒指", null, "远程武器",
            "鞋子", "护腕", "下装", "上衣", "帽子", "腰带", "进程武器"};
    static {
        FILTER_MAP.put("crit", "会心");
        FILTER_MAP.put("hit", "命中");
        FILTER_MAP.put("overcome", "破防");
        FILTER_MAP.put("strain", "无双");
        FILTER_MAP.put("acce", "加速");
        FILTER_MAP.put("toughness", "御劲");
        FILTER_MAP.put("huajing", "化劲");
    }

    public static String getFilterName(String filter) {
        return FILTER_MAP.get(filter);
    }

    public static String getEquipTypeName(int type) {
        if (type < 0 || type >= EQUIP_TYPE_ARRAY.length) {
            return null;
        }
        return EQUIP_TYPE_ARRAY[type];
    }
}
