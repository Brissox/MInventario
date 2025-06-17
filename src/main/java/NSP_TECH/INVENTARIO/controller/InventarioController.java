package NSP_TECH.INVENTARIO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import NSP_TECH.INVENTARIO.DTO.inventarioProductoDTO;
import NSP_TECH.INVENTARIO.DTO.producto;
import NSP_TECH.INVENTARIO.model.inventario;
import NSP_TECH.INVENTARIO.services.inventarioServices;

@RestController
@RequestMapping("api/v1/Inventarios")

public class InventarioController {

    @Autowired
    private inventarioServices inventarioservices;

    @GetMapping
    public ResponseEntity<?> ListarInventarios(){
        List<inventario> inventarios = inventarioservices.BuscarTodosInventarios();
        if (inventarios.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran datos");
        } else {
            return ResponseEntity.ok(inventarios);
        }

    }
    @GetMapping("/{ID_INVENTARIO}")
    public ResponseEntity<?> BuscarUnInventario(@PathVariable Long ID_INVENTARIO){

        try {
            inventario inventarioBuscado = inventarioservices.BuscarUnInventario(ID_INVENTARIO);
            return ResponseEntity.ok(inventarioBuscado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran Producto");
        }
        
    }
    
    @PostMapping
    public ResponseEntity<?> GuardarInventario(@RequestBody inventario IGuardar){
    try {
            inventario IRegistrar = inventarioservices.GuardarInventario(IGuardar);
            return ResponseEntity.ok(IRegistrar);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el Producto");
    }
    }
    @PutMapping("/{ID_INVENTARIO}") //SOLO PERMITE ACTUALIZAR ESCRIBIENDO TODOS LOS DATOS
        
    public ResponseEntity<?> ActualizarInventario(@PathVariable Long ID_INVENTARIO, @RequestBody inventario inventarioctualizar){
        try {
            inventario iActualizado = inventarioservices.BuscarUnInventario(ID_INVENTARIO);
            iActualizado.setCANTIDAD(inventarioctualizar.getCANTIDAD());
            iActualizado.setSTOCK_MAXIMO(inventarioctualizar.getSTOCK_MAXIMO());
            iActualizado.setSTOCK_MINIMO(inventarioctualizar.getSTOCK_MINIMO());
            iActualizado.setID_SUCURSAL(inventarioctualizar.getID_SUCURSAL());
            iActualizado.setID_PRODUCTO(inventarioctualizar.getID_PRODUCTO());
            iActualizado.setID_INVENTARIO(inventarioctualizar.getID_INVENTARIO());
            inventarioservices.GuardarInventario(iActualizado);
            return ResponseEntity.ok(iActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no esta registrado");
        }
    }


@GetMapping("/IP/{ID_INVENTARIO}")
public ResponseEntity<?> inventarioP(@PathVariable Long ID_INVENTARIO) {
    try {
        inventario iBuscado = inventarioservices.BuscarUnInventario(ID_INVENTARIO);
        producto iproducto = inventarioservices.buscarInventario(iBuscado.getID_INVENTARIO());
        inventarioProductoDTO inventarioDTO = new inventarioProductoDTO();
        inventarioDTO.setNOMBRE(iproducto.getNOMBRE());
        inventarioDTO.setPRECIO(iproducto.getPRECIO());
        inventarioDTO.setSKU(iproducto.getSKU());
        inventarioDTO.setID_PRODUCTO(iBuscado.getID_INVENTARIO());
        inventarioDTO.setCANTIDAD(iBuscado.getCANTIDAD());
        inventarioDTO.setEstado(iproducto.getEstado());
        inventarioDTO.setID_INVENTARIO(iBuscado.getID_INVENTARIO());
        inventarioDTO.setSTOCK_MAXIMO(iBuscado.getSTOCK_MAXIMO());
        inventarioDTO.setSTOCK_MINIMO(iBuscado.getSTOCK_MINIMO());
        inventarioDTO.setID_SUCURSAL(iBuscado.getID_SUCURSAL());

        return ResponseEntity.ok(inventarioDTO);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno: " + e.getMessage());
    }
}


}
