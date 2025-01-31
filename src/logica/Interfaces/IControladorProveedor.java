/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Interfaces;

import logica.Clases.Proveedor;
import java.util.ArrayList;

public interface IControladorProveedor {
    
    // Método para alta de proveedor
    public abstract boolean altaProveedor(Proveedor proveedor);
    
    // Método para modificar proveedor
    boolean modificarProveedor(String id, String nombre, String telefono, String direccion, String correoElectronico);
    
    // Método para eliminar proveedor
    public abstract boolean eliminarProveedor(int id);
    
    // Método para listar proveedores
    public abstract ArrayList<Proveedor> listarProveedores();
}