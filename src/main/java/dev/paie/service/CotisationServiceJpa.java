package dev.paie.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Service
@Transactional // pour autoriser les transactions sur toutes les méthodes
public class CotisationServiceJpa implements CotisationService {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		em.persist(nouvelleCotisation);
	}

	@Override
	public void mettreAJour(Cotisation cotisation, int vid) {
		Cotisation ancienneCot = em.find(Cotisation.class, vid);
		ancienneCot.setCode(cotisation.getCode());
		ancienneCot.setLibelle(cotisation.getLibelle());
		ancienneCot.setTauxPatronal(cotisation.getTauxPatronal());
		ancienneCot.setTauxSalarial(cotisation.getTauxSalarial());
	}

	@Override
	public List<Cotisation> lister() {
		//première méthode - lourde
//		List<Cotisation> tab = new ArrayList<Cotisation>();
//		Cotisation cotisation;
//		int i = 1;
//		do {
//			cotisation = em.find(Cotisation.class, i);
//			if (cotisation != null){
//				tab.add(cotisation);
//			}
//			i++;
//		}
//		while (cotisation != null);
//		return tab;
		
//		deuxième méthode - querry		
		 Query query = em.createQuery("FROM Cotisation");
		 return (List<Cotisation>)query.getResultList();
	}

}
