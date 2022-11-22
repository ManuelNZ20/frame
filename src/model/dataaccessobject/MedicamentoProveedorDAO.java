package model.dataaccessobject;

import java.util.ArrayList;
import model.transferobject.MedicamentoProveedorDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.dataSource.ConnectionSQLServer;

public class MedicamentoProveedorDAO implements Crud<MedicamentoProveedorDTO> {

    PreparedStatement ps;
    ResultSet rs;
    ConnectionSQLServer con;

    public MedicamentoProveedorDAO() {
        con = new ConnectionSQLServer();
    }

    @Override
    public boolean agregar(MedicamentoProveedorDTO t) {
        try {
            ps = con.connect().prepareStatement("insert MedicamentoProveedor values(?,?,?,?,?)");
            ps.setFloat(1, t.getPrecioInicial());
            ps.setShort(2, t.getCantidadInicial());
            ps.setInt(3, t.getIdProveedor());
            ps.setInt(4, t.getIdMedicamento());
            ps.setTimestamp(5, t.getFecha());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            return false;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public boolean eliminar(String id) {
        try {
            ps = con.connect().prepareStatement("delete from MedicamentoProveedor where idMedicamentoProveedor=" + id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            return false;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public boolean actualizar(MedicamentoProveedorDTO t) {
        try {
            ps = con.connect().prepareStatement("update MedicamentoProveedor set precioInicial=?,cantidadInicial=?,idProveedor=?,idMedicamento=?,fecha=? where idMedicamentoProveedor=" + t.getIdMedicamentoProveedor());
            ps.setFloat(1, t.getPrecioInicial());
            ps.setShort(2, t.getCantidadInicial());
            ps.setInt(3, t.getIdProveedor());
            ps.setInt(4, t.getIdMedicamento());
            ps.setTimestamp(5, t.getFecha());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            return false;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public MedicamentoProveedorDTO buscar(String id) {
        try {
            ps = con.connect().prepareStatement("select * from MedicamentoProveedor where idMedicamentoProveedor=" + id);
            rs = ps.executeQuery();
            return (rs.next())
                    ? new MedicamentoProveedorDTO(rs.getInt(1), rs.getFloat(2), rs.getShort(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6))
                    : null;
        } catch (Exception e) {
            return null;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public ArrayList<MedicamentoProveedorDTO> getAll() {
        ArrayList<MedicamentoProveedorDTO> aMedicamentoProveedorDTO = new ArrayList<>();
        try {
            ps = con.connect().prepareStatement("select * from MedicamentoProveedor");
            rs = ps.executeQuery();
            while (rs.next()) {
                aMedicamentoProveedorDTO.add(new MedicamentoProveedorDTO(rs.getInt(1),
                        rs.getFloat(2), rs.getShort(3), rs.getInt(4), rs.getInt(5), rs.getTimestamp(6)));
            }
            aMedicamentoProveedorDTO.trimToSize();
            return aMedicamentoProveedorDTO;
        } catch (Exception e) {
            return null;
        } finally {
            con.disconnect();
        }
    }

}
