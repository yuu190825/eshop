package main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.model.Order;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Order> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Order where cancel = 0", Order.class).list();
	}

	@Override
	public Order getById(long orderId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Order.class, orderId);
	}

	@Override
	public void saveOrUpdate(Order order) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(order);
	}

	@Override
	public void delete(long orderId) {
		Session session = sessionFactory.getCurrentSession();
		Order order = getById(orderId);
		session.delete(order);
	}

}
