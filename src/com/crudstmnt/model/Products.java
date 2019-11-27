package com.crudstmnt.model;

public class Products {
	private int idProduct;
	private String nameProduct;
	private double priceProduct;
	
	public Products() {}
	public Products(int idProduct, String nameProduct, double priceProduct)
	{
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.priceProduct = priceProduct;
	}
	public int getIdProduct()
	{
		return this.idProduct;
	}
	public void setIdProduct(int idProduct)
	{
		this.idProduct = idProduct;
	}
	public String getNameProduct()
	{
		return this.nameProduct;
	}
	public void setNameProduct(String nameProduct)
	{
		this.nameProduct = nameProduct;
	}
	public double getPriceProduct()
	{
		return this.priceProduct;
	}
	public void setPriceProduct(double priceProduct)
	{
		this.priceProduct = priceProduct;
	}
}
