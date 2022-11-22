package model.dataaccessobject;

import java.util.ArrayList;
import model.transferobject.CategoriaDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.dataSource.ConnectionSQLServer;

public class CategoriaDAO implements Crud<CategoriaDTO> {

    PreparedStatement ps;
    ResultSet rs;
    ConnectionSQLServer con;

    public CategoriaDAO() {
        con = new ConnectionSQLServer();
    }

    @Override
    public boolean agregar(CategoriaDTO t) {
        try {
            ps = con.connect().prepareStatement("insert Categoria values(?)");
            ps.setString(1, t.getNombre());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            System.out.println("Error agregar Categoria, " + e);
            return false;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public boolean eliminar(String id) {
        try {
            ps = con.connect().prepareStatement("delete from Categoria where idCategoria=" + id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            System.out.println("Error eliminar Categoria, " + e);
            return false;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public boolean actualizar(CategoriaDTO t) {
        try {
            ps = con.connect().prepareStatement("update Categoria set nombre=? where idCategoria=" + t.getIdCategoria());
            ps.setString(1, t.getNombre());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            System.out.println("Error actualizar Categoria, " + e);
            return false;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public CategoriaDTO buscar(String id) {
        try {
            ps = con.connect().prepareStatement("select * from Categoria where idCategoria=" + id);
            rs = ps.executeQuery();
            return (rs.next()) ? new CategoriaDTO(rs.getShort(1), rs.getString(2)) : null;
        } catch (Exception e) {
            System.out.println("Error actualizar Categoria, " + e);
            return null;
        } finally {
            con.disconnect();
        }
    }

    @Override
    public ArrayList<CategoriaDTO> getAll() {
        ArrayList<CategoriaDTO> aCategoriaDTO = new ArrayList<>();
        try {
            ps = con.connect().prepareStatement("select * from Categoria");
            rs = ps.executeQuery();
            while (rs.next()) {
                aCategoriaDTO.add(new CategoriaDTO(rs.getShort(1), rs.getString(2)));
            }
            aCategoriaDTO.trimToSize();
            return aCategoriaDTO;
        } catch (Exception e) {
            return null;
        }finally{
            con.disconnect();
        }
    }

}
