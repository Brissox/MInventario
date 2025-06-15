package NSP_TECH.INVENTARIO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


@GetMapping("/IP/{ID_INVENTARIO}")
public ResponseEntity<?> inventarioP(@PathVariable Long ID_INVENTARIO) {
    try {
        inventario iBuscado = inventarioservices.BuscarUnInventario(ID_INVENTARIO);
        producto iproducto = inventarioservices.buscarInventario(iBuscado.getID_INVENTARIO());
        inventarioProductoDTO inventarioDTO = new inventarioProductoDTO();
        inventarioDTO.setNOMBRE(iproducto.getNOMBRE());
        inventarioDTO.setPRECIO(iproducto.getPRECIO());
        inventarioDTO.setSKU(iproducto.getSKU());
        inventarioDTO.setCANTIDAD(iproducto.getCANTIDAD());
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
