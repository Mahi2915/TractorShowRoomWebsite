package com.web.ashoktractors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {

	@Autowired
	TractorCusRepository customers;
	@Autowired
	TractorRepository Tractors;
	@Autowired
	AdminRepository admins;

	@Autowired
	SalesInterface sale;

	@Autowired
	FeedRepository feed;

	@GetMapping("/register")
	public String showRegister() {
		return "signup";
	}

	@GetMapping("/")
	public String showLogin() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();

		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		return "redirect:/";
	}

	@GetMapping("/l")
	public String showLoginAdmin() {
		return "loginadmin";
	}

	@GetMapping("/cus")
	public String showCustomer() {
		return "customerdashboard";
	}

	@GetMapping("/jivo")
	public String showjivo() {
		return "jivo";
	}

	@GetMapping("/oja")
	public String showoja() {
		return "oja";
	}

	@GetMapping("/yuvraj")
	public String showyuvraj() {
		return "yuvraj";
	}

	@GetMapping("/arjun")
	public String showArjun() {
		return "arjun";
	}

	@GetMapping("/xp")
	public String showXP() {
		return "xp";
	}

	@GetMapping("/yuvo")
	public String showYuvo() {
		return "yuvo";
	}

	@GetMapping("/adminsignup")
	public String showAdmin() {
		return "/addadmin";
	}

	@GetMapping("/admindash")
	public String showPanel(Model model) {

		List<Object[]> Result = sale.getTop3SoldPartsToday();
		List<TopSoldPartDTO> dtoList = new ArrayList<>();

		for (Object[] row : Result) {
			TopSoldPartDTO dto = new TopSoldPartDTO();

			dto.setPartName((String) row[1]);
			dto.setTotalQuantitySold(((Number) row[2]).intValue());
			dtoList.add(dto);
		}
		int count = Tractors.getCount();
		int c = customers.getCount();
		model.addAttribute("count", count);
		model.addAttribute("c", c);
		model.addAttribute("topsales", dtoList);
		return "admindashboard";
	}

	@PostMapping("/signup-post")
	public String postSignup(@ModelAttribute TractorCustomerModel customer, RedirectAttributes red) {
		try {
			customers.signUpCustomer(customer.getName(), customer.getAge(), customer.getGender(), customer.getEmail(),
					customer.getPhone(), customer.getAddress(), customer.getPassword());
			red.addFlashAttribute("successMessage", "customer registered successfully!");
		} catch (DataAccessException e) {
			red.addFlashAttribute("errorMessage", "Error: not added");
		}

		return "redirect:/register";

	}

	@PostMapping("/login-check")
	public String loginCustomer(@RequestParam String email, @RequestParam String password, HttpSession session,
			Model model) {

		List<TractorCustomerModel> customerList = customers.loginCustomer(email, password);

		if (!customerList.isEmpty()) {

			session.setAttribute("loggedInCustomer", customerList.get(0));

			return "redirect:/customerdashboard";
		} else {
			model.addAttribute("error", "Invalid Email or Password");
			return "login";
		}
	}

	@PostMapping("/loginAdmin")
	public String loginAdmin(@RequestParam String username, @RequestParam String password, HttpSession session,
			Model model) {

		List<AdminModel> adminList = admins.loginAdmin(username, password);

		if (!adminList.isEmpty()) {

			session.setAttribute("loggedInAdmin", adminList.get(0));
			session.setAttribute("isAdmin", true);

			return "redirect:/admindash";
		} else {
			model.addAttribute("error", "Invalid username or Password");
			return "loginadmin";
		}
	}

	@GetMapping("/admindashboard")
	public String adminDashboard(HttpSession session, Model model) {
		AdminModel admin = (AdminModel) session.getAttribute("loggedInAdmin");

		if (admin != null) {
			model.addAttribute("admin", admin);

			return "admindashboard";
		} else {
			return "redirect:/loginAdmin";
		}
	}

	@GetMapping("/customerdashboard")
	public String customerDashboard(HttpSession session, Model model) {
		TractorCustomerModel customer = (TractorCustomerModel) session.getAttribute("loggedInCustomer");

		if (customer != null) {
			model.addAttribute("customer", customer);

			return "customerdashboard";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/tractorlist")
	public String TractorList(Model m, RedirectAttributes re) {
		try {
			List<TractorModel> list = Tractors.getAllTractors();
			m.addAttribute("tractorHistory", list);

		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", "Tractors not found");
		}
		return "Tractorlist";
	}

	@PostMapping("/addadmin")
	public String adminsignup(@ModelAttribute AdminModel admin, RedirectAttributes red) {
		try {
			admins.signUpAdmin(admin.getName(), admin.getUsername(), admin.getPassword());
			red.addFlashAttribute("successMessage", "admin registered successfully!");
		} catch (DataAccessException e) {
			red.addFlashAttribute("errorMessage", "Error: not added");
		}

		return "redirect:/adminlist";

	}

	@GetMapping("/adminlist")
	public String AdminList(Model m, RedirectAttributes re) {
		try {
			List<AdminModel> list = admins.getAllAdmins();
			m.addAttribute("Admins", list);

		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", "Admins not found");
		}
		return "Admins";
	}

	@GetMapping("/customerlist")
	public String CustomersList(Model m, RedirectAttributes re) {
		try {
			List<TractorCustomerModel> list = customers.getAllCustomers();
			m.addAttribute("customersList", list);

		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "Allcustomers";
	}

	@GetMapping("/edit/{id}")
	public String getOneAdmin(@PathVariable int id, Model m, RedirectAttributes re) {
		try {
			List<AdminModel> list = admins.fetchOne(id);

			if (!list.isEmpty()) {
				AdminModel admin = list.get(0);
				m.addAttribute("admin", admin);
			} else {
				m.addAttribute("error", "admin not found");
			}
		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", "admin id doesnot exists");

		}

		return "editadmin";
	}

	@PostMapping("/updateAdmin")
	public String editAdmin(@ModelAttribute AdminModel b) {
		{
			admins.editAdmin(b.getAdmin_id(), b.getName(), b.getUsername(), b.getPassword());

			return "redirect:/adminlist";
		}
	}

	@GetMapping("/delete/{id}")
	public String DeleteAdmin(@PathVariable int id) {
		admins.deleteAdmin(id);

		return "redirect:/adminlist";
	}

	@GetMapping("/ed/{id}")
	public String getOneCustomer(@PathVariable int id, Model m, RedirectAttributes re) {
		try {
			List<TractorCustomerModel> list = customers.fetchOneCustomer(id);

			if (!list.isEmpty()) {
				TractorCustomerModel cust = list.get(0);
				m.addAttribute("customer", cust);
			} else {
				m.addAttribute("error", "customer not found");
			}
		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", "customer id doesnot exists");

		}

		return "editcustomer";
	}

	@PostMapping("/updateCustomer")
	public String editCustomers(@ModelAttribute TractorCustomerModel b, HttpSession session) {

		customers.editCustomer(b.getCustomerId(), b.getName(), b.getAge(), b.getGender(), b.getEmail(), b.getPhone(),
				b.getAddress());
		Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

		if (Boolean.TRUE.equals(isAdmin)) {
			return "redirect:/customerlist";
		}
		return "redirect:/customerdashboard";

	}

	@GetMapping("/del/{id}")
	public String DeleteCustomer(@PathVariable int id) {
		customers.deleteCustomer(id);

		return "redirect:/customerlist";
	}

	@PostMapping("/feed-post")
	public String Feedbackpost(@ModelAttribute ServiceFeedBack s, HttpSession session, RedirectAttributes red) {
		try {

			TractorCustomerModel loggedInCustomer = (TractorCustomerModel) session.getAttribute("loggedInCustomer");

			if (loggedInCustomer != null) {

				s.setCustomerId(loggedInCustomer.getCustomerId());

				feed.addFeed(s.getCustomerId(), s.getTractorNumber(), s.getRating(), s.getComment());

				red.addFlashAttribute("successMessage", "Feedback added successfully!");
			} else {
				red.addFlashAttribute("errorMessage", "User session not found. Please login again.");
				return "redirect:/login";
			}
		} catch (DataAccessException e) {
			red.addFlashAttribute("errorMessage", "Error: Feedback not added");
		}

		return "redirect:/feedlist";
	}

}
