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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class ServiceController {

	@Autowired
	TractorserviceRepository repo;

	@Autowired
	FeedRepository feed;

	@GetMapping("/showService")
	public String showserviceForm(Model model) {

		List<Object[]> Result = repo.getAllTypes();
		List<ServiceDTO> dtoList = new ArrayList<>();

		for (Object[] row : Result) {
			ServiceDTO dto = new ServiceDTO();

			dto.setTypeName((String) row[1]);

			dtoList.add(dto);
		}

		model.addAttribute("types", dtoList);
		return "serviceentry";
	}

	@GetMapping("/showComplete")
	public String showcompleteForm() {
		return "completeservice";
	}

	@PostMapping("/completeService")
	public String CompleteService(@ModelAttribute TractorServiceBook service, RedirectAttributes red) {
		try {
			repo.CompleteService(service.getServiceId(), service.getCost());
			red.addFlashAttribute("successMessage", "service completed successfully!");
		} catch (DataAccessException e) {
			red.addFlashAttribute("errorMessage", "Error: not added");
		}

		return "redirect:/service";

	}

	@PostMapping("/serviceEntry")
	public String postService(@ModelAttribute TractorServiceBook service, RedirectAttributes red) {
		try {
			repo.addService(service.getCustomerName(), service.getTractorNumber(), service.getServiceType(),
					service.getServiceDetails(), service.getMechanicName());
			red.addFlashAttribute("successMessage", "service added successfully!");
		} catch (DataAccessException e) {
			red.addFlashAttribute("errorMessage", "Error: not added");
		}

		return "redirect:/service";

	}

	@GetMapping("/service")
	public String getService(Model m, RedirectAttributes re) {
		try {
			List<TractorServiceBook> list = repo.getAllService();
			m.addAttribute("serviceList", list);

			if (!list.isEmpty()) {
				System.out.println("List contains data.");
				return "servicelist";
			} else {
				m.addAttribute("error", "service not found");
				System.out.println("No data found.");
			}
		} catch (DataAccessException e) {
			re.addFlashAttribute("errorMessage", "service id does not exist");
			e.printStackTrace();
		}
		return "servicelist";
	}

	@GetMapping("/modify4/{id}")
	public String getOneService(@PathVariable int id, Model m, RedirectAttributes re) {
		try {
			List<TractorServiceBook> list = repo.fetchOneService(id);

			if (!list.isEmpty()) {
				TractorServiceBook service = list.get(0);
				m.addAttribute("service", service);
			} else {
				m.addAttribute("error", "service not found");
			}
		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", "service id doesnot exists");

		}

		return "editservice";
	}

	@PostMapping("/updateService")
	public String editService(@ModelAttribute TractorServiceBook t) {
		{
			repo.editService(t.getServiceId(), t.getCustomerName(), t.getTractorNumber(), t.getServiceType(),
					t.getServiceDetails(), t.getMechanicName());

			return "redirect:/service";
		}
	}

	@GetMapping("/drop4/{id}")
	public String DeleteService(@PathVariable int id) {
		repo.deleteService(id);

		return "redirect:/service";
	}

	@GetMapping("/servicetype")
	public String gettypes(Model model) {
		List<Object[]> Result = repo.getAllTypes();
		List<ServiceDTO> dtoList = new ArrayList<>();

		for (Object[] row : Result) {
			ServiceDTO dto = new ServiceDTO();

			dto.setTypeName((String) row[1]);
			System.out.print((String) row[1]);

			dtoList.add(dto);
		}

		model.addAttribute("types", dtoList);
		return "serviceentry";
	}

	@GetMapping("/showFeedback")
	public String showAddFeedbackForm(HttpSession session, Model model) {
		TractorCustomerModel loggedInCustomer = (TractorCustomerModel) session.getAttribute("loggedInCustomer");

		if (loggedInCustomer == null) {
			return "redirect:/";
		}

		model.addAttribute("loggedInCustomer", loggedInCustomer);
		model.addAttribute("serviceFeedBack", new ServiceFeedBack());

		return "addfeed";
	}

	@GetMapping("/feedlist")
	public String getAllFeeds(Model m, RedirectAttributes re) {
		try {
			List<ServiceFeedBack> list = feed.getAllFeeds();
			m.addAttribute("feedList", list);

			if (!list.isEmpty()) {
				System.out.println("List contains data.");
				return "feedlist";
			} else {
				m.addAttribute("error", "service not found");
				System.out.println("No data found.");
			}
		} catch (DataAccessException e) {
			re.addFlashAttribute("errorMessage", "service id does not exist");
			e.printStackTrace();
		}
		return "feedlist";
	}

}
