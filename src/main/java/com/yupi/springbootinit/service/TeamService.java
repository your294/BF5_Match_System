package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.springbootinit.model.dto.team.TeamJoinRequest;
import com.yupi.springbootinit.model.dto.team.TeamQueryRequest;
import com.yupi.springbootinit.model.dto.team.TeamQuitRequest;
import com.yupi.springbootinit.model.dto.user.UserQueryRequest;
import com.yupi.springbootinit.model.entity.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.vo.TeamUserVO;
import com.yupi.springbootinit.model.vo.TeamVO;

import java.util.List;

/**
* @author ASUS
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2024-02-28 20:11:17
*/
public interface TeamService extends IService<Team> {

    /**
     * 数据库增加队伍
     * @param team
     * @param loginUser
     * @return
     */
    Long addTeam(Team team, User loginUser);


    List<TeamUserVO> listTeams(TeamQueryRequest teamQuery, boolean isAdmin);


    /**
     * 加入队伍，这里需要考虑多线程
     * todo: 考虑多线程, 明天完成
     * @param teamJoinRequest
     * @param loginUser
     * @return
     */
    Boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);


    long countTeamUserByTeamId(Long teamId);

    /**
     * 获取查询条件
     *
     * @param teamQueryRequest
     * @return
     */
    QueryWrapper<Team> getQueryWrapper(TeamQueryRequest teamQueryRequest);


    TeamVO getTeamVO(Team team);

    List<TeamVO> getTeamVO(List<Team> teamList);

    /**
     * 退出队伍
     * @param teamQuitRequest
     * @param loginUser
     * @return
     */
    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);
}
