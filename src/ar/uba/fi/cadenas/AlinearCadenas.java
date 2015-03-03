package ar.uba.fi.cadenas;

import java.util.LinkedList;
import java.util.List;

public class AlinearCadenas {

    public static final char VACIO = '~';
    private String cadena1;
    private String cadena2;
    private Alineacion[][] alineaciones;

    public AlinearCadenas(String cadena1, String cadena2) {

        this.cadena1 = cadena1;
        this.cadena2 = cadena2;
        this.alineaciones = new Alineacion[cadena1.length() + 1][cadena2.length() + 1];
    }

    public String cadena1() {

        return this.cadena1;
    }

    public String cadena2() {

        return this.cadena2;
    }

    public void ejecutar() {
        
        alineaciones[0][0] = new Alineacion(0);
        
        /* condición inicial para V(i, 0) */
        for (int i = 1; i < alineaciones.length; i++) {
            
            int valor = alineaciones[i-1][0].valor() + puntaje(cadena1.charAt(i-1), VACIO);
            alineaciones[i][0] = new Alineacion( valor );
        }
        
        /* condición inicial para V(0, j) */
        for (int j = 1; j < alineaciones[0].length; j++) {
            
            int valor = alineaciones[0][j-1].valor() + puntaje(VACIO, cadena2.charAt(j-1));
            alineaciones[0][j] = new Alineacion( valor );
        }
        
        for (int i = 1; i < alineaciones.length; i++) {
            
            for (int j = 1; j < alineaciones[i].length; j++) {
                
                int diagonal = alineaciones[i-1][j-1].valor() + puntaje(cadena1.charAt(i-1), cadena2.charAt(j-1));
                int anterior = alineaciones[i-1][ j ].valor() + puntaje(cadena1.charAt(i-1), VACIO);
                int superior = alineaciones[ i ][j-1].valor() + puntaje(VACIO, cadena2.charAt(j-1));
                int valor = maximo(diagonal, superior, anterior);
                
                alineaciones[i][j] = new Alineacion( valor );
                
                if (valor == diagonal) {
                    alineaciones[i][j].desde(Alineacion.Origen.diagonal);
                }
                
                if (valor == anterior) {
                    alineaciones[i][j].desde(Alineacion.Origen.anterior);
                }
                
                if (valor == superior) {
                    alineaciones[i][j].desde(Alineacion.Origen.superior);
                }
            }
        }
    }

    private int maximo(int... valores) {
        
        int maximo = valores[0];
        
        for (int h = 1; h < valores.length; h++) {
            
            if (valores[h] > maximo) {
                
                maximo = valores[h];
            }
        }
        
        return maximo;
    }
    
    public int valor() {

        return alineacionOptima().valor();
    }

    private Alineacion alineacionOptima() {
        
        return alineaciones[columnas() - 1][filas() - 1];
    }

    private int filas() {
        
        return alineaciones[columnas() - 1].length;
    }
    
    private int columnas() {
        
        return alineaciones.length;
    }
    
    public int puntaje(char caracter1, char caracter2) {
        
        return caracter1 == caracter2 ? 1 : 0;
    }
    
    public String cadena1Alineada() {
     
        String cadena1Alineada = "";
        
        int i = columnas() - 1;
        int j = filas() - 1;
        
        do {
            
            Alineacion alineacion = alineaciones[i][j];
            
            Alineacion.Origen origen = alineacion.origenes().get(0);
            
            switch (origen) {
            
                case diagonal:
                    j--;
                case anterior:
                    cadena1Alineada = cadena1.charAt(i - 1) + cadena1Alineada;
                    i--;
                    break;
                case superior:
                    cadena1Alineada = VACIO + cadena1Alineada;
                    j--;
                    break;
            }

        } while ((i > 0) && (j > 0));
        
        return cadena1Alineada.toString();
    }
    

    public String cadena2Alineada() {

        String cadena2Alineada = "";
        
        int i = columnas() - 1;
        int j = filas() - 1;
        
        do {
            
            Alineacion alineacion = alineaciones[i][j];
            
            Alineacion.Origen origen = alineacion.origenes().get(0);
            
            switch (origen) {
            
                case diagonal:
                    i--;
                case superior:
                    cadena2Alineada = cadena2.charAt(j - 1) + cadena2Alineada;
                    j--;
                    break;
                case anterior:
                    cadena2Alineada = VACIO + cadena2Alineada;
                    i--;
                    break;
            }

        } while ((i > 0) && (j > 0));
        
        return cadena2Alineada.toString();
    }

    public static class Alineacion {
        
        private int valor;
        private List<Origen> origenes = new LinkedList<>();
        
        public Alineacion(int nuevoValor) {
            
            valor = nuevoValor;
        }
        
        public int valor() {
            
            return valor;
        }
        
        public void desde(Origen origen) {
            
            origenes.add(origen);
        }
        
        public List<Origen> origenes() {
            
            return origenes;
        }
        
        public enum Origen {
            
            diagonal,
            superior,
            anterior;
        }
    }

}
