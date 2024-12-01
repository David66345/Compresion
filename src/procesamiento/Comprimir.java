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
public class Comprimir {
    
    public void comprimirFrase(ArrayList<ProcesarFrase> fraseProcesada){
        
        byte[] comprimido = new byte[fraseProcesada.size()*2];
        Descomprimir descomprimir = new Descomprimir();
        
        System.out.println("\nCompresión:");
        for(int i = 0; i <fraseProcesada.size();i++){
            ProcesarFrase procesado = fraseProcesada.get(i);
            
            byte byteResultante = (byte) ((procesado.getPosicion() << 3) | procesado.getCoincidencias());
            byte byteCaracter = (byte) procesado.getCaracterUnico();
            
            
            comprimido[i*2] = byteResultante;
            comprimido[i*2 + 1] = byteCaracter;
            System.out.println(i+1 + " Byte Enteros: "+comprimido[i*2]+" Byte Caracter: "+comprimido[i*2 + 1]);
            
        }
        
        descomprimir.descomprimirBytes(comprimido);
    }
    
    // Método para procesar las cadenas y encontrar coincidencias
    public ArrayList<ProcesarFrase> procesarCadenas(String frase, char[] arreglo) {
        ArrayList<ProcesarFrase> iteraciones = new ArrayList<>();
        boolean primerCoincidencia = false;
        boolean fraseTerminada = false;
        for (int i = 0; i < frase.length(); i++) {
            char caracter = arreglo[8];
            //System.out.print("("+arreglo[0]+")");
            int coincidencias = 0;
            int posicion = 0;
            char caracterUnico = caracter;
            int m = 0;
            
            /*
            System.out.println("Aquí inicia: ");
            for(int j = 0; j < arreglo.length; j++){
                System.out.print(arreglo[j]);
            }
            
            System.out.println();*/
            
            
            for(int j = 0; j < 8;j++){
                char caracterActual = arreglo[j];
                //System.out.print("("+arreglo[j]+")");
                if(!primerCoincidencia){
                    for(int k=8; k <14;k++){
                        //System.out.print("("+arreglo[k]+")");
                        if(caracterActual == arreglo[8] && coincidencias == 0 && arreglo[k]!='.'){
                            coincidencias++;
                            if(coincidencias == 1){
                                posicion = 8 - j;
                                primerCoincidencia = true;
                                m = k+1;
                            }
                        }
                    }
                } else {
                    if(arreglo[m] == arreglo[j] && coincidencias > 0 && m < 13 && arreglo[m]!='.'){
                        coincidencias++;
                         m++;
                    } else if(arreglo[m] == arreglo[j] && coincidencias > 0 && m == 13 && arreglo[m]!='.'){
                        coincidencias++;
                    } else if(arreglo[m] == '.'){
                        fraseTerminada = true;
                        break;
                    }else {
                        primerCoincidencia = false;
                        break;
                    }
                }
                
                //System.out.print("("+arreglo[j]+")");
                //System.out.print("("+arreglo[k]+")");
                    
            }
            
            //System.out.println("No. Coincidencias: "+coincidencias);
            //System.out.println("Posicion: "+posicion);
            //System.out.println();
            
            if(coincidencias == 0){    
                iteraciones.add(new ProcesarFrase(posicion, coincidencias, caracterUnico));
                //System.out.println(frase.charAt(i));
                arreglo = moverIzquierda(arreglo, frase, coincidencias, i);
            } else if(coincidencias < 6){
                caracterUnico = arreglo[8+coincidencias];
                iteraciones.add(new ProcesarFrase(posicion, coincidencias, caracterUnico));
                
                if(fraseTerminada){
                    primerCoincidencia = false;
                    return iteraciones;
                }
                
                arreglo = moverIzquierda(arreglo, frase, coincidencias, i);
            } else if(coincidencias == 6){
                caracterUnico = arreglo[coincidencias];
                iteraciones.add(new ProcesarFrase(posicion, coincidencias, caracterUnico));
                
                if(fraseTerminada){
                    primerCoincidencia = false;
                    return iteraciones;
                }
                
                arreglo = moverIzquierda(arreglo, frase, coincidencias, i);
            }
        }
        primerCoincidencia = false;
        return iteraciones;
    }
    
    public char[] moverIzquierda(char[] arreglo, String frase, int coincidencias, int indice){
        
        //System.out.println();
        //System.out.println(frase.charAt(indice));
        int indiceArreglo = arreglo.length-coincidencias-1;
        boolean fraseTerminada = false;
        
        if(coincidencias == 0){
            for(int i = 0; i < arreglo.length-1;i++){
                arreglo[i] = arreglo[i+1];
                
                //System.out.print(arreglo[i]);
            }
            //arreglo[13] = frase.charAt(indice+6);
        } else if(coincidencias < 6){
            
            for(int i = 0; i < indiceArreglo;i++){
                arreglo[i] = arreglo[i+coincidencias+1];
                if(arreglo[i] == '.'){
                    fraseTerminada = true;
                }
                //System.out.print(arreglo[i]);
            }
            //System.out.println();
            
            for(int i = indice, j = indiceArreglo; i < frase.length() && j < arreglo.length; i++,j++){
                arreglo[j] = frase.charAt(i);
            }
            //System.out.println();
            
            //System.out.println(arreglo[indiceArreglo]);
            //arreglo[indiceArreglo] = frase.substring(indice);
            //System.out.println(arreglo.length);
        } else if(coincidencias == 6){
            
            for(int i = 0; i < indiceArreglo;i++){
                arreglo[i] = arreglo[i+coincidencias+1];
                if(arreglo[i] == '.'){
                    fraseTerminada = true;
                }
                //System.out.print(arreglo[i]);
            }
            //System.out.println();
            for(int i = indice, j = indiceArreglo; i < frase.length() && j < arreglo.length; i++,j++){
                if(!fraseTerminada){
                    arreglo[j] = frase.charAt(i);
                    if(frase.charAt(i) == '.'){
                        fraseTerminada = true;
                    } 
                } else {
                    arreglo[j] = ' ';
                }
            }
            //System.out.println();
            
            //System.out.println(arreglo[indiceArreglo]);
            //arreglo[indiceArreglo] = frase.substring(indice);
            //System.out.println(arreglo.length);
        }
        //System.out.println();
        fraseTerminada = false;
        
        return arreglo;
    }
}
