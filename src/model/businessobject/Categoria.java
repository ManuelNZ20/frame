package model.businessobject;

import java.util.ArrayList;
import model.dataaccessobject.CategoriaDAO;
import model.transferobject.CategoriaDTO;

public class Categoria {

    private CategoriaDTO categoriaDTO;
    private CategoriaDAO categoriaDAO;

    public Categoria() {
        categoriaDAO = new CategoriaDAO();
        categoriaDTO = new CategoriaDTO();
    }

    public boolean agregar(String nombre) {
        return categoriaDAO.agregar(new CategoriaDTO((short)0, nombre));
    }

    public boolean eliminar(String id) {
        return categoriaDAO.eliminar(id);
    }

    public boolean actualizar(short idCategoria, String nombre) {
        return categoriaDAO.actualizar(new CategoriaDTO(idCategoria, nombre));
    }

    public CategoriaDTO obtenerCategoria(String id) {
        return categoriaDAO.buscar(id);
    }

    public ArrayList<CategoriaDTO> listaCategorias() {
        return categoriaDAO.getAll();
    }

}
