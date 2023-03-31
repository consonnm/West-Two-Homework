package com.example.hotSpot.controller;

import com.example.hotSpot.response.ResultVo;
import com.example.hotSpot.service.IJudgeService;
import com.example.hotSpot.service.IVoteService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/vote")
public class VoteController {
    @Autowired
    IVoteService iVoteService;
    @Autowired
    IJudgeService iJudgeService;
    //@RequiresRoles("user::user")
    @ApiOperation("增加投票")
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id"),
            @ApiImplicitParam(name="id",value="庭审Id"),
            @ApiImplicitParam(name="flag",value="1为正方，0 为反方"),
    })
    public ResultVo insert(int userId,int id,int flag){
        log.info("增加投票");
        if(iVoteService.find(userId,id)) {
            return new ResultVo().setMessage("已投过票");
        }
        iJudgeService.change(flag,id);
        return new ResultVo().setCode(200).setData(iVoteService.insert(userId,id));
    }
}
