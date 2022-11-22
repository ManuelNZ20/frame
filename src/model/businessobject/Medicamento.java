package model.businessobject;

import java.util.ArrayList;
import model.dataaccessobject.MedicamentoDAO;
import model.transferobject.MedicamentoDTO;

public class Medicamento {

    private MedicamentoDTO medicamentoDTO;
    private MedicamentoDAO medicamentoDAO;

    public Medicamento() {
        medicamentoDAO = new MedicamentoDAO();
        medicamentoDTO = new MedicamentoDTO();
    }

    public int newMedicamento() {
        return medicamentoDAO.getNewIdMed();
    }

    public boolean agregar(String nombre, float precioActual, short cantidadActual, short idCategoria) {
        return medicamentoDAO.agregar(new MedicamentoDTO(0, nombre, precioActual, cantidadActual, idCategoria));
    }

    public boolean eliminar(String id) {
        return medicamentoDAO.eliminar(id);
    }

    public boolean actualizar(int idMedicamento, String nombre, float precioActual, short cantidadActual, short idCategoria) {
        return medicamentoDAO.actualizar(new MedicamentoDTO(idMedicamento, nombre, precioActual, cantidadActual, idCategoria));
    }

    public MedicamentoDTO obtenerMedicamento(String id) {
        return medicamentoDAO.buscar(id);
    }

    public ArrayList<MedicamentoDTO> listaMedicamentos() {
        return medicamentoDAO.getAll();
    }
}
