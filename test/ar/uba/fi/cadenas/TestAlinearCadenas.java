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
    public void alinearCadenasIguales() {
        
        AlinearCadenas alinear = new AlinearCadenas("casa", "casa");
        
        alinear.ejecutar();
        
        assertThat("valor de la alineación", alinear.valor(), is( equalTo( 4 )));
    }
    
    @Test
    public void alinearCadenasConPrimerCaracterDiferente() {
        
        AlinearCadenas alinear = new AlinearCadenas("fase", "pase");
        
        alinear.ejecutar();
        
        assertThat("valor de la alineación", alinear.valor(), is( equalTo( 3 )));
    }
    
    @Test
    public void alinearCadenasConUltimoCaracterDiferente() {
        
        AlinearCadenas alinear = new AlinearCadenas("manto", "manta");
        
        alinear.ejecutar();
        
        assertThat("valor de la alineación", alinear.valor(), is( equalTo( 4 )));
    }
    
    @Test
    public void alinearCadenasConUnCaracterInteriorDiferente() {
        
        AlinearCadenas alinear = new AlinearCadenas("casa", "caza");
        
        alinear.ejecutar();
        
        assertThat("valor de la alineación", alinear.valor(), is( equalTo( 3 )));
    }

    @Test
    public void alinearConCadena1MasLargaQueCadena2() {
        
        AlinearCadenas alinear = new AlinearCadenas("casa", "as");
        
        alinear.ejecutar();
        
        assertThat("valor de la alineación", alinear.valor(), is( equalTo( 2 )));
    }
    
    @Test
    public void alinearConCadena2MasLargaQueCadena1() {
        
        AlinearCadenas alinear = new AlinearCadenas("as", "casa");
        
        alinear.ejecutar();
        
        assertThat("valor de la alineación", alinear.valor(), is( equalTo( 2 )));
    }

    @Test
    public void obtenerCadena1Alineada() {
        
        AlinearCadenas alinear = new AlinearCadenas("casa", "caza");
        
        alinear.ejecutar();
        
        assertThat("cadena1 alineada", alinear.cadena1Alineada(), is( equalTo( "casa" )));
    }

    @Test
    public void obtenerCadena2Alineada() {
        
        AlinearCadenas alinear = new AlinearCadenas("casa", "caza");
        
        alinear.ejecutar();
        
        assertThat("cadena2 alineada", alinear.cadena2Alineada(), is( equalTo( "caza" )));
    }

    @Test
    public void obtenerCadena1AlineadaConVacio() {
        
        AlinearCadenas alinear = new AlinearCadenas("casa", "carta");
        
        alinear.ejecutar();
        
        assertThat("cadena1 alineada", alinear.cadena1Alineada(), is( equalTo( "ca~sa" )));
    }

    @Test
    public void obtenerCadena2AlineadaConVacio() {
        
        AlinearCadenas alinear = new AlinearCadenas("carta", "casa");
        
        alinear.ejecutar();
        
        assertThat("cadena2 alineada", alinear.cadena2Alineada(), is( equalTo( "ca~sa" )));
    }
    
    @Test
    public void obtenerCadenasAlineadasConMultiplesVacios() {
        
        AlinearCadenas alinear = new AlinearCadenas("cacdbd", "cacbbdb");
        
        alinear.ejecutar();
        
        assertThat("cadena1 alineada", alinear.cadena1Alineada(), is( equalTo( "cac~~dbd" )));
        assertThat("cadena2 alineada", alinear.cadena2Alineada(), is( equalTo( "cacbbdb~" )));
    }
}
