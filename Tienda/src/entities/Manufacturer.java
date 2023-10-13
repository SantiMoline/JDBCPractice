package entities;

public class Manufacturer {
    private int id;
    private String name;


    public Manufacturer() {
    }

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getid() {
        return this.id;
    }

    public void setid(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Manufacturer's id must be greater than 0.");
        }
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Manufacturer's name cannot be null or blank.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + getid() + "\nName: " + getName();
    }

}
