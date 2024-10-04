package logicaPersistencia.valueObjects;

public class VORevision {
    private int numero;
    private String descripcion;
    private String codFolio;

    public VORevision(int numero, String descripcion, String codFolio) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.codFolio = codFolio;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getCodigoFolio() {
        return this.codFolio;
    }
}