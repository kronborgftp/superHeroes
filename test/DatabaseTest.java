import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class DatabaseTest {

    private Database database;

    //Indicates that this method should be run before each test method.
    @BeforeEach
    void setUp() {
        //Instance of Database so each test starts with a clean and fresh Database object.
        database = new Database();
    }

    //test addSuperhero method
    @Test
    void addSuperhero() {
        //Hardcoded example of adding a superhero
        Superhero superhero = new Superhero("Superman", "Joe Biden", "Super duper", 1938, false, 1000);
        database.addSuperhero(superhero);

        List<Superhero> superheroes = database.getAllSuperheroes();
        //Assertions to ensure the superhero was added correctly
        assertEquals(1, superheroes.size());
        assertEquals(superhero, superheroes.get(0));

    }

    //test of getAllSuperheroes method
    @Test
    void getAllSuperheroes() {
        List<Superhero> superheroes = database.getAllSuperheroes();
        //makes sure that it doesn't return a null
        assertNotNull(superheroes);
        assertEquals(0, superheroes.size());
    }

    //Test of search method
    @Test
    void searchSuperheroes() {
        //Hardcoded superheroes (Added for search)
        Superhero superman = new Superhero("Superman", "Clark Kent", "Super yeah", 1938, false, 10000);
        Superhero batman = new Superhero("Batman", "Bruce Wayne", "bat lol", 1940, true, 1000);
        database.addSuperhero(superman);
        database.addSuperhero(batman);

        //Call matchingsuperheroes with 'man' since both names contains it
        List<Superhero> matchingSuperheroes = database.searchSuperheroes("man");
        assertEquals(2, matchingSuperheroes.size());

        //test if mathcingSuperheroes contains superman and batman
        assertTrue(matchingSuperheroes.contains(superman));
        assertTrue(matchingSuperheroes.contains(batman));

        //checking if matchingSuperheroes contains anything that shouldn't be there
        matchingSuperheroes = database.searchSuperheroes("woman");
        assertEquals(0, matchingSuperheroes.size());
    }

    //Test of edit function
    @Test
    void editSuperhero() {
        Superhero superman = new Superhero("Superman", "Clark Kent", "Flight", 1938, true, 100);
        database.addSuperhero(superman);

        Superhero editedSuperman = new Superhero("Superman", "Kal-El", "Flight and Laser Vision", 1938, false, 95);

        // Redirect input for testing purposes (simulate user input)
        System.setIn(new ByteArrayInputStream("2\nKal-El\n3\nFlight and Laser Vision\n4\n1938\n5\nfalse\n6\n95\n".getBytes()));

        // Call the editSuperhero method
        database.editSuperhero();

        // Get the updated superhero from the database
        List<Superhero> superheroes = database.getAllSuperheroes();
        assertEquals(1, superheroes.size());
        assertEquals(editedSuperman, superheroes.get(0));
    }

}