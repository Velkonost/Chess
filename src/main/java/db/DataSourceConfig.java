package db;

import javax.sql.DataSource;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {
    
    @Bean
    @Autowired
    public DataSource dataSource() {
      DriverManagerDataSource dataSource
        = new DriverManagerDataSource();

      dataSource.setUrl("jdbc:h2:file:~/h2/chess");
      dataSource.setDriverClassName(org.h2.Driver.class.getName());
      dataSource.setUsername("sa");
      dataSource.setPassword("");
      init(dataSource);

      return dataSource;
    }
 
    private void init(DataSource dataSource) {
      JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

      jdbcTemplate.update(
        "CREATE TABLE IF NOT EXISTS STEPS("
          + "ID BIGINT AUTO_INCREMENT PRIMARY KEY,"
          + "PLAYER_ID INT);");
    }

    
//    @Bean
//    ServletRegistrationBean h2servletRegistration() {
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
////                = new ServletRegistrationBean(new WebServlet());
//        registrationBean.addUrlMappings("/console/*");
//        return registrationBean;
//    }

  
}
