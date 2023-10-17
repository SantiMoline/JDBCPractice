package services;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Manufacturer;
import persistance.ManufacturerDAO;

public class ManufacturerService {
    
    private ManufacturerDAO dao;


    public ManufacturerService() {
        dao = new ManufacturerDAO();
    }

    public Manufacturer createManufacturer(String name) {
        Manufacturer man = new Manufacturer();
        man.setName(name);
        
        return man;
    }

    public void persistManufacturer(Manufacturer man) throws SQLException {
        dao.saveManufacturer(man);
    }

    public void updateManufacturer(int id, Manufacturer man) throws SQLException {
        Manufacturer currentManufacturer = dao.getManufacturerById(id);
        if (currentManufacturer == null) {
            throw new IllegalArgumentException("There is no Manufacturer with the chosen Id.");
        }
        dao.updateManufacturer(man);
    }

    public ArrayList<Manufacturer> getManufacturers() throws SQLException {
        return dao.getManufacturers();
    }
    
    public ArrayList<Manufacturer> getManufacturersContaining(String word) throws SQLException {
        return dao.getManufacturerContaining(word);
    }

    public int getMaxManufacturerId() throws SQLException {
        return dao.maxManufacturerId();
    }
}
