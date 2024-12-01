/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesamiento;

/**
 *
 * @author david
 */
public class ProcesarFrase {
    
    private int posicion;
    private int coincidencias;
    private char caracterUnico;

    public ProcesarFrase(){
        
    }
    
    public ProcesarFrase(int posicion, int coincidencias, char caracterUnico) {
        this.posicion = posicion;
        this.coincidencias = coincidencias;
        this.caracterUnico = caracterUnico;
    }

    public int getPosicion() {
        return posicion;
    }

    public int getCoincidencias() {
        return coincidencias;
    }

    public char getCaracterUnico() {
        return caracterUnico;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void setCoincidencias(int coincidencias) {
        this.coincidencias = coincidencias;
    }

    public void setCaracterUnico(char caracterUnico) {
        this.caracterUnico = caracterUnico;
    }
    
    
}
