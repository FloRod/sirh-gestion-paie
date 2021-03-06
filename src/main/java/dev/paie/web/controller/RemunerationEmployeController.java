package dev.paie.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	@Autowired
	ProfilRemunerationRepository profilRemunerationRepository;
	
	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	RemunerationEmployeRepository remunerationEmployeRepository;
	


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

	

}
