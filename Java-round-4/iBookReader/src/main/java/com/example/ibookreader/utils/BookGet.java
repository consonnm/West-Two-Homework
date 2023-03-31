package com.example.ibookreader.utils;

import com.example.ibookreader.service.IGetBookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
@Component
public class BookGet {
    @Autowired
    private IGetBookService iGetBookService;
    @Autowired
    private GetChapter getChapter;
    public void getBook(String bookName,String url) throws IOException {
        Document document = Jsoup.parse(new URL(url),300000);
        Elements element = document.select("#fmimg img");
        Elements element1 =document.select("#info p");
        Elements element2 =document.select("#intro p");
        String bookPhoto = element.get(0).absUrl("src");
        String bookAuthor =element1.get(0).text();
        String bookIntroduce =element2.get(1).text();
        try{
            iGetBookService.insertBook(bookName,bookAuthor,bookIntroduce,bookPhoto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(bookAuthor);
        Elements element3 = document.select("#list a");
        for(Element a:element3){
            String bookChapter =a.text();
            String url1 =a.absUrl("href");
            System.out.println(bookChapter);
            getChapter.chapterGet(bookName,bookChapter,url1);
        }
        return;

    }

}
