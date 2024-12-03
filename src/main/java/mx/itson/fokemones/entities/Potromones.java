
package mx.itson.fokemones.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author zBook
 */
public class Potromones {

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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the puntaje
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * @return the entrenadores
     */
    public Entrenadores getEntrenadores() {
        return entrenadores;
    }

    /**
     * @param entrenadores the entrenadores to set
     */
    public void setEntrenadores(Entrenadores entrenadores) {
        this.entrenadores = entrenadores;
    }

    /**
     * @return the habilidad
     */
    public List<Habilidades> getHabilidad() {
        return habilidad;
    }

    /**
     * @param habilidad the habilidad to set
     */
    public void setHabilidad(List<Habilidades> habilidad) {
        this.habilidad = habilidad;
    }
  private int id;
  private String nombre;
  private String descripcion;
  private String imagen;
  private int puntaje;
  private Entrenadores entrenadores;
  private List<Habilidades> habilidad;
}
/**
     * En esta seccion de codigo se utiliza para poder obtener el id del servicio en especifico
     * la fecha de realizacion y la descripcion del problema del servicio
     * @param id es un int el cual es el id del servicio
     * @param fecha_realizacion es un dato tipo date el cual es la fecha que se realizo el servicio
     * @param descripcion_problema es un dato tipo string el cual es la descripcion del problema del servicio realizado
     * @return Un objeto de tipo Servicio que contiene los datos del registro correspondiente.
     * Si no se encuentra el registro, se devuelve un objeto vacío con valores predeterminados.
     */
    public static Potromones getById(int id){
            Potromones p = new Potromones ();
        try {
            Connection conexion = Conexion.obtener();
            
            String query = "SELECT id, nombre, descripcion, imagen, puntaje, entrenador_id, FROM Potromones WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            
            ResultSet rs = rs = statement.executeQuery();
            while(rs.next()) {
 
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setDescripcion(rs.getString(3));
                p.setImagen(rs.getString(4));
                p.setPuntaje(rs.getInt(5));

            }
        } catch (Exception ex) {
            System.err.println("Ocurrió un error " + ex.getMessage());
        }
        return p;
    }
      /**
     * Guarda un registro de Servicio en la base de datos
     * @param fecha dato de tipo date el cual es la fecha de realizacion del servicio
     * @param idResponsable Dato de tipo int el cual es el identificador unico del responsable que realizo el servicio
     * @param descripcionProblema Dato de tipo String el cual es la descripcion del problema del servicio realizado
     * @return true si se guardo exitosamente ; de lo contrario, false.
     */
    public static boolean save(Date fecha, int idResponsable, String descripcionProblema) {
        boolean resultado = false;
        try{
                Connection conexion = Conexion.obtener();
                String consulta = "INSERT INTO servicio (fecha_realizacion, id_responsable, descripcion_problema) VALUES (?, ?, ?)";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setDate(1, new java.sql.Date(fecha.getTime()));
                statement.setInt(2, idResponsable);
                statement.setString(3, descripcionProblema);
                statement.execute();
                resultado = statement.getUpdateCount() == 1;
                conexion.close();
        }catch(Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }return resultado;
        
        
    }
        /**
     * Aqui esta seccion de codigo sirve para poder editar desde nuestro programa, la base de datos y poder 
     * modificar toda la tabla de Servicio
     * @param fecha dato de tipo date el cual es la fecha de realizacion del servicio
     * @param idResponsable Dato de tipo int el cual es el identificador unico del responsable que realizo el servicio
     * @param descripcionProblema Dato de tipo String el cual es la descripcion del problema del servicio realizado
     * @return la edicion de los elementos seleccionados del servicio especificado
     */
    
    public static boolean edit(Date fecha, int idResponsable, int idServicio, String descripcionProblema) {
        boolean resultado = false;
        try{
                Connection conexion = Conexion.obtener();
                String consulta = "UPDATE servicio SET fecha_realizacion = ?, descripcion_problema = ?, id_responsable = ? WHERE id = ?";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setDate(1, new java.sql.Date(fecha.getTime()));
                statement.setString(2, descripcionProblema);
                statement.setInt(3, idResponsable);
                statement.setInt(4, idServicio);
                
                statement.execute();
                resultado = statement.getUpdateCount() == 1;
                conexion.close();
        }catch(Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }return resultado;
    } 
     /**
     * En esta seccion de codigo se elimina el id especifico seleccionado de nuestra base de datos
     * @param idServicio es un int el cual es el identificador unico de nuestro servicio especificado
     * @return La eliminacion del identificador unico de nuestro servicio especificado
     */
    public static boolean delete(int idServicio) {
        boolean resultado = false;
        try{
                Connection conexion = Conexion.obtener();
                String consulta = "DELETE FROM Servicio WHERE id = ?";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                
                statement.setInt(1, idServicio);
                
                statement.execute();
                resultado = statement.getUpdateCount() == 1;
                conexion.close();
        }catch(Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }return resultado;
    } 
    
     /**
     * Esta seccion de codigo se usa para poder seleccionar todo lo que este dentro de "Servicio"
     * En la base de datos
     * @return todos los datos seleccionados de la base de datos
     */   
    public static List<Servicio> getAll (){
        List<Servicio> servicios = new ArrayList<>();
        try {
            Connection conexion = Conexion.obtener();
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, fecha_realizacion, id_responsable, descripcion_problema FROM servicio");
            while(rs.next()) {
                Servicio s = new Servicio ();
                s.setId(rs.getInt(1));
                s.setFechaRealizacion(rs.getDate(2));
                
                //Obtenemos un ejemplo de tipo responsable 
                Responsable r = Responsable.getById(rs.getInt(3));
                s.setResponsable(r);
                
                s.setDescripcionProblema(rs.getString(4));
                
                //Obtenemos una lista de tipo Actividad
                List<Actividad> actividades = Actividad.getList(rs.getInt(1));
                s.setActividades(actividades);
                
                servicios.add(s);
                
            }
            
        } catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        } return servicios; 
    } 
}

