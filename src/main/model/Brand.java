package main.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "brand")
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "brand_id")
	private long id;

	@Column(name = "brand_name", length = 20)
	private String brandName;

//	@NotBlank(message = "{brand.description.notblank}")
//  @Size(min = 2, message = "{brand.description.size}")
	@Column(name = "brand_description", length = 20)
	private String brandDescription;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "create_date")
	private Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "update_date")
	private Date updateDate;

	/*
	 * uncomment if you want to have a bidirectional mapping
	 *
	 * @OneToOne(mappedBy = "product") private Product product;
	 */

	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Product> products;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandDescription() {
		return brandDescription;
	}

	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
