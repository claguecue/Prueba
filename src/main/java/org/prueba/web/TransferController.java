
package org.prueba.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.prueba.model.Account;
import org.prueba.model.Transfer;
import org.prueba.service.AccountService;
import org.prueba.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts/account")
public class TransferController {

	private static final String	VIEWS_PAYMENTS_CREATE_FORM	= "/createTransfer";

	private static final String	VIEWS_ACCOUNT_DETAILS		= "/accountDetails";

	private static final String	VIEWS_ERROR					= "/errorEnough";

	@Autowired
	private TransferService		transferService;

	@Autowired
	private AccountService		accountService;


	@InitBinder("transfer")
	public void initTransferBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new TransferValidator());
	}
	@ModelAttribute("account")
	public Account findAccount(@PathVariable("accountId") final int accountId) {
		return this.accountService.findAccountById(accountId);
	}

	@InitBinder("account")
	public void initAccountBinder(final WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/{accountId}/transfer/new")
	public String initCreationForm(final Account account, final ModelMap model) {
		Transfer transfer = new Transfer();
		model.addAttribute("transfer", transfer);

		List<Account> toType = new ArrayList<Account>();
		for (Account ac : this.accountService.findAllAccount()) {
			Integer id = ac.getId();
			toType.add(this.accountService.findAccountById(id));
		}

		toType.remove(account);

		model.addAttribute("toType", toType);

		return TransferController.VIEWS_PAYMENTS_CREATE_FORM;
	}

	@PostMapping(value = "/{accountId}/transfer/new")
	public String processCreationForm(final Account account, @Valid final Transfer transfer, final BindingResult result, final ModelMap model, final Errors errors) {

		List<Account> toType = new ArrayList<Account>();
		for (Account ac : this.accountService.findAllAccount()) {
			Integer id = ac.getId();
			toType.add(this.accountService.findAccountById(id));
		}

		toType.remove(account);

		model.addAttribute("toType", toType);

		if (result.hasErrors()) {
			model.addAttribute("transfer", transfer);
			return TransferController.VIEWS_PAYMENTS_CREATE_FORM;

		} else if (account.getTreasury() == false && (account.getBalance() - transfer.getQuantity()) < 0) {
			return TransferController.VIEWS_ERROR;

		} else {

			Double quantityFrom = account.getBalance();
			Double quantityTo = this.accountService.findAccountById(transfer.getTo_id()).getBalance();
			Double quantityChange = transfer.getQuantity();

			account.setBalance(quantityFrom - quantityChange);
			Account account_to = this.accountService.findAccountById(transfer.getTo_id());
			account_to.setBalance(quantityTo + quantityChange);

			transfer.setFrom_id(account.getId());

			this.transferService.saveTransfer(transfer);
			return "redirect:/accounts/all";
		}
	}

	@GetMapping(value = "/{accountId}")
	public String viewsAccount(final Account account, final ModelMap model) {

		model.addAttribute("account", account);

		return TransferController.VIEWS_ACCOUNT_DETAILS;
	}

}
