package com.company;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IVoitsekhovskyi on 03.04.2015.
 */
public class ParserURL {

    private String protokol;
    private String host;
    private String port;
    private String page;
    private HashMap<String,String> mapParametrs;

    String parse(String url){

        String regex = "([^/:?]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        int number = 0;
        while (matcher.find()) {

            if (number == 0)  protokol = matcher.group();
            if (number == 1)  host = matcher.group();
            if (number == 2)  port = matcher.group();
            if (number == 3)  page = matcher.group();

            if (number == 4) {
                Pattern p1 = Pattern.compile("([\\w]+)=([\\w]+)");
                Matcher m = p1.matcher(matcher.group());

                mapParametrs = new HashMap<>();

                while(m.find()){
                    Pattern p2 = Pattern.compile("\\=");
                    String[] words = p2.split(m.group());

                    mapParametrs.put(words[0],words[1]);
                }

            }

            number++;

        }

       return toString();
    }

    public static void main(String[] args) {
        System.out.println(new ParserURL().parse("http://server.com.ua:8080/index.html?awe=1&bd=7&bds=7"));
    }

    @Override
    public String toString() {
        return "ParserURL{" +
                "protokol='" + protokol + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", page='" + page + '\'' +
                ", mapParametrs=" + mapParametrs +
                '}';
    }
}
