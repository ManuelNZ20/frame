package model.transferobject;

public class MedicamentoDTO {
    private int idMedicamento;
    private String nombre;
    private float precioActual;
    private short cantidadActual;
    private short idCategoria;

    public MedicamentoDTO() {
    }

    public MedicamentoDTO(int idMedicamento, String nombre, float precioActual, short cantidadActual, short idCategoria) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.precioActual = precioActual;
        this.cantidadActual = cantidadActual;
        this.idCategoria = idCategoria;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(float precioActual) {
        this.precioActual = precioActual;
    }

    public short getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(short cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public short getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(short idCategoria) {
        this.idCategoria = idCategoria;
    }
    
}
