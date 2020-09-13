
package org.prueba.contractTests.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.prueba.model.Account;
import org.prueba.web.AccountValidator;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class AccountValidatorTest {

	private AccountValidator accountValidator;


	// POSITIVE TEST
	@Test
	void shoulAcceptAccount() {
		//1. Arrange
		Account account = new Account();
		account.setId(1);
		account.setBalance(200.00);
		account.setCurrency("EUR");
		account.setName("Maria Garcia");
		account.setTreasury(true);

		Errors errors = new BeanPropertyBindingResult(account, "account");

		this.accountValidator = new AccountValidator();

		//2. Act
		accountValidator.validate(account, errors);

		//3. Assert
		assertThat(errors.getErrorCount()).isEqualTo(0);
		assertThat(account.getBalance() == 200.00);
		assertThat(account.getCurrency() == "EUR");
	}

	// NEGATIVE TEST
	// Name and balance is not included
	@Test
	void shoulNotIncludedFields() {
		//1. Arrange
		Account account = new Account();
		account.setId(1);
		account.setCurrency("EUR");
		account.setTreasury(true);

		Errors errors = new BeanPropertyBindingResult(account, "account");

		this.accountValidator = new AccountValidator();

		//2. Act
		accountValidator.validate(account, errors);

		//3. Assert
		assertThat(errors.getErrorCount()).isEqualTo(2);
		assertThat(errors.getFieldError("balance")).isNotNull();
		assertThat(errors.getFieldError("name")).isNotNull();
	}

	// NEGATIVE TEST
	// Initial balance negative
	@Test
	void shoulNotInitialBalance() {
		//1. Arrange
		Account account = new Account();
		account.setId(1);
		account.setCurrency("EUR");
		account.setTreasury(true);
		account.setName("Maria Lopez");
		account.setBalance(-150.00);

		Errors errors = new BeanPropertyBindingResult(account, "account");

		this.accountValidator = new AccountValidator();

		//2. Act
		accountValidator.validate(account, errors);

		//3. Assert
		assertThat(errors.getErrorCount()).isEqualTo(1);
		assertThat(errors.getFieldError("balance")).isNotNull();
	}

}
