package main.service;

import java.util.List;

import main.model.Product;

public interface ProductService {

	public List<Product> getAll();

	public Product getById(long productId);

	public void saveOrUpdate(Product product);

	public void delete(long productId);

	public List<Product> getAllWithImage();

	public Product getByIdWithImage(long id);

}
