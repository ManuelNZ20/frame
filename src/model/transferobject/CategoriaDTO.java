package model.transferobject;

public class CategoriaDTO {
    private short idCategoria;
    private String nombre;

    public CategoriaDTO(short idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public CategoriaDTO() {
    }

    public short getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(short idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
