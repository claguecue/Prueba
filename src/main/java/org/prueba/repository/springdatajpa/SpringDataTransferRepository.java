
package org.prueba.repository.springdatajpa;

import org.prueba.model.Transfer;
import org.prueba.repository.TransferRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface SpringDataTransferRepository extends TransferRepository, Repository<Transfer, Integer> {

}
