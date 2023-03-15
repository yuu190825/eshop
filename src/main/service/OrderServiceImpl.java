package main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dao.OrderDAO;
import main.model.Order;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Override
	public List<Order> getAll() {
		return orderDAO.getAll();
	}

	@Override
	public Order getById(long orderId) {
		return orderDAO.getById(orderId);
	}

	@Override
	public void saveOrUpdate(Order order) {
		orderDAO.saveOrUpdate(order);
	}

	@Override
	public void delete(long orderId) {
		orderDAO.delete(orderId);
	}

}
