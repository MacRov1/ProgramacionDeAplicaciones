/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.servicios;

import Persistencia.ConexionDB;
import logica.Clases.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProveedorServicios {

    private Connection conexion;

    public ProveedorServicios() {
        conexion = new ConexionDB().getConexion();
    }

    // Método para obtener un proveedor por su ID
    public Proveedor getProveedor(int id) {
        Proveedor proveedor = null;
        String sql = "SELECT * FROM proveedor WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setId(String.valueOf(rs.getInt("id")));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setCorreoElectronico(rs.getString("correo_electronico"));
                proveedor.setDireccion(rs.getString("direccion"));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return proveedor;
    }

    // Método para listar todos los proveedores
    public ArrayList<Proveedor> listarProveedores() {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT id, nombre, telefono, correo_electronico, direccion FROM proveedor";
        try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(String.valueOf(rs.getInt("id")));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setCorreoElectronico(rs.getString("correo_electronico"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedores.add(proveedor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return proveedores;
    }

    // Método para validar existencia de proveedor por ID
    public boolean existeProveedor(int id) {
        String sql = "SELECT id FROM proveedor WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            boolean existe = rs.next();
            rs.close();
            return existe;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Método para insertar un nuevo proveedor
    public boolean altaProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO proveedor (id, nombre, telefono, direccion, correo_electronico) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(proveedor.getId()));
            ps.setString(2, proveedor.getNombre());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDireccion());
            ps.setString(5, proveedor.getCorreoElectronico());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un proveedor existente
    public boolean modificarProveedor(String id, Proveedor proveedor) {
       String sql = "UPDATE proveedor SET nombre = ?, telefono = ?, direccion = ?, correo_electronico = ? WHERE id = ?";
    try (Connection conexion = new ConexionDB().getConexion(); // Crear una instancia de ConexionDB y obtener la conexión
         PreparedStatement ps = conexion.prepareStatement(sql)) {
        ps.setString(1, proveedor.getNombre());
        ps.setString(2, proveedor.getTelefono());
        ps.setString(3, proveedor.getDireccion());
        ps.setString(4, proveedor.getCorreoElectronico());
        ps.setString(5, id);  // Usar String para el ID
        return ps.executeUpdate() > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
    }

    // Método para eliminar un proveedor por su ID
    public boolean eliminarProveedor(int id) {
        String sql = "DELETE FROM proveedor WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}