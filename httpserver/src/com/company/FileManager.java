package com.company;

import java.io.*;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
// КЕШ
public class FileManager {
    private String path;

    //секунды жизни
    private int timeLife;

    private  DateFile datefile;

    // ключ( адрес запроса, массив - содержимое файла)
    private static ConcurrentHashMap<String, DateFile> map = new ConcurrentHashMap<>();

    public FileManager(){

        this.timeLife = 60;

    }


    public FileManager(String path,int timeLife) {

        this();

        if (path.endsWith("/") || path.endsWith("\\"))
        	path = path.substring(0, path.length() - 1);

        this.path = path;
        this.timeLife = timeLife;
    }
    
    public byte[] get(String url) {
        try {

            if (map.containsKey(url)){
                     datefile = map.get(url);
                     if(new Date().getTime()<=( datefile.getDateCreate().getTime()+timeLife*1000)) {
                        return datefile.getData();
                     }

            }

                String fullPath = path + url.replace('/', '\\');
                byte[] buf;

                RandomAccessFile f = new RandomAccessFile(fullPath, "r");
                try {
                    buf = new byte[(int)f.length()];
                    f.read(buf, 0, buf.length);
                } finally {
                    f.close();
                }


                datefile = new DateFile();
                datefile.setData(buf);
                datefile.setDateCreate(new Date());

                map.put(url, datefile);
                return buf;

        } catch (IOException ex) {
            return null;
        }

 }
}
