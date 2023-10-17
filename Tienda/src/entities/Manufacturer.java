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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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
        return "id: " + getId() + "\nName: " + getName();
    }

}
