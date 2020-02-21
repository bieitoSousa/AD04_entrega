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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bieito
 */
@Entity
@Table(name = "PRODUCTO")
public class Producto implements Serializable{
    @OneToMany(mappedBy = "producto")
    private Set<TiendaProducto> tiendaProducto = new HashSet<TiendaProducto>();

    @Id
    @Column(name = "PRODUCTO_id")
      @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "PRODUCTO_name", nullable=false, unique = true)

    private String name;
    @Column(name = "PRODUCTO_price",nullable=false)

    private float price;
    @Column(name = "PRODUCTO_description",nullable=false)

    private String description;


   
    public Set<TiendaProducto> getTiendaProducto() {
        return tiendaProducto;
    }

    public void addTiendaProducto(TiendaProducto tiendaProducto) {
        this.tiendaProducto.add(tiendaProducto);
    }

    public void setTiendaProducto(Set<TiendaProducto> tiendaProducto) {
        this.tiendaProducto = tiendaProducto;
    }
public Producto(){}
    public Producto(String name, float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
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

   
}
