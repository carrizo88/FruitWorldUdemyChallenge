package francocompany.fruitworld.models;

/**
 * Created by PC on 23/05/2017.
 */

public class Frutas {
    private String nombre;
    private int imagen;
    private String origen;

    public Frutas(){}

    public Frutas(String nombre, int imagen,String origen){
        this.nombre=nombre;
        this.imagen=imagen;
        this.origen=origen;
    }

    public String getNombre(){
        return nombre;
    }
    public void SetNombre(String nombre){
        this.nombre=nombre;
    }
    public int getImagen(){
        return imagen;
    }
    public void setImagen(int imagen){
        this.imagen=imagen;
    }
    public String getOrigen(){
        return origen;
    }
    public void setOrigen(String origen){
        this.origen=origen;
    }
}
