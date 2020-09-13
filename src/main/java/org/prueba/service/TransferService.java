
package org.prueba.service;

import javax.transaction.Transactional;

import org.prueba.model.Transfer;
import org.prueba.repository.TransferRepository;
import org.prueba.repository.springdatajpa.SpringDataTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

	private TransferRepository transferRepository;


	@Autowired
	public TransferService(final SpringDataTransferRepository transferRepository) {
		this.transferRepository = transferRepository;
	}

	@Transactional
	public void saveTransfer(final Transfer transfer) throws DataAccessException {
		this.transferRepository.save(transfer);
	}

}
