
package org.prueba.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "account")
public class Account extends BaseEntity {

	// ATTRIBUTES -------------------------------------------------------------

	@Column(name = "name")
	private String	name;

	@Column(name = "currency")
	private String	currency;

	@Column(name = "balance")
	private Double	balance;

	@Column(name = "treasury")
	private Boolean	treasury;


	// GETTERS / SETTERS ------------------------------------------------------

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(final Double balance) {
		this.balance = balance;
	}

	public Boolean getTreasury() {
		return this.treasury;
	}

	public void setTreasury(final Boolean treasury) {
		this.treasury = treasury;
	}
}
