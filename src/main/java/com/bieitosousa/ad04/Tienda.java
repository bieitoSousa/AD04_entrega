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
public class Tienda implements Serializable{

//    private Franquicia f = null;
    private HashMap<String, Producto> mapProd = new HashMap<String, Producto>();
    private HashMap<String, Empleado> mapEmp = new HashMap<String, Empleado>();

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
    @Column(name = "TIENDA_ciudad", nullable=false, unique = true)
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

    public Tienda(){}
    public Tienda(String name, Provincia provincia, String ciudad) {
        this.name = name;
        this.provincia = provincia;
        this.ciudad = ciudad;
    }

    public void addProducto(Producto producto, int Stock) {
        TiendaProducto tp = new TiendaProducto();
        tp.setProducto(producto);
        tp.setTienda(this);
        tp.setStock(Stock);
        this.tiendaProducto.add(tp);
    }

//    public int getProductStock(Producto producto) {
//        int stock = -1;
//        if (this.productos.contains(producto)) {
//            for (TiendaProducto tp : this.tiendaProducto) {
//                if (tp.getProducto().equals(producto)) {
//                    return (stock = tp.getStock());
//                }
//            }
//        }
//        return stock;
//    }
    
    public void addEmpeado(Empleado empleado, float nhoras) {
        TiendaEmpleado te = new TiendaEmpleado();
        te.setEmpleado(empleado);
        te.setTienda(this);
        te.setNhora(nhoras);
        this.tiendaEmpleado.add(te);
    }

//    public float getNhora(Empleado empleado) {
//        float nhora = -1;
//        if (this.empleados.contains(empleado)) {
//            for (TiendaEmpleado te : this.tiendaEmpleado) {
//                if (te.getEmpleado().equals(empleado)) {
//                    return (nhora = te.getNhora());
//                }
//            }
//        }
//        return nhora;
//    }

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

    /**
     * * ... METODOS PUBLICOS ... **
     */
    /**
     * ************************************************************
     * METODOS GET/SET atributos del constructor
     *
     **************************************************************
     */
//    public Franquicia getFranquicia() {
//        if (f == null) {
//            f = Franquicia.getInstance();
////        }else if (!f.mapTienda.containsKey(this.name)){
////            f.addTienda(this);
//        }
//        return f;
//    }

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

    /**
     * ************************************************************
     * METODOS toString
     *
     **************************************************************
     */
    @Override
    public String toString() {
        return "Tienda{" + "mapProd=" + mapProd + ", mapEmp=" + mapEmp + ", id=" + id + ", name=" + name + ", provincia=" + provincia + ", ciudad=" + ciudad + '}';
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
     * ************************************************************
     * METODOS GET # evalua super.op{name} : determina si hubo operaciones de
     * escritura en la DB # true : Carga los datos de la DB. # false : Carga los
     * datos de la memoria. = getMapEmp Devuelve los empleados de la tienda =
     * getMapProd Devuelve los productos de la tienda = getId Recupera el id de
     * la tienda **************************************************************
     * //
     */
//    public HashMap<String, Producto> getMapProd() {
//        if (getFranquicia().opProd) {
//            cargarProductos();
//        }
//        return mapProd;
//    }
//
//    public HashMap<String, Empleado> getMapEmp() {
//        if (getFranquicia().opEmp) {
//            cargarEmpleados();
//        }
//        return mapEmp;
//    }
//
//    public int getId() {
//        if (id == -1) {
//            cargarId();
//        }
//        return id;
//    }
//
//    /**
//     * ************************************************************
//     * METODOS SET # guarda los datos en memoria = setMapEmp = setMapProd
//     *
//     ***************************************************************
//     */
//    public void setMapProd(HashMap<String, Producto> mapProd) {
//        this.mapProd = mapProd;
//    }
//
//    public void setMapEmp(HashMap<String, Empleado> mapEmp) {
//        this.mapEmp = mapEmp;
//    }
    /**
     * ************************************************************
     * METODOS ADD # llama a un metodo privado para inserta los registros datos
     * en la DB = addProducto => inserta Producto_id Tienda_id = addStock =>
     * inserta Producto_id Tienda_id stock = addHoras => inserta Producto_id
     * Empleado_id nHoras # En el metodo privado se determina op{name} = true se
     * ha escrito en la DB
     *
     * @param p **************************************************************
     */
//    public void addProducto(Producto p) {
//        if (getFranquicia().getMapProd().get(p.getName()) != null) {
//            if (p.getStock(this) == -1) {
//                insertTiendaProducto(p, 0);
//            } else {
//                System.out.println("El producto ya a sido creado Modifiquelo");
//            }
//        } else {
//            System.out.println("Producto " + p.getName() + " No es parte de la Franquicia");
//        }
//    }
//
//    public void addProducto(String name) {
//        Producto p = getFranquicia().getMapProd().get(name);
//        if (p != null) {
//            if (p.getStock(this) == -1) {
//                insertTiendaProducto(p, 0);
//            } else {
//                System.out.println("El producto ya a sido creado Modifiquelo");
//            }
//        } else {
//            System.out.println("Producto " + name + " No es parte de la Franquicia");
//        }
//    }
//
//    public void addProducto(String name, int stock) {
//        Producto p = getFranquicia().getMapProd().get(name);
//        if (p != null) {
//            if (p.getStock(this) == -1) {
//                insertTiendaProducto(p, stock);
//            }else if (p.getStock(this) >= 0) {
//                updateTiendaProducto(p, stock);
//             }else{
//                     System.out.println("producto no existe en la db");
//                     }
//        } else {
//            System.out.println("Producto " + name + " No es parte de la Franquicia");
//        }
//    }
//    
//    public void addEmpleado(Empleado em) {
//        if (getFranquicia().getMapEmp().get(em.getName()) != null) {
//            if (em.getnHoras(this) == (float) -1) {
//                insertTiendaEmpleado(em, 0);
//            } else {
//                System.out.println("El Empleado ya a sido creado Modifiquelo");
//            }
//        } else {
//            System.out.println("Empleado " + em.getName() + " No es parte de la Franquicia");
//        }
//    }
//
//    public void addEmpleado(String name) {
//        Empleado em = getFranquicia().getMapEmp().get(name);
//        if (em != null) {
//            if (em.getnHoras(this) == (float) -1) {
//                insertTiendaEmpleado(em, 0);
//            } else {
//                System.out.println("El Empleado ya a sido creado Modifiquelo");
//            }
//        } else {
//            System.out.println("Empleado " + name + " No es parte de la Franquicia");
//        }
//    }
//
//    public void addHoras(Empleado em, float nHoras) {
//        if (getMapEmp().get(em.getName()) != null) {
//            if (em.getnHoras(this) > 0 && nHoras > (float) 0) {
//                nHoras = nHoras + em.getnHoras(this);
//                updateTiendaEmpleado(em, nHoras);
//            } else {
//                System.out.println("El empleado NO HA SIDO CREEADO o las horas deven de ser > a 0  ");
//            }
//        } else {
//            System.out.println("Empleado " + em.getName() + " No es parte de la Franquicia");
//        }
//    }
//
//    public void addHoras(String name, float nHoras) {
//        Empleado em = getFranquicia().mapEmp.get(name);
//        if (em != null) {
//            if (em.getnHoras(this) >= 0 && nHoras > (float) 0) {
//                nHoras = nHoras + em.getnHoras(this);
//                updateTiendaEmpleado(em, nHoras);
//            } else if(em.getnHoras(this)==-1){
//               insertTiendaEmpleado(em, (int) nHoras);
//            }else{ 
//                System.out.println("El empleado NO HA SIDO CREEADO o las horas deven de ser > a 0 ");
//            }
//        } else {
//            System.out.println("Empleado " + name + " No es parte de la Franquicia");
//        }
//    }
//
//    public void addStock(Producto p, int stock) {
//        if (getMapProd().get(p.getName()) != null) {
//            if (p.getStock(this) >= (float) 0 && stock > (float) 0) {
//                stock = stock + p.getStock(this);
//                updateTiendaProducto(p, stock);
//            } else {
//                System.out.println("El producto no ha sido creado o el stock deve ser > que 0");
//            }
//        } else {
//            System.out.println("Producto " + p.getName() + " No es parte de la Franquicia");
//        }
//    }
//
//    public void addStock(String name, int stock) {
//        Producto p = getFranquicia().mapProd.get(name);
//        if (p != null) {
//            if (p.getStock(this) >= (float) 0 && stock > (float) 0) {
//                stock = stock + p.getStock(this);
//                updateTiendaProducto(p, stock);
//            } else {
//                System.out.println("El producto no ha sido creado o el stock deve ser > que 0");
//            }
//
//        } else {
//            System.out.println("Producto " + name + " No es parte de la Franquicia");
//        }
//    }
//
//    /**
//     * ************************************************************
//     * METODOS DELETE # llama a un metodo privado para Eliminar los registros
//     * datos en la DB = delProducto => elimina Producto_id Tienda_id elimina el
//     * stock y el producto = delEmpleado => elimina Producto_id Tienda_id stock
//     * elimna las horas del trabajador # En el metodo privado se determina
//     * op{name} = true se ha escrito en la DB
//     *
//     * @param p
//     ***************************************************************
//     */
//    public void delProducto(Producto p) {
//        if (getFranquicia().mapProd.get(p.getName()) != null) {
//            deleteTiendaProducto(this, p);
//        } else {
//            System.out.println("Producto " + p.getName() + " No es parte de la Franquicia");
//        }
//    }
//
//    public void delProducto(String name) {
//        Producto p = getFranquicia().mapProd.get(name);
//        if (p != null) {
//            deleteTiendaProducto(this, p);
//        } else {
//            System.out.println("Producto " + p.getName() + " No es parte de la Franquicia");
//        }
//    }
//
//    public void delEmpleado(Empleado em) {
//        if (getFranquicia().mapEmp.get(em.getName()) != null) {
//            deleteTiendaEmpleado(this, em);
//        } else {
//            System.out.println("Empleado " + em.getName() + " No es parte de la Franquicia");
//        }
//    }
//
//    public void delEmpleado(String name) {
//        Empleado em = getFranquicia().mapEmp.get(name);
//        if (em != null) {
//            deleteTiendaEmpleado(this, em);
//        } else {
//            System.out.println("Empleado " + em.getName() + " No es parte de la Franquicia");
//        }
//    }
//
//    /**
//     * ************************************************************
//     * METODOS VIEW VIEW{Name} recorre e imprimir los mapas = mapEmp = mapCli =
//     * mapProd = mapTienda =================== evalua : op{Name} = true --> Los
//     * datos se han modificado : \-> hay que cargar los datos en memoria. false
//     * --> Los datos no se modificaron : \-> no hay que cargar datos en memoria.
//     ***************************************************************
//     */
//    public void viewProductos() {
//        if (opStock) {
//            if(cargarProductos()){
//            System.out.println("Cargando PRODUCTOS [........]");
//            }else{
//                System.out.println("NO HA SIDO POSIBLE CARGAR LOS PRODUCTOS");
//            }
//        }
//        System.out.println("_____________ FRANQUICIA : " + this.name + " PRODUCTOS _____________");
//        for (Producto p : mapProd.values()) {
//             System.out.println(""
//                    + "Tienda ["+ this.name+"]"
//                    + "Producto ["+ getMapProd().get(p.getName()).getName() +"]"
//                    + "==>  Strock [ "+String.valueOf( getMapProd().get(p.getName()).getStock(this))
//            );
//        }
//        System.out.println("===================================");
//    }
//
//    public void viewProductos(String n) {
//        if (opStock) {
//            if(cargarProductos()){
//                
//            }else {
//                System.out.println("NO HA SIDO PISBLE CARGAR LOS PRODUCTOS");
//            }
//            //System.out.println("el producto esta "+getMapProd().containsKey(n));
//        }
//        if (getMapProd().containsKey(n)){
//        System.out.println("_____________ FRANQUICIA : " + this.name + " PRODUCTOS _____________");
//            System.out.println(""
//                    + "Tienda ["+ this.name+"]"
//                    + "Producto ["+ getMapProd().get(n).getName() +"]"
//                    + "==>  Strock [ "+String.valueOf( getMapProd().get(n).getStock(this)+"]")
//            );
//        System.out.println("===================================");
//        }else System.out.println("no se ha encontrado el producto : "+n);
//    }
//
//    public void viewEmpleados() {
//        if (opHoras) {
//            if(cargarEmpleados()){
//            System.out.println("Cargando EMPLEADOS [........]");
//            }else{System.out.println("NO HA SIDO POSIBLE CARGAR LOS EMPLEADOS");}
//        }
//        System.out.println("_____________ FRANQUICIA : " + this.name + " EMPLEADOS _____________");
//        for (Empleado em : mapEmp.values()) {
//            System.out.println(em.toString(this));
//        }
//        System.out.println("===================================");
//    }
//
//    private boolean cargarProductos() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private boolean  cargarEmpleados() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private void cargarId() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private void insertTiendaProducto(Producto p, int i) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private void updateTiendaProducto(Producto p, int stock) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private void insertTiendaEmpleado(Empleado em, int i) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private void updateTiendaEmpleado(Empleado em, float nHoras) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private void deleteTiendaProducto(Tienda aThis, Producto p) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private void deleteTiendaEmpleado(Tienda aThis, Empleado em) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
}
