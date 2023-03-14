package main.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.OrderDetailDAO;
import main.model.OrderDetail;

import java.util.List;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Override
    public OrderDetail getById(long orderDetailId) {
        return orderDetailDAO.getById(orderDetailId);
    }

    @Override
    public List<OrderDetail> getByOrderId(long orderId) {
        return orderDetailDAO.getByOrderId(orderId);
    }

    @Override
    public void saveOrUpdate(OrderDetail orderDetail) {
        orderDetailDAO.saveOrUpdate(orderDetail);
    }

    @Override
    public void delete(long orderDetailId) {
        orderDetailDAO.delete(orderDetailId);
    }

}
