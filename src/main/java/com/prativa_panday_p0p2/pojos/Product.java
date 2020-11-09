package com.prativa_panday_p0p2.pojos;

import java.util.Date;

/*
 * The is a model class to hold information about the product.
 * It includes product category, name and other different features of product.
 * */

public class Product {
	
	public enum ProductCategory{
		FOOD,
		BEVERAGES,
		PERSONAL_CARE,
		ELECTRONICS,
		HOME_DECOR
	}
	
	private String productId;
	
	private String productName;
	
	private double costPrice;
	
	private double sellPrice;
	
	private int productQuantity;
			
	private String productQuality;
	
	private ProductCategory productCategory;
	
	public Product() {
		super();
	}
	
	public Product(ProductCategory productCat, String productName,  String productQuality) {
		super();
		this.productId = productName + Double.toString(Math.random() * 100) ;
		this.setProductCategory(productCat);
		this.productName = productName;
		this.productQuality = productQuality;
	}
	
	public Product(ProductCategory productCat, String productName, double costPrice, double sellPrice, int quantity) {
		super();
		this.setProductCategory(productCat);
		this.productName = productName;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.productQuantity = quantity;
//		this.expiryDate = expiryDate;
	}


	public String getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	
	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	
	public String getProductQuality() {
		return productQuality;
	}

	public void setProductQuality(String productQuality) {
		this.productQuality = productQuality;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	

}
