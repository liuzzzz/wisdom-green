package com.secrething.core;

import java.util.Map;

/**
 * Created by xiaoq on 2018/11/25 上午12:36.
 */
public class Record {
    private String index;
    private String type;
    private String id;
    private Map source;

    @SuppressWarnings("unchecked")
    public static Record create(Object obj, String uid,DateParser parser) {
        Class clzz = obj.getClass();
        try {
            if (!clzz.isAnnotationPresent(Document.class))
                throw new UnsupportedOperationException("not mark Document");
            Document document = (Document) clzz.getAnnotation(Document.class);
            Record record = new Record();
            record.setIndex(document.index());
            record.setType(document.type());
            Map<String, Object> s = MapWriter.map(obj,parser);
            if (null != s) {
                Object id = s.remove("id");
                String setId = uid;
                if (StringUtils.isBlank(uid) && null != id) {
                    setId = id.toString();
                }
                record.setId(setId);
                record.setSource(s);
            }
            return record;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Record create(Object obj) {
        return create(obj, null,null);
    }
    public static Record create(Object obj,String uid) {
        return create(obj, uid,null);
    }
    public static Record create(Object obj,DateParser parser) {
        return create(obj, null,parser);
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map getSource() {
        return source;
    }

    public void setSource(Map source) {
        this.source = source;
    }
}
