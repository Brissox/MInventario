package NSP_TECH.INVENTARIO.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class inventarioProductoDTO {
    @Schema(description="aa")
    private Long ID_INVENTARIO;
    @Schema(description="aa")
    private Long ID_PRODUCTO;
    @Schema(description="aa")
    private String NOMBRE;
    @Schema(description="aa")
    private int CANTIDAD;
    @Schema(description="aa")
    private Long PRECIO;
    @Schema(description="aa")
    private String SKU;
    @Schema(description="aa")
    private char estado;
    @Schema(description="aa")
    private Integer STOCK_MINIMO;
    @Schema(description="aa")
    private Integer STOCK_MAXIMO;
    @Schema(description="aa")
    private Long ID_SUCURSAL;

}
