package dev.paie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.repository.CotisationRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilRemunerationRepository;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {
	
	@Autowired
	private List<Cotisation> cotisations;
	
	@Autowired
	private List<Entreprise> entreprises;
	
	@Autowired
	private List<Grade> grades;
	
	@Autowired
	private List<ProfilRemuneration> profilRemunerations;
	
	@Autowired
	private CotisationRepository cotisationRepository;
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private ProfilRemunerationRepository profilRemunerationRepository;
	
	@Autowired
	private PeriodeRepository periodeRepository;

	@Override
	public void initialiser() {
		
		for (Cotisation i : cotisations){
			cotisationRepository.save(i);
		}
		
		for (Entreprise i : entreprises){
			entrepriseRepository.save(i);
		}
		
		for (Grade i : grades){
			gradeRepository.save(i);
		}
		
		for (ProfilRemuneration i : profilRemunerations){
			profilRemunerationRepository.save(i);
		}
		
		for (int i = 1 ; i < 13; i++){
			periodeRepository.save(new Periode(i));
		}

	}

}
