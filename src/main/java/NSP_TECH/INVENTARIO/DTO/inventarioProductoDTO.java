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
    private Long id_inventario;
    @Schema(description="aa")
    private Long id_producto;
    @Schema(description="aa")
    private String nombre;
    @Schema(description="aa")
    private int cantidad;
    @Schema(description="aa")
    private Long precio;
    @Schema(description="aa")
    private String sku;
    @Schema(description="aa")
    private char estado;
    @Schema(description="aa")
    private Integer stock_minimo;
    @Schema(description="aa")
    private Integer stock_maximo;
    @Schema(description="aa")
    private Long id_sucursal;

}
