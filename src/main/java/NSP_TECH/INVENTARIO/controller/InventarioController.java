package NSP_TECH.INVENTARIO.controller;

import java.util.ArrayList;
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

import NSP_TECH.INVENTARIO.Assambler.inventarioModelAssambler;
import NSP_TECH.INVENTARIO.DTO.inventarioProductoDTO;
import NSP_TECH.INVENTARIO.DTO.producto;
import NSP_TECH.INVENTARIO.model.inventario;
import NSP_TECH.INVENTARIO.services.inventarioServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/Inventarios")

public class InventarioController {

    @Autowired
    private inventarioServices inventarioservices;
    
    @Autowired
    private inventarioModelAssambler assambler;
    

    // ENDPOINT PARA BUSCAR TODOS LOS INVENTARIOS
    @GetMapping
    @Operation(summary = "INVENTARIOS", description = "Operacion que lista todos los inventarios")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente los inventarios", content = @Content(mediaType = "application/json", schema = @Schema(implementation = inventario.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun inventario", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })

    public ResponseEntity<?> ListarInventarios(){
        List<inventario> inventarios = inventarioservices.BuscarTodosInventarios();
        if (inventarios.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran datos");
        } else {
            return ResponseEntity.ok(assambler.toCollectionModel(inventarios));
        }
    }

     // ENDPOINT PARA BUSCAR UN INVENTARIO
    @GetMapping("/{ID_INVENTARIO}")
    @Operation(summary = "INVENTARIO", description = "Operacion que lista un inventario")
    @Parameters (value = {
        @Parameter (name="ID_INVENTARIO", description= "ID del inventario que se buscara", in = ParameterIn.PATH, required= true)

    })
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el inventario ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = inventario.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun inventario", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })
    public ResponseEntity<?> BuscarUnInventario(@PathVariable Long ID_INVENTARIO){

        try {
            inventario inventarioBuscado = inventarioservices.BuscarUnInventario(ID_INVENTARIO);
            return ResponseEntity.ok(assambler.toModel(inventarioBuscado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra Usuario");
        }
        
    }

 // ENDPOINT PARA BUSCAR UN INVENTARIO POR SUCURSAL
    @GetMapping("/Sucursal/{ID_SUCURSAL}")
    @Operation(summary = "INVENTARIO POR SUCURSAL", description = "Operacion que lista un inventario por sucursal")
    @Parameters (value = {
        @Parameter (name="ID_SUCURSAL", description= "ID de la sucursal del inventario que se buscara", in = ParameterIn.PATH, required= true)

    })
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el inventario ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = inventario.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun inventario", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })
        public ResponseEntity<?> buscarSucursal(@PathVariable Long ID_SUCURSAL){
        List<inventario> inventarios = inventarioservices.BuscarUnInventarioS(ID_SUCURSAL);
        if (inventarios.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran datos");
        } else {
            return ResponseEntity.ok(assambler.toCollectionModel(inventarios));
        }
    }

// ENDPOINT PARA BUSCAR TODOS LOS DTOINVENTARIOPRODUCTOS

    @GetMapping("/IP/listar")

    @Operation(summary = "DTOINVENTARIOPRODUCTOS", description = "Operacion que lista todos los DTOINVENTARIOPRODUCTOS")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente los inventarios", content = @Content(mediaType = "application/json", schema = @Schema(implementation = inventarioProductoDTO.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun inventario", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos"))),
        @ApiResponse(responseCode = "500",description = "Error interno al obtener los inventarios con productos", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Error al listar inventarios con productos")))
    })

    public ResponseEntity<?> listarInventarioProductos() {
    try {
        List<inventario> inventarios = inventarioservices.BuscarTodosInventarios();
        List<inventarioProductoDTO> listaDTO = new ArrayList<>();

        for (inventario inv : inventarios) {
            try {
                producto prod = inventarioservices.buscarInventario(inv.getId_inventario());
                inventarioProductoDTO dto = new inventarioProductoDTO();
                dto.setNombre(prod.getNombre());
                dto.setPrecio(prod.getPrecio());
                dto.setSku(prod.getSku());
                dto.setId_producto(inv.getId_producto());
                dto.setCantidad(inv.getCantidad());
                dto.setEstado(prod.getEstado());
                dto.setId_inventario(inv.getId_inventario());
                dto.setStock_maximo(inv.getStock_maximo());
                dto.setStock_minimo(inv.getStock_minimo());
                dto.setId_sucursal(inv.getId_sucursal());

                listaDTO.add(dto);
            } catch (Exception e) {
                // puedes registrar el error o continuar con el siguiente
                System.err.println("Error al obtener producto para inventario ID " + inv.getId_inventario() + ": " + e.getMessage());
            }
        }

        return ResponseEntity.ok(assambler.toCollectionModel(inventarios));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran datos");
    }
}


     // ENDPOINT PARA REGISTRAR UN INVENTARIO
    @PostMapping
    @Operation(summary = "ENDPOINT QUE REGISTRA UN INVENTARIO", description = "ENDPOINT QUE REGISTRA UN INVENTARIO",requestBody= @io.swagger.v3.oas.annotations.parameters.RequestBody(description="INVENTARIO QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = inventario.class))))
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se registro correctamente el inventario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = inventario.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar el inventario", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el inventario")))
    })
    public ResponseEntity<?> GuardarInventario(@RequestBody inventario IGuardar){
    try {
            inventario IRegistrar = inventarioservices.GuardarInventario(IGuardar);
            return ResponseEntity.ok(assambler.toModel(IGuardar));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el Producto");
    }
    }

  // ENDPOINT PARA EDITAR UN INVENTARIO

    @PutMapping("/{ID_INVENTARIO}") //SOLO PERMITE ACTUALIZAR ESCRIBIENDO TODOS LOS DATOS

@Operation(summary = "ENDPOINT QUE EDITA UN INVENTARIO", description = "ENDPOINT QUE EDITA UN INVENTARIO", requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(description="INVENTARIO QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = inventario.class))))
    @Parameters (value = {
        @Parameter (name="ID_INVENTARIO", description= "ID del inventario que se editara", in = ParameterIn.PATH, required= true)})

    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se edito correctamente el inventario", content = @Content(mediaType = "application/json", schema = @Schema(implementation = inventario.class))),
        @ApiResponse(responseCode = "500", description = "inventario no esta registrado", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar el inventario")))
    })
        
    public ResponseEntity<?> ActualizarInventario(@PathVariable Long ID_INVENTARIO, @RequestBody inventario inventarioctualizar){
        try {
            inventario iActualizado = inventarioservices.BuscarUnInventario(ID_INVENTARIO);
            iActualizado.setCantidad(inventarioctualizar.getCantidad());
            iActualizado.setStock_maximo(inventarioctualizar.getStock_maximo());
            iActualizado.setStock_minimo(inventarioctualizar.getStock_minimo());
            iActualizado.setId_sucursal(inventarioctualizar.getId_sucursal());
            iActualizado.setId_producto(inventarioctualizar.getId_producto());
            iActualizado.setId_inventario(inventarioctualizar.getId_inventario());
            inventarioservices.GuardarInventario(iActualizado);
            return ResponseEntity.ok(assambler.toModel(iActualizado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("inventario no esta registrado");
        }
    }


       // ENDPOINT PARA BUSCAR UN DTOINVENTARIOPRODUCTO
@GetMapping("/IP/{ID_INVENTARIO}")

@Operation(summary = "DTOINVENTARIOPRODUCTO", description = "Operacion que lista un DTOINVENTARIOPRODUCTO")
    @Parameters (value = {
        @Parameter (name="ID_INVENTARIO", description= "ID del inventario que se buscara con el producto", in = ParameterIn.PATH, required= true)

    })
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el DTOINVENTARIOPRODUCTO ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = inventarioProductoDTO.class))), 
        @ApiResponse(responseCode = "404", description = "No se encontro ningun DTOINVENTARIOPRODUCTO", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })
public ResponseEntity<?> inventarioP(@PathVariable Long ID_INVENTARIO) {
    try {
        inventario iBuscado = inventarioservices.BuscarUnInventario(ID_INVENTARIO);
        producto iproducto = inventarioservices.buscarInventario(iBuscado.getId_inventario());
        inventarioProductoDTO inventarioDTO = new inventarioProductoDTO();
        inventarioDTO.setNombre(iproducto.getNombre());
        inventarioDTO.setPrecio(iproducto.getPrecio());
        inventarioDTO.setSku(iproducto.getSku());
        inventarioDTO.setId_producto(iBuscado.getId_producto());
        inventarioDTO.setCantidad(iBuscado.getCantidad());
        inventarioDTO.setEstado(iproducto.getEstado());
        inventarioDTO.setId_inventario(iBuscado.getId_inventario());
        inventarioDTO.setStock_maximo(iBuscado.getStock_maximo());
        inventarioDTO.setStock_minimo(iBuscado.getStock_minimo());
        inventarioDTO.setId_sucursal(iBuscado.getId_sucursal());

        return ResponseEntity.ok(assambler.toModel(iBuscado));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno: " + e.getMessage());
    }
}

}
