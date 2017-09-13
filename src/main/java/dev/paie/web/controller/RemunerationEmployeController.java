package dev.paie.web.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationServiceSimple;

@Controller
@RequestMapping({ "/employes", "/bulletins" })
public class RemunerationEmployeController {

	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	@Autowired
	ProfilRemunerationRepository profilRemunerationRepository;
	
	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	RemunerationEmployeRepository remunerationEmployeRepository;
	
	@Autowired
	PeriodeRepository periodeRepository;
	
	@Autowired
	BulletinSalaireRepository bulletinSalaireRepository;
	
	@Autowired
	ResultatCalculRemuneration resultatCalculRemuneration;
	
	@Autowired
	CalculerRemunerationServiceSimple calculerRemunerationServiceSimple;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("listeEntreprises", entrepriseRepository.findAll());
		mv.addObject("listeProfils", profilRemunerationRepository.findAll());
		mv.addObject("listeGrades", gradeRepository.findAll());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/validerForm") 
	public void validerForm(HttpServletRequest request) { 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/validerForm");
		
		RemunerationEmploye employe = new RemunerationEmploye();
		employe.setMatricule(request.getParameter("matricule"));
		employe.setGrade(gradeRepository.findOne(Integer.parseInt(request.getParameter("gradeId"))));
		employe.setEntreprise(entrepriseRepository.findOne(Integer.parseInt(request.getParameter("entrepriseId"))));
		employe.setProfilRemuneration(profilRemunerationRepository.findOne(Integer.parseInt(request.getParameter("profilId"))));
		
		remunerationEmployeRepository.save(employe);
		
	}

	@RequestMapping(method = RequestMethod.GET, path = "/afficher")
	public ModelAndView listeEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listeEmployes");
		mv.addObject("listeEmployes", remunerationEmployeRepository.findAll());
		return mv;
	}

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
		List<BulletinSalaire> tabBulletin = bulletinSalaireRepository.findAll();
		List<ResultatCalculRemuneration> tabResultat = new ArrayList<ResultatCalculRemuneration>();
		for(BulletinSalaire bulletin : tabBulletin){
			resultatCalculRemuneration = calculerRemunerationServiceSimple.calculer(bulletin);
			tabResultat.add(resultatCalculRemuneration);
		}
		mv.addObject("listeResultatBulletins", tabResultat);
		return mv;
	}

}
