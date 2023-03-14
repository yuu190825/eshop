package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.model.Brand;

/**
 * 廠牌 Repository
 * @author aries
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{

	// public Brand findByBrandName(String name);
	public Brand findByBrandDescription(String name);

	//	改 left join, 才能在購物車尚未有購買項目的時候(CartDetail == null)的時候，也能被撈出來
//	@Query("from Cart c left join fetch c.CartDetail where c.customerId = :customerId")
//	public Cart getByCustomerId(@Param("customerId") long customerId);
	@Query("from Brand b left join fetch b.products where b.id = :brandId")
	public Brand findByIdWithBrandId(@Param("brandId") long brandId);

}
