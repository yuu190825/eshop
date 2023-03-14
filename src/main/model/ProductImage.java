package main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_image")
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id")
	private long imageId;
	
    
    @Column(name = "image_String", columnDefinition="MEDIUMTEXT")
    private String imageBase64String;
    
	@Column(name = "file_name")
	private String fileName;

	public long getImageNum() {
		return imageId;
	}

	public void setImageNum(long imageNum) {
		this.imageId = imageNum;
	}

	public String getImageBase64String() {
		return imageBase64String;
	}

	public void setImageBase64String(String imageBase64String) {
		this.imageBase64String = imageBase64String;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
