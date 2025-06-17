package NSP_TECH.INVENTARIO.model;

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
    private long ID_INVENTARIO;

    @Column(name= "STOCK_MINIMO",nullable= true , precision= 5)
    private int STOCK_MINIMO;

    @Column(name= "CANTIDAD",nullable= false , precision= 10)
    private int CANTIDAD;

    @Column(name= "STOCK_MAXIMO",nullable= true , precision = 5)
    private int  STOCK_MAXIMO;

    @Column(name = "ID_SUCURSAL",nullable= false , precision = 10)
    private Long ID_SUCURSAL;

    @Column(name = "ID_PRODUCTO",nullable= false , precision = 10)
    private Long ID_PRODUCTO;




}