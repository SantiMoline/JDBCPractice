package persistance;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Manufacturer;

public class ManufacturerDAO extends DAO {
    
    public void saveManufacturer(Manufacturer man) throws SQLException {
        if (man == null) {
            throw new IllegalArgumentException("Manufacturer cannot be null.");
        }
        String query = "INSERT INTO fabricante(nombre) " + "VALUES( '" + man.getName() + "');";
        insertUpdateDelete(query);
    }

    public void updateManufacturer(Manufacturer man) throws SQLException {
        if (man == null) {
            throw new IllegalArgumentException("Product cannot be null.");      
        }
        String query = "UPDATE fabricante SET " + 
            "nombre = '" + man.getName() + "', " +
            "WHERE codigo = " + man.getId() + ";";
        
        insertUpdateDelete(query);
    }

    public void deleteManufacturer(int id) throws SQLException {
        String query = "DELETE FROM fabricante WHERE codigo = " + id;
        insertUpdateDelete(query);
    }

    public Manufacturer getManufacturerById(int id) throws SQLException {
        try {
            String query = "SELECT * FROM fabricante WHERE codigo = " + id;
            queryDatabase(query);
            Manufacturer man = null;
            while (result.next()) {
                man = mapManufacturer();     
            }
            disconnectDatabase();                
            return man;
        } catch (SQLException e) {
            disconnectDatabase();
            throw e;
        }
    }

    public ArrayList<Manufacturer> getManufacturers() throws SQLException {
        try {
            String query = "SELECT * FROM fabricante";
            queryDatabase(query);
            ArrayList<Manufacturer> manufacturers = new ArrayList<>();

            while (result.next()) {
                Manufacturer man = mapManufacturer();
                manufacturers.add(man);
            }
            disconnectDatabase();
            return manufacturers;
        } catch (SQLException e) {
            disconnectDatabase();
            throw e;
        }
    }

    public ArrayList<Manufacturer> getManufacturerContaining(String string) throws SQLException {
        try {
            String query = "SELECT * FROM fabricante WHERE nombre LIKE '%" + string + "%';";
            queryDatabase(query);
            ArrayList<Manufacturer> manufacturers = new ArrayList<>();
            while (result.next()) {
                Manufacturer man = mapManufacturer();
                manufacturers.add(man);
            }
            disconnectDatabase();
            return manufacturers;
        } catch (SQLException e) {
            disconnectDatabase();
            throw e;
        }
    }

    private Manufacturer mapManufacturer() throws SQLException {
        Manufacturer man = new Manufacturer();
        man.setId(result.getInt("codigo"));
        man.setName(result.getString("nombre"));
        return man;
    }

    public int maxManufacturerId() throws SQLException {
        try {
            String query = "SELECT MAX(codigo) FROM fabricante;";
            queryDatabase(query);
            int maxId = 0;
            if (result.next())
                maxId = result.getInt(1);
            disconnectDatabase();
            return maxId;
        } catch (SQLException e) {
            disconnectDatabase();
            throw e;
        }


    }
}
