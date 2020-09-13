
package org.prueba.service;

import java.util.List;

import javax.transaction.Transactional;

import org.prueba.model.Account;
import org.prueba.repository.AccountRepository;
import org.prueba.repository.springdatajpa.SpringDataAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	private AccountRepository accountRepository;


	@Autowired
	public AccountService(final SpringDataAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Transactional
	public void saveAccount(final Account account) throws DataAccessException {
		this.accountRepository.save(account);
	}

	@Transactional
	public Account findAccountById(final int id) throws DataAccessException {
		return this.accountRepository.findAccountById(id);
	}

	@Transactional
	public List<Account> findAllAccount() throws DataAccessException {
		return this.accountRepository.findAllAccount();
	}

}
