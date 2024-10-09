package logicaPersistencia;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logicaPersistencia.accesoBD.AccesoBD;
import logicaPersistencia.excepciones.FolioExistenteException;
import logicaPersistencia.excepciones.FolioNoExistenteException;
import logicaPersistencia.excepciones.PersistenciaException;
import logicaPersistencia.excepciones.RevisionExistenteException;
import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import logicaPersistencia.valueObjects.VORevision;

public class Fachada {
    private AccesoBD accesoBD = new AccesoBD();
    private Connection con = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/estudionotarial";
    private String usuario = "root";
    private String password = "root";

    public Fachada() {

    }

    public void agregarFolio(VOFolio voF) throws RemoteException, PersistenciaException, ClassNotFoundException, SQLException, FolioExistenteException {
        Class.forName(driver);
        con = DriverManager.getConnection(url, usuario, password);

        if (accesoBD.existeFolio(con, voF.getCodigo())) {
            throw new FolioExistenteException();
        }

        accesoBD.agregarFolio(con, voF.getCodigo(), voF.getCaratula(), voF.getPaginas());
    }

    public void agregarRevision(VORevision voR) throws RemoteException, PersistenciaException, ClassNotFoundException, SQLException, FolioNoExistenteException, RevisionExistenteException {
        Class.forName(driver);
        con = DriverManager.getConnection(url, usuario, password);

        if (!accesoBD.existeFolio(con, voR.getCodigoFolio())) {
            throw new FolioNoExistenteException();
        }

        int numero = accesoBD.obtenerNumeroDeUltimaRevisionPorFolio(con, voR.getCodigoFolio()) + 1;

        accesoBD.agregarRevision(con, numero, voR.getCodigoFolio(), voR.getDescripcion());
    }

    public void borrarFolioRevisiones(String codF) throws RemoteException, PersistenciaException, ClassNotFoundException, SQLException, FolioNoExistenteException {

    }

    public String darDescripcion(String codF, int numR) {
        //retorno descripcion a travez de accesoDB
        return null;
    }

    public List<VOFolio> listarFolios() {
        List<VOFolio> listaFolios = new ArrayList<VOFolio>();
        //Logica
        return listaFolios;
    }

    public List<VORevision> listarRevisiones() {
        List<VORevision> listaRevisiones = new ArrayList<VORevision>();
        //Logica
        return listaRevisiones;
    }

    public VOFolioMaxRev folioMasRevisado() {
        VOFolioMaxRev maxRevF = new VOFolioMaxRev("", "", 1, 1);
        //logica
        return maxRevF;
    }
}
