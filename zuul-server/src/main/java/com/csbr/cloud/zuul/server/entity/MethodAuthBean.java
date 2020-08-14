package com.csbr.cloud.zuul.server.entity;

import com.csbr.cloud.zuul.server.annotation.Logical;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhangheng
 * @date 2020/7/1 13:26
 */
@Data
public class MethodAuthBean {

    /**
     * get，post，put，delete.一个接口，可以有多个，譬如可以通知接收get和post
     */
    private Set<String> actions;
    /**
     * url=/role/{id}, url={/index, /""} ，可以是多个
     */
    private Set<String> urls = new HashSet<>();
    /**
     * 需要的角色，可以是多个
     */
    private Set<String> roles = new HashSet<>();
    /**
     * 角色之间的关系，and， or
     */
    private Logical rolesLogical;
    /**
     * 需要匹配的权限，如menu:add
     */
//    private Set<String> codes = new HashSet<>();
    /**
     * 权限之间的关系，and， or
     */
    private Logical codesLogical;

}
