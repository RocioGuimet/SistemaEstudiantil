package archivo.DAO;

import archivo.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static archivo.conexion.Conexion.getConexion;

// Data Access Object
public class EstudianteDAO {
    public List<Estudiante> listarEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getInt("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            } //fin while
        } catch (Exception e){
            System.out.println("Ocurrió un error al seleccionar datos: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e){
                System.out.println("Ocurrió un error al cerrar conexion: " + e.getMessage());
            }
        }
        return estudiantes;
    }

    public boolean buscarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getInt("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        } catch (Exception e){
            System.out.println("Ocurrió un error al buscar estudiante: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e){
                System.out.println("Ocurrió un error al cerrar conexión: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email) VALUES (?, ?, ?, ?)";
        try {
        ps = con.prepareStatement(sql);
        ps.setString(1, estudiante.getNombre());
        ps.setString(2, estudiante.getApellido());
        ps.setInt(3, estudiante.getTelefono());
        ps.setString(4, estudiante.getEmail());
        ps.execute();
        return true;
        } catch (Exception e){
            System.out.println("Ocurrió un error al agregar estudiante: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e){
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean modificarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=? WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setInt(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("Ocurrió un error al modificar estudiante: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e){
                System.out.println("Ocurrió un error al cerrar la conexión: " + e.getMessage());
            }
        }
            return false;
    }

    public boolean eliminarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("Ocurrió un error al eliminar estudiante: " + e.getMessage());
        }
        finally{
            try{
                con.close();
            } catch (Exception e){
                System.out.println("Ocurrió un error al cerrar la conexión: " + e.getMessage());
            }
        }
        return false;
    }


    static void main(String[] args) {
        var estudianteDao = new EstudianteDAO();

        // Agregar estudiante
//        var nuevoEstudiante = new Estudiante("Carlos", "Lara", 55117788, "Carlitos@gmail.com");
//        var agregado = estudianteDao.agregarEstudiante(nuevoEstudiante);
//        if(agregado)
//            System.out.println("Estudiante agregado: " + nuevoEstudiante);
//        else
//            System.out.println("No se agregó el estudiante: " + nuevoEstudiante);

        // Modificar estudiante
//        var estudianteModificar = new Estudiante(1, "Juan Carlos", "Juarez", 12345678, "Juan@mail.com");
//        var modificado = estudianteDao.modificarEstudiante(estudianteModificar);
//        if(modificado)
//            System.out.println("Estudiante modificado: " + estudianteModificar);
//        else
//            System.out.println("No se modificó estudiante: " + estudianteModificar);

        // Eliminar estudiante
        var estudianteEliminar = new Estudiante(4);
        var eliminado = estudianteDao.eliminarEstudiante(estudianteEliminar);
        if (eliminado)
            System.out.println("Estudiante eliminado: " + estudianteEliminar);
        else
            System.out.println("No se eliminó el estudiante: " + estudianteEliminar);

        // Listar estudiantes
        System.out.println("Listado de Estudiantes: ");
        List<Estudiante> estudiantes = estudianteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);

        // Buscar estudiantes
//        var estudiante1 = new Estudiante(1);
//        System.out.println("Estudiante antes de la búsqueda: " + estudiante1);
//        var encontrado = estudianteDao.buscarEstudiante(estudiante1);
//        if(encontrado)
//            System.out.println("Estudiante encontrado: " + estudiante1);
//        else
//            System.out.println("No se encontró estudiante: " + estudiante1.getIdEstudiante());
    }
}
