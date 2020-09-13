
package org.prueba.contractTests.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.prueba.model.Account;
import org.prueba.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository	accountRepository;

	@Mock
	private AccountRepository	stubAccountRepository;

	// -------------------------- findAccountById(@Param("id") int id) ---------------------------


	// POSITIVE TEST
	@Test
	void shouldFindAccountById() {
		//1. Arrange
		int id = 1;

		//2. Act
		Account account = this.accountRepository.findAccountById(id);

		//3. Assert
		assertThat(account).isNotNull();
		assertThat(account.getCurrency() == "EUR");
		assertThat(account.getBalance() == 200.00);

	}

	// NEGATIVE TEST
	// Doesn't exist a account with this id
	@Test
	void shouldNotFindAccountById() {
		//1. Arrange
		int id = 50;

		//2. Act
		Account account = this.accountRepository.findAccountById(id);

		//3. Assert
		assertThat(account).isNull();
	}

	// -------------------------- findAllAccount() ---------------------------

	// POSITIVE TEST
	@Test
	void shouldFindAllAccount() {
		//1. Arrange

		//2. Act
		List<Account> accounts = this.accountRepository.findAllAccount();

		//3. Assert
		assertThat(accounts).isNotNull();
		assertThat(accounts).hasSize(3);
		assertThat(accounts.get(0).getBalance() == 200.00);
		assertThat(accounts.get(2).getCurrency() == "EUR");

	}

	// NEGATIVE TEST
	// Use an empty list
	@Test
	void shouldNotFindAllAccount() {
		//1. Arrange
		List<Account> accounts = new ArrayList<Account>();
		when(this.stubAccountRepository.findAllAccount()).thenReturn(accounts);

		//2. Act
		List<Account> payments = this.stubAccountRepository.findAllAccount();

		//3. Assert
		assertThat(accounts).hasSize(0);
	}

}
