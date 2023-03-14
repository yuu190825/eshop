package main.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import main.model.Customer;
import main.service.CustomerService;

/**
 * 客戶控制器
 * @author sam
 *
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * @param model
     * @return
     */
    @GetMapping("/add-customer")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    /**
     * @param customer
     * @param bindingResult
     * @return
     */
    @PostMapping("/process-customer-form")
//		public String showCustomerData(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    public String showCustomerData(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, Errors errors) throws IOException {

        if(customerService.companyNameExists(customer.getCompanyName())) {
            //errors.rejectValue("field name",  "message key from message.properties")
            errors.rejectValue("companyName", "customer.inputvalid.companyName");
        }
        if(customerService.contactNameExists(customer.getContactName())) {
            errors.rejectValue("contactName", "customer.inputvalid.contactName");
        }
        if(customerService.phonenumberExists(customer.getPhonenumber())) {
            errors.rejectValue("phonenumber", "customer.inputvalid.phonenumber");
//				System.out.println(errors);
        }

        if(errors.hasErrors()) {
            return "customer-form";
        }
        if(bindingResult.hasErrors()) {
            return "customer-form";
        }

//			boolean errors = false;
//
//			if(customerService.companyNameExists(customer.getCompanyName())) {
//				redirectAttributes.addAttribute("companyNameExists", "該公司名稱在資料庫已存在");
//				errors = true;
//			}
//
//			if(customerService.contactNameExists(customer.getContactName())) {
//				redirectAttributes.addAttribute("contactNameExists", "該聯絡人在資料庫已存在");
//				errors = true;
//			}
//
//			if(customerService.phonenumberExists(customer.getPhonenumber())) {
//				redirectAttributes.addAttribute("phonenumberExists", "該電話號碼在資料庫已存在");
//				errors = true;
//			}
//
//			if(errors) {
//				return "redirect:/add-customer";
//			}
//
//			if(bindingResult.hasErrors()) {
//				return "customer-form";
//			}

        customerService.saveOrUpdate(customer);
        return "redirect:/show-customer-data";

    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/show-customer-data")
    public String getCustomers(Model model) {
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    /**
     * @param customerId
     * @return
     */
    @GetMapping("/delete-customer/{customerId}")
    public String deleteCustomer(@PathVariable("customerId") long customerId) {
        Customer customer = customerService.getById(customerId);
        if(customer != null) {
            customerService.delete(customerId);
        }
        return "redirect:/show-customer-data";
    }

    /**
     * @param customerId
     * @param model
     * @return
     */
    @GetMapping("/edit-customer/{customerId}")
    public String editCustomer(@PathVariable("customerId") long customerId, Model model) {
        Customer customer = customerService.getById(customerId);
        if(customer != null) {
            model.addAttribute("customer", customer);
            return "customer-form";
        }
        return "redirect:/show-customer-data";
    }

    /**
     * @param customerId
     * @param principal
     * @return
     */
    @GetMapping("/add-user-to-customer/{customerId}")
    public String addUserToCustomer(@PathVariable long customerId, Principal principal) {
        customerService.addUserToCustomer(customerId, principal.getName());
        return "redirect:/show-customer-data";
    }

//		@GetMapping("/add-user-to-customer/{customerId}/{userId}")
//		public String addUserToCustomer(@PathVariable long customerId, @PathVariable long userId) {
//			customerService.addUserToCustomer(customerId, userId);
//			return "redirect:/show-customer-data";
//		}

    /**
     * @return
     */
    @RequestMapping("/customer-home")
    public String getCustomerHome() {
        return "home";
    }

    @RequestMapping("/customer-search")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Customer> customers = customerService.listAll(keyword);
        model.addAttribute("customers", customers);
        model.addAttribute("keyword", keyword);

        return "customers";
    }


}
