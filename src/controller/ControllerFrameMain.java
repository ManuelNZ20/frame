package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.businessobject.Categoria;
import model.businessobject.Medicamento;
import model.businessobject.MedicamentoProveedor;
import model.businessobject.Proveedor;
import model.dataaccessobject.CategoriaDAO;
import model.dataaccessobject.Crud;
import model.transferobject.CategoriaDTO;
import model.transferobject.MedicamentoDTO;
import model.transferobject.MedicamentoProveedorDTO;
import model.transferobject.ProveedorDTO;
import view.FrameMain;

public class ControllerFrameMain {

    Categoria mCategoria;
    Medicamento mMedicamento;
    MedicamentoProveedor mMProveedor;
    Proveedor mProveedor;
    FrameMain vFrame;
    CategoriaDTO cdto;
    ProveedorDTO pdto;
    MedicamentoDTO mdto;
    MedicamentoProveedorDTO mpdto;
    static int idMedicamento;

    public ControllerFrameMain(FrameMain vFrame, Categoria mCategoria, Proveedor mProveedor, Medicamento mMedicamento, MedicamentoProveedor mMProveedor) {
        this.vFrame = vFrame;
        this.mCategoria = mCategoria;
        this.mProveedor = mProveedor;
        this.mMedicamento = mMedicamento;
        this.mMProveedor = mMProveedor;
    }

    public void init() {
        vFrame.setTitle("MVC Dental Clinic");
        idMedicamento = mMedicamento.newMedicamento();
        vFrame.txtCodigoMedicamento.setText(String.valueOf(idMedicamento));
        botonesMedicamento();
        botonesCategoria();
        botonesProveedor();
        botonesInventario();
    }

    //<editor-fold desc="CRUD-Medicamento">
    public void botonesMedicamento() {
        vFrame.btnGuardarMedicamento.addActionListener((ActionEvent e) -> {
            btnGuardarMedicamentoActionPerformed(e);
        });
        vFrame.btnListarMedicamento.addActionListener((ActionEvent e) -> {
            btnListarMedicamentoActionPerformed(e);
        });

        vFrame.btnBuscarMedicamento.addActionListener((ActionEvent e) -> {
            btnBuscarMedicamentoActionPerformed(e);
        });
    }

    public void btnGuardarMedicamentoActionPerformed(ActionEvent evt) {
        if (mMedicamento.agregar(getNombreMedicamento(), getPrecioActual(), getCantidadActual(), getIdCategoriaMedicamento())) {
            mMProveedor.agregar(getPrecioActual(), getCantidadActual(), getIdProveedorMedicamento(), getIdMedicamento(), new Timestamp(System.currentTimeMillis()));
            idMedicamento++;
            clearBoxes();
            vFrame.txtCodigoMedicamento.setText(String.valueOf(idMedicamento));
            JOptionPane.showMessageDialog(vFrame, "Medicamento registrado exitosamente");
        } else {
            JOptionPane.showMessageDialog(vFrame, "Medicamento NO registrado exitosamente");
        }
    }

    public void btnListarMedicamentoActionPerformed(ActionEvent evt) {
        tableMedicamento();
    }

    public void btnBuscarMedicamentoActionPerformed(ActionEvent evt) {
        mdto = mMedicamento.obtenerMedicamento(String.valueOf(getIdMedicamento()));
        if (mdto != null) {
            JOptionPane.showMessageDialog(null, "Busqueda exitosa de medicamento ");
            vFrame.txtNombreMedicamento.setText(mdto.getNombre());
            vFrame.txtPrecioMedicamento.setText(String.valueOf(mdto.getPrecioActual()));
            vFrame.txtCantidadMedicamento.setText(String.valueOf(mdto.getCantidadActual()));
            vFrame.txtCcategoriaMedicamento.setText(String.valueOf(mdto.getIdCategoria()));
        } else {
            JOptionPane.showMessageDialog(null, "Busqueda fallida");

        }
    }

    public void btnEliminarMedicamentoActionPerformed(ActionEvent evt) {
        if (mMedicamento.eliminar(String.valueOf(mdto.getIdMedicamento())))
        {
            clearBoxes();
            JOptionPane.showMessageDialog(null, "Eliminación exitosa");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Eliminación fallida");
        }
    }

    //<editor-fold desc="getters and clearBoxes and tableListaMedicamento">
    public int getIdMedicamento() {
        return Integer.valueOf(vFrame.txtCodigoMedicamento.getText());
    }

    public String getNombreMedicamento() {
        return vFrame.txtNombreMedicamento.getText();
    }

    public float getPrecioActual() {
        return Float.valueOf(vFrame.txtPrecioMedicamento.getText());
    }

    public short getCantidadActual() {
        return Short.valueOf(vFrame.txtCantidadMedicamento.getText());
    }

    public short getIdCategoriaMedicamento() {
        return Short.valueOf(vFrame.txtCcategoriaMedicamento.getText());
    }

    public int getIdProveedorMedicamento() {
        return Integer.valueOf(vFrame.txtCproveedorMedicamento.getText());
    }

    public void clearBoxes() {
        vFrame.txtCodigoMedicamento.setText("");
        vFrame.txtNombreMedicamento.setText("");
        vFrame.txtPrecioMedicamento.setText("");
        vFrame.txtCantidadMedicamento.setText("");
        vFrame.txtCcategoriaMedicamento.setText("");
        vFrame.txtCproveedorMedicamento.setText("");
        cdto = null;
        pdto = null;
        vFrame.txtNombreMedicamento.requestFocus();
    }

    public void tableMedicamento() {
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"Id", "Nombre", "Precio", "Cantidad", "Categoria"});
        vFrame.tMedicamento.setModel(model);
        for (MedicamentoDTO med : mMedicamento.listaMedicamentos()) {
            model.addRow(new Object[]{med.getIdMedicamento(),
                med.getNombre(),
                med.getPrecioActual(),
                med.getCantidadActual(),
                med.getIdCategoria()});
        }
    }
    //</editor-fold>

    //</editor-fold>
    //<editor-fold desc="Inventario">
    public void botonesInventario() {
        vFrame.btnListarInventario.addActionListener((ActionEvent e) -> {
            btnListarInventarioActionPerformed(e);
        });
    }

    public void btnListarInventarioActionPerformed(ActionEvent evt) {
        tableInventario();
    }

    //Listar Inventario de medicamento
    public void tableInventario() {
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"id", "Precio I", "Cantidad I", "Proveedor", "Medicamento", "Fecha"});
        vFrame.tInventario.setModel(model);
        for (MedicamentoProveedorDTO m : mMProveedor.listMedicamentoProveedores()) {
            model.addRow(new Object[]{
                m.getIdMedicamentoProveedor(),
                m.getPrecioInicial(),
                m.getCantidadInicial(),
                m.getIdProveedor(),
                m.getIdMedicamento(),
                m.getFecha()});
        }
    }

    //</editor-fold>
    //<editor-fold desc="Crud-Categoria">
    public void botonesCategoria() {
        vFrame.btnGuardarCategoria.addActionListener((ActionEvent e) -> {
            btnGuardarCategoriaActionPerformed(e);
        });
        vFrame.btnListarCategoria.addActionListener((ActionEvent e) -> {
            btnListarCategoriaActionPerformed(e);
        });
        vFrame.btnBuscarCategoria.addActionListener((ActionEvent e) -> {
            btnBuscarCategoriaActionPerformed(e);
        });

        vFrame.btnActualizarCategoria.addActionListener((ActionEvent e) -> {
            btnActualizarCategoriaActionPerformed(e);
        });
        vFrame.btnEliminarCategoria.addActionListener((ActionEvent e) -> {
            btnEliminarCategoriaActionPerformed(e);
        });
    }

    public void btnGuardarCategoriaActionPerformed(ActionEvent evt) {
        if (mCategoria.agregar(getNombreCategoria())) {
            JOptionPane.showMessageDialog(vFrame, "Categoría almacenada correctamente");
            clearBoxesCategory();
        } else {
            JOptionPane.showMessageDialog(vFrame, "Categoría NO almacenada correctamente");
        }
    }

    public void btnListarCategoriaActionPerformed(ActionEvent evt) {
        showTableCategory();
    }

    public void btnBuscarCategoriaActionPerformed(ActionEvent evt) {
        cdto = mCategoria.obtenerCategoria(String.valueOf(getIdCategoria()));
        if (cdto != null) {
            JOptionPane.showMessageDialog(null, "Busqueda exitosa");
            vFrame.txtNombreCategoria.setText(cdto.getNombre());
            vFrame.txtCcategoriaMedicamento.setText(String.valueOf(cdto.getIdCategoria()));
        } else {
            JOptionPane.showMessageDialog(null, "La categoría no existe");
        }
    }

    public void btnActualizarCategoriaActionPerformed(ActionEvent evt) {
        if (cdto != null) {
            JOptionPane.showMessageDialog(vFrame, mCategoria.actualizar(getIdCategoria(), getNombreCategoria())
                    ? "Actualizacion Exitosa" : "Actualización Fallida");
        }
    }

    public void btnEliminarCategoriaActionPerformed(ActionEvent evt) {
        if (mCategoria.eliminar(String.valueOf(cdto.getIdCategoria()))) {
            JOptionPane.showMessageDialog(vFrame, "Eliminación exitosa");
            cdto = null;
        } else {
            JOptionPane.showMessageDialog(vFrame, "Eliminación Fallida");
        }

    }

    public void clearBoxesCategory() {
        vFrame.txtCodigoCategoria.setText("");
        vFrame.txtCodigoCategoria.setEnabled(false);
        vFrame.txtNombreCategoria.setText("");
        vFrame.txtNombreCategoria.requestFocus();
    }

    public short getIdCategoria() {
        return Short.valueOf(vFrame.txtCodigoCategoria.getText());
    }

    public String getNombreCategoria() {
        return vFrame.txtNombreCategoria.getText();
    }

    public void showTableCategory() {
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"Id", "Nombre"});
        vFrame.tCategoria.setModel(model);
        for (CategoriaDTO c : mCategoria.listaCategorias()) {
            model.addRow(new Object[]{c.getIdCategoria(), c.getNombre()});
        }
    }
    //</editor-fold>

    //<editor-fold desc="Crud-Proveedor">
    public void botonesProveedor() {
        vFrame.btnGuardarProveedor.addActionListener((ActionEvent e) -> {
            btnGuardarProveedorActionPerformed(e);
        });
        vFrame.btnListarProveedor.addActionListener((ActionEvent e) -> {
            btnListarProveedorActionPerformed(e);
        });
        vFrame.btnBuscarProveedor.addActionListener((ActionEvent e) -> {
            btnBuscarProveedorActionPerformed(e);
        });
        vFrame.btnActualizarProveedor.addActionListener((ActionEvent e) -> {
            btnActualizarProveedorActionPerformed(e);
        });
        vFrame.btnEliminarProveedor.addActionListener((ActionEvent e) -> {
            btnEliminarProveedorActionPerformed(e);
        });
    }

    public void btnGuardarProveedorActionPerformed(ActionEvent e) {
        if (mProveedor.agregar(getNombreProveedor())) {
            JOptionPane.showMessageDialog(vFrame, "Proveedor registrado correctamente");
        } else {
            JOptionPane.showMessageDialog(vFrame, "Proveedor NO registrado correctamente");
        }
    }

    public void btnListarProveedorActionPerformed(ActionEvent e) {
        showTableProveedor();
    }

    public void btnBuscarProveedorActionPerformed(ActionEvent e) {
        pdto = mProveedor.obtenerDTO(String.valueOf(getIdProveedor()));
        if (pdto != null) {
            JOptionPane.showMessageDialog(vFrame, "Busqueda exitosa");
            vFrame.txtNombreProveedor.setText(pdto.getNombre());
            vFrame.txtCproveedorMedicamento.setText(String.valueOf(pdto.getIdProveedor()));
        } else {
            JOptionPane.showMessageDialog(vFrame, "Busqueda fallida");
        }
    }

    public void btnActualizarProveedorActionPerformed(ActionEvent e) {
        if (pdto != null) {
            if (mProveedor.actualizar(getIdProveedor(), getNombreProveedor())) {
                JOptionPane.showMessageDialog(vFrame, "Datos actualizados exitosamente");
                return;
            }
            JOptionPane.showMessageDialog(vFrame, "Datos NO actualizados exitosamente");
        }
    }

    public void btnEliminarProveedorActionPerformed(ActionEvent e) {
        if (mProveedor.eliminar(String.valueOf(pdto.getIdProveedor()))) {
            JOptionPane.showMessageDialog(vFrame, "Proveedor eliminado exitosamente");
            pdto = null;
            clearBoxesProveedor();
        } else {
            JOptionPane.showMessageDialog(vFrame, "Proveedor NO eliminado exitosamente");
        }
    }

    public int getIdProveedor() {
        return Integer.valueOf(vFrame.txtCodigoProveedor.getText());
    }

    public String getNombreProveedor() {
        return vFrame.txtNombreProveedor.getText();
    }

    public void clearBoxesProveedor() {
        vFrame.txtCodigoProveedor.setText("");
        vFrame.txtNombreProveedor.setText("");
        vFrame.txtCodigoProveedor.requestFocus();
    }

    public void showTableProveedor() {
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new Object[]{"Id", "Nombre"});
        vFrame.tProveedor.setModel(model);
        for (var p : mProveedor.listaProveedores()) {
            model.addRow(new Object[]{p.getIdProveedor(), p.getNombre()});
        }
    }
    //</editor-fold>

}
