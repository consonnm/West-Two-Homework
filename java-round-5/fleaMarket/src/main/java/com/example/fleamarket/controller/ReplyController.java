package com.example.fleamarket.controller;
import com.example.fleamarket.exception.ControllerException;
import com.example.fleamarket.response.ResultVo;
import com.example.fleamarket.service.IPostsService;
import com.example.fleamarket.service.IReplyService;
import com.example.fleamarket.utils.AliyunOSSUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping(value = "/reply")
public class ReplyController {
    @Autowired
    IPostsService iPostsService;
    IReplyService iReplyService;
    AliyunOSSUtil aliyunOSSUtil;
    @RequiresRoles("usr::user")
    @ApiOperation("无图片添加回复接口")
    @GetMapping("/insertReplyWithoutPic")
    public ResultVo insert1(@ApiParam("帖子id")int postId,@ApiParam("楼层")int floor,@ApiParam("卖家id")int sellManId,@ApiParam("描述")String description) {
        log.info("无图片添加回复接口");
        iReplyService.insertWithoutPic(floor,postId,sellManId,description);
        return new ResultVo().setCode(200);

    }
    @RequiresRoles("usr::user")
    @ApiOperation("有图片添加回复接口")
    @PostMapping("/insertReplyWithPic")
    public ResultVo insert2(@ApiParam("帖子id")int postId, @ApiParam("楼层")int floor, @ApiParam("卖家id")int sellManId, @ApiParam("描述")String description,
                            @ApiParam("照片") MultipartFile file) {
        log.info("有图片添加回复接口");
        String pic = aliyunOSSUtil.upload(file);
        iReplyService.insertWithPic(floor,postId,sellManId,pic,description);
        return new ResultVo().setCode(200);

    }
    @RequiresRoles("usr::user")
    @ApiOperation("删除回复接口")
    @GetMapping("/deleteReply")
    public ResultVo delete(@ApiParam("主键id")int Id) {
        log.info("删除回复接口");
        if(iReplyService.remove(Id)){
            return new ResultVo().setCode(200);
        }
        else throw new ControllerException("id不存在");

    }
}
