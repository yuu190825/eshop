package main.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Product;
import main.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getById(long productId) {
		return productRepository.findById(productId).orElseGet(null);
	}

	@Override
	public void saveOrUpdate(Product product) {
		Date dateTimeDate = new Date();
		if (product.getProductId()!= 0) {
			product.setUpdateDate(dateTimeDate);
		}else {
			product.setCreateDate(dateTimeDate);
			product.getBrand().setCreateDate(dateTimeDate);
		}
		productRepository.save(product);
	}

	@Override
	public void delete(long productId) {
		productRepository.deleteById(productId);
	}

	@Override
	public List<Product> getAllWithImage() {
		return productRepository.findByIdIsNotNull();
	}

	@Override
	public Product getByIdWithImage(long id) {
		return productRepository.findById(id).orElseGet(null);
	}

}
