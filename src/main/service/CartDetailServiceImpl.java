package main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.CartDetail;
import main.repository.CartDetailRepository;

@Service
@Transactional
public class CartDetailServiceImpl implements CartDetailService {

	@Autowired
	private CartDetailRepository cartDetailRepository;
	
	@Override
	public List<CartDetail> getAll() {
		return cartDetailRepository.findAll();
	}

	@Override
	public CartDetail getById(long cartDetailId) {
		return cartDetailRepository.getOne(cartDetailId);
	}

	@Override
	public void saveOrUpdate(CartDetail cartDetail) {
		cartDetailRepository.save(cartDetail);
	}

	@Override
	public void delete(long cartDetailId) {
		cartDetailRepository.deleteById(cartDetailId);
	}

}
