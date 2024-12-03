/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.fokemones.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.itson.fokemones.persistencia.Conexion;

/**
 *
 * @author zBook
 */
public class Entrenador {

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apodo
     */
    public String getApodo() {
        return apodo;
    }

    /**
     * @param apodo the apodo to set
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }
    private int id;
    private String nombre;
    private String apodo;

/**
     * Esta seccion de codigo se usa para poder seleccionar todo lo que este dentro de "Responsable"
     * En la base de datos
     * @return todos los datos seleccionados de la base de datos
     */   
    public static List<Entrenador> getAll() {
        List<Entrenador> entrenadores = new ArrayList<>();
        try {
            Connection conexion = Conexion.obtener();
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, nombre, apodo FROM entrenadores");
            while(rs.next()) {
                Entrenador e = new Entrenador();
                e.setId(rs.getInt(1));
                e.setNombre(rs.getString(2));
                e.setApodo(rs.getString(3));
                entrenadores.add(e);
            }
        } catch (Exception ex) {
            System.err.println("Ocurrió un error " + ex.getMessage());
        }
        return entrenadores;
    }
    
    /**
      * En esta seccion de codigo se utiliza para poder obtener el id del responsable en especifico
     * el id del responsable nombre del responsable y puesto del responsable especificado
     * @param id es un int el cual es el id del responsable espesifcado
     * @param nombre es un string el cual es el nombre del responsable especificado
     * @param puesto es un string el cual es el puesto asigado del responsable especificado
     * @return Un objeto de tipo Responsable que contiene los datos del registro correspondiente.
     * Si no se encuentra el registro, se devuelve un objeto vacío con valores predeterminados.
     */
    public static Entrenador getById(int id){
        Entrenador e = new Entrenador ();
        try {
            Connection conexion = Conexion.obtener();
            
            String query = "SELECT id, nombre, apodo FROM entrenadores WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            
            ResultSet rs = rs = statement.executeQuery();
            while(rs.next()) {
 
                e.setId(rs.getInt(1));
                e.setNombre(rs.getString(2));
                e.setApodo(rs.getString(3));

            }
        } catch (Exception ex) {
            System.err.println("Ocurrió un error " + ex.getMessage());
        }
        return e;
    }
    
    
    /**
     * Aqui esta seccion de codigo sirve para poder editar desde nuestro programa, la base de datos y poder 
     * modificar toda la tabla de Responsable
     * @param id es un int el cual es el identificador unico del responsable
     * @param nombre es un string el cual es el nombre del responsable especificado
     * @param puesto es un string el cual es el puesto del responsable especificado
     * @return la edicion de los elementos seleccionados del responsable especificado
     */
    public static boolean edit(int id,String nombre, String apodo) {
        boolean resultado = false;
        try{
                Connection conexion = Conexion.obtener();
                String consulta = "UPDATE entrenadores SET nombre = ?, apodo = ? WHERE id = ?";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, nombre);
                statement.setString(2, apodo);
                statement.setInt(3, id);
                
                statement.execute();
                resultado = statement.getUpdateCount() == 1;
                conexion.close();
        }catch(Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }return resultado;
    } 
    /**
     * En esta seccion de codigo se elimina el id especifico seleccionado de nuestra base de datos
     * @param id es un int el cual es el identificador unico de nuestro responsable especificado
     * @return La eliminacion del identificador unico de nuestro responsable especificado
     */
    
    public static boolean delete(int id) {
        boolean resultado = false;
        try{
                Connection conexion = Conexion.obtener();
                String consulta = "DELETE FROM entrenadores WHERE id = ?";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                
                statement.setInt(1, id);
                
                statement.execute();
                resultado = statement.getUpdateCount() == 1;
                conexion.close();
        }catch(Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }return resultado;
    } 
    /**
     * Guarda un registro de responsable en la base de datos
     * @param nombre Valor del nombre del responsable
     * @param puesto Valor del puesto del responsable
     * @return true si se guardo exitosamente ; de lo contrario, false.
     */
    public static boolean save(String nombre, String apodo) {
        boolean resultado = false;
        try{
                Connection conexion = Conexion.obtener();
                String consulta = "INSERT INTO entrenadores (nombre, apodo) VALUES (?, ?)";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, nombre);
                statement.setString(2, apodo);
                
                statement.execute();
                resultado = statement.getUpdateCount() == 1;
                conexion.close();
        }catch(Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }return resultado;
        
}
    } 
    

