package db;

import java.util.List;

public interface DAOInterface {
     void savePlayerOrder(int player);
     void saveStep(int step);

     List<Integer> getLastStep();
}
