package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.mapper.TagMapper;
import com.yupi.springbootinit.model.entity.Tag;

import com.yupi.springbootinit.service.TagService;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2024-02-26 16:53:23
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

}




