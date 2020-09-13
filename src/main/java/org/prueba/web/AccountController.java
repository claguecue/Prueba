
package org.prueba.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.prueba.model.Account;
import org.prueba.service.AccountService;
import org.prueba.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class AccountController {

	private static final String	VIEWS_ACCOUNT_CREATE_FORM	= "createAccount";

	private static final String	VIEWS_ACCOUNT				= "viewsAccount";

	@Autowired
	private TransferService		transferService;

	@Autowired
	private AccountService		accountService;


	@InitBinder("account")
	public void initAccountBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new AccountValidator());
	}

	@GetMapping(value = "/account/new")
	public String initCreationForm(final ModelMap model) {
		Account account = new Account();
		model.addAttribute("account", account);

		List<String> treasuryType = new ArrayList<String>();
		treasuryType.add("true");
		treasuryType.add("false");
		model.addAttribute("treasuryType", treasuryType);

		List<String> currencyType = new ArrayList<String>();
		currencyType.add("EUR");
		currencyType.add("USD");
		currencyType.add("CNY");
		model.addAttribute("currencyType", currencyType);

		return VIEWS_ACCOUNT_CREATE_FORM;
	}

	@PostMapping(value = "/account/new")
	public String processCreationForm(@Valid final Account account, final BindingResult result, final ModelMap model) {

		List<String> treasuryType = new ArrayList<String>();
		treasuryType.add("true");
		treasuryType.add("false");
		model.addAttribute("treasuryType", treasuryType);

		List<String> currencyType = new ArrayList<String>();
		currencyType.add("EUR");
		currencyType.add("USD");
		currencyType.add("CNY");
		model.addAttribute("currencyType", currencyType);

		if (result.hasErrors()) {
			model.addAttribute("account", account);
			return AccountController.VIEWS_ACCOUNT_CREATE_FORM;
		} else {
			this.accountService.saveAccount(account);

			return "redirect:/accounts/all";
		}
	}

	@GetMapping(value = "/all")
	public String viewsAccount(final ModelMap model) {
		List<Account> accounts = new ArrayList<Account>();
		accounts = this.accountService.findAllAccount();
		model.addAttribute("accounts", accounts);

		return AccountController.VIEWS_ACCOUNT;
	}

}
