package logicaPersistencia.accesoBD;

import logicaPersistencia.excepciones.PersistenciaException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccesoBD {
    private final Consultas consultas = new Consultas();

    public AccesoBD() {

    }

    public void agregarFolio(Connection con, int codigo, String caratula, String paginas) throws PersistenciaException {
        try {
            String consultaInsert = consultas.agregarFolio();

            PreparedStatement pstm = con.prepareStatement(consultaInsert);
            pstm.setInt(1, codigo);
            pstm.setString(2, caratula);
            pstm.setString(3, paginas);

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }
    }

    public void agregarRevision(Connection con, int numero, int codFolio, String descripcion) throws PersistenciaException {
        try {
            String consultaInsert = consultas.agregarRevision();

            PreparedStatement pstm = con.prepareStatement(consultaInsert);
            pstm.setInt(1, numero);
            pstm.setInt(2, codFolio);
            pstm.setString(3, descripcion);

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }
    }

    public boolean existeFolio(Connection con, int codigo) throws PersistenciaException {
        try {
            String consultaExiste = consultas.existeFolio();
            boolean existe = false;

            PreparedStatement pstm = con.prepareStatement(consultaExiste);
            pstm.setInt(1, codigo);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                existe = true;
            }

            rs.close();
            pstm.close();

            return existe;
        } catch (Exception e) {
            throw new PersistenciaException(e.getMessage());
        }
    }

    public int contarRevisionesPorFolio(Connection con, int codFolio) throws PersistenciaException {
        try {
            String consultaContador = consultas.contarRevisionesPorFolio();
            int numeroRevisiones = 0;

            PreparedStatement pstm = con.prepareStatement(consultaContador);
            pstm.setInt(1, codFolio);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                numeroRevisiones = rs.getInt(1);
            }

            rs.close();
            pstm.close();

            return numeroRevisiones;
        } catch (Exception e) {
            throw new PersistenciaException(e.getMessage());
        }
    }

    public void eliminarRevisionesPorFolio(Connection con, int codFolio) throws PersistenciaException {
        try {
            String consultaDelete = consultas.eliminarRevisionesPorFolio();

            PreparedStatement pstm = con.prepareStatement(consultaDelete);
            pstm.setInt(1, codFolio);

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }
    }

    public void eliminarFolio(Connection con, int codigo) throws PersistenciaException {
        try {
            String consultaDelete = consultas.eliminarFolio();

            PreparedStatement pstm = con.prepareStatement(consultaDelete);
            pstm.setInt(1, codigo);

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }
    }


}
