package NSP_TECH.INVENTARIO.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import NSP_TECH.INVENTARIO.model.inventario;

public interface inventarioRepository extends JpaRepository<inventario, Long>{

    /*@Query("SELECT i FROM inventario i WHERE i.ID_SUCURSAL = :id_sucursal")
    List<inventario> buscarPorIdSucursal(@Param("id_Sucursal") Long id_sucursal);

    @Query("SELECT i FROM Inventario i WHERE i.id_sucursal = :idSucursal")
    List<inventario> buscarPorIdSucursal(@Param("idSucursal") Long idSucursal); */

    @Query(value = "SELECT * FROM INVENTARIO WHERE ID_SUCURSAL = :idSucursal", nativeQuery = true)
    List<inventario> buscarPorIdSucursalNativo(@Param("idSucursal") Long idSucursal);
}
