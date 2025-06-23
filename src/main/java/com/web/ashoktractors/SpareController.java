package com.web.ashoktractors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

@Controller
public class SpareController {

	@Autowired
	SpareRepository spares;

	@Autowired
	SalesInterface sale;

	@Autowired
	SupplierRepository supplier;

	@Autowired
	OrderRepository orders;

	@Autowired
	PurchaseRepository purchase;

	@GetMapping("/showSpares")
	public String showSparesForm() {
		return "addspares";
	}

	@PostMapping("/addspares")
	public String postSpares(@ModelAttribute SparesModel spare, RedirectAttributes red) {
		try {

			spares.addSpares(spare.getPartName(), spare.getBrand(), spare.getPrice(), spare.getQuantity(),
					spare.getAvailability());

		} catch (DataAccessException e) {
			red.addFlashAttribute("errorMessage", e.getMessage());
		}

		return "redirect:/spareslist";

	}

	@GetMapping("/spareslist")
	public String SparesList(Model m, RedirectAttributes re) {
		try {
			List<SparesModel> list = spares.getSpares();
			m.addAttribute("sparesList", list);

		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "allspares";
	}

	@GetMapping("/searchresult")
	public String showSpareOne() {
		return "searchresult";
	}

	@GetMapping("/search-part")
	public String SearchspareList(@RequestParam("partId") int partId, Model m, RedirectAttributes re) {
		try {
			List<SparesModel> list = spares.fetchOnePart(partId);
			if (!list.isEmpty()) {
				SparesModel spare = list.get(0);
				m.addAttribute("spare", spare);
			} else {
				re.addFlashAttribute("errorMessage", "No spare part found with ID: " + partId);
				return "redirect:/searchresult";
			}
		} catch (DataAccessException e) {
			re.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/searchresult";
		}
		return "allspares";
	}

	@GetMapping("/modify1/{id}")
	public String getOneSpare(@PathVariable int id, Model m, RedirectAttributes re) {
		try {
			List<SparesModel> list = spares.fetchOnePart(id);

			if (!list.isEmpty()) {
				SparesModel spares = list.get(0);
				m.addAttribute("spares", spares);
			} else {
				m.addAttribute("error", "spare not found");
			}
		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", "part id doesnot exists");

		}

		return "editspare";
	}

	@PostMapping("/updateSpare")
	public String editSpare(@ModelAttribute SparesModel s) {
		{
			spares.editSpare(s.getPartId(), s.getPartName(), s.getBrand(), s.getPrice(), s.getQuantity(),
					s.getAvailability());

			return "redirect:/spareslist";
		}
	}

	@GetMapping("/drop1/{id}")
	public String DeleteSpares(@PathVariable int id) {
		spares.deleteSpare(id);

		return "redirect:/spareslist";
	}

	@GetMapping("/monthlysaleschart")
	public String showSalesChart(Model model) {
		List<Object[]> data = sale.getMonthlySparePartSales();

		List<MonthlyPartSalesDTO> salesData = data.stream()
				.map(row -> new MonthlyPartSalesDTO((String) row[0], ((Number) row[1]).intValue()))
				.collect(Collectors.toList());

		model.addAttribute("salesData", salesData);
		return "monthlysaleschart";
	}

	@GetMapping("/monthlylinechart")
	public String showLineChart(Model model) {
		List<Object[]> data = sale.getMonthlySparePartSales();

		List<MonthlyPartSalesDTO> salesData = data.stream()
				.map(row -> new MonthlyPartSalesDTO((String) row[0], ((Number) row[1]).intValue()))
				.collect(Collectors.toList());

		model.addAttribute("salesData", salesData);
		return "saleslinechart";
	}

	@GetMapping("/topsoldspares")
	public String getTop3PartsToday(Model model) {
		List<Object[]> Result = sale.getTop3SoldPartsToday();
		List<TopSoldPartDTO> dtoList = new ArrayList<>();

		for (Object[] row : Result) {
			TopSoldPartDTO dto = new TopSoldPartDTO();
			dto.setPartId((Integer) row[0]);
			dto.setPartName((String) row[1]);
			dto.setTotalQuantitySold(((Number) row[2]).intValue());
			dtoList.add(dto);
		}

		model.addAttribute("topParts", dtoList);
		return "topsoldparts";
	}

	@GetMapping("/salesform")
	public String showSalesForm() {
		return "saleentry";
	}

	@PostMapping("/saleEntry")
	public String saleSpare(@ModelAttribute SalesModel sales, RedirectAttributes red) {
		try {

			sale.saleEntry(sales.getCustomerId(), sales.getPartId(), sales.getSaleQuantity());

		} catch (DataAccessException e) {
			red.addFlashAttribute("errorMessage", e.getMessage());
		}

		return "redirect:/spareslist";

	}

	@GetMapping("/saleslist")
	public String SalesList(Model m, RedirectAttributes re) {
		try {
			List<SalesModel> list = sale.getAllSales();
			m.addAttribute("salesList", list);

		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "allsales";
	}

	@GetMapping("/supplierform")
	public String showSupplyForm() {
		return "addsupplier";
	}

	@PostMapping("/saveSupplier")
	public String Supplierpost(@ModelAttribute SupplierModel s, RedirectAttributes red) {
		try {

			supplier.addSupplier(s.getSupplierName(), s.getPhone(), s.getAddress());

		} catch (DataAccessException e) {
			red.addFlashAttribute("errorMessage", e.getMessage());
		}

		return "redirect:/supplierlist";

	}

	@GetMapping("/supplierlist")
	public String SupplyList(Model m, RedirectAttributes re) {
		try {
			List<SupplierModel> list = supplier.getAllSuppliers();
			m.addAttribute("suppliersList", list);

		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "allsuppliers";
	}

	@GetMapping("/orderform")
	public String showOrderForm() {
		return "addorder";
	}

	@PostMapping("/saveOrder")
	public String Orderpost(@ModelAttribute OrderSparesModel o, RedirectAttributes red) {
		try {

			orders.addOrder(o.getPartId(), o.getSupplierId(), o.getQuantityOrdered());

		} catch (DataAccessException e) {
			red.addFlashAttribute("errorMessage", e.getMessage());
		}

		return "redirect:/orderlist";

	}

	@GetMapping("/orderlist")
	public String OrderList(Model m, RedirectAttributes re) {
		try {
			List<OrderSparesModel> list = orders.getAllOrders();
			m.addAttribute("ordersList", list);

		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "allorders";
	}

	/* purchase */

	@GetMapping("/purchaseform")
	public String showpurchaseForm() {
		return "addpurchase";
	}

	@PostMapping("/savePurchase")
	public String Purchasepost(@ModelAttribute PurchaseModel p, RedirectAttributes red) {
		try {

			purchase.addpurchase(p.getPartId(), p.getSupplierId(), p.getQuantity(), p.getPurchasePrice());

		} catch (DataAccessException e) {
			red.addFlashAttribute("errorMessage", e.getMessage());
		}

		return "redirect:/purchaselist";

	}

	@GetMapping("/purchaselist")
	public String purchaseList(Model m, RedirectAttributes re) {
		try {
			List<PurchaseModel> list = purchase.getAllPurchase();
			m.addAttribute("purchaseList", list);

		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "allpurchases";
	}

	@GetMapping("/edit1/{id}")
	public String getOne(@PathVariable int id, Model m, RedirectAttributes re) {
		try {
			List<PurchaseModel> list = purchase.fetchOne(id);

			if (!list.isEmpty()) {
				PurchaseModel purchase = list.get(0);
				m.addAttribute("purchases", purchase);
			} else {
				m.addAttribute("error", " not found");
			}
		} catch (DataAccessException e) {

			re.addFlashAttribute("errorMessage", " doesnot exists");

		}

		return "editpurchase";
	}

	@PostMapping("updatePurchase")
	public String editPurchase(@ModelAttribute PurchaseModel p) {
		{
			purchase.editPurchase(p.getPurchaseId(), p.getPartId(), p.getSupplierId(), p.getQuantity(),
					p.getPurchasePrice());

			return "redirect:/purchaselist";
		}
	}

	@GetMapping("/delete1/{id}")
	public String DeletePurchase(@PathVariable int id) {
		purchase.deletePurchase(id);
		return "redirect:/purchaselist";
	}

}
