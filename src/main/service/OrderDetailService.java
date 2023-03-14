package main.service;

import main.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    public OrderDetail getById(long orderDetailId);

    public List<OrderDetail> getByOrderId(long orderId);

    public void saveOrUpdate(OrderDetail orderDetail);

    public void delete(long orderDetailId);

}
