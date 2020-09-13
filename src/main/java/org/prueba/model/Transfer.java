
package org.prueba.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "transfer")
public class Transfer extends BaseEntity {

	// ATTRIBUTES -------------------------------------------------------------

	@Column(name = "quantity")
	private Double	quantity;

	@Column(name = "account_from_id")
	private Integer	from_id;

	@Column(name = "account_to_id")
	private Integer	to_id;


	// GETTERS / SETTERS ------------------------------------------------------

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(final Double quantity) {
		this.quantity = quantity;
	}

	public Integer getFrom_id() {
		return this.from_id;
	}

	public void setFrom_id(final Integer from_id) {
		this.from_id = from_id;
	}

	public Integer getTo_id() {
		return this.to_id;
	}

	public void setTo_id(final Integer to_id) {
		this.to_id = to_id;
	}

}
