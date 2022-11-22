package model.dataaccessobject;

import java.util.ArrayList;
import model.transferobject.MedicamentoDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dataSource.ConnectionSQLServer;

public class MedicamentoDAO implements Crud<MedicamentoDTO> {

    PreparedStatement ps;
    ResultSet rs;
    ConnectionSQLServer con;

    public MedicamentoDAO() {
        con = new ConnectionSQLServer();
    }

    @Override
    public boolean agregar(MedicamentoDTO t) {
        try {
            ps = con.connect().prepareStatement("insert Medicamento values(?,?,?,?)");
            ps.setString(1, t.getNombre());
            ps.setFloat(2, t.getPrecioActual());
            ps.setInt(3, t.getCantidadActual());
            ps.setInt(4, t.getIdCategoria());
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
            ps = con.connect().prepareStatement("delete from Medicamento where idMedicamento=" + id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            return false;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public boolean actualizar(MedicamentoDTO t) {
        try {
            ps = con.connect().prepareStatement("update Medicamento set nombre=?,precioActual=?,cantidadActual=?,idCategoria=?");
            ps.setString(1, t.getNombre());
            ps.setFloat(2, t.getPrecioActual());
            ps.setShort(3, t.getCantidadActual());
            ps.setShort(4, t.getIdCategoria());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            return false;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public MedicamentoDTO buscar(String id) {
        try {
            ps = con.connect().prepareStatement("select * from Medicamento where idMedicamento=" + id);
            rs = ps.executeQuery();
            return (rs.next())
                    ? new MedicamentoDTO(Integer.valueOf(id), rs.getString(2), rs.getFloat(3), rs.getShort(4), rs.getShort(5))
                    : null;
        } catch (Exception e) {
            return null;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public ArrayList<MedicamentoDTO> getAll() {
        ArrayList<MedicamentoDTO> aMedicamentoDTO = new ArrayList<>();
        try {
            ps = con.connect().prepareStatement("select * from Medicamento");
            rs = ps.executeQuery();
            while (rs.next()) {
                aMedicamentoDTO.add(new MedicamentoDTO(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getShort(4), rs.getShort(5)));
            }
            aMedicamentoDTO.trimToSize();
            return aMedicamentoDTO;
        } catch (Exception e) {
            return null;
        } finally {
            con.disconnect();
        }
    }

    public int getNewIdMed() {
        try {
            ps = con.connect().prepareStatement("select max(idMedicamento) as idMedicamento from Medicamento");
            rs = ps.executeQuery();
            return (rs.next())?(rs.getInt("idMedicamento") + 1):Integer.MIN_VALUE;
        } catch (SQLException e) {
            System.out.println("new Id: " + e);
            return Integer.MIN_VALUE;
        } finally {
            con.disconnect();
        }
    }
}
