package logicaPersistencia.excepciones;

public class FolioNoExistenteException extends Exception {
    public FolioNoExistenteException() {
        super("No existe el folio dado.");
    }

    public FolioNoExistenteException(String msg) {
        super(msg);
    }
}
