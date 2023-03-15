package main.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
	private long productId;

    @Column(name = "product_category")
    private String productCategory;

	@Column(name = "product_name",length = 100)
    @NotBlank(message = "{product.name.notblank}")
    @Size(min = 2, message = "{product.name.size}")
    private String productName;

	//@JoinColumn(name = "product_image_id")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Column(name = "product_image")
	private ProductImage productImage;

	@Column(name = "product_description")
    private String productDescription;

	@NotNull(message = "{product.price.notnull}")
	@DecimalMin(value="0", message = "{product.price.min}")
	@Column(name = "product_price")
	private BigDecimal productPrice;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id")// 物件"brand"加底限"_"加物件欄位 "id"(PS:id欄位名稱)
	private Brand brand;

	@Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "create_date")
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "update_date")
	private Date updateDate;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
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

}
