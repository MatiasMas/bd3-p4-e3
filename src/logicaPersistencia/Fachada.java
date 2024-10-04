package logicaPersistencia;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import logicaPersistencia.valueObjects.VOFolio;
import logicaPersistencia.valueObjects.VOFolioMaxRev;
import logicaPersistencia.valueObjects.VORevision;

public class Fachada {

    public Fachada() {

    }

    public void agregarFolio(VOFolio voF) throws RemoteException {
        //insertaFolio a travez de accesoDB
    }

    public void agregarRevision(String codF, String desc) {
        //insertoRevision a travez de accesoDB
    }

    public void borrarFolioRevisiones(String codF) {
        //borroFolio
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
