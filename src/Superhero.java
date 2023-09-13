public class Superhero {
    private String name;
    private String realName;
    private String superPower;
    private int yearCreated;
    private boolean isHuman;
    private double strength;

    public Superhero(String name, String realName, String superPower, int yearCreated, boolean isHuman, double strength) {
        this.name = name;
        this.realName = realName;
        this.superPower = superPower;
        this.yearCreated = yearCreated;
        this.isHuman = isHuman;
        this.strength = strength;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {

        return "Name: " + name + ". Real name: " + realName + ". Superpower: " + superPower + ". Year created: " + yearCreated + ". Strength in numbers: " + strength + ". Is the super hero human?: " + isHuman;
    }
}
