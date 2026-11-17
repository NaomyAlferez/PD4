package mx.uady.filey.modelo;

public class Editorial {
    private int id;
    private String nombre;
    private String correo;
    private String telefono;

    public Editorial(int id, String nombre, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return String.format("Editorial #%d | Nombre: %-28s | Correo: %-22s | Tel: %s",
                id, nombre, correo, telefono);
    }
}