import java.util.*;


public class UserInterface {
    private final Scanner keyboard = new Scanner(System.in).useLocale(Locale.UK);

    private final Database database = new Database();
    private final Controller controller = new Controller(database);

    public void startProgram() {
        int choice;

        database.loadSuperHeroesFromFile("superheroes.txt");
        do {
            System.out.println("-------Welcome to the SUPERHERO UNIVERSE------");
            System.out.println("""
                    1. Add superhero
                    2. Show list of superheroes
                    3. Show all superheroes sorted
                    4. Search for a superhero
                    5. Edit a superhero
                    6. Remove a superhero
                    7. Exit the program""");

            choice = getIntInput();

            switch (choice) {
                case 1:
                    userAddSuperhero(controller);
                    break;
                case 2:
                    showSuperheroList(controller);
                    break;
                case 3:
                    showSuperheroListSorted(controller);
                    break;
                case 4:
                    userSearchSuperhero(database);
                    break;
                case 5:
                    database.editSuperhero();
                    break;
                case 6:
                    database.removeSuperhero();
                    break;

                case 7:
                    System.out.println("Have a nice day.");
                    break;
                default:
                    System.out.println("Please choose one of the options listed above.");
                    break;
            }

        } while (choice != 7);

        database.saveSuperheroesToFile("superheroes.txt");
    }

    public void userAddSuperhero(Controller controller) {
        System.out.println("Add superhero by typing its superhero name: ");
        keyboard.nextLine();
        String name = keyboard.nextLine();

        System.out.println("Enter the real name of your superhero!: ");
        String realName = keyboard.nextLine();

        System.out.println("Enter the amazing superpower of your superhero: ");
        String superpower = keyboard.nextLine();

        System.out.println("Enter what year your superhero was created: ");
        int yearCreated = getIntInput();

        System.out.println("Is your superhero human? (true/false): ");
        keyboard.nextLine(); //clearing input buffer - the error message would be displayed before the user typed anything.
        boolean isHuman = getBooleanInput();


        System.out.println("Enter the strength of your superhero in numbers: ");
        double strength = getDoubleInput();

        //Adds the new superhero to the database
        controller.addSuperhero(name, realName, superpower, yearCreated, isHuman, strength);

    }

    public void showSuperheroList(Controller controller){
        List<Superhero> superheroList = controller.getAllSuperheroes();

        //Sort the superheroes by name
        Collections.sort(superheroList, (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));

        System.out.println("All the amazing superheroes: ");
        for (Superhero superhero : superheroList) {
            if (superhero != null) {
                System.out.println(superhero);
            }
        }
    }

    public void showSuperheroListSorted(Controller controller) {
        System.out.println("Choose the primary attribute to sort by: ");
        printAttributesMenu();

        int primaryAttributeChoice = getIntInput();

        System.out.println("Choose the secondary attribute to sort by: ");
        printAttributesMenu();

        int secondaryAttributeChoice = getIntInput();

        List<Superhero> superheroList = controller.getAllSuperheroes();
        sortSuperheroesByMultipleAttributes(superheroList, primaryAttributeChoice, secondaryAttributeChoice);

        System.out.println("All the amazing superheroes sorted by " + getAttributeName(primaryAttributeChoice) +
                " and then by " + getAttributeName(secondaryAttributeChoice) + ": ");

        for (Superhero superhero : superheroList) {
            if (superhero != null) {
                System.out.println(superhero);
            }
        }
    }

    private void printAttributesMenu() {
        System.out.println("1. Name");
        System.out.println("2. Year Created");
        System.out.println("3. Strength");
    }

    private void sortSuperheroesByMultipleAttributes(List<Superhero> superheroList, int primaryAttribute, int secondaryAttribute) {
        Collections.sort(superheroList, (s1, s2) -> {
            int primaryComparison = compareAttributes(s1, s2, primaryAttribute);
            if (primaryComparison == 0) {
                // If primary attributes are the same, use secondary attribute for comparison
                return compareAttributes(s1, s2, secondaryAttribute);
            } else {
                return primaryComparison;
            }
        });
    }

    private int compareAttributes(Superhero s1, Superhero s2, int attributeChoice) {
        switch (attributeChoice) {
            case 1:
                return s1.getName().compareToIgnoreCase(s2.getName());
            case 2:
                return Integer.compare(s1.getYearCreated(), s2.getYearCreated());
            case 3:
                return Double.compare(s1.getStrength(), s2.getStrength());
            default:
                return 0;
        }
    }

    private String getAttributeName(int attributeChoice) {
        switch (attributeChoice) {
            case 1:
                return "Name";
            case 2:
                return "Year Created";
            case 3:
                return "Strength";
            default:
                return "Unknown Attribute";
        }
    }


    public void userSearchSuperhero(Database database) {
        System.out.println("Type the superhero name or part of the name of your superhero: ");
        keyboard.nextLine();
        String userSearchCriteria = keyboard.nextLine().toLowerCase();

        List<Superhero> matchingSuperheroes = database.searchSuperheroes(userSearchCriteria);

        //Switched for each loop out for a normal for loop to display index of the superheroes.
        if (!matchingSuperheroes.isEmpty()) {
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
    }

    private int getIntInput() {
        while (true) {
            try {
                return keyboard.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer/whole number.");
                keyboard.nextLine(); // consume the invalid input
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            try {
                return keyboard.nextDouble();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number");
                keyboard.nextLine();
            }
        }
    }

    private boolean getBooleanInput() {
        while (true) {
            String input = keyboard.nextLine().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            } else {
                System.out.println("Invalid input. Please enter either 'true' or 'false': ");
            }
        }
    }
}
