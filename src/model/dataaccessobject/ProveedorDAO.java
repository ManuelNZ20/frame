package model.dataaccessobject;

import java.util.ArrayList;
import model.transferobject.ProveedorDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.dataSource.ConnectionSQLServer;

public class ProveedorDAO implements Crud<ProveedorDTO> {

    PreparedStatement ps;
    ResultSet rs;
    ConnectionSQLServer con;

    public ProveedorDAO() {
        con = new ConnectionSQLServer();
    }

    @Override
    public boolean agregar(ProveedorDTO t) {
        try {
            ps = con.connect().prepareStatement("insert Proveedor values(?)");
            ps.setString(1, t.getNombre());
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
            ps = con.connect().prepareStatement("delete from Proveedor where idProveedor=" + id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            return false;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public boolean actualizar(ProveedorDTO t) {
        try {
            ps = con.connect().prepareStatement("update Proveedor set nombre=? where idProveedor=" + t.getIdProveedor());
            ps.setString(1, t.getNombre());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public ProveedorDTO buscar(String id) {
        try {
            ps = con.connect().prepareStatement("select * from Proveedor where idProveedor=" + id);
            rs = ps.executeQuery();
            return (rs.next())
                    ? new ProveedorDTO(rs.getInt(1), rs.getString(2))
                    : null;
        } catch (Exception e) {
            return null;
        }finally{
            con.disconnect();
        }
    }

    @Override
    public ArrayList<ProveedorDTO> getAll() {
        ArrayList<ProveedorDTO> aProveedorDTO = new ArrayList<>();
        try {
            ps = con.connect().prepareStatement("select * from Proveedor");
            rs = ps.executeQuery();
            while(rs.next()){
                aProveedorDTO.add(new ProveedorDTO(rs.getInt(1), rs.getString(2)));
            }
            aProveedorDTO.trimToSize();
            return aProveedorDTO;
        } catch (Exception e) {
            return null;
        }
    }

}
