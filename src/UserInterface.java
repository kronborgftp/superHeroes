import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    public void startProgram() {
        Scanner keyboard = new Scanner(System.in).useLocale(Locale.UK);

        Database database = new Database();
        Controller controller = new Controller(database);

        System.out.println("-------Welcome to the SUPERHERO UNIVERSE------");

        int choice = 0;

        //loops around until the user chooses to exit the program.
        while (choice != 5) {

            System.out.println("""
                    1. Add superhero\s
                    2. Show list of superheroes\s
                    3. Search for a superhero\s
                    4. Edit existing superheroes\s
                    5. Exit the program""");

            choice = keyboard.nextInt();


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


                case 3:
                    System.out.println("Type the superhero name or part of the name of your superhero: ");
                    keyboard.nextLine();
                    String userSearchCriteria = keyboard.nextLine().toLowerCase();

                    List<Superhero> matchingSuperheroes = database.searchSuperheroes(userSearchCriteria);

                    //Switched for each loop out for a normal for loop to display index of the superheroes.
                    if(!matchingSuperheroes.isEmpty()) {
                        System.out.println("Superhero(s) found: ");
                        for (int i = 0; i < matchingSuperheroes.size(); i++) {
                            Superhero superhero = matchingSuperheroes.get(i);
                                System.out.println(i + ". " + superhero.getName());
                        }

                        System.out.println("Enter the number of the superhero you want to view: ");
                        int userSuperheroInt = keyboard.nextInt();

                        //checking if userSuperheroInt is within the range (greater than or equal to 0 and less than the number of matching superheroes)
                        if (userSuperheroInt >= 0 && userSuperheroInt < matchingSuperheroes.size()) {
                            //if the index is valid we retrieve the data from matchingSuperheroes
                            Superhero selectedSuperhero = matchingSuperheroes.get(userSuperheroInt);
                            if (selectedSuperhero != null) {
                                System.out.println("Superhero Details: ");
                                System.out.println(selectedSuperhero);
                            } else {
                                System.out.println("Superhero not found");
                            }
                        } else {
                            System.out.println("Invalid number. Please enter a valid number.");
                        }
                    } else {
                        System.out.println("No matching superhero found.");
                    }
                    break;

                case 4:
                    //edit existing superhero
                    database.editSuperhero();

                    break;

                //allows the user to exit the program
                case 5:
                    System.out.println("Have a nice day.");
                    break;

                default:
                    System.out.println("Please choose one of the option listed above.");
                    do {
                        choice = keyboard.nextInt();
                    } while (choice < 1 || choice > 5);
                    break;
            }
        }
    }
}
