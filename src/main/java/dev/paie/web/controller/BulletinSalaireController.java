package dev.paie.web.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationServiceSimple;

@Transactional
@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {
	
	@Autowired
	RemunerationEmployeRepository remunerationEmployeRepository;
	
	@Autowired
	PeriodeRepository periodeRepository;
	
	@Autowired
	BulletinSalaireRepository bulletinSalaireRepository;
	
	@Autowired
	CalculerRemunerationServiceSimple calculerRemunerationServiceSimple;
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/create")
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("listePeriodes", periodeRepository.findAll());
		mv.addObject("listeEmployes", remunerationEmployeRepository.findAll());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/valider") 
	public void valider(HttpServletRequest request) { 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/valider");
		
		BulletinSalaire bulletin = new BulletinSalaire();
		bulletin.setPeriode(periodeRepository.findOne(Integer.parseInt(request.getParameter("periodeId"))));
		bulletin.setRemunerationEmploye(remunerationEmployeRepository.findOne(Integer.parseInt(request.getParameter("employeId"))));
		bulletin.setPrimeExceptionnelle(new BigDecimal(request.getParameter("prime")));
		//String date = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		bulletin.setDateCreation(LocalDateTime.now());
		
		bulletinSalaireRepository.save(bulletin);
		
	}

	@RequestMapping(method = RequestMethod.GET, path = "/liste")
	public ModelAndView listeBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listeBulletins");
		List<BulletinSalaire> tabBulletins = new ArrayList<BulletinSalaire>();
		for(BulletinSalaire bulletin : bulletinSalaireRepository.findAll()){
			bulletin.setResultatCalculRemuneration(calculerRemunerationServiceSimple.calculer(bulletin));
			tabBulletins.add(bulletin);
		}
		mv.addObject("listeBulletins", tabBulletins);
		return mv;
	}

}
