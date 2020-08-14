package com.csbr.cloud.zuul.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csbr.cloud.zuul.server.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/12/26.
 */
@Component
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
