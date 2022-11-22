package model.dataaccessobject;

import java.util.ArrayList;

public interface Crud <T>{
    boolean agregar(T t);
    boolean eliminar(String id);
    boolean actualizar(T t);
    T buscar(String id);
    ArrayList<T> getAll();
}
