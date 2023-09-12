import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class Main {

    private Controller controller;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in).useLocale(Locale.UK);

        Database database = new Database();
        Controller controller = new Controller(database);

        System.out.println("-------Welcome to the SUPERHERO UNIVERSE------");
        System.out.println("1. Add superhero \n" +
                "9. Exit the program");
        String choice = keyboard.nextLine().toLowerCase();


        while (choice.equals("1")) {
            System.out.println("Add superhero by typing its name! ");
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
            controller.addSuperhero(name, realName, superpower, yearCreated, isHuman, strength);

            System.out.println("Do you want to add another superhero? (yes/no)");
            keyboard.nextLine(); //Sikrer os at programmet ser næste line. Programmet ville slutte ellers
            choice = keyboard.nextLine().toLowerCase();
        }

        //Henter alle superheroes fra databasen
        List<Superhero> superheroList = controller.getAllSuperheroes();

        //Udskriver Superhero objekter
        System.out.println("All the amazing superheroes!:");
        for (Superhero superhero : superheroList) {
            if (superhero != null) {
                System.out.println(superhero);
            }
        }
    }
}