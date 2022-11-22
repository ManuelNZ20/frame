package model.transferobject;

import java.sql.Timestamp;

public class MedicamentoProveedorDTO {

    private int idMedicamentoProveedor;
    private float precioInicial;
    private short cantidadInicial;
    private int idProveedor;
    private int idMedicamento;
    private Timestamp fecha;

    public MedicamentoProveedorDTO(int idMedicamentoProveedor, float precioInicial, short cantidadInicial, int idProveedor, int idMedicamento, Timestamp fecha) {
        this.idMedicamentoProveedor = idMedicamentoProveedor;
        this.precioInicial = precioInicial;
        this.cantidadInicial = cantidadInicial;
        this.idProveedor = idProveedor;
        this.idMedicamento = idMedicamento;
        this.fecha = fecha;
    }

    public MedicamentoProveedorDTO() {
    }

    public int getIdMedicamentoProveedor() {
        return idMedicamentoProveedor;
    }

    public void setIdMedicamentoProveedor(int idMedicamentoProveedor) {
        this.idMedicamentoProveedor = idMedicamentoProveedor;
    }

    public float getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(float precioInicial) {
        this.precioInicial = precioInicial;
    }

    public short getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(short cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

}
