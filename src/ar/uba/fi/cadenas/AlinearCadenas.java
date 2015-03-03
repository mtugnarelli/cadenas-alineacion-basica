package ar.uba.fi.cadenas;

import java.util.LinkedList;
import java.util.List;

public class AlinearCadenas {

    public static final char VACIO = '~';
    private String cadena1;
    private String cadena2;
    private AlineacionParcial[][] alineaciones;

    public AlinearCadenas(String cadena1, String cadena2) {

        this.cadena1 = cadena1;
        this.cadena2 = cadena2;
        this.alineaciones = new AlineacionParcial[cadena1.length() + 1][cadena2.length() + 1];
    }

    public String cadena1() {

        return this.cadena1;
    }

    public String cadena2() {

        return this.cadena2;
    }

    public void ejecutar() {
        
        alineaciones[0][0] = new AlineacionParcial(0);
        
        /* condición inicial para V(i, 0) */
        for (int i = 1; i < alineaciones.length; i++) {
            
            int valor = alineaciones[i-1][0].valor() + puntaje(cadena1.charAt(i-1), VACIO);
            alineaciones[i][0] = new AlineacionParcial( valor );
        }
        
        /* condición inicial para V(0, j) */
        for (int j = 1; j < alineaciones[0].length; j++) {
            
            int valor = alineaciones[0][j-1].valor() + puntaje(VACIO, cadena2.charAt(j-1));
            alineaciones[0][j] = new AlineacionParcial( valor );
        }
        
        /* calcula V(i,j) */
        for (int i = 1; i < alineaciones.length; i++) {
            
            for (int j = 1; j < alineaciones[i].length; j++) {
                
                int diagonal = alineaciones[i-1][j-1].valor() + puntaje(cadena1.charAt(i-1), cadena2.charAt(j-1));
                int anterior = alineaciones[i-1][ j ].valor() + puntaje(cadena1.charAt(i-1), VACIO);
                int superior = alineaciones[ i ][j-1].valor() + puntaje(VACIO, cadena2.charAt(j-1));
                int valor = maximo(diagonal, superior, anterior);
                
                /* la Alineación se crea con el valor máximo, registrando el/los orígen/es de ese valor */
                alineaciones[i][j] = new AlineacionParcial( valor );
                
                if (valor == diagonal) {
                    alineaciones[i][j].desde(Origen.diagonal);
                }
                
                if (valor == anterior) {
                    alineaciones[i][j].desde(Origen.anterior);
                }
                
                if (valor == superior) {
                    alineaciones[i][j].desde(Origen.superior);
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

        return alineacion().valor();
    }

    private AlineacionParcial optima() {
        
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

    public Alineacion alineacion() {

        return new Alineacion();
    }

    private static class AlineacionParcial {
        
        private int valor;
        private List<Origen> origenes = new LinkedList<>();
        
        public AlineacionParcial(int nuevoValor) {
            
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
    }

    public enum Origen {
        
        diagonal,
        superior,
        anterior;
    }
    
    public class Alineacion {
        
        private String cadena1Alineada;
        private String cadena2Alineada;
        
        public int valor() {
            
            return optima().valor();
        }

        public String cadena1() {

            if (cadena1Alineada == null) {
                
                this.calcular();
            }
            return cadena1Alineada;
        }
        
        public String cadena2() {

            if (cadena2Alineada == null) {

                this.calcular();
            }
            return cadena2Alineada;
        }

        private void calcular() {

            cadena1Alineada = "";
            cadena2Alineada = "";
            
            int i = columnas() - 1;
            int j = filas() - 1;
            
            do {
                
                AlineacionParcial alineacionParcial = alineaciones[i][j];

                /* toma la primer alternativa registrada */
                switch (alineacionParcial.origenes().get(0)) {
                
                    case diagonal:
                        j--;
                        i--;
                        cadena1Alineada = cadena1.charAt(i) + cadena1Alineada;
                        cadena2Alineada = cadena2.charAt(j) + cadena2Alineada;
                        break;
                    case anterior:
                        i--;
                        cadena1Alineada = cadena1.charAt(i) + cadena1Alineada;
                        cadena2Alineada = VACIO + cadena2Alineada;
                        break;
                    case superior:
                        j--;
                        cadena2Alineada = cadena2.charAt(j) + cadena2Alineada;
                        cadena1Alineada = VACIO + cadena1Alineada;
                        break;
                }

            } while ((i > 0) && (j > 0));
        }
    }
}
