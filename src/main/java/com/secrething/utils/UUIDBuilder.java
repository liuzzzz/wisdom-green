package com.secrething.utils;

/**
 * Created by xiaoq on 2018-11-25.
 */
public abstract class UUIDBuilder {

    private UUIDBuilder(){
        throw new UnsupportedOperationException("Illegal Operation");
    }
    public static final String genUUID(){
        String UUID = java.util.UUID.randomUUID().toString();
        StringBuilder sbff = new StringBuilder();
        sbff.append(UUID, 0, 8);
        sbff.append(UUID, 9, 13);
        sbff.append(UUID, 14, 18);
        sbff.append(UUID, 19, 23);
        sbff.append(UUID, 24, 36);
        return sbff.toString();
    }
}
