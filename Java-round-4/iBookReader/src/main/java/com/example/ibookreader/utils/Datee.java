package com.example.ibookreader.utils;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
@Component
public class Datee {
        @Autowired
        private BookGet bookGet;
        @SneakyThrows
        public void get() {
            String url1 = "https://www.xbiquge.la/xiaoshuodaquan/";
            Document document = Jsoup.parse(new URL(url1),300000);
            Elements element = document.select("#main a");
            for(Element a:element){
                String bookName = a.text();
                System.out.println(bookName);
                String url2 = a.absUrl("href");
                bookGet.getBook(bookName,url2);
            }
        }
}
