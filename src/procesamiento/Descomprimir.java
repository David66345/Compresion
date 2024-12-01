/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesamiento;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Descomprimir {
    
    public void descomprimirBytes (byte[] comprimido){
        
        ArrayList<ProcesarFrase> proceso = new ArrayList<>();
        
        int posicion = 0;
        int coincidencias = 0;
        char caracterUnicos = ' ';
        System.out.println("\nDescompresión:");
        for(int i = 0; i < comprimido.length; i++){
            
            if(i % 2 == 0){
                posicion = (comprimido[i] >> 3)& 0b11111;
                coincidencias = comprimido[i]& 0b111;
                System.out.println(i+". Posicion: "+posicion+" Coincidencias: "+coincidencias);
            } else {
                caracterUnicos = (char) comprimido[i];
                System.out.println(i+". "+caracterUnicos);
                proceso.add(new ProcesarFrase(posicion,coincidencias,caracterUnicos));
            }
        }
        
        imprimirProcesamientoDeFrase(proceso);
        descomprimirFrase(proceso);
        
    }
    
    public void descomprimirFrase (ArrayList<ProcesarFrase> proceso){
        
        String fraseDescomprimida = "";
        
        System.out.println("\nLa frase descomprimida es:");
        
        for(int i = 0; i <proceso.size();i++){
            ProcesarFrase procesado = proceso.get(i);
            
            if(procesado.getPosicion() == 0 && procesado.getCoincidencias() == 0){
                fraseDescomprimida += procesado.getCaracterUnico();
            } else {
                
                for(int j = 0; j <=procesado.getCoincidencias();j++){
                    //System.out.println("Iteración: "+j);
                    if(j < procesado.getCoincidencias()){
                        char caracterSiguiente = fraseDescomprimida.charAt(fraseDescomprimida.length()-procesado.getPosicion());
                        fraseDescomprimida += caracterSiguiente;
                    } else {
                        fraseDescomprimida += procesado.getCaracterUnico();
                    }
                    
                }
            }
        }
        
        System.out.println(fraseDescomprimida);
        System.out.println();
    }
    
    public void imprimirProcesamientoDeFrase (ArrayList<ProcesarFrase> proceso){
        int i = 1;
        for (ProcesarFrase iteracion : proceso) {
            System.out.println(i+". "+"Posición: " + iteracion.getPosicion() +
                    ", Número de coincidencias: " + iteracion.getCoincidencias() +
                    ", Carácter único: " + iteracion.getCaracterUnico());
            i++;
        }
    }
}
