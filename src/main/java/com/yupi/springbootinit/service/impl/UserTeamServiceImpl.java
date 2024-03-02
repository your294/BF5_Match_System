package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.UserTeam;
import com.yupi.springbootinit.service.UserTeamService;
import com.yupi.springbootinit.mapper.UserTeamMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2024-02-28 20:15:45
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}




