package com.increff.pos.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"brand","category"})})
public class BrandMasterPojo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;
	private String brand;
	private String category;
}

// todo mockmultipart file
//

// MockMultipartFile file = new MockMultipartFile("file", "hello.txt", "text/csv", "articleCode,barCode,attribute1,attribute2,attribute3,attribute4,attribute5,attribute6,attribute7,attribute8,attribute9,attribute10,imageUrl\n1,2,XL,S,red,null,null,null,null,null,null,null,\n2,2,L,M,blue,null,null,null,null,null,null,null,https://imageurl.com".getBytes());
