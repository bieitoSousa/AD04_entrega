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

import com.bieitosousa.ad04.Json.Provincia;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

/**
 *
 * @author bieito
 */
//    @ManyToOne
//    @JoinColumn(name = "TIENDAEMPLEADO_id")
//    private TiendaEmpleado tiendaempleado;
//
//    @ManyToOne
//    @JoinColumn(name = "TIENDAPRODUCTO_id")
//    private TiendaProducto tindaproducto;
@Entity
@Table(name = "TIENDA")
public class Tienda implements Serializable {

//    private Franquicia f = null;
    private HashMap<String, Producto> mapProducto = new HashMap<String, Producto>();
    private HashMap<String, Empleado> mapEmpleado = new HashMap<String, Empleado>();

    boolean operacionTiendaProducto = true;
    boolean operacionTiendaEmpleado = true;

    @OneToMany(mappedBy = "tienda")
    private Set<TiendaProducto> tiendaProducto = new HashSet<TiendaProducto>();
//    @OneToMany(mappedBy = "tienda")
//    private Set<Producto> productos = new HashSet<Producto>();
//     @OneToMany(mappedBy = "tienda")
//    private Set<Empleado> empleados = new HashSet<Empleado>();
    @OneToMany(mappedBy = "tienda")
    private Set<TiendaEmpleado> tiendaEmpleado = new HashSet<TiendaEmpleado>();

    @Id
    @Column(name = "TIENDA_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "TIENDA_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "PROVINCIA_id")
    private Provincia provincia;
    @Column(name = "TIENDA_ciudad", nullable = false, unique = true)
    private String ciudad;

    public Set<TiendaProducto> getTiendaProducto() {
        return tiendaProducto;
    }
//   
//    public Set<Producto> getProducto() {
//        return productos;
//    }

    public Set<TiendaEmpleado> getTiendaEmpleado() {
        return tiendaEmpleado;
    }

//    public Set<Empleado> getEmpleado() {
//        return empleados;
//    }
    public Tienda() {
    }

    public Tienda(String name, Provincia provincia, String ciudad) {
        this.name = name;
        this.provincia = provincia;
        this.ciudad = ciudad;
    }

    public void addTiendaProducto(TiendaProducto tiendaProducto) {
        this.tiendaProducto.add(tiendaProducto);
    }

    public void addTiendaEmpleado(TiendaEmpleado tiendaEmpleado) {
        this.tiendaEmpleado.add(tiendaEmpleado);
    }

    public void setTiendaProducto(Set<TiendaProducto> tiendaProducto) {
        this.tiendaProducto = tiendaProducto;
    }

    public void setTiendaEmpleado(Set<TiendaEmpleado> tiendaEmpleado) {
        this.tiendaEmpleado = tiendaEmpleado;
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public HashMap<String, Producto> getMapProducto() {
        if (operacionTiendaProducto) {
            return mapProducto = cargarProducto();
        } else {
            return mapProducto;

        }

    }

    public void setMapProducto(HashMap<String, Producto> mapProducto) {
        this.mapProducto = mapProducto;
    }

    public HashMap<String, Empleado> getMapEmpleado() {
        if (this.operacionTiendaEmpleado) {
            return mapEmpleado = cargarEmpleado();
        } else {
            return mapEmpleado;
        }
    }

    public void setMapEmpleado(HashMap<String, Empleado> mapEmpleado) {
        this.mapEmpleado = mapEmpleado;
    }

    /**
     * ************************************************************
     * METODOS toString
     *
     **************************************************************
     */
    @Override
    public String toString() {
        return "Tienda{ name=" + name + ", provincia=" + provincia.toString() + ", ciudad=" + ciudad + '}';
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
        final Tienda other = (Tienda) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.provincia, other.provincia)) {
            return false;
        }
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        return true;
    }

    /**
     * ************************************************************************************
     * OPERACIONES HIVERNATE ADD -> AÑADE UN OBJETO PRODUCTO/EMPLEADO A LOS
     * REGISTROS DB CORRESPONDIENTES UPDATE -> MODIFICA UN OBJETO
     * PRODUCTO/EMPLEADO A LOS REGISTROS DB CORRESPONDIENTES DELETE -> ELIMINA
     * UN OBJETO PRODUCTO/EMPLEADO A LOS REGISTROS DB CORRESPONDIENTES VIEW ->
     * MUESTRA LOS VALORES DE LOS REGISTROS DE UN OBJETO PRODUCTO/EMPLEADO
     * ====== GET/SET
     * ===============================================================
     * GETPRODUCTOSTOCK -> MUESTRA MUESTRA EL STOCK DE UN PRODUCTO GETNHORA ->
     * MUESTRA LAS HORAS DE UN EMPLEADO ASOCIADAS A LA TIENDA
     * **************************************************************************************
     */
    public boolean addProducto(Producto producto, int Stock) {
        TiendaProducto tp = new TiendaProducto();
        tp.setProducto(producto);
        tp.setTienda(this);
        tp.setStock(Stock);
        if (this.tiendaProducto.add(tp)) {
            HibernateUtil.getInstance().add(tp);
            this.operacionTiendaProducto = false;
            return true;
        }
        return false;

    }

    public boolean addEmpleado(Empleado empleado, float nhoras) {
        TiendaEmpleado te = new TiendaEmpleado();
        te.setEmpleado(empleado);
        te.setTienda(this);
        te.setNhora(nhoras);
        if (this.tiendaEmpleado.add(te)) {
            HibernateUtil.getInstance().add(te);
            this.operacionTiendaEmpleado = false;
            return true;
        }
        return false;
    }

    public boolean updateProducto(Producto producto, int Stock) {
        for (TiendaProducto tp : this.tiendaProducto) {
            if (tp.getProducto().equals(producto)) {
                tp.setStock(Stock);
                HibernateUtil.getInstance().update(tp);
                this.operacionTiendaProducto = false;
                return true;
            }
        }
        return false;

    }

    public boolean updateEmpleado(Empleado empleado, float nhoras) {
        for (TiendaEmpleado te : this.tiendaEmpleado) {
            if (te.getEmpleado().equals(empleado)) {
                te.setNhora(nhoras);
                HibernateUtil.getInstance().update(te);
                this.operacionTiendaEmpleado = false;

                return true;
            }
        }
        return false;
    }

    public boolean deleteEmpleado(Empleado empleado) {
        for (TiendaEmpleado te : this.tiendaEmpleado) {
            if (te.getEmpleado().equals(empleado)) {
                HibernateUtil.getInstance().delete(te);
                this.operacionTiendaEmpleado = false;

                return true;
            }
        }
        return false;
    }

    public boolean deleteProducto(Producto producto) {
        for (TiendaProducto tp : this.tiendaProducto) {
            if (tp.getProducto().equals(producto)) {
                HibernateUtil.getInstance().delete(tp);
                this.operacionTiendaProducto = false;
                return true;
            }
        }
        return false;

    }

    public boolean viewProducto() {
        boolean exit = false;
        System.out.println("|| ==== LISTA DE PRODUCTOS EN LA TIENDA [" + this.name + "] === ||");
        for (TiendaProducto tp : this.tiendaProducto) {
            System.out.println(tp.toString());
            exit = true;
        }
        System.out.println(" || =========================================================== ||");
        return exit;
    }

    public boolean viewEmpleado() {
        boolean exit = false;
        System.out.println("|| ==== LISTA DE EMPLEADOS EN LA TIENDA [" + this.name + "] === ||");
        for (TiendaEmpleado te : this.tiendaEmpleado) {
            System.out.println(te.toString());
            exit = true;
        }
        System.out.println(" || =========================================================== ||");

        return exit;
    }

    public float getNhora(Empleado empleado) {
        float nhora = -1;
        for (TiendaEmpleado te : this.tiendaEmpleado) {
            if (te.getEmpleado().equals(empleado)) {
                return (nhora = te.getNhora());
            }
        }
        return nhora;
    }

    public int getProductStock(Producto producto) {
        int stock = -1;
        for (TiendaProducto tp : this.tiendaProducto) {
            if (tp.getProducto().equals(producto)) {
                return (stock = tp.getStock());
            }
        }

        return stock;
    }

    private HashMap<String, Producto> cargarProducto() {
        List<TiendaProducto> listTiendaProducto = HibernateUtil.getInstance().get("from TiendaProducto", TiendaProducto.class);
        if (listTiendaProducto.size() > 0) {
            for (TiendaProducto tp : listTiendaProducto) {
                mapProducto.put(tp.getProducto().getName(), tp.getProducto());
            }
            this.operacionTiendaProducto = false;
        }
        return mapProducto;
    }

    private HashMap<String, Empleado> cargarEmpleado() {
        List<TiendaEmpleado> listTiendaEmpleado = HibernateUtil.getInstance().get("from TiendaEmpleado", TiendaEmpleado.class);
        if (listTiendaEmpleado.size() > 0) {
            for (TiendaEmpleado te : listTiendaEmpleado) {
                mapEmpleado.put(te.getEmpleado().getName(), te.getEmpleado());
            }
            this.operacionTiendaProducto = false;
        }
        return mapEmpleado;
    }
}
