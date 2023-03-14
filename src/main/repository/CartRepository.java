package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Cart;

/**
 * 購物車 Repository
 * @author aries
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}
