package model.businessobject;

import model.dataaccessobject.MedicamentoProveedorDAO;
import model.transferobject.MedicamentoProveedorDTO;
import java.sql.Timestamp;
import java.util.ArrayList;

public class MedicamentoProveedor {

    private MedicamentoProveedorDTO mpdto;
    private MedicamentoProveedorDAO mpdao;

    public MedicamentoProveedor() {
        mpdto = new MedicamentoProveedorDTO();
        mpdao = new MedicamentoProveedorDAO();
    }

    public boolean agregar(float precioInicial, short cantidadInicial, int idProveedor, int idMedicamento, Timestamp fecha) {
        return mpdao.agregar(new MedicamentoProveedorDTO(0, precioInicial, cantidadInicial, idProveedor, idMedicamento, fecha));
    }

    public boolean eliminar(String id) {
        return mpdao.eliminar(id);
    }

    public boolean actualizar(int idMedicamentoProveedor, float precioInicial, short cantidadInicial, int idProveedor, int idMedicamento, Timestamp fecha) {
        return mpdao.actualizar(new MedicamentoProveedorDTO(idMedicamentoProveedor, precioInicial, cantidadInicial, idProveedor, idMedicamento, fecha));
    }

    public MedicamentoProveedorDTO obtenerMedicamentoProveedorDTO(String id) {
        return mpdao.buscar(id);
    }

    public ArrayList<MedicamentoProveedorDTO> listMedicamentoProveedores() {
        return mpdao.getAll();
    }
}
