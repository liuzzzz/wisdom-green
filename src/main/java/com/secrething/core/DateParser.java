package com.secrething.core;

import java.util.Date;

/**
 * Created by xiaoq on 2018-11-28 18:17.
 */
public interface DateParser {
    Date parse(Object t);

    Object format(Date t);
}
