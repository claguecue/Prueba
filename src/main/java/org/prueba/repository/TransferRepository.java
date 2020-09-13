
package org.prueba.repository;

import org.prueba.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;

public interface TransferRepository {

	void save(Transfer transfer) throws DataAccessException;

}
