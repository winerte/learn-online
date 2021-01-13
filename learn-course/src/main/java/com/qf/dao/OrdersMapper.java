package com.qf.dao;

import com.qf.pojo.vo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: yanjia
 * @DATE: 2021/1/12 20:09
 */
@Mapper
public interface OrdersMapper {
    void insertOrders(Orders orders) ;

    Orders findByTransferId(@Param("transferId") String out_trade_no);

    void updateById(Orders byTransferId);
}
