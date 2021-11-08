package it.prova.gestionesatelliti.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionesatelliti.model.Satellite;
import it.prova.gestionesatelliti.service.SatelliteService;
import it.prova.gestionesatelliti.utility.UtilityForm;

@Controller
@RequestMapping(value = "/satellite")
public class SatelliteController {
	
	@Autowired
	private SatelliteService satelliteService;

	@GetMapping
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView();
		List<Satellite> results = satelliteService.listAllElements();
		mv.addObject("satellite_list_attribute", results);
		mv.setViewName("satellite/list");
		return mv;
	}

	@GetMapping("/search")
	public String search() {
		return "satellite/search";
	}

	@PostMapping("/list")
	public String listByExample(Satellite example, ModelMap model) {
		List<Satellite> results = satelliteService.findByExample(example);
		model.addAttribute("satellite_list_attribute", results);
		return "satellite/list";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("insert_satellite_attr", new Satellite());
		return "satellite/insert";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("insert_satellite_attr") Satellite satellite, ModelMap model,
			RedirectAttributes redirectAttrs) {

		// ATTENZIONE!!!! la validazione con spring viene fatta in un modo più elegante
		// questa è fatta a mano e per ora prendiamola per buona
		if (!UtilityForm.validateBean(satellite)) {
			model.addAttribute("errorMessage", "Attenzione! Sono presenti errori di validazione");
			return "satellite/insert";
		}
		// ======================================================================

		satelliteService.inserisciNuovo(satellite);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/satellite";
	}

	@GetMapping("/show/{idSatellite}")
	public String show(@PathVariable(required = true) Long idSatellite, Model model) {
		model.addAttribute("show_satellite_attr", satelliteService.caricaSingoloElemento(idSatellite));
		return "satellite/show";
	}
	
	@GetMapping("/infodelete/{idSatellite}")
	public String infodelete(@PathVariable(required = true) Long idSatellite, Model model) {
		
		model.addAttribute("show_satellite_attr", satelliteService.caricaSingoloElemento(idSatellite));
		return "satellite/delete";
	}
	
	@PostMapping("/delete/{idSatellite}")
	public String delete(@PathVariable(required = true) Long idSatellite, Model model, RedirectAttributes redirectAttrs) {
		
		Satellite satelliteDaRimuovere = satelliteService.caricaSingoloElemento(idSatellite);
		satelliteService.rimuovi(satelliteDaRimuovere);		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/satellite";
	}
	
	@GetMapping("/formedit/{idSatellite}")
	public String formedit(@PathVariable(required = true) Long idSatellite, Model model) {
		
		model.addAttribute("edit_satellite_attr",satelliteService.caricaSingoloElemento(idSatellite));
		return "satellite/edit";
	}
	
	@PostMapping("/edit")
	public String edit(@ModelAttribute("insert_satellite_attr") Satellite satellite, Model model, RedirectAttributes redirectAttrs) {
		satelliteService.aggiorna(satellite);	
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/satellite";
	}
}