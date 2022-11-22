package model.businessobject;

import java.util.ArrayList;
import model.dataaccessobject.ProveedorDAO;
import model.transferobject.ProveedorDTO;

public class Proveedor {

    private ProveedorDTO proveedorDTO;
    private ProveedorDAO proveedorDAO;

    public Proveedor() {
        proveedorDTO = new ProveedorDTO();
        proveedorDAO = new ProveedorDAO();
    }

    public boolean agregar(String nombre) {
        return proveedorDAO.agregar(new ProveedorDTO(0, nombre));
    }

    public boolean eliminar(String id) {
        return proveedorDAO.eliminar(id);
    }

    public boolean actualizar(int idProveedor, String nombre) {
        return proveedorDAO.actualizar(new ProveedorDTO(idProveedor, nombre));
    }

    public ProveedorDTO obtenerDTO(String id) {
        return proveedorDAO.buscar(id);
    }

    public ArrayList<ProveedorDTO> listaProveedores() {
        return proveedorDAO.getAll();
    }
}
