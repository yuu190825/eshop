package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}
