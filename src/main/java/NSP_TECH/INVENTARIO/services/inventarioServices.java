package NSP_TECH.INVENTARIO.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NSP_TECH.INVENTARIO.model.inventario;
import NSP_TECH.INVENTARIO.repository.inventarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional


public class inventarioServices {
    @Autowired
    private inventarioRepository inventariorepository;


    public List<inventario> BuscarTodosInventarios(){
        return inventariorepository.findAll();
    }

    public List<inventario> BuscarUnInventarioS(Long ID_SUCURSAL) {
    return inventariorepository.buscarPorIdSucursal(ID_SUCURSAL);
}

    public inventario BuscarUnInventario(Long ID_INVENTARIO){
        return inventariorepository.findById(ID_INVENTARIO).get();
    
}

    public inventario GuardarInventario(inventario inventario){
        return inventariorepository.save(inventario);
    }


    ///SE COMENTA EL WEBCLIENT DEBIDO A QUE SIN ESTE LOS TEST SE REALIZAN, DE LO CONTRARIO DA ERROR
/*
    private final WebClient webClient;

    public inventarioServices(WebClient webClient){
        this.webClient = webClient;
    }

        public producto buscarInventario(long ID_INVENTARIO){
        producto inventarioP = webClient.get()
                                .uri("/{ID_INVENTARIO}",ID_INVENTARIO)
                                                    .retrieve()
                                                    .bodyToMono(producto.class)
                                                    .block();
        return inventarioP;

    }

    public List<producto> listarProductos() {
        return webClient.get()
                        .uri("/") // ya estás en /api/v1/productos por defecto
                        .retrieve()
                        .bodyToFlux(producto.class)
                        .collectList()
                        .block();
    }
*/
}

