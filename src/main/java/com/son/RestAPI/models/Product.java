package com.son.RestAPI.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/** Validation Constraints
 * @NotNull: to say that a field must not be null.
 * @NotEmpty: to say that a list field must not empty.
 * @NotBlank: to say that a string field must not be the empty string (i.e. it must have at least one character).
 * @Min and @Max: to say that a numerical field is only valid when it’s value is above or below a certain value.
 * @Pattern: to say that a string field is only valid when it matches a certain regular expression.
 * @Email: to say that a string field must be a valid email address.
 */

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Can not be empty.") // Para dizer que um campo não deve ser nulo.
	@NotBlank(message = "Can not be blank.") // Para dizer que um campo de lista não deve ficar vazio
	@Min(value = 4) // dizer que um campo numérico só é válido quando seu... 
	@Max(value = 255) // ...valor está acima ou abaixo de um determinado valor.
	@Size(min = 4, max = 255) // @Size => sempre que usar String
	private String name;
	
	@NotEmpty(message = "Can not be empty.")
	@Min(value = 0) // @Min e @Max => sempre que usar Inteiro
	@Max(value = 1000)
	private Integer qtd;
	
	private Date dateCreated;

	public Product() {
	}

	public Product(String name, Integer qtd) {
		this.name = name;
		this.qtd = qtd;
	}

	@PrePersist
	public void onPrePesist() {
		if (this.dateCreated == null) {
			this.dateCreated = new Date();
		}
	}
	
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

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "{id: " + this.id + ", name: " + this.name + ", qtd: " + this.qtd + ", dateCreated: " + this.dateCreated
				+ "}";
	}

}
