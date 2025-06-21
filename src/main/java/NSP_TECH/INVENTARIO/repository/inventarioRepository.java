package NSP_TECH.INVENTARIO.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import NSP_TECH.INVENTARIO.model.inventario;

public interface inventarioRepository extends JpaRepository<inventario, Long>{
    @Query("SELECT i FROM inventario i WHERE i.ID_SUCURSAL = :idSucursal")
List<inventario> buscarPorIdSucursal(@Param("idSucursal") Long idSucursal);
}
