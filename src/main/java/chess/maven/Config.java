package chess.maven;

import chess.maven.figures.King;
import chess.maven.interfaces.GameInterface;
import db.DAO;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        System.out.print("123");
        return new DAO(dataSource);
    }
}
