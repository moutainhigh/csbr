package com.csbr.cloud.hy.server.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtils<T> {

    private List<T> result;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public PageUtils(){}

    public PageUtils(List<T> result) {
        this.result = result;
    }

    public Map<String, Object> build(IPage<Map<String, Object>> page) {
//        PageInfo page = new PageInfo(this.result);
//
//        //返回值封装
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        resultMap.put("totalRows", page.getTotal());
//        resultMap.put("totalPages", page.getPages());
//        resultMap.put("pageSize", page.getPageSize());
//        resultMap.put("pageNum", page.getPageNum());
//        resultMap.put("list", result);
//        //返回值封装
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("totalRows", page.getTotal());//当前满足条件总行数
        resultMap.put("totalPages", page.getPages());//分页总页数
        resultMap.put("pageSize", page.getSize());//当前分页总页数
        resultMap.put("pageNum", page.getCurrent());//当前页
        resultMap.put("list", page.getRecords());
        return resultMap;
    }

}
