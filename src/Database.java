
import java.io.*;
import java.util.*;

public class Database {
    Scanner keyboard = new Scanner(System.in).useLocale(Locale.UK);
    private List<Superhero> superheroList;
    private boolean dataChanged;

    public Database() {
        superheroList = new ArrayList<>();
        dataChanged = false;
    }

    public void saveSuperheroesToFile(String fileName) {
        if (!dataChanged) {
            System.out.println("No changes to data. Not saving to file.");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(superheroList);
            System.out.println("Superheroes saved to file.");
            dataChanged = false; // Reset the flag after saving
        } catch (IOException e) {
            System.out.println("Error saving superheroes to file " + e.getMessage());
        }
    }

    public void loadSuperHeroesFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            superheroList = (List<Superhero>) ois.readObject();
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading superheroes from file: " + e.getMessage());
        }
    }

    public void addSuperhero(Superhero superhero) {
        superheroList.add(superhero);
        dataChanged = true;
    }

    public List<Superhero> getAllSuperheroes() {
        return superheroList;
    }

    public List<Superhero> searchSuperheroes(String userSearchCriteria) {
        List<Superhero> matchingSuperheroes = new ArrayList<>();

        for (Superhero superhero : getAllSuperheroes()) {
            if (superhero != null && superhero.getName().toLowerCase().contains(userSearchCriteria)) {
                matchingSuperheroes.add(superhero);
            }
        }

        return matchingSuperheroes;
    }


    public void editSuperhero() {
        System.out.println("Enter name or part of the name of the superhero you want to edit");

        String editSuperheroCriteria = keyboard.nextLine().toLowerCase();

        List<Superhero> matchingSuperheroes = searchSuperheroes(editSuperheroCriteria);

        if (!matchingSuperheroes.isEmpty()) {
            System.out.println("Matching superheroes to edit: ");
            for (int i = 0; i < matchingSuperheroes.size(); i++) {
                Superhero superhero = matchingSuperheroes.get(i);
                System.out.println(i + ". " + superhero.getName());
            }

            System.out.println("Enter the number of the superhero you want to edit: ");
            int editSuperheroNumber = getIntInput();
            keyboard.nextLine();

            if (editSuperheroNumber >= 0 && editSuperheroNumber < matchingSuperheroes.size()) {
                Superhero editSuperhero = matchingSuperheroes.get(editSuperheroNumber);

                System.out.println("Editing superhero: " + editSuperhero.getName());
                System.out.println(("""
                        Choose an attribute to edit: \s
                        1. Name\s
                        2. Real name\s
                        3. Superpower\s
                        4. Year Created\s
                        5. Human or not\s
                        6. Strength number"""));

                int attributeChoice = getIntInput();
                keyboard.nextLine();

                switch (attributeChoice) {
                    case 1:
                        System.out.println("Enter the new superhero name: ");
                        String newName = keyboard.nextLine();
                        editSuperhero.setName(newName);
                        break;
                    case 2:
                        System.out.println("Enter the new real name of the superhero: ");
                        String newRealName = keyboard.nextLine();
                        editSuperhero.setRealName(newRealName);
                        break;
                    case 3:
                        System.out.println("Enter the new amazing superpower of the superhero: ");
                        String newSuperpower = keyboard.nextLine();
                        editSuperhero.setSuperpower(newSuperpower);
                        break;
                    case 4:
                        System.out.println("Enter the new year the superhero was created: ");
                        int newYearCreated = getIntInput();
                        editSuperhero.setYearCreated(newYearCreated);
                        break;
                    case 5:
                        System.out.println("Is the superhero human? (true/false): ");
                        boolean newIsHuman = getBooleanInput();
                        editSuperhero.setIsHuman(newIsHuman);
                        break;
                    case 6:
                        System.out.println("Enter the new strength of the superhero in numbers: ");
                        double newStrength = getDoubleInput();
                        editSuperhero.setStrength(newStrength);
                        break;
                    default:
                        System.out.println("Invalid choice. No attribute edited.");
                        break;
                }

                System.out.println("Superhero details updated.");
            } else {
                System.out.println("Invalid index. Please enter a valid index.");
            }
        } else {
            System.out.println("No matching superhero found for editing.");
        }
    }

    public void removeSuperhero() {
        System.out.println("Type the superhero name or part of the name of your superhero that you want to remove: ");
        String userSearchCriteria = keyboard.nextLine().toLowerCase();

        List<Superhero> matchingSuperheroes = searchSuperheroes(userSearchCriteria);

        if (!matchingSuperheroes.isEmpty()) {
            System.out.println("Superhero(s) found: ");
            for (int i = 0; i < matchingSuperheroes.size(); i++) {
                Superhero superhero = matchingSuperheroes.get(i);
                System.out.println(i + ". " + superhero.getName());
            }

            System.out.println("Enter the number of the superhero you want to remove: ");
            int userSuperheroInt = getIntInput();
            keyboard.nextLine();

            if (userSuperheroInt >= 0 && userSuperheroInt < matchingSuperheroes.size()) {
                // Remove the selected superhero by index
                Superhero superheroToRemove = matchingSuperheroes.get(userSuperheroInt);
                if (superheroToRemove.getName().equals(userSearchCriteria)) {
                    matchingSuperheroes.remove(superheroToRemove);
                    System.out.println("Superhero removed");
                } else {
                    System.out.println("The selected superhero does not match the search criteria");
                }
            } else {
                System.out.println("Invalid superhero number.");
            }
        } else {
            System.out.println("No matching superheroes found.");
        }
    }



    private int getIntInput() {
        while (true) {
            try {
                return keyboard.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer/whole number. ");
                keyboard.nextLine();
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