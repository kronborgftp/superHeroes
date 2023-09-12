import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Superhero> superheroList;

    public Database() {
        superheroList = new ArrayList<>();

    }
    public void addSuperhero(Superhero superhero) {
        superheroList.add(superhero);
    }

    public List<Superhero> getAllSuperheroes() {
        return superheroList;
    }
}