package main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.model.OrderDetail;

import java.util.List;

@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public OrderDetail getById(long orderDetailId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(OrderDetail.class, orderDetailId);
    }

    @Override
    public List<OrderDetail> getByOrderId(long orderId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderDetail where order_id = :orderId and cancel = 0", OrderDetail.class)
                .setParameter("orderId", orderId)
                .list();
    }

    @Override
    public void saveOrUpdate(OrderDetail orderDetail) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(orderDetail);
    }

    @Override
    public void delete(long orderDetailId) {
        Session session = sessionFactory.getCurrentSession();
        OrderDetail orderDetail = getById(orderDetailId);
        session.delete(orderDetail);
    }

}
