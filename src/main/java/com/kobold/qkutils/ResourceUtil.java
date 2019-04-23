package com.kobold.qkutils;

import java.io.InputStream;

public class ResourceUtil {
    public static InputStream getResource(String path){
        ClassLoader classLoader=ResourceUtil.class.getClassLoader();
        InputStream resource=classLoader.getResourceAsStream(path);
        if(resource==null)
            throw new RuntimeException("文件不存在");
        return resource;
    }
}
