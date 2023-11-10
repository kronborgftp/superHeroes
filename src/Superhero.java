import java.io.Serial;
import java.io.Serializable;

public class Superhero implements Serializable{

    @Serial
    private static final long serialVersionUID = -1772422366595125179L;

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

    public int getYearCreated() {
        return this.yearCreated;
    }

    public double getStrength() {
        return this.strength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setSuperpower(String superPower) {
        this.superPower = superPower;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public void setIsHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public String toString() {

        return "Name: " + name + ". Real name: " + realName + ". Superpower: " + superPower + ". Year created: " + yearCreated + ". Strength in numbers: " + strength + ". Is the super hero human?: " + isHuman;
    }
}
