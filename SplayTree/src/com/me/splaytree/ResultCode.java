package com.me.splaytree;

import com.fingard.ats.framework.core.constant.enumeration.PersistEnum;

import java.util.Map;
import java.util.TreeMap;


public enum ResultCode implements PersistEnum<ResultCode> {
    /**
     * 成功
     */
    SUCCESS("success"),
    /**
     * 重复
     */
    DUPLITED("duplited"),
    /**
     * 变更
     */
    FAILED("failed");

    private String value;

    public static Map<String, ResultCode> getMap() {
        return map;
    }

    private ResultCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    private static final Map<String, ResultCode> map = new TreeMap<String, ResultCode>();

    static {
        map.put(SUCCESS.getValue(), SUCCESS);
        map.put(DUPLITED.getValue(), DUPLITED);
        map.put(FAILED.getValue(), FAILED);
    }

    @Override
    public String getPersistedValue() {
        return this.getValue();
    }

    @Override
    public ResultCode returnEnum(String persistedValue) {
        return map.get(persistedValue);
    }

    @Override
    public Map<String, ResultCode> getAllValueMap() {
        return map;
    }
}
