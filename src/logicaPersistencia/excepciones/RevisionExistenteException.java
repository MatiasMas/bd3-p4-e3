package logicaPersistencia.excepciones;

public class RevisionExistenteException extends Exception {
    public RevisionExistenteException() {
        super("Una revision con ese numero ya existe en el sistema.");
    }

    public RevisionExistenteException(String msg) {
        super(msg);
    }
}
