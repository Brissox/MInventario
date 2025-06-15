package NSP_TECH.INVENTARIO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor


public class producto {

    private Long id_producto;
    private String NOMBRE;
    private String descripcion;
    private String marca;
    private Long CANTIDAD;
    private Long PRECIO;
    private String SKU;
    private char estado;
    private Long ID_INVENTARIO;

}
