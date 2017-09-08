package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"dev.paie.service", "dev.paie.util", "dev.paie.entite", "dev.paie.controller"})
@ImportResource(value = {"classpath:cotisations-imposables.xml", "classpath:cotisations-non-imposables.xml" , "classpath:entreprises.xml", "classpath:grades.xml" , "classpath:profils-remuneration.xml"})
@EnableJpaRepositories("dev.paie.repository")
public class ServicesConfig {

}
