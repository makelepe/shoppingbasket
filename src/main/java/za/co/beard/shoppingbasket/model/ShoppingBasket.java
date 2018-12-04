package za.co.beard.shoppingbasket.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.beard.shoppingbasket.service.ShoppingBasketService;

@Entity
@Table(name = "shopping_basket")
public class ShoppingBasket {
	private static final Logger log = LoggerFactory.getLogger(ShoppingBasket.class);
	@Id
    @GeneratedValue
	private Long id;
    
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_ts")
    private Date createdTS;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_ts")
    private Date lastUpdateTS;
    
	private String username;
	
	@OneToMany(/*mappedBy = "basket",*/ cascade = CascadeType.ALL)
	private Set<Item> items = new HashSet<>();
	
	@Column(name = "sub_total")
	private BigDecimal subTotal;
	
	@Column(name = "tax_perc")
	private Integer taxPercentage;
	
	@Column(name = "tax_amount")
	private BigDecimal taxAmount;
	
	private BigDecimal total;
	
	public void addItem(Item item) {

		if (items.contains(item)) {
			updateItemQuantity (item);
		} else {
			items.add(item);
		}
		
		updateTotals();
	}
	
	public void removeItem (Item item) {
		
		for (Item _item : items) {
			log.info("1. " + _item.toString() + " --- " + item.toString());
			if (_item.equals(item)) {
				// quantity more than item on list
				if (item.getQuantity() >= _item.getQuantity()) {
					log.info("item found - removing");
					items.remove(item);
				} else {
					log.info("item found - update quantity");
					updateItemQuantity (item);
				}
			}
		}
	}
	
	private void updateTotals () {
		// calculate sub-total
		subTotal = new BigDecimal(0);
		for (Item item : items) {
			subTotal = subTotal.add(item.getPrice());
		}
		
		// calculate tax
		taxAmount = new BigDecimal(0);
		if (taxPercentage != null) {
			taxAmount = subTotal.multiply(new BigDecimal(taxPercentage));
		}
		
		// calculate total
		total = new BigDecimal(0);
		total = subTotal.add(taxAmount);
		
	}
	
	private void updateItemQuantity (Item item) {
		for (Item _item : this.getItems()) {
			if (_item.equals(item)){
				_item.addQuantity(item.getQuantity());
			}
		}
		
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedTS() {
		return createdTS;
	}
	public void setCreatedTS(Date createdTS) {
		this.createdTS = createdTS;
	}
	public Date getLastUpdateTS() {
		return lastUpdateTS;
	}
	public void setLastUpdateTS(Date lastUpdateTS) {
		this.lastUpdateTS = lastUpdateTS;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	public Integer getTaxPercentage() {
		return taxPercentage;
	}


	public void setTaxPercentage(Integer taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

}
