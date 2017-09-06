package dev.paie.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.spring.DataSourceMySQLConfig;
import dev.paie.spring.JpaConfig;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { JpaConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)

public class CotisationServiceJpaTest {

	@Autowired
	private CotisationService cotisationService;

	// permet de récupérer une liste de tous les objets de type Cotisation créé dans le context
	// (objets définis par cotisations-imposables.xml et cotisations-non-imposables.xml)
	@Autowired
	private List<Cotisation> nouvelleCotisation;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		// TODO sauvegarder une nouvelle cotisation
		// récupération des cotisations déjà définie (d'où l'utilisation de Autowired sur List<Cotisation>)
		List<Cotisation> tabSql = cotisationService.lister();
		int tailleAvant =tabSql.size();
		
		cotisationService.sauvegarder(nouvelleCotisation.get(6));
		cotisationService.sauvegarder(nouvelleCotisation.get(10));
		cotisationService.sauvegarder(nouvelleCotisation.get(7));
		
		tabSql = cotisationService.lister();
		int tailleApres = tabSql.size();
		
		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation via la méthode lister*
		assertEquals(tailleAvant + 3, tailleApres);
		assertTrue(tabSql.contains(nouvelleCotisation.get(10)));

		// TODO modifier une cotisation : sélection de l'objet à modifier par un stream
		String code = nouvelleCotisation.get(10).getCode();
		Optional<Cotisation> optionCot = tabSql.stream().filter(cot -> cot.getCode().equals(code)).findFirst();
		
		Cotisation cotisationSelection = null;
		if (optionCot != null){
			cotisationSelection = optionCot.get();
		}

		cotisationService.mettreAJour(nouvelleCotisation.get(18), cotisationSelection.getId());

		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		tabSql = cotisationService.lister();
		assertTrue(tabSql.contains(nouvelleCotisation.get(18)));

	}

}
