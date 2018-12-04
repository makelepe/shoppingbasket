package za.co.beard.shoppingbasket.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {
	@Id
    @GeneratedValue
	private Long id;
	private Integer quantity;
	private String name;
	private BigDecimal price;
	
	/*@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="basket_id")
	private ShoppingBasket basket;
	*/
	
	@Column(name="basket_id")
	private Long basketId;
	
	
	public void addQuantity (Integer quantity) {
		this.quantity += quantity;
	}
	
	public void subtractQuantity(Integer quantity) {
		this.quantity -= quantity; 
	}
	
	@Override
	public String toString() {
		return this.getQuantity() + " - " + this.getName() + " - " + this.getPrice();
	} 
	
	@Override
	public boolean equals (Object obj) {
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof Item)) {
			return false;
		}
		Item other = (Item)obj;
		
		return other.hashCode() == this.hashCode();
	}
	

	@Override
	public int hashCode() {
		String _name = this.getName() != null ? this.getName() : "";
		//String _price = this.getPrice() != null ? this.getPrice().toString() : "0";
		
	    return _name.hashCode();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
/*
	public ShoppingBasket getBasket() {
		return basket;
	}

	public void setBasket(ShoppingBasket basket) {
		this.basket = basket;
	}

*/

	public Long getBasketId() {
		return basketId;
	}

	public void setBasketId(Long basketId) {
		this.basketId = basketId;
	}

}
