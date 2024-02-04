package com.demo.onlinebook.Entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AUTHOR_MST")
public class Author implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long authorId;

	private String authorName;

//	@Transient
//	public List<Books> books;
}
