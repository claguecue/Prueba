
package org.prueba.web;

import org.prueba.model.Account;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AccountValidator implements Validator {

	//SERVICES ---------------------------------------------------------------

	// CONSTRUCTOR ------------------------------------------------------------

	// SUPPORTS ---------------------------------------------------------------

	public boolean supports(final Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz);
	}

	// VALIDATE ---------------------------------------------------------------

	public void validate(final Object obj, final Errors errors) {

		Account account = (Account) obj;

		//Validate required fields
		if (account.getName() == null || StringUtils.isEmpty(account.getName())) {
			errors.rejectValue("name", "Name is required.", "Name is required.");
		}

		if (account.getBalance() == null || StringUtils.isEmpty(account.getBalance())) {
			errors.rejectValue("balance", "Balance is required.", "Balance is required.");
		}

		// Validate that balance is positive
		if (!errors.hasFieldErrors("balance")) {

			if (account.getBalance() < 0) {
				errors.rejectValue("balance", "Initial balance must be positive.", "Initial balance must be positive.");
			}
		}

	}

}
