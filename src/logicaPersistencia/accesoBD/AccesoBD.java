package logicaPersistencia.accesoBD;

import logicaPersistencia.excepciones.PersistenciaException;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VORevision;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoBD {
    private final Consultas consultas = new Consultas();

    public AccesoBD() {

    }

    public void agregarFolio(Connection con, String codigo, String caratula, String paginas) throws PersistenciaException {
        try {
            String consultaInsert = consultas.agregarFolio();

            PreparedStatement pstm = con.prepareStatement(consultaInsert);
            pstm.setString(1, codigo);
            pstm.setString(2, caratula);
            pstm.setString(3, paginas);

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }
    }

    public void agregarRevision(Connection con, int numero, String codFolio, String descripcion) throws PersistenciaException {
        try {
            String consultaInsert = consultas.agregarRevision();

            PreparedStatement pstm = con.prepareStatement(consultaInsert);
            pstm.setInt(1, numero);
            pstm.setString(2, codFolio);
            pstm.setString(3, descripcion);

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }
    }

    public int obtenerNumeroDeUltimaRevisionPorFolio(Connection con, String codigo) throws PersistenciaException {
        try {
            String consultaObtenerNumero = consultas.ultimoNumeroDeRevision();
            int numero = 0;

            PreparedStatement pstm = con.prepareStatement(consultaObtenerNumero);
            pstm.setString(1, codigo);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                numero = rs.getInt(1);
            }

            rs.close();
            pstm.close();

            return numero;
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }
    }

    public boolean existeFolio(Connection con, String codigo) throws PersistenciaException {
        try {
            String consultaExiste = consultas.existeFolio();
            boolean existe = false;

            PreparedStatement pstm = con.prepareStatement(consultaExiste);
            pstm.setString(1, codigo);

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

    public boolean existeRevision(Connection con, String codigoFolio, int numeroRevision) throws PersistenciaException {
        try {
            String consultaExiste = consultas.existeRevision();
            boolean existe = false;

            PreparedStatement pstm = con.prepareStatement(consultaExiste);
            pstm.setString(1, codigoFolio);
            pstm.setInt(2, numeroRevision);

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

    public String darDescripcionPorFolioYNumero(Connection con, int codFolio, int numeroRevision) throws PersistenciaException {
        try {
            String consultaDescripcion = consultas.darDescripcionPorFolioYNumero();
            String descripcion = "";

            PreparedStatement pstm = con.prepareStatement(consultaDescripcion);
            pstm.setInt(1, codFolio);
            pstm.setInt(2, numeroRevision);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                descripcion = rs.getString(1);
            }

            rs.close();
            pstm.close();

            return descripcion;
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }
    }

    public List<VOFolio> listarFolios(Connection con) throws PersistenciaException {
        try {
            String consultaListado = consultas.listarFolios();
            List<VOFolio> folios = new ArrayList<>();

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery(consultaListado);

            while (rs.next()) {
                VOFolio voFolio = new VOFolio(rs.getString(1), rs.getString(2), rs.getString(3));
                folios.add(voFolio);
            }

            rs.close();
            stm.close();

            return folios;
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }
    }

    public List<VORevision> listarRevisionesPorFolio(Connection con, int codFolio) throws PersistenciaException {
        try {
            String consultaListado = consultas.listarRevisiones();
            List<VORevision> revisiones = new ArrayList<>();

            Statement stm = con.createStatement();

            ResultSet rs = stm.executeQuery(consultaListado);

            while (rs.next()) {
                VORevision voRevision = new VORevision(rs.getInt(1), rs.getString(2), rs.getString(3));
                revisiones.add(voRevision);
            }

            rs.close();
            stm.close();

            return revisiones;
        } catch (SQLException e) {
            throw new PersistenciaException(e.getMessage());
        }
    }
}
