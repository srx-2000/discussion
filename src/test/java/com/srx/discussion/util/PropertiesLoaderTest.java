package com.srx.discussion.util;

import com.srx.discussion.utils.PropertiesLoader;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author srx
 * @description
 * @create 2020-08-24 19:10:44
 */
public class PropertiesLoaderTest {
    PropertiesLoader loader=new PropertiesLoader("message.properties");
    @Test
    public void Test(){
        Object value = loader.getValue("errorMessage.login");
        Map<String,Object> map=new HashMap<>();
        System.out.println(value);
        map.put("string",loader.getValue("errorMessage.login"));
        System.out.println(map);
    }

}
