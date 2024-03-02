package com.yupi.springbootinit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.model.dto.team.*;
import com.yupi.springbootinit.model.entity.Team;
import com.yupi.springbootinit.model.entity.User;
import com.yupi.springbootinit.model.entity.UserTeam;
import com.yupi.springbootinit.model.vo.TeamUserVO;
import com.yupi.springbootinit.model.vo.TeamVO;
import com.yupi.springbootinit.service.TeamService;
import com.yupi.springbootinit.service.UserService;
import com.yupi.springbootinit.service.UserTeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
@Slf4j
public class TeamController {
    @Resource
    private UserService userService;

    @Resource
    private TeamService teamService;

    @Resource
    private UserTeamService userTeamService;

    @PostMapping("/add")
    public BaseResponse<Long> addTeam(@RequestBody TeamAddRequest teamAddRequest, HttpServletRequest request) {
        if (teamAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        User loginUser = userService.getLoginUser(request);
        Team team = new Team();
        BeanUtils.copyProperties(teamAddRequest, team);
        long teamId = teamService.addTeam(team, loginUser);
        return ResultUtils.success(teamId);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTeam(@RequestBody TeamDeleteRequest teamDeleteRequest, HttpServletRequest request) {
        if (teamDeleteRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        boolean remove = teamService.removeById(teamDeleteRequest.getId());
        if (!remove) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "删除队伍失败");
        }
        return ResultUtils.success(remove);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateTeam(@RequestBody TeamUpdateRequest teamUpdateRequest, HttpServletRequest request) {
        if (teamUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        Team team = new Team();
        BeanUtils.copyProperties(teamUpdateRequest, team);
        boolean update = teamService.updateById(team);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "更新队伍失败");
        }
        return ResultUtils.success(update);
    }

    @PostMapping("/join")
    public BaseResponse<Boolean> joinTeam(@RequestBody TeamJoinRequest teamJoinRequest, HttpServletRequest request) {
        if (teamJoinRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        User loginUser = userService.getLoginUser(request);
        boolean result = teamService.joinTeam(teamJoinRequest, loginUser);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "加入队伍失败");
        }
        return ResultUtils.success(result);
    }

    @PostMapping("/quit")
    public BaseResponse<Boolean> quitTeam(@RequestBody TeamQuitRequest teamQuitRequest, HttpServletRequest request) {
        if (teamQuitRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        boolean result = teamService.quitTeam(teamQuitRequest, loginUser);
        return ResultUtils.success(result);

    }

    @GetMapping("/get")
    public BaseResponse<Team> getTeamById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        Team team = teamService.getById(id);
        if (team == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "查无此队伍");
        }
        return ResultUtils.success(team);
    }

    @PostMapping("/search/team")
    public BaseResponse<List<TeamVO>> searchTeamByConditions(@RequestBody TeamQueryRequest teamQueryRequest, HttpServletRequest request) {
        QueryWrapper<Team> queryWrapper = teamService.getQueryWrapper(teamQueryRequest);
        List<Team> teamList = teamService.list(queryWrapper);
        List<TeamVO> teamVOList = teamList.stream().map((team) -> {
            TeamVO teamVO = new TeamVO();
            BeanUtils.copyProperties(team, teamVO);
            return teamVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(teamVOList);
    }

    @PostMapping("/list/vo")
    public BaseResponse<List<TeamUserVO>> listAllTeam(@RequestBody TeamQueryRequest teamQueryRequest, HttpServletRequest request) {
        if (teamQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        List<TeamUserVO> teamUserVOList = teamService.listTeams(teamQueryRequest, true);
        User loginUser = userService.getLoginUser(request);
        for (TeamUserVO vo : teamUserVOList) {
            long teamId = vo.getId();
            QueryWrapper<UserTeam> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("teamId", teamId);
            queryWrapper.eq("userId", loginUser.getId());
            long num = userTeamService.count(queryWrapper);
            if (num != 0L) {
                vo.setHasJoin(true);
            }
        }
        return ResultUtils.success(teamUserVOList);
    }

    @PostMapping("/list/page/vo")
    public BaseResponse<Page<TeamVO>> listTeamByPage(@RequestBody TeamQueryRequest teamQueryRequest, HttpServletRequest request) {
        if (teamQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        long current = teamQueryRequest.getCurrent();
        long pageSize = teamQueryRequest.getPageSize();
        if (pageSize > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求页数太多");
        }
        Page<Team> teamPage = teamService.page(new Page<>(current, pageSize),
                teamService.getQueryWrapper(teamQueryRequest));
        Page<TeamVO> teamVOPage = new Page<>(current, pageSize, teamPage.getTotal());
        List<Team> teamList = teamPage.getRecords();
        List<TeamVO> teamVOList = teamService.getTeamVO(teamList);
        teamVOPage.setRecords(teamVOList);
        return ResultUtils.success(teamVOPage);
    }

    @GetMapping("/list/my/team")
    public BaseResponse<List<TeamUserVO>> listMyTeam(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        TeamQueryRequest teamQueryRequest = new TeamQueryRequest();
        teamQueryRequest.setUserId(loginUser.getId());
        List<TeamUserVO> teamUserVOList = teamService.listTeams(teamQueryRequest, true);
        return ResultUtils.success(teamUserVOList);
    }
}
