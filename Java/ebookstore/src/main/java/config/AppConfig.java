package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ebookstore.utils.HibernateUtil;

import javax.persistence.EntityManager;

@Configuration
@EnableWebMvc
@ComponentScan("ebookstore")
@PropertySource("classpath:/config.properties")
public class AppConfig {

    @Bean
    public Logger logger(){
        final Logger LOGGER = LogManager.getLogger();
        return LOGGER;
    }

    @Bean
    public EntityManager em(){
        EntityManager em = HibernateUtil.getEm();
        return em;
    }
}
