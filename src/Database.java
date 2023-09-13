import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class Database {
    Scanner keyboard = new Scanner(System.in).useLocale(Locale.UK);

    private List<Superhero> superheroList;

    public Database() {
        superheroList = new ArrayList<>();

    }
    public void addSuperhero(Superhero superhero) {
        superheroList.add(superhero);
    }

    public List<Superhero> getAllSuperheroes() {
        return superheroList;
    }
    public List<Superhero> searchSuperheroes(String userSearchCriteria) {
        List<Superhero> matchingSuperheroes = new ArrayList<>();

        for (Superhero superhero : getAllSuperheroes()) {
            if(superhero != null && superhero.getName().toLowerCase().contains(userSearchCriteria)) {
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

            System.out.println("Enter the index of the superhero you want to edit: ");
            int editSuperheroIndex = keyboard.nextInt();
            keyboard.nextLine();

            if (editSuperheroIndex >= 0 && editSuperheroIndex < matchingSuperheroes.size()) {
                Superhero editSuperhero = matchingSuperheroes.get(editSuperheroIndex);

                System.out.println("Editing superhero: " + editSuperhero.getName());
                System.out.println("Choose an attribute to edit:");
                System.out.println("1. Name");
                System.out.println("2. Real Name");
                System.out.println("3. Superpower");
                System.out.println("4. Year Created");
                System.out.println("5. Human Status");
                System.out.println("6. Strength");

                int attributeChoice = keyboard.nextInt();
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
                        int newYearCreated = keyboard.nextInt();
                        editSuperhero.setYearCreated(newYearCreated);
                        break;
                    case 5:
                        System.out.println("Is the superhero human? (true/false): ");
                        boolean newIsHuman = keyboard.nextBoolean();
                        editSuperhero.setIsHuman(newIsHuman);
                        break;
                    case 6:
                        System.out.println("Enter the new strength of the superhero in numbers: ");
                        double newStrength = keyboard.nextDouble();
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
}