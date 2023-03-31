package com.example.ibookreader.utils;

import com.example.ibookreader.service.IGetContextService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
@Component
public class GetChapter {
    @Autowired
    private IGetContextService iGetContextService;
    public void chapterGet(String bookName, String bookChapter,String url) throws IOException {
        Document document = Jsoup.parse(new URL(url),300000);
        Elements element = document.select("#content");
        String bookContext =element.text();
        try{
            iGetContextService.insertBook(bookName,bookChapter,bookContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
