package com.example.hotSpot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.hotSpot.entity.Spot;
import com.example.hotSpot.service.ISpotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TaskService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    ISpotService iSpotService;
    @PostConstruct
    public void init(){
        log.info("启动初始化 ..........");
        //初始化热点热度
        refreshHour();
        //定时1小时合并统计 天、周的排行榜。
        new Thread(()->this.refresh()).start();
    }
    //初始化新的小时热点热度
    public void refreshHour(){
        //计算当前的小时key
        long hour=System.currentTimeMillis()/(1000*60*60);
        //获取近30天的热点
        List<Spot> spots=iSpotService.list(new LambdaQueryWrapper<Spot>()
                .ge(Spot::getTime,hour-24*30));
        //初始化当前热点的小时热度值
        for(Spot spot:spots){
            this.redisTemplate.opsForZSet().incrementScore("HOUR"+hour,spot.getSpotId(),0);
            //设置下小时key 40天过期
            this.redisTemplate.expire("HOUR"+hour,40, TimeUnit.DAYS);

        }
    }

    /**
     *刷新当天的统计数据
     */
    public void refreshDay(){
        long hour=System.currentTimeMillis()/(1000*60*60);
        List<String> otherKeys=new ArrayList<>();
        //算出近24小时内的key
        for(int i=1;i<23;i++){
            String  key="HOUR"+(hour-i);
            otherKeys.add(key);
        }
        //把当前的时间key，并且把后推23个小时，共计近24小时，求出并集存入Constants.DAY_KEY中
        //redis ZUNIONSTORE 求并集
        this.redisTemplate.opsForZSet().unionAndStore("HOUR"+hour,otherKeys,"DAY");

        //设置当天的key 40天过期，不然历史数据浪费内存
        for(int i=0;i<24;i++){
            Long key=hour-i;
            this.redisTemplate.expire("HOUR"+key,40, TimeUnit.DAYS);
        }
        log.info("天刷新完成..........");
    }
    /**
     *刷新7天的统计数据
     */
    public void refreshWeek(){
        long hour=System.currentTimeMillis()/(1000*60*60);
        List<String> otherKeys=new ArrayList<>();
        //算出近7天内的key
        for(int i=1;i<24*7-1;i++){
            otherKeys.add("HOUR"+(hour-i));
        }
        //合并当前24*7小时的小时key，为周热度
        this.redisTemplate.opsForZSet().unionAndStore("HOUR"+hour,otherKeys,"WEEK");
        log.info("周刷新完成..........");
    }

    /**
     *定时1小时合并统计 天、周、月的排行榜。
     */
    public void refresh(){
        while (true){
            //刷新新的小时热点热度
            this.refreshHour();
            //刷新当天的统计数据
            this.refreshDay();
//          //刷新7天的统计数据
            this.refreshWeek();
            try {
                Thread.sleep(1000*60*60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
