package com.gold.smith.users.model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "file_upload")
public class FileUpload {

	public FileUpload(String name, String type, byte[] image) {
		this.name = name;
		this.type = type;
		this.image = image;
	}

	public FileUpload() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "file_name")
	private String name;

	@Column(name = "file_type")
	private String type;

	@Column(name = "image", unique = false, nullable = false,length = 300)
	private byte[] image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "FileUpload{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", image=" + Arrays.toString(image) +
				'}';
	}
}