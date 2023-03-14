package main.service;

import java.util.List;

import main.model.Cart;

public interface CartService {

	public List<Cart> getAll();
	
	public Cart getById(long cartId);
	
	public void saveOrUpdate(Cart cart);
	
	public void delete(long cartId);

    public Cart getByCustomerId(long customerId);

    public void addProduct2cart(long productId, int quantity);
}
