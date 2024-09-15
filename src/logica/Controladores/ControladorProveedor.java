/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Controladores;

import logica.Clases.Proveedor;
import logica.Interfaces.IControladorProveedor;
import logica.servicios.ProveedorServicios;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class ControladorProveedor implements IControladorProveedor {
    // Atributos
    private static ControladorProveedor instancia; 
    private ProveedorServicios proveedorServicios; 

    // Constructor privado para patrón singleton
    private ControladorProveedor() {
        proveedorServicios = new ProveedorServicios(); // Instancia del servicio de Proveedores
    }

    // Método estático para obtener la única instancia (singleton)
    public static ControladorProveedor getInstance() {
        if (instancia == null) {
            instancia = new ControladorProveedor();
        }
        return instancia;
    }
    
    // Método para alta de proveedor
    @Override
    public boolean altaProveedor(Proveedor proveedor) {
        return proveedorServicios.altaProveedor(proveedor);
    }

    // Método para modificar proveedor
    @Override
   public boolean modificarProveedor(String id, String nombre, String telefono, String direccion, String correoElectronico) {
        Proveedor proveedor = new Proveedor(id, nombre, telefono, direccion, correoElectronico);
        return proveedorServicios.modificarProveedor(id, proveedor);
    }

    // Método para eliminar proveedor
    @Override
    public boolean eliminarProveedor(int id) {
        return proveedorServicios.eliminarProveedor(id);
    }

    // Método para listar proveedores
    @Override
   public ArrayList<Proveedor> listarProveedores() {
    return proveedorServicios.listarProveedores(); // Llamada al método del servicio que lista los proveedores
}
}