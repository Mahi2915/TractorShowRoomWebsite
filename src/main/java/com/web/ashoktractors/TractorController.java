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

@Controller
public class TractorController {
	
	@Autowired
	TractorRepository tractors;
	
	@Autowired
	TractorSaleRepository sales;
	
	@GetMapping("/showTractor")
	public String showTractorForm() {
		return "addtractor";
	}
	
	@PostMapping("/addtractor")
	public String postTractor(@ModelAttribute TractorModel Tractor, RedirectAttributes red) {
		try {
			tractors.addTractor(Tractor.getModel_name(),Tractor.getBrand(),Tractor.getColor(),Tractor.getEngine_power(),Tractor.getCurrent_count(), Tractor.getPrice());
			red.addFlashAttribute("successMessage", "Tractor added successfully!");
		} catch (DataAccessException e) {
			red.addFlashAttribute("errorMessage", "Error: not added");
		}

		return "redirect:/showTractor";

	}
	
	
	
	@GetMapping("/tractorslist")
	public String TractorList(Model m, RedirectAttributes re) {
		try {
			List<TractorModel> list = tractors.getAllTractors();
			m.addAttribute("tractors", list);

		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", "Tractors not found");
		}
		return "admtractorlist";
	}
	
	
	@GetMapping("/modify/{id}")
	public String getOneTractor(@PathVariable int id, Model m, RedirectAttributes re) {
		try {
			List<TractorModel> list = tractors.fetchOneTractor(id);

			if (!list.isEmpty()) {
				TractorModel tractor = list.get(0);
				m.addAttribute("Tractor", tractor);
			} else {
				m.addAttribute("error", "Tractor not found");
			}
		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", "Tractor id doesnot exists");

		}

		return "edittractor";
	}
    
    @PostMapping("/updateTractor")
	public String editTractorOne(@ModelAttribute TractorModel t) {
		{
			tractors.editTractor(t.getTractor_id(),t.getModel_name(),t.getBrand(),t.getColor(),t.getEngine_power(),t.getCurrent_count(),t.getPrice());

			return "redirect:/tractorslist";
		}
	}
    
    @GetMapping("/drop/{id}")
	public String DeleteTractor(@PathVariable int id) {
	tractors.deleteTractor(id);

		return "redirect:/tractorslist";
	}
    
    /*tractor sale*/
    
    
    @GetMapping("/modify2/{id}")
	public String getOneTractorSale(@PathVariable int id, Model m, RedirectAttributes re) {
		try {
			List<TractorSaleModel> list = sales.fetchOneSale(id);

			if (!list.isEmpty()) {
				TractorSaleModel tractor = list.get(0);
				m.addAttribute("Tractor", tractor);
			} else {
				m.addAttribute("error", "Tractor sale not found");
			}
		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", "sale id doesnot exists");

		}

		return "edittractorsale";
	}
    
    @PostMapping("/updateTractorSale")
	public String editTractorSale(@ModelAttribute TractorSaleModel t) {
		{
			sales.editTractorSale(t.getSaleId(), t.getTractorId(), t.getCustomerId(), t.getSaleQuantity(), t.getSalePrice());

			return "redirect:/tractorsalelist";
		}
	}
    
    @GetMapping("/drop3/{id}")
	public String DeleteTractorSale(@PathVariable int id) {
	sales.deleteTractorSale(id);

		return "redirect:/tractorsalelist";
	}
    
    
	
    @GetMapping("/tractorsalelist")
	public String TractorSaleList(Model m, RedirectAttributes re) {
		try {
			List<TractorSaleModel> list = sales.getAllTractorSales();
			m.addAttribute("sales", list);

		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", "Tractors not found");
		}
		return "saleshistory";
	}
	
    @GetMapping("/TractorSalesForm")
	public String showTractorSalesForm() {
		return "addtractorSales";
	}
	
	@PostMapping("/saleEntries")
	public String SalesTractor(@ModelAttribute TractorSaleModel t, RedirectAttributes red) {
		try {
			sales.addTractorSales(t.getTractorId(),t.getCustomerId(),t.getSaleQuantity(),t.getSalePrice());
			red.addFlashAttribute("successMessage", "TractorSales added successfully!");
		} catch (DataAccessException e) {
			red.addFlashAttribute("errorMessage", e.getMessage());
		}

		return "redirect:/tractorsalelist";

	}
	@GetMapping("/chart")
	public String showSalesChart(Model model) {
	    List<Object[]> result = sales.fetchMonthlySales();
	    List<MonthlyTractorDTO> chartData = new ArrayList<>();

	    for (Object[] row : result) {
	        String modelName = (String) row[0];
	        int totalSold = ((Number) row[1]).intValue();

	        MonthlyTractorDTO dto = new MonthlyTractorDTO();
	        dto.setModelName(modelName);
	        dto.setTotalSold(totalSold);

	        chartData.add(dto);
	    }

	    model.addAttribute("chartData", chartData);
	    return "sales_chart";
	}


}
