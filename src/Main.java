import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in).useLocale(Locale.UK);

        //Max antal superheroes
        int databaseCapacity = 5;

        Database database = new Database(databaseCapacity);

        System.out.println("-------Welcome to the SUPERHERO UNIVERSE------");
        System.out.println("1. Do you want to add a superhero? (type: yes) \n" +
                "9. Exit the program by typing 'no'");
        String choice = keyboard.nextLine().toLowerCase();


        while (choice.equals("yes")) {
            System.out.println("1. Add superhero by typing its name! ");
            String name = keyboard.nextLine();

            System.out.println("Enter the real name of your superhero!: ");
            String realName = keyboard.nextLine();

            System.out.println("Enter the amazing superpower of your superhero!: ");
            String superpower = keyboard.nextLine();

            System.out.println("Enter what year your superhero was created!: ");
            int yearCreated = keyboard.nextInt();

            System.out.println("Is your superhero human? (true/false): ");
            boolean isHuman = keyboard.nextBoolean();

            System.out.println("Enter the strength of your superhero in numbers!: ");
            double strength = keyboard.nextDouble();

            //adder superheroen til databasen.
            database.addSuperhero(name, realName, superpower, yearCreated, isHuman, strength);

            System.out.println("Do you want to add another superhero? (yes/no)");
            keyboard.nextLine(); //Sikrer os at programmet ser næste line. Programmet ville slutte ellers
            choice = keyboard.nextLine().toLowerCase();
        }

        //Henter alle superheroes fra databasen
        Superhero[] superheroArray = database.getAllSuperheroes();

        //Udskriver Superhero objekter
        System.out.println("All the amazing superheroes!:");
        for (Superhero superhero : superheroArray) {
            if (superhero != null) {
                System.out.println(superhero);
            }
        }
    }
}