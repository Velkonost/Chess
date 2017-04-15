package chess.maven;

import chess.maven.figures.King;
import chess.maven.interfaces.GameInterface;
import db.DAO;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class Config {
    
    @Bean
    @Autowired
    public King whiteKing(GameInterface game) {
        return new King(game, 0, 0, 0);
    }
    
    @Bean
    @Autowired
    public King blackKing(GameInterface game) {
        return new King(game, 1, 7, 7);
    }
    
    
    @Bean 
    @Autowired
    public DAO dao(DataSource dataSource) {
        return new DAO(dataSource);
    }
    
    
    
    
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
}
