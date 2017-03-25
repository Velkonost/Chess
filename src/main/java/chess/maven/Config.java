package chess.maven;

import chess.maven.figures.King;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    
    @Bean
    public King whiteKing() {
        return new King();
    }
    
}
