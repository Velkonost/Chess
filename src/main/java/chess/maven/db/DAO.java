package chess.maven.db;


import chess.maven.Figure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class DAO extends JdbcDaoSupport implements DAOInterface {
     
    @Autowired
    public DAO(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public void savePlayerOrder(int player) {
       this.getJdbcTemplate().update(
        "INSERT INTO PLAYERS (PLAYER_ID) VALUES(?)", player
       );
    }
    
    @Override
    public void saveStep(int step) {
        this.getJdbcTemplate().update(
        "UPDATE STEPS SET PLAYER_ID='" + step + "' WHERE ID=1"
       );
    }

    @Override
    public List<Integer> getLastStep() {
       return this.getJdbcTemplate().query("SELECT PLAYER_ID FROM STEPS WHERE ID=1",
               (RowMapper<Integer>) new RowMapper<Integer>() {
           @Override
           public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
             
               return rs.getInt("PLAYER_ID");
               
           }       
        });
    }

    @Override
    public void saveField(char[][] field) {
      
    }

    @Override
    public void saveFigures(int side, List<Figure> figures) {
    }

    @Override
    public int getStep() {
        return 0;
    }

    @Override
    public char[][] getField() {
        return null;
    }

    @Override
    public List<Figure> getFigures(int side) {
       return null;
    }
    
}
