package za.co.beard.shoppingbasket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "za.co.beard.shoppingbasket")
public class ShoppingbasketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingbasketApplication.class, args);
	}
}
