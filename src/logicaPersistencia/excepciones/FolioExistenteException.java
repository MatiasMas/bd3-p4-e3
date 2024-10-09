package logicaPersistencia.excepciones;

public class FolioExistenteException extends Exception {
    public FolioExistenteException() {
        super("El folio a ingresar ya existe en el sistema.");
    }

    public FolioExistenteException(String msg) {
        super(msg);
    }
}
