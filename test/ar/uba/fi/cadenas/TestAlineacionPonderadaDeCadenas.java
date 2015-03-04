package ar.uba.fi.cadenas;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import ar.uba.fi.cadenas.AlinearCadenas.Alineacion;

@RunWith(Theories.class)
public class TestAlineacionPonderadaDeCadenas {

    @DataPoint
    public static final Datos PINO_VINO = Datos.cadenas("pino", "vino").resulta("~pino", "v~ino"); 
    
    @DataPoints
    public static final Datos[] DATOS = new Datos[] {
        
        Datos.cadenas("alineacion", "expansion").resulta("ali~~~neacion", "~~expan~~sion"),
        Datos.cadenas("matematica", "algebra").resulta("matemati~ca", "~a~~~lgebra"),
        Datos.cadenas("simetria", "enfermeria").resulta("sim~etr~i~~a", "~enfe~rmeria"),
        Datos.cadenas("xaacccbbaaa", "aaacbba").resulta("xaacccbbaaa", "~aa~acbb~~a"),
        Datos.cadenas("el hombre ilustrado", "cronicas marcianas").resulta("el hombre~~~ ilustra~do", "~~cron~~icas marc~ianas"),
        Datos.cadenas("iniciado", "terminado").resulta("~i~niciado", "termi~nado"),
        Datos.cadenas("algoritmos y programacion", "teoria de algoritmos").resulta("~~~~~~~~~~algoritmos y programacion", "teoria de algoritm~~~~~~~o~~~~~s~~~"),
        Datos.cadenas("uno dos tres cuatro", "cuatro tres dos uno").resulta("uno dos~~~~~ tres ~~c~uatro", "~~~~~~cuatro tres dos u~~no"),
        Datos.cadenas("1234567", "7654321").resulta("1~234567", "76543~21"),
        Datos.cadenas("entrada", "salida").resulta("~~~entrada", "sali~~~~da"),
        Datos.cadenas("", "").resulta("", "")

    };
    
    @Theory
    public void ejecutar(Datos datos) {
        
        /* inicialización */
        AlinearCadenas alinear = new AlinearCadenas(datos.cadena1, datos.cadena2);
        alinear.puntuar('p', 'v', -5);
        alinear.puntuar('s', 'c',  5);
        alinear.puntuar('a', 'e', -6);
        alinear.puntuar('a', 'x', -5);
        alinear.puntuar('i', 'e',  2);
        alinear.puntuar('m', 'n',  4);
        
        alinear.puntuar('1', '7',  1);
        alinear.puntuar('2', '3', -1);
        alinear.puntuar('3', '4',  1);

        /* operación */
        alinear.ejecutar();
        Alineacion resultado = alinear.alineacion();
    
        /* comprobación */
        assertThat("alinear <'" + alinear.cadena1() + "'> con '" + alinear.cadena2() + "'", 
                   resultado.cadena1(), is( equalTo( datos.alineacionEsperada1 )));
        
        assertThat("alinear '" + alinear.cadena1() + "' con <'" + alinear.cadena2() + "'>", 
                   resultado.cadena2(), is( equalTo( datos.alineacionEsperada2 )));
     
    }
    
    protected static class Datos {
        
        public String cadena1;
        public String cadena2;
        public String alineacionEsperada1;
        public String alineacionEsperada2;
        
        
        public Datos(String cadena1, String cadena2) {
            
            this.cadena1 = cadena1;
            this.cadena2 = cadena2;
        }

        public Datos resulta(String alineacionEsperada1, String alineacionEsperada2) {
            
            this.alineacionEsperada1 = alineacionEsperada1;
            this.alineacionEsperada2 = alineacionEsperada2;
            return this;
        }
        
        public static Datos cadenas(String cadena1, String cadena2) {
            
            return new Datos(cadena1, cadena2);
        }
    }
}
