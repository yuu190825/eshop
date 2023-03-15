package main.service;

import main.model.Brand;
import main.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandRepository brandRepository;

	@Override
	public List<Brand> getAll() {
		return brandRepository.findAll();
	}

	@Override
	public Brand getById(long brandId) {
		return brandRepository.findById(brandId).orElseThrow(null);
	}

	@Override
	public void saveOrUpdate(Brand brand) {
		brandRepository.save(brand);
	}

	@Override
	public void delete(long brandId) {
		brandRepository.deleteById(brandId);
	}

	@Override
	public Brand getByName(String name) {
		return brandRepository.findByBrandDescription(name);
		//return brandRepository.findByBrandName(name);
	}

}
