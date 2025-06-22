package NSP_TECH.INVENTARIO.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor


public class producto {
    @Schema(description="aa")
    private String nombre;
    @Schema(description="aa")
    private String descripcion;
    @Schema(description="aa")
    private String marca;
    @Schema(description="aa")
    private Long precio;
    @Schema(description="aa")
    private String sku;
    @Schema(description="aa")
    private char estado;
    @Schema(description="aa")
    private Long id_proveedor;

}
