
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

class DatabaseTest {

    private Database database;


    //Indicates that this method should be run before each test method.
    @BeforeEach
    void setUp() {
        //Instance of Database so each test starts with a clean and fresh Database object.
        database = new Database();
        database.getAllSuperheroes().addAll(List.of(
                new Superhero("Superman", "Clark Kent", "Super yeah", 1938, false, 10000),
                new Superhero("Batman", "Bruce Wayne", "bat lol", 1940, true, 1000)));
    }

    @Test
    void testSaveAndLoadSuperheroesToFile() {
        // Create a temporary file for testing
        String fileName = "test_superheroes.dat";
        Database database = new Database();

        // Add a superhero to the database
        Superhero superhero = new Superhero("TestHero", "Test Real Name", "Test Power", 2000, true, 9.8);
        database.addSuperhero(superhero);

        // Save the superheroes to the file
        database.saveSuperheroesToFile(fileName);

        // Clear the current superheroes in the database
        database = new Database();

        // Load superheroes from the file
        database.loadSuperHeroesFromFile(fileName);

        // Verify that the superhero was loaded successfully
        List<Superhero> loadedSuperheroes = database.getAllSuperheroes();
        assertEquals(1, loadedSuperheroes.size());
        assertEquals(superhero, loadedSuperheroes.get(0));

        // Clean up: delete the temporary file
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    //test addSuperhero method
    @Test
    void addSuperhero() {
        //Hardcoded example of adding a superhero
        Superhero superhero = new Superhero("Spiderman", "Joe Biden", "spider", 1938, true, 1000);
        database.addSuperhero(superhero);

        List<Superhero> superheroes = database.getAllSuperheroes();
        //Assertions to ensure the superhero was added correctly
        assertEquals(3, superheroes.size());
        assertEquals(superhero, superheroes.get(2));

    }

    //test of getAllSuperheroes method
    @Test
    void getAllSuperheroes() {
        List<Superhero> superheroes = database.getAllSuperheroes();
        //makes sure that it doesn't return a null
        assertNotNull(superheroes);
        assertEquals(2, superheroes.size());
    }

    //Test of search method
    @Test
    void searchSuperheroes() {

        //Call matchingsuperheroes with 'man' since both names contains it
        List<Superhero> matchingSuperheroes = database.searchSuperheroes("man");
        assertEquals(2, matchingSuperheroes.size());

        //checking if matchingSuperheroes contains anything that shouldn't be there
        matchingSuperheroes = database.searchSuperheroes("woman");
        assertEquals(0, matchingSuperheroes.size());
    }

    //Test of edit function
    @Test
    void editSuperhero() {

        Superhero spiderman = new Superhero("Spiderman", "Peter Parker", "Flight", 1964, true, 1000);
        database.addSuperhero(spiderman);


        Superhero editedSpiderman = new Superhero("Spiderman", "Peter Parker", "Spider", 1964, true, 1000);


        spiderman = editedSpiderman;


        // Get the updated superhero from the database
        List<Superhero> superheroes = database.getAllSuperheroes();
        assertEquals(3, superheroes.size());
        assertEquals(editedSpiderman, superheroes.get(2));
    }

    @Test
    void removeSuperhero() {

    }

}