
package org.prueba.contractTests.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.prueba.model.Account;
import org.prueba.repository.springdatajpa.SpringDataAccountRepository;
import org.prueba.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AccountServiceTest {

	@Autowired
	protected AccountService			accountService;

	@Mock
	private SpringDataAccountRepository	stubAccountRepository;

	@Mock
	private AccountService				stubAccountService;

	// -------------------------- saveAccount(final Account account) ---------------------------


	// POSITIVE TEST
	@Test
	void shouldSaveAccount() {

		//1. Arrange
		Account account = new Account();
		account.setId(1);
		account.setBalance(100.00);
		account.setCurrency("EUR");
		account.setName("account one");

		//2. Act
		this.stubAccountService.saveAccount(account);

		//3. Assert
		verify(this.stubAccountService).saveAccount(account);

	}

	// NEGATIVE TEST
	// Some mandatory fields are not completed
	@Test
	void shouldNotSaveAccount() {
		//1. Arrange
		Account account = new Account();
		account.setId(1);
		account.setBalance(100.00);
		account.setCurrency(null);

		//2. Act
		this.stubAccountService.saveAccount(account);

		//3. Assert
		verify(this.stubAccountService).saveAccount(account);
		assertThat(account.getCurrency()).isNull();
		assertThat(account.getName()).isNull();
	}

	// -------------------------- findAccountById(final int id) ---------------------------

	//POSITIVE TEST
	@Test
	void shouldFindAccountById() {
		//1. Arrange

		//2. Act
		Account account = this.accountService.findAccountById(1);

		//3. Assert
		assertThat(account.getBalance() == 200.00);
		assertThat(account.getCurrency() == "EUR");
		assertThat(account.getName() == "Maria Muñoz");
	}

	// NEGATIVE TEST
	// Introduce a id not valid (don't exits a payment with this id)
	@Test
	void shouldNotFindAccountById() {
		//1. Arrange

		//2. Act
		Account account = this.accountService.findAccountById(10);

		//3. Assert
		assertNull(account);
	}

	// -------------------------- findAllAccount() ---------------------------

	// POSITIVE TEST
	@Test
	void shouldFindAllAccount() {
		//1. Arrange

		//2. Act
		List<Account> accounts = this.accountService.findAllAccount();

		//3. Assert
		assertThat(accounts).isNotNull();
		assertThat(accounts).hasSize(3);
		assertThat(accounts.get(0).getCurrency() == "EUR");
		assertThat(accounts.get(2).getBalance() == 10000.00);

	}

	// NEGATIVE TEST
	// There aren't any account
	@Test
	void shouldNotFindAllAccount() {
		//1. Arrange
		accountService = new AccountService(stubAccountRepository);

		//2. Act
		List<Account> account = this.stubAccountService.findAllAccount();

		//3. Assert
		assertThat(account).hasSize(0);
	}

}
