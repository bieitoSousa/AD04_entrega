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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author bieito
 */
public class Producto {

      @Id
@Column (name="PRODUCTO_id")  
    private int id = -1;
@Column (name="PRODUCTO_name")  

      private String name;
@Column (name="PRODUCTO_price")  

    private float price;
@Column (name="PRODUCTO_description")  

    private String description;
@Column (name="TIENDAPRODUCTO_id")  

    private int stock;

    public Producto(String name, float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public int getStock(Tienda t) {
        cargarStock(t);
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        if (id == -1) {
            cargarId();
        }
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Producto{ id=" + id + "name=" + name + ", price=" + price + ", description=" + description + '}';
    }

 

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (Float.floatToIntBits(this.price) != Float.floatToIntBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    //      ==== OPERACIONES LECTURA  SOBRE DB ======== \\
    /**
     * ************************************************************
     * Recupera el id del Objeto : con una conslta en la DB  
     ***************************************************************
     */
    private void cargarId() {
       
    }

    private void cargarStock(Tienda t) {
        
    }

    
}

