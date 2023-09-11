public class Database {
    private Superhero[] superheroArray;
    private int size;

  public Database(int capacity) {
      superheroArray = new Superhero[capacity];
      //spor antallet af objekter i databasen
      size = 0;
  }
//adder ny superhero til databasen, hvis der plads
  public void addSuperhero(String name, String realName, String superPower, int yearCreated, boolean isHuman, double strength) {
      if (size < superheroArray.length) {
          //laver ny superhero med parametrene
          Superhero superhero = new Superhero(name, realName, superPower, yearCreated, isHuman, strength);
          //adder den nye superhero til arrayet
          superheroArray[size] = superhero;
          //Keeping track af hvor mange superhelte der er i databasen
          size++;
      } else {
          //Hvis databasen er fuld bliver denne besked vist
          System.out.println("Database full");
      }
  }

  //returnerer alle superheroes til arrayet.
  public Superhero[] getAllSuperheroes() {
      return superheroArray;
  }

}
