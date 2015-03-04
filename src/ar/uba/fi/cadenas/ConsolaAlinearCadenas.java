package ar.uba.fi.cadenas;

import java.util.Scanner;

import ar.uba.fi.cadenas.AlinearCadenas.Alineacion;

public class ConsolaAlinearCadenas {

    public void iniciar() {
        
        Scanner entrada = new Scanner(System.in);
        
        boolean salir = false;
        
        do {
            
            System.out.print("Cadena 1>");
            String cadena1 = entrada.nextLine();
            
            System.out.print("Cadena 2>");
            String cadena2 = entrada.nextLine();
            
            
            AlinearCadenas alinear = new AlinearCadenas(cadena1, cadena2);
            
            alinear.puntuar('p', 'v', -5);
            alinear.puntuar('s', 'c',  5);
            alinear.puntuar('a', 'e', -6);
            alinear.puntuar('a', 'x', -5);
            alinear.puntuar('i', 'e',  2);
            alinear.puntuar('m', 'n',  4);

            alinear.puntuar('1', '7',  1);
            alinear.puntuar('2', '3', -1);
            alinear.puntuar('3', '4',  1);
            
            alinear.ejecutar();
            Alineacion alineacion = alinear.alineacion();
            
            System.out.print("Valor: ");
            System.out.println(alineacion.valor());
            
            System.out.println(alineacion.cadena1());
            System.out.println(alineacion.cadena2());
            System.out.println();
            
        } while (!salir);
        
        entrada.close();
    }
    
    public static void main(String[] args) {
        
        new ConsolaAlinearCadenas().iniciar();
    }
}
