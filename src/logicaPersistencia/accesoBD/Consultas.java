package logicaPersistencia.accesoBD;

public class Consultas {

    public Consultas() {

    }

    public String agregarFolio() {
        String query = "INSERT INTO folios (codigo, caratula, paginas) VALUES (?,?,? )";
        return query;
    }

    public String existeFolio() {
        String query = "SELECT * FROM folios WHERE codigo = ?";
        return query;
    }

    public String agregarRevision() {
        String query = "INSERT INTO revisiones (numero, codFolio, descripcion) VALUES (?,?,? )";
        return query;
    }

    public String contarRevisionesPorFolio() {
        String query = "SELECT COUNT(*) FROM revisiones WHERE codFolio = ?";
        return query;
    }

    public String eliminarFolio() {
        String query = "DELETE FROM folios WHERE codigo = ? ";
        return query;
    }

    public String eliminarRevisionesPorFolio() {
        String query = "DELETE FROM revisiones WHERE codFolio = ? ";
        return query;
    }

    public String darDescripcion() {
        String query = "SELECT descripcion FROM revisiones WHERE codFolio = ? ";
        return query;
    }

    public String listarFolios() {
        String query = "SELECT * FROM folios";
        return query;
    }

    public String listarRevisiones() {
        String query = "SELECT * FROM revisiones";
        return query;
    }

    public String folioMasRevisado() {
        String query = "SELECT COUNT(*) AS cantidadRevs FROM revisiones WHERE codFolio = ?";
        return query;
    }
}
