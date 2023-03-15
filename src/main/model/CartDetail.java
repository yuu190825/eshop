package main.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cart_detail")
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    // @NotBlank(message = "{cart.productId.notblank}")
    @Column(name = "cart_detail_id")
    private long cartDetailId;

    // @NotBlank(message = "{cart.productId.notblank}")
    @Column(name = "product_id")
    private long productId;

    // @NotBlank(message = "{cart.quantity.notblank}")
    @Column(name = "quantity")
    private int quantity;

    // @NotBlank(message = "{cart.name.notblank}")
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    // @NotBlank(message = "{cart.name.notblank}")
    private BigDecimal discount;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "create_date")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "update_date")
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public long getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(long cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
