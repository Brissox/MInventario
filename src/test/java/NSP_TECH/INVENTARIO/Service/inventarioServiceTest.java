package NSP_TECH.INVENTARIO.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import NSP_TECH.INVENTARIO.model.inventario;
import NSP_TECH.INVENTARIO.repository.inventarioRepository;
import NSP_TECH.INVENTARIO.services.inventarioServices;

public class inventarioServiceTest{
    
    @Mock
    private inventarioRepository inventariorepository;
    
    @InjectMocks
    private inventarioServices inventarioservices;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testBuscarTodo(){
    List<inventario> lista = new  ArrayList<>();

    inventario inv1 = new inventario();
    inventario inv2 = new inventario();

    inv1.setId_inventario(11L);
    inv1.setStock_minimo(11);
    inv1.setCantidad(12);
    inv1.setStock_maximo(50);
    inv1.setId_sucursal(11L);
    inv1.setId_producto(11L);

    inv2.setId_inventario(12L);
    inv2.setStock_minimo(12);
    inv2.setCantidad(12);
    inv2.setStock_maximo(60);
    inv2.setId_sucursal(12L);
    inv2.setId_producto(12L);
    

    lista.add(inv1);
    lista.add(inv2);

    when(inventariorepository.findAll()).thenReturn(lista);
    List<inventario> resultBusqueda = inventarioservices.BuscarTodosInventarios();
    assertEquals(2,resultBusqueda.size());
    verify(inventariorepository, times(1)).findAll();

}

    @Test
    public void testBuscarUnInventario(){
    inventario inv1 = new inventario();

    inv1.setId_inventario(11L);
    inv1.setStock_minimo(11);
    inv1.setCantidad(12);
    inv1.setStock_maximo(50);
    inv1.setId_sucursal(11L);
    inv1.setId_producto(11L);

    when(inventariorepository.findById(11L)).thenReturn(Optional.of(inv1));

    inventario invBuscado = inventarioservices.BuscarUnInventario(11L);
    assertEquals(11L,invBuscado.getId_inventario());
    verify(inventariorepository, times(1)).findById(11L);

    }



    @Test
    public void testGuardarInventario(){
        inventario i = new inventario();
        i.setId_inventario(12L);
        i.setStock_minimo(12);
        i.setCantidad(12);
        i.setStock_maximo(60);
        i.setId_sucursal(12L);
        i.setId_producto(12L);

        when(inventariorepository.save(any())).thenReturn(i);


        inventario inventarioGuardar = inventarioservices.GuardarInventario(i);
        assertEquals(12L, inventarioGuardar.getId_inventario());
        verify(inventariorepository, times(1)).save(i);

    }

    @Test
    public void testEditarInventario(){

        inventario inventarioO = new inventario();
        inventarioO.setId_inventario(11L);
        inventarioO.setCantidad(1);
        inventarioO.setStock_minimo(10);

        inventario inventarioE = new inventario();
        inventarioE.setId_inventario(11L);
        inventarioE.setCantidad(5);
        inventarioE.setStock_minimo(100);

        when(inventariorepository.save(any(inventario.class))).thenReturn(inventarioE);
        when(inventariorepository.existsById(11L)).thenReturn(true);
        inventario resultado = inventarioservices.GuardarInventario(inventarioE);

        assertNotNull(resultado);
        assertEquals(11L, resultado.getId_inventario());
        assertEquals(5, resultado.getCantidad());
        assertEquals(100, resultado.getStock_minimo());

        verify(inventariorepository, times(1)).save(inventarioE);
    }

    @Test
    public void testEliminarInventario(){
        Long id = 11L;
        doNothing().when(inventariorepository).deleteById(id);

        inventarioservices.EliminarInventario(11L);

        verify(inventariorepository, times(1)).deleteById(id);

    }

}


