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

        int choice = 0;

        System.out.println("1. Add superhero \n" +
                "2. Show list of superheroes \n" +
                "3. Exit the program");

        choice = keyboard.nextInt();

        //loops around until the user chooses to exit the program.
        while (choice != 3) {
            // Using a "Switch Case" instead of while loop.
            switch (choice) {
                case 1:
                    System.out.println("Add superhero by typing its superhero name: ");
                    keyboard.nextLine();
                    String name = keyboard.nextLine();

                    System.out.println("Enter the real name of your superhero!: ");
                    String realName = keyboard.nextLine();

                    System.out.println("Enter the amazing superpower of your superhero: ");
                    String superpower = keyboard.nextLine();

                    System.out.println("Enter what year your superhero was created: ");
                    int yearCreated = keyboard.nextInt();

                    System.out.println("Is your superhero human? (true/false): ");
                    boolean isHuman = keyboard.nextBoolean();

                    System.out.println("Enter the strength of your superhero in numbers: ");
                    double strength = keyboard.nextDouble();

                    //Adds the new superhero to the database
                    controller.addSuperhero(name, realName, superpower, yearCreated, isHuman, strength);
                    break;

                case 2:
                    //Allows the user to display a list of the superheroes, without adding any
                    List<Superhero> superheroList = controller.getAllSuperheroes();
                    System.out.println("All the amazing superheroes: ");
                    for (Superhero superhero : superheroList) {
                        if (superhero != null) {
                            System.out.println(superhero);
                        }
                    }
                    break;

                //allows the user to exit the program
                case 3:
                    System.out.println("Have a nice day.");
                    break;

                default:
                    System.out.println("Please choose one of the option listed above.");
                    break;
            }
        }
    }
}