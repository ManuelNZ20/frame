package controller;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import model.businessobject.Categoria;
import model.businessobject.Medicamento;
import model.businessobject.MedicamentoProveedor;
import model.businessobject.Proveedor;
import view.FrameMain;

public class JavaMainFrameMain {

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        FrameMain view = new FrameMain();
        Categoria categoria = new Categoria();
        Proveedor proveedor = new Proveedor();
        Medicamento medicamento = new Medicamento();
        MedicamentoProveedor medicamentoProveedor = new MedicamentoProveedor();
        ControllerFrameMain ctrl
                = new ControllerFrameMain(view, categoria, proveedor, medicamento, medicamentoProveedor);
        ctrl.init();
        view.setVisible(true);
    }

}
