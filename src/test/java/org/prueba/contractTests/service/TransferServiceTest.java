
package org.prueba.contractTests.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.prueba.model.Account;
import org.prueba.model.Transfer;
import org.prueba.repository.springdatajpa.SpringDataAccountRepository;
import org.prueba.repository.springdatajpa.SpringDataTransferRepository;
import org.prueba.service.AccountService;
import org.prueba.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TransferServiceTest {

	@Autowired
	protected TransferService				transferService;

	@Mock
	private SpringDataTransferRepository	stubTransferRepository;

	@Mock
	private TransferService					stubTransferService;

	// -------------------------- saveTransfer(final Transfer transfer) ---------------------------


	// POSITIVE TEST
	@Test
	void shouldSaveTransfer() {

		//1. Arrange
		Transfer transfer = new Transfer();
		transfer.setId(1);
		transfer.setFrom_id(2);
		transfer.setTo_id(1);
		transfer.setQuantity(100.00);

		//2. Act
		this.stubTransferService.saveTransfer(transfer);

		//3. Assert
		verify(this.stubTransferService).saveTransfer(transfer);

	}

	// NEGATIVE TEST
	// Some mandatory fields are not completed
	@Test
	void shouldNotSaveTransfer() {
		//1. Arrange
		Transfer transfer = new Transfer();
		transfer.setId(1);
		transfer.setQuantity(null);
		transfer.setTo_id(1);

		//2. Act
		this.stubTransferService.saveTransfer(transfer);

		//3. Assert
		verify(this.stubTransferService).saveTransfer(transfer);
		assertThat(transfer.getFrom_id()).isNull();
		assertThat(transfer.getQuantity()).isNull();
	}

}
