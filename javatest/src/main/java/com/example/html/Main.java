package com.example.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by zhaoningqiang on 16/7/3.
 */

public class Main {
    public static void main(String args[]) throws IOException {
        Document document = Jsoup.connect("http://tcpm.mrlazyinc.com/files/users/themafia/te/beauty/").get();
        Elements a = document.getElementsByTag("a");
        for (int i = 0;i<a.size();i++){
            Element element = a.get(i);
            String href = element.attr("href");

           // "http://tcpm.mrlazyinc.com/files/users/themafia/te/beauty"+href;
            //System.out.println(href);
        }
    }
}
