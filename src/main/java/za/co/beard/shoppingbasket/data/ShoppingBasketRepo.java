package za.co.beard.shoppingbasket.data;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.beard.shoppingbasket.model.ShoppingBasket;

public interface ShoppingBasketRepo extends JpaRepository<ShoppingBasket, Long>{
	public ShoppingBasket findByUsername(String username);
}
