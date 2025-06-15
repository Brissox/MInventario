package NSP_TECH.INVENTARIO.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import NSP_TECH.INVENTARIO.DTO.producto;
import NSP_TECH.INVENTARIO.model.inventario;
import NSP_TECH.INVENTARIO.repository.inventarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional


public class inventarioServices {
    private final WebClient webClient;

    public inventarioServices(WebClient webClient){
        this.webClient = webClient;
    }


    @Autowired
    private inventarioRepository inventariorepository;

    public producto buscarInventario(long ID_INVENTARIO){
        producto inventarioP = webClient.get()
                                .uri("/{ID_INVENTARIO}",ID_INVENTARIO)
                                                    .retrieve()
                                                    .bodyToMono(producto.class)
                                                    .block();
        return inventarioP;

    }


    public List<inventario> BuscarTodosInventarios(){
        return inventariorepository.findAll();
    }

    public inventario BuscarUnInventario(Long ID_SUCURSAL){
        return inventariorepository.findById(ID_SUCURSAL).get();
    }

}
