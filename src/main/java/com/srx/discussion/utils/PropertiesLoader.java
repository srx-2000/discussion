package com.srx.discussion.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author srx
 * @description
 * @create 2020-08-24 16:56:41
 */
public class PropertiesLoader {
    private String propertiesName;
    private Properties properties;

    public PropertiesLoader(String propertiesName) {
        this.propertiesName = propertiesName;
        this.loadProperties();
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }


    private void loadProperties() {
        try {
            properties = new Properties();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesName);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            properties.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getValue(String key) {
        return this.properties.getProperty(key);
    }
}
