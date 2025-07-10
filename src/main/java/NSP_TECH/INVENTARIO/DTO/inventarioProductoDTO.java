package NSP_TECH.INVENTARIO.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor


public class inventarioProductoDTO {
    @Schema(description="identificador de inventario", example="11")
    private Long id_inventario;
    @Schema(description="identificador de producto", example="1")
    private Long id_producto;
    @Schema(description="nombre del producto",example="Monster")
    private String nombre;
    @Schema(description="cantidad existente de productos en stock", example="15")
    private int cantidad;
    @Schema(description="valor de venta del producto", example="1500")
    private Long precio;
    @Schema(description="codigo unico que identifica el producto en el inventaro", example="1112225")
    private String sku;
    @Schema(description="estado del producto", example="D = Disponible / N = NoDisponible")
    private char estado;
    @Schema(description="cantidad minima de productos en stock", example="10")
    private Integer stock_minimo;
    @Schema(description="cantidad maxima de productos en stock", example="50")
    private Integer stock_maximo;
    @Schema(description="identificacdor de sucursal", example="1")
    private Long id_sucursal;

}
