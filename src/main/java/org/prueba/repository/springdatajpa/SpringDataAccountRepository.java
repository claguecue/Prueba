
package org.prueba.repository.springdatajpa;

import java.util.List;

import org.prueba.model.Account;
import org.prueba.repository.AccountRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataAccountRepository extends AccountRepository, Repository<Account, Integer> {

	@Override
	@Query("SELECT a FROM Account a WHERE a.id = ?1")
	Account findAccountById(@Param("id") int id) throws DataAccessException;

	@Override
	@Query("SELECT a FROM Account a")
	List<Account> findAllAccount() throws DataAccessException;

}
