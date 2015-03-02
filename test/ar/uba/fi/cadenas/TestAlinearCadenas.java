package ar.uba.fi.cadenas;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TestAlinearCadenas {

    @Test
    public void construir() {
        
        AlinearCadenas alinear = new AlinearCadenas("primera","rima");
        
        assertThat("cadena 1", alinear.cadena1(), is( equalTo( "primera" )));
        assertThat("cadena 2", alinear.cadena2(), is( equalTo( "rima" )));
    }
    
    @Test
    public void ejecutar() {
        
        AlinearCadenas alinear = new AlinearCadenas("casa", "casa");
        
        alinear.ejecutar();
        
        assertThat("valor de la alineación", alinear.valor(), is( equalTo( 4 )));
    }
}
