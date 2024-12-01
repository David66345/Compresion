/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package compresion;

import java.util.ArrayList;
import java.util.Scanner;
import procesamiento.Comprimir;
import procesamiento.Descomprimir;
import procesamiento.ProcesarFrase;

/**
 *
 * @author david
 */
public class Compresion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Frase 1: cuantos cuentos cuentas
        // Frase 2: tres tristes tigres
        // Frase 3: 0 99 0 111 0 109 17 32 42 99 44 109 42 112 25 99 42 99 46 109 0 112 0 114 33 0
        
        String opcion = "";
        do{
            System.out.println("Elija una opción");
            System.out.println("1. Comprimir.");
            System.out.println("2. Descomprimir.");
            System.out.println("3. Salir.");
            opcion = scanner.nextLine();
            
            switch(opcion){
                case "1":
                    iniciarCompresion(scanner);
                    break;
                case "2":
                    iniciarDescompresion(scanner);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Opción invalida");
                    break;
            }
        }while(opcion != "3");

        
    }
    
    private static void iniciarDescompresion(Scanner scanner) {
        Descomprimir descomprimir = new Descomprimir();
        // Pedir al usuario que introduzca la frase
        System.out.println("Introduce una la frase comprimida:");
        String frase = scanner.nextLine();
        
        String[] fraseSeparada = frase.split(" ");
        byte[] comprimido = new byte[fraseSeparada.length];
        
        
        for(int i = 0; i < comprimido.length; i++){
            comprimido[i] = (byte) Integer.parseInt(fraseSeparada[i]);
        }
        
        descomprimir.descomprimirBytes(comprimido);
    }
    
    private static void iniciarCompresion(Scanner scanner) {
        Comprimir comprimirABytes = new Comprimir();
        
        // Pedir al usuario que introduzca la frase
        System.out.println("Introduce una frase:");
        String frase = scanner.nextLine();
        //String frase = "cuantos cuentos cuentas";
        //String frase = "tres tristes tigres";
        frase = frase.replace(' ', '_');
        frase = frase+".";
        System.out.println(frase);
        
        char[] arreglo = iniciarArreglo(frase);

        // Procesar las cadenas y almacenar las iteraciones en un ArrayList
        ArrayList<ProcesarFrase> iteraciones = iteraciones = comprimirABytes.procesarCadenas(frase, arreglo);
        
        imprimirProcesamientoDeFrase(iteraciones);
        
        
        comprimirABytes.comprimirFrase(iteraciones);
    }
    
    public static char[] iniciarArreglo(String frase){
        
        char[] arreglo = new char[frase.length()+8];
        
        for(int i = 0; i < arreglo.length; i++){
            if(i < 8){
                arreglo[i] = ' ';
            } 
            if(i >= 8){
                arreglo[i] = frase.charAt(i-8);
            }
            //System.out.print(arreglo[i]);
        }
        System.out.println();
        
        return arreglo;
    }
    
    public static void imprimirProcesamientoDeFrase(ArrayList<ProcesarFrase> iteraciones){
        // Mostrar los resultados
        int i = 1;
        for (ProcesarFrase iteracion : iteraciones) {
            System.out.println(i+". "+"Posición: " + iteracion.getPosicion() +
                    ", Número de coincidencias: " + iteracion.getCoincidencias() +
                    ", Carácter único: " + iteracion.getCaracterUnico());
            i++;
        }
    }
}
