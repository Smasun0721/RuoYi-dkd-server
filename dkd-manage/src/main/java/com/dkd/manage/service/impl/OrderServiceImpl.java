package com.dkd.manage.service.impl;

import java.time.LocalDate;
import java.util.List;
import com.dkd.common.utils.DateUtils;
import com.dkd.manage.domain.dto.OrderQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dkd.manage.mapper.OrderMapper;
import com.dkd.manage.domain.Order;
import com.dkd.manage.service.IOrderService;

/**
 * 订单管理Service业务层处理
 * 
 * @author Smasun
 * @date 2026-05-19
 */
@Service
public class OrderServiceImpl implements IOrderService 
{
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询订单管理
     * 
     * @param id 订单管理主键
     * @return 订单管理
     */
    @Override
    public Order selectOrderById(Long id)
    {
        return orderMapper.selectOrderById(id);
    }

    /**
     * 查询订单管理列表
     * 
     * @param order 订单管理
     * @return 订单管理
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    @Override
    public int insertOrder(Order order)
    {
        order.setCreateTime(DateUtils.getNowDate());
        return orderMapper.insertOrder(order);
    }

    /**
     * 修改订单管理
     * 
     * @param order 订单管理
     * @return 结果
     */
    @Override
    public int updateOrder(Order order)
    {
        order.setUpdateTime(DateUtils.getNowDate());
        return orderMapper.updateOrder(order);
    }

    /**
     * 批量删除订单管理
     * 
     * @param ids 需要删除的订单管理主键
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(Long[] ids)
    {
        return orderMapper.deleteOrderByIds(ids);
    }

    /**
     * 删除订单管理信息
     * 
     * @param id 订单管理主键
     * @return 结果
     */
    @Override
    public int deleteOrderById(Long id)
    {
        return orderMapper.deleteOrderById(id);
    }

    @Override
    public List<Order> selectOrderListByQuery(OrderQuery query) {
        // 将 DTO 中的查询条件转换为实体查询对象（只用于传参）
        Order searchOrder = new Order();
        searchOrder.setOrderNo(query.getOrderNo());

        // 处理日期范围：转换为 beginCreateTime / endCreateTime
        if (query.getCreateTime() != null && query.getCreateTime().size() == 2) {
            LocalDate begin = query.getCreateTime().get(0);
            LocalDate end   = query.getCreateTime().get(1);
            searchOrder.setBeginCreateTime(begin);
            searchOrder.setEndCreateTime(end);
        }

        return orderMapper.selectOrderListByCondition(searchOrder);
    }
}
