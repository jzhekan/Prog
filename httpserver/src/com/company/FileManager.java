package com.company;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
// КЕШ
public class FileManager {
    private String path;

    // ключ( адрес запроса, массив - содержимое файла)
    private static ConcurrentHashMap<String, byte[]> map = new ConcurrentHashMap<String, byte[]>();
    
    public FileManager(String path) {
        if (path.endsWith("/") || path.endsWith("\\"))
        	path = path.substring(0, path.length() - 1);
        
        this.path = path;
    }
    
    public byte[] get(String url) {
        try {
            if (map.containsKey(url)) {
                return map.get(url);
            } else {
                String fullPath = path + url.replace('/', '\\');
                byte[] buf;

                RandomAccessFile f = new RandomAccessFile(fullPath, "r");
                try {
                    buf = new byte[(int)f.length()];
                    f.read(buf, 0, buf.length);
                } finally {
                    f.close();
                }
                
                map.put(url, buf);
                
                return buf;
            }
        } catch (IOException ex) {
            return null;
        }
    }
}
