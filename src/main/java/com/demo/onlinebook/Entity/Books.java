package com.demo.onlinebook.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
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
@Table(name = "BOOK_MST")
public class Books implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "book_id_seq", sequenceName = "BOOK_MST_BOOK_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
	private Long bookId;

	private String title;

	@ManyToOne
	@JoinColumn(name = "authorId", referencedColumnName = "authorId", insertable = false, updatable = false)
	private Author author;

	private Long authorId;

	@Column(unique = true)
	private Long isbnNum;

	private String review;

}
