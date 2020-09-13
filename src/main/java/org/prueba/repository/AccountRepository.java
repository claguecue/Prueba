
package org.prueba.repository;

import java.util.List;

import org.prueba.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;

public interface AccountRepository {

	void save(Account account) throws DataAccessException;

	Account findAccountById(@Param("id") int id) throws DataAccessException;

	List<Account> findAllAccount() throws DataAccessException;

}
