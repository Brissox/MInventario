package NSP_TECH.INVENTARIO.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class inventarioProductoDTO {

    private Long ID_INVENTARIO;
    private Long ID_PRODUCTO;
    private String NOMBRE;
    private int CANTIDAD;
    private Long PRECIO;
    private String SKU;
    private char estado;
    private Integer STOCK_MINIMO;
    private Integer STOCK_MAXIMO;
    private Long ID_SUCURSAL;

}
