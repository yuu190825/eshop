package main.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private long orderDetailId;

    @Column(name = "product_id")
    private long productId;

    @Min(value = 0, message = "{detail.price}")
    @Column(name = "order_price")
    private BigDecimal orderPrice = new BigDecimal("0");

    @Min(value = 1, message = "{detail.quantity}")
    @Column(name = "order_quantity")
    private long orderQuantity = 1;

    @Min(value = 0, message = "{detail.discount}")
    @Column(name = "discount")
    private BigDecimal discount = new BigDecimal("0");

    @Column(name = "update_date")
    private Date updateDate = Date.from(Instant.now());

    @Column(name = "cancel")
    private boolean cancel = false;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice.setScale(0, RoundingMode.DOWN);
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public long getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(long orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getDiscount() {
        return discount.setScale(0, RoundingMode.DOWN);
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

}
