package com.secrething.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaoq on 2018-11-28 18:11.
 */
public enum DatePaserEnum implements DateParser {
    LONG {
        @Override
        public Date parse(Object l) {
            return new Date((Long) l);
        }

        @Override
        public Long format(Date t) {
            return t.getTime();
        }
    },
    STRING {
        private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public Date parse(Object l) {
            try {
                return format.parse((String) l);
            } catch (ParseException e) {
                return null;
            }
        }

        @Override
        public String format(Date t) {
            return format.format(t);
        }
    };
}
