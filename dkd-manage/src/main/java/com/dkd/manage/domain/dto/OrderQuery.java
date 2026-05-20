package com.dkd.manage.domain.dto;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 订单查询参数
 */
public class OrderQuery {

    /** 分页页码 */
    private Integer pageNum;

    /** 每页数量 */
    private Integer pageSize;

    /** 订单编号 */
    private String orderNo;

    /** 日期范围，前端传 createTime[0] 和 createTime[1] */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private List<LocalDate> createTime;

    // ========= getter / setter =========
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public List<LocalDate> getCreateTime() {
        return createTime;
    }

    public void setCreateTime(List<LocalDate> createTime) {
        this.createTime = createTime;
    }
}