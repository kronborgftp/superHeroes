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

    //getters
    public String getName() {
        return name;
    }
    public String getRealName() {
        return realName;
    }
    public String getSuperPower() {
        return superPower;
    }
    public int getYearCreated() {
        return yearCreated;
    }
    public double getStrength() {
        return strength;
    }
    public boolean isHuman() {
        return isHuman;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }
    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }
    public void setStrength(double strength) {
        this.strength = strength;
    }
    public void setHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }


    public String toString() {

        return "Name: " + name + ". Real name: " + realName + ". Superpower: " + superPower + ". Year created: " + yearCreated + ". Strength in numbers: " + strength + ". Is the super hero human?: " + isHuman;
    }
}
