package dev.paie.repository;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;
import dev.paie.entite.Cotisation;
import dev.paie.spring.DataSourceMySQLConfig;
import dev.paie.spring.JpaConfig;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { JpaConfig.class,
								DataSourceMySQLConfig.class, 
								ServicesConfig.class }
						)

//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)

public class AvantageRepositoryTest {
	
	@Autowired 
	private AvantageRepository avantageRepository;
	
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		
	// TODO sauvegarder un nouvel avantage
		Avantage nouvelAvantage = new Avantage();
		nouvelAvantage.setCode("maxlamenace");
		nouvelAvantage.setMontant(new BigDecimal(184.6));
		nouvelAvantage.setNom("Flo");
		
		avantageRepository.save(nouvelAvantage);
		
	// TODO vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
	//	Avantage test = avantageRepository.findByCode(nouvelAvantage.getCode());
		Avantage test = avantageRepository.findOne(nouvelAvantage.getId());
		assertTrue(test.equals(nouvelAvantage));
		
	// TODO modifier un avantage
		List<Avantage> tabSqlAvantage = avantageRepository.findAll();
		
		Optional<Avantage> optionAvantage = tabSqlAvantage.stream().filter(ava -> ava.getCode().equals("azerty")).findFirst();
		
		Avantage avantageSelection = null;
		if (optionAvantage != null){
			avantageSelection = optionAvantage.get();
		}
		
		//avantageRepository.se
	// TODO vérifier que les modifications sont bien prises en compte via la méthode findOne
	}

}
