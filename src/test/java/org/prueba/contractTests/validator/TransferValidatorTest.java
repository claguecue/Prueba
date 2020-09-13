
package org.prueba.contractTests.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.prueba.model.Account;
import org.prueba.model.Transfer;
import org.prueba.web.AccountValidator;
import org.prueba.web.TransferValidator;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class TransferValidatorTest {

	private TransferValidator transferValidator;


	// POSITIVE TEST
	@Test
	void shoulAcceptTransfer() {
		//1. Arrange
		Transfer transfer = new Transfer();
		transfer.setId(1);
		transfer.setFrom_id(1);
		transfer.setTo_id(2);
		transfer.setQuantity(150.00);

		Errors errors = new BeanPropertyBindingResult(transfer, "transfer");

		this.transferValidator = new TransferValidator();

		//2. Act
		transferValidator.validate(transfer, errors);

		//3. Assert
		assertThat(errors.getErrorCount()).isEqualTo(0);
		assertThat(transfer.getFrom_id() == 1);
		assertThat(transfer.getQuantity() == 150.00);
	}

	// NEGATIVE TEST
	// Quantity and to_id is not included
	@Test
	void shoulNotIncludedFields() {
		//1. Arrange
		Transfer transfer = new Transfer();
		transfer.setId(1);
		transfer.setFrom_id(1);
		transfer.setQuantity(null);

		Errors errors = new BeanPropertyBindingResult(transfer, "transfer");

		this.transferValidator = new TransferValidator();

		//2. Act
		transferValidator.validate(transfer, errors);

		//3. Assert
		assertThat(errors.getErrorCount()).isEqualTo(1);
		assertThat(errors.getFieldError("quantity")).isNotNull();
	}

	// NEGATIVE TEST
	// Quantity negative
	@Test
	void shoulNotInitialBalance() {
		//1. Arrange
		Transfer transfer = new Transfer();
		transfer.setId(1);
		transfer.setFrom_id(1);
		transfer.setTo_id(2);
		transfer.setQuantity(-150.00);

		Errors errors = new BeanPropertyBindingResult(transfer, "transfer");

		this.transferValidator = new TransferValidator();

		//2. Act
		transferValidator.validate(transfer, errors);

		//3. Assert
		assertThat(errors.getErrorCount()).isEqualTo(1);
		assertThat(errors.getFieldError("quantity")).isNotNull();
	}

}
