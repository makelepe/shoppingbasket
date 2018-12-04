package za.co.beard.shoppingbasket.controller;

import za.co.beard.shoppingbasket.model.Item;
import za.co.beard.shoppingbasket.model.ShoppingBasket;
import za.co.beard.shoppingbasket.service.ShoppingBasketService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(path = "/shopping", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingBasketController {
	private static final Logger log = LoggerFactory.getLogger(ShoppingBasketController.class);
	
	@Autowired
	private ShoppingBasketService service;
	
	@GetMapping("basket/ping/{name}")
    public String welcomeUser(@PathVariable(name="name", required=false) String name) {
		log.info("ping controller : " + name);
        return String.format("Welcome Mr/Mrs. %s!", name);
    }
	
	@GetMapping ("basket/{username}")
	public ShoppingBasket getBasket (@PathVariable(name="username", required=true) String username) {
		log.info("reading item from basket!");
		return service.getBasket(username);
	}
	
	@PostMapping("basket/{username}")
	public ShoppingBasket addToBasket (@PathVariable(name="username", required=true) String username, 
										@RequestBody(required=true) Item item, 
										@RequestParam(name="tax", required=false) Integer taxPercentage) throws Exception {
		log.info("Adding new item to basket - " + item.toString() + " - username " + username);
		return service.addToBasket(username, item, taxPercentage);
	}
	
	@DeleteMapping("basket/{username}")
	public ShoppingBasket removeFromBasket(@PathVariable(name="username", required=true) String username, 
											@RequestBody(required=true) Item item)  throws Exception{
		log.info("removing item from basket!");
		return service.removeFromBasket(username, item);
	}

}
