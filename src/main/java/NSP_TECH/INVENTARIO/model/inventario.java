package NSP_TECH.INVENTARIO.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="INVENTARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class inventario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "ID_INVENTARIO")
    @Schema(description="identificador de inventario", example="11")
    private long id_inventario;

    @Column(name= "STOCK_MINIMO",nullable= true , precision= 5)
    @Schema(description="cantidad minima de productos en stock", example="10")
    private int stock_minimo;

    @Column(name= "CANTIDAD",nullable= false , precision= 10)
    @Schema(description="cantidad existente de productos en stock", example="15")
    private int cantidad;

    @Column(name= "STOCK_MAXIMO",nullable= true , precision =10 )
    @Schema(description="cantidad maxima de productos en stock", example="50")
    private int  stock_maximo;

    @Column(name = "ID_SUCURSAL",nullable= false , precision = 10)
    @Schema(description="identificacdor de sucursal", example="1")
    private Long id_sucursal;

    @Column(name = "ID_PRODUCTO",nullable= false , precision = 10)
    @Schema(description="identificador de producto", example="1")
    private Long id_producto;




}