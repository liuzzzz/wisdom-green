package com.secrething.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by xiaoq on 2018/11/19 下午6:13.
 */
public class PropUtil {
    private static final Logger log = LoggerFactory.getLogger(PropUtil.class);

    private PropUtil() {
        throw new UnsupportedOperationException();
    }

    private static final Properties p = new Properties();

    static {
        try {
            File f = new File(PropUtil.class.getResource("/").getPath() + "/es.properties");
            InputStream is = new FileInputStream(f);
            p.load(is);
            is.close();
            log.info("----------config from cfg.properties start----------");
            log.info("|                                                  |");
            p.forEach((k, v) -> {
                log.info("\t\t\t{}->{}", k, v);
            });
            log.info("|                                                  |");
            log.info("----------config from cfg.properties end----------");
        } catch (Exception e) {
            log.error("load properties fail", e);
        }
    }

    public static String getProperty(String key) {
        String v = p.getProperty(key);
        return StringUtils.isBlank(v) ? "" : v;
    }

    public static void main(String[] args) {
        System.out.println("h");
    }

}
