package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Product;

/**
 * 產品 Repository
 * 
 * @author aries
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@EntityGraph(attributePaths = { "productImage" })
	List<Product> findByIdIsNotNull();

	//改 left join, 才能在購物車尚未有購買項目的時候(CartDetail == null)的時候，也能被撈出來
//	@Query("from Cart c left join fetch c.CartDetail where c.customerId = :customerId")
//	public Cart getByCustomerId(@Param("customerId") long customerId);
//	@Query("from Product p left join fetch p.Brand where p.id = :id")
//	public Product findByIdWithBrandId(@Param("productId") long productId);

}
