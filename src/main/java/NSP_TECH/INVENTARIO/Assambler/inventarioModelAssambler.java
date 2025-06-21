package NSP_TECH.INVENTARIO.Assambler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import NSP_TECH.INVENTARIO.controller.InventarioController;
import NSP_TECH.INVENTARIO.model.inventario;

@Component
public class inventarioModelAssambler implements RepresentationModelAssembler<inventario, EntityModel<inventario>>{

    @Override
    public EntityModel<inventario> toModel(inventario i) {
        return EntityModel.of(
            i,
            linkTo(methodOn(InventarioController.class).BuscarUnInventario(i.getID_INVENTARIO())).withRel("LINKS"),
            linkTo(methodOn(InventarioController.class).ListarInventarios()).withRel("todas-los-inventario"),
            linkTo(methodOn(InventarioController.class).ActualizarInventario(i.getID_INVENTARIO(), i)).withRel("actualiza-una-venta"),
            linkTo(methodOn(InventarioController.class).buscarSucursal(i.getID_SUCURSAL())).withRel("Todos los inventarios segun sucursal"),
            linkTo(methodOn(InventarioController.class).listarInventarioProductos()).withRel("todas-los-inventario-productos"),
            linkTo(methodOn(InventarioController.class).inventarioP(i.getID_INVENTARIO())).withRel("Inventario-Producto-DTO")
        );

}
}

/*   linkTo(methodOn(InventarioController.class).EliminarInventario(p.getID_INVENTARIO())).withRel("elimina-un-inventario"), */

