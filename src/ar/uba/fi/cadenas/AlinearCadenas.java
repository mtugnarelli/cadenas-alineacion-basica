package ar.uba.fi.cadenas;

public class AlinearCadenas {

    public static final char VACIO = '_';
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
                
                int valor = maximo( 
                                    alineaciones[i-1][j-1].valor() + puntaje(cadena1.charAt(i-1), cadena2.charAt(j-1)),
                                    alineaciones[i-1][j].valor() + puntaje(cadena1.charAt(i-1), VACIO),
                                    alineaciones[i][j-1].valor() + puntaje(VACIO, cadena2.charAt(j-1))
                                    );
                
                alineaciones[i][j] = new Alineacion( valor );
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

        return alineaciones[alineaciones.length - 1][alineaciones[alineaciones.length - 1].length - 1].valor();
    }

    public int puntaje(char caracter1, char caracter2) {
        
        return caracter1 == caracter2 ? 1 : -1;
    }
    
    public static class Alineacion {
        
        private int valor;
        
        public Alineacion(int nuevoValor) {
            
            valor = nuevoValor;
        }
        
        public int valor() {
            
            return valor;
        }
    }
}
