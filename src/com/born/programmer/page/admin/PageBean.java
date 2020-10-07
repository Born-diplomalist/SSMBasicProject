package com.born.programmer.page.admin;

import java.util.List;

/**
 * @Description: 分页工具类
 * @Since: jdk1.8
 * @Author: gyk
 * @Date: 2020-01-02 14:41:56
 */
public class PageBean<T> {
    /**
     * 存放需要显示的实体类数据的字段名，用于遍历标题使用
     * 该属性可选，如果标题是前台写死的就不需要传了，如果追求前后端分离，使用该属性可以让前端页面表格动态生成，更灵活
     * 
     */
    private List<String> titleList;

    /**
     * 要展示的实体类数据
     */
    private List<T> dataList;

    /**
     * 步长 每页显示的行数
     */
    private Integer pageSize;

    /**
     * 当前页码数（默认给1）
      */
    private Integer pageNo = 1;

    /**
     * 当前页起始下标
     */
    private Integer pageStartIndex=0;

    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总行数
     */
    private Integer rows;

    /**
     * 使用空参构造器后，记得设定各个参数并调用页数、页码的设定函数
     */
    public PageBean() {
    }

    /**
     * 懒人构造器，自动初始化各个内容，不初始化标题列表
     */
    public PageBean(List<T> dataList, Integer pageNo, Integer pageSize, Integer rows) {
        this.dataList = dataList;
        this.pageSize = pageSize;
        this.rows = rows;
        setRows(rows);
        setPageNo(pageNo);
        setPageStartIndex();
    }

    /**
     * 升级版构造器，自动初始化各个内容并可以初始化标题列表
     */
    public PageBean(List<String> titleList,List<T> dataList, Integer pageNo, Integer pageSize, Integer rows){
        this(dataList, pageNo, pageSize, rows);
        setTitleList(titleList);
    }


    //对私有属性的封装
    // 不需要对外提供totalPage总页数的set方法   因为totalPage是根据  总行数  和  每页显示的行数求出来的
    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageStartIndex() {
        return pageStartIndex;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getRows() {
        return rows;
    }

    //设置起始下标 当前页起始下标由当前页数和步长算得
    public void setPageStartIndex() {
        this.pageStartIndex = (pageNo-1)*pageSize;
    }

    //设置总数据个数 并求出页数
    public void setRows(Integer rows) {
        this.rows = rows;
        //页数      根据传入的 总行数 以及 每页显示的行数 求出总页数
        this.totalPage=rows % pageSize==0 ? rows/pageSize : (rows/pageSize+1);
    }

    //设置页码
    public void setPageNo(Integer pageNo) {
        //如果传入的页码为空 或者小于0  就默认给 1
        if (null == pageNo || pageNo < 0)
            this.pageNo = 1;
            //如果当前页码数>总页码数    就让 当前页码数 等于 最大页码数
        else if (pageNo > this.totalPage && this.totalPage> 0)
            this.pageNo = this.totalPage;
            //都符合条件 就让 当前页码数 等于 传入的页码数
        else
            this.pageNo = pageNo;
    }


}