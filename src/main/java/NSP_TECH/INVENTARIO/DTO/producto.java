package NSP_TECH.INVENTARIO.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor


public class producto {
    @Schema(description="nombre del producto",example="Monster")
    private String nombre;
    @Schema(description="aa")
    private String descripcion;
    @Schema(description="nombre de la empresa que fabrica el producto",example="Coca-Cola company")
    private String marca;
    @Schema(description="valor de venta del producto", example="1500")
    private Long precio;
    @Schema(description="codigo unico que identifica el producto en el inventaro", example="1112225")
    private String sku;
    @Schema(description="estado del producto", example="D = Disponible / N = NoDisponible")
    private char estado;
    @Schema(description="identificador del proveedor del producto", example="11")
    private Long id_proveedor;

}
