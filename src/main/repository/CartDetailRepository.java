package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.CartDetail;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

//	@Query("from CartDetail c where c.cart.cartId = :cart.cartId")
//    public List<CartDetail> getByCartId(@Param("cart.cartId") long cartId);
}
