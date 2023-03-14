package main.model;

import java.math.BigDecimal;

public class CartForm {

    private long productId;

    private String productName;

    private ProductImage productImage;
//  原來是private String productImage;

    private BigDecimal discount;

    private String productDescription;

    private BigDecimal productPrice;

    private int quantity;

    public CartForm() {
        super();
    }

    public CartForm(long productId, String productName, ProductImage productImage, BigDecimal discount,
            String productDescription, BigDecimal productPrice, int quantity) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.discount = discount;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
