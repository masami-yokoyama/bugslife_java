package com.example.model;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tax_types")
public class TaxType extends TimeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "rate", nullable = false)
	private int rate;

	@Column(name = "tax_included", nullable = false)
	private int taxIncluded;

	@Column(name = "rounding", nullable = false)
	private String rounding;

	@OneToMany(mappedBy = "tax", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Product> products;
}
