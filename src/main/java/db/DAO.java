package db;


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
        "INSERT INTO STEPS(PLAYER_ID) VALUES(?)", step
       );
    }

    @Override
    public List<Integer> getLastStep() {
       return this.getJdbcTemplate().query("SELECT PLAYER_ID FROM STEPS",
               (RowMapper<Integer>) new RowMapper<Integer>() {
           @Override
           public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
               return rs.getInt("PLAYER_ID");
           }       
        });
    }
    
}
