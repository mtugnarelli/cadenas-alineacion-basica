package ar.uba.fi.cadenas;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ar.uba.fi.cadenas.AlinearCadenas.Alineacion;

@RunWith(Parameterized.class)
public class TestAlineacionDeCadenas {

    private AlinearCadenas alinear;
    private String cadena1AlineadaEsperada;
    private String cadena2AlineadaEsperada;
    
    public TestAlineacionDeCadenas(String cadena1, String cadena2, 
                                                String cadena1Alineada, String cadena2Alineada) {
        
        alinear = new AlinearCadenas(cadena1, cadena2);
        cadena1AlineadaEsperada = cadena1Alineada;
        cadena2AlineadaEsperada = cadena2Alineada;
    }
    
    @Test
    public void buscar() {
        
        alinear.ejecutar();
        Alineacion alineacion = alinear.alineacion();
        
        assertThat("alineacion 1", alineacion.cadena1(), is( equalTo( cadena1AlineadaEsperada )));
        assertThat("alineacion 2", alineacion.cadena2(), is( equalTo( cadena2AlineadaEsperada )));
    }
    
    @Parameters(name = "{0} | {1}")
    public static Collection<Object[]> obtenerParametros() {

        return tests(
                    test("distancia", "estancia", "distancia", "~estancia"),
                    test("mira", "mirador", "mira~~~", "mirador"),
                    test("muro", "marmol", "mur~o~", "marmol"),
                    test("aaggctttaaagc", "atggtaaagaat", "aaggctttaaag~~c", "atgg~~~taaagaat")
                    
                );
    }
    
    protected static Object[] test(Object... parametros) {
        
        return parametros;
    }
    
    protected static Collection<Object[]> tests(Object[]... parametros) {
        
        return Arrays.asList(parametros);
    }
}
