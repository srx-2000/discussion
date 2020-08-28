package com.srx.discussion.util;

import com.srx.discussion.utils.PropertiesLoader;
import org.junit.Test;

/**
 * @author srx
 * @description
 * @create 2020-08-24 19:10:44
 */
public class PropertiesLoaderTest {
    PropertiesLoader loader=new PropertiesLoader("message.properties");
    @Test
    public void Test(){
        Object value = loader.getValue("errorMessage.error1");
        System.out.println(value);
    }

}
