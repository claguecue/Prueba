
package org.prueba.web;

import org.prueba.model.Transfer;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TransferValidator implements Validator {

	//SERVICES ---------------------------------------------------------------

	// CONSTRUCTOR ------------------------------------------------------------

	// SUPPORTS ---------------------------------------------------------------

	public boolean supports(final Class<?> clazz) {
		return Transfer.class.isAssignableFrom(clazz);
	}

	// VALIDATE ---------------------------------------------------------------

	public void validate(final Object obj, final Errors errors) {

		Transfer transfer = (Transfer) obj;

		//Validate required fields
		if (transfer.getQuantity() == null || StringUtils.isEmpty(transfer.getQuantity())) {
			errors.rejectValue("quantity", "Quantity is required.", "Quantity is required.");
		}

		// Validate that quantity is positive
		if (!errors.hasFieldErrors("quantity")) {
			if (transfer.getQuantity() < 0) {
				errors.rejectValue("quantity", "Quantity must be positive.", "Quantity must be positive.");
			}

		}

	}

}
