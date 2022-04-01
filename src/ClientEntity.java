import java.time.LocalDate;
import java.util.ArrayList;

public class ClientEntity {
    public int id;
    public String name;
    public LocalDate birthDate;
    public int travels;
    public ArrayList<Integer> foodsHistory = new ArrayList<Integer>(0);
    public ArrayList<Integer> foodsFullHistory = new ArrayList<Integer>(0);
}
