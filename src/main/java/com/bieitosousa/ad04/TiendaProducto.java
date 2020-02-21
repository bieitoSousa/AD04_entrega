/*
 * The MIT License
 *
 * Copyright 2020 bieito.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.bieitosousa.ad04;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author bieito
 */
@Entity
@Table(name = "TIENDAPRODUCTO")

public class TiendaProducto implements Serializable  {
       @Id
    @Column (name="TIENDAPRODUCTO_id") 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
           @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TIENDA_ID")	
	private Tienda tienda;
               @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCTO_ID")
	private Producto producto;
               @Column(name = "TIENDAPRODUCTO_stock")
        private int stock;
    

    public int getId(){
    return id;
    } 
    
    public void setId(int id) {
		this.id = id;
	}
    

	public Tienda getTienda() {
		return tienda;
	}

        public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	
	public Producto getProducto() {
		return producto;
	}

        public void setProducto(Producto producto) {
		this.producto = producto;
	}
        
        
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

    @Override
    public String toString() {
        return "TiendaProducto{" + "tienda=[" + tienda.toString() + "], producto=[" + producto.toString() + "], stock=[" + stock + "]"+'}';
    }
        

}
