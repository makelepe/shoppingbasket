package za.co.beard.shoppingbasket.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.beard.shoppingbasket.data.ShoppingBasketRepo;
import za.co.beard.shoppingbasket.model.Item;
import za.co.beard.shoppingbasket.model.ShoppingBasket;

@Service
public class ShoppingBasketService {
	private static final Logger log = LoggerFactory.getLogger(ShoppingBasketService.class);
	
	@Autowired
	private ShoppingBasketRepo repo;
	
	public ShoppingBasket getBasket (String username) {
		return repo.findByUsername(username);
	}
	
	
	/**
	 * add new item to basket
	 * 
	 * @param username
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public ShoppingBasket addToBasket (String username, Item item, Integer taxPerc) throws Exception {
		
		if (username == null ) {
			throw new Exception("Username cannot be empty.");
		}

		if (item == null ) {
			throw new Exception("Item cannot be empty.");
		}
		
		ShoppingBasket basket = repo.findByUsername(username);
		log.info("basket : " + basket);

		if (basket == null) {
			basket = new ShoppingBasket();
			basket.setUsername(username);
			basket.setCreatedTS(new Date());
			basket.setLastUpdateTS(new Date());
		}
		
		log.info("basket items(before) : " + basket.getItems());
		
		basket.setTaxPercentage(taxPerc);
		basket.addItem(item);
		
		log.info("basket items(after) : " + basket.getItems());

		repo.save(basket);
		
		// link item to basket (NB: for some reason JPA is failing to set the ID)
		item.setBasketId(basket.getId());
		repo.save(basket);
		
		return basket;
	}
	
	/**
	 * remove item or update item quantity from basket
	 * 
	 * @param username to used to retrieve basket
	 * @param item to be removed
	 * @return
	 * @throws Exception 
	 */
	public ShoppingBasket removeFromBasket(String username, Item item) throws Exception {
		if (username == null ) {
			throw new Exception("Username cannot be empty.");
		}

		if (item == null ) {
			throw new Exception("Item cannot be empty.");
		}
		
		ShoppingBasket basket = repo.findByUsername(username);
		log.info("basket : " + basket);

		if (basket != null) {
			log.info("basket items(before) : " + basket.getItems());
			basket.removeItem(item);
			log.info("basket items(after) : " + basket.getItems());

			basket.setLastUpdateTS(new Date());
			repo.save(basket);
		}
		
		return basket;
	}

	
}
