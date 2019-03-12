package com.secrething.model;

import com.secrething.core.Key;
import com.secrething.utils.UUIDBuilder;

/**
 * Created by xiaoq on 2018-11-27 12:07.
 */
public class Base {
    @Key("id")
    private String uid = UUIDBuilder.genUUID();

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public static void main(String[] args) throws Exception {
        int[] r = {1,2,3,4};
        int[] s = {1,2,3,4,5,6,7,8};
        int[] t = {r[0],r[0],r[0],r[0],r[0],r[0],r[0]};
        for (int i=0;i<t.length;i++){
            int ti = t[i];
            for (int j = 0; j <r.length ; j++) {
                int rj = r[j];
                
            }
        }
    }
}
