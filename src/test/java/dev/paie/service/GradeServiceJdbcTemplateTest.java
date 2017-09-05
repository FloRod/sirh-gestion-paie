package dev.paie.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.entite.Grade;
import dev.paie.spring.DataSourceMySQLConfig;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { DataSourceMySQLConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {

	@Autowired
	private GradeService gradeService;
	@Autowired
	private Grade nouveauGrade;
	
	@Autowired
	private Grade updateGrade;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		// TODO sauvegarder un nouveau grade
		List<Grade> tabSql = new ArrayList<Grade>();
		tabSql = gradeService.lister();
		
		int avant = tabSql.size();
		
		nouveauGrade.setCode("test");
		nouveauGrade.setNbHeuresBase(new BigDecimal(63.5));
		nouveauGrade.setTauxBase(new BigDecimal(100));
		gradeService.sauvegarder(nouveauGrade);
		
		tabSql = gradeService.lister();
		int apres = tabSql.size();
		assertEquals(avant +1, apres);
		
		
		// TODO vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		
		
		assertTrue(tabSql.contains(nouveauGrade));

		// TODO modifier un grade
		// 1 - ajout de l'élément util dans la table
		String code = "util";
		nouveauGrade.setCode(code);
		nouveauGrade.setNbHeuresBase(new BigDecimal(6.3));
		gradeService.sauvegarder(nouveauGrade);
		
		// récupération des données de la table avec la fonction lister
		tabSql = gradeService.lister();
		assertTrue(tabSql.contains(nouveauGrade));
	
		// 2- tri des objets Grade selon leur code :  "util"
		List<Grade> tri = tabSql.stream().filter(grad -> grad.getCode().equals(code)).collect(Collectors.toList());
		
		// 3- Modification de nouveauGrade
		updateGrade.setCode("yahoo");
		updateGrade.setNbHeuresBase(new BigDecimal(1234.0));
		updateGrade.setTauxBase(new BigDecimal(456.0));

		// 4- Mise à jour de l'élément dans la BD retenu dans le tableau tri
		gradeService.mettreAJour(updateGrade, tri.get(0).getId());
		
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		tabSql = gradeService.lister();
		assertTrue(tabSql.contains(nouveauGrade));
		

		
	}

}
