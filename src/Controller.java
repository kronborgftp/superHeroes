import java.util.List;

class Controller {
    private final Database database;

    public Controller(Database database) {
        this.database = database;
    }

    public void addSuperhero(String name, String realName, String superPower, int yearCreated, boolean isHuman, double strength) {
        Superhero superhero = new Superhero(name, realName, superPower, yearCreated, isHuman, strength);
        database.addSuperhero(superhero);
    }

    public List<Superhero> getAllSuperheroes() {
        return database.getAllSuperheroes();
    }
}
