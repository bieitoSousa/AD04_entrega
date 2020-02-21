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

import com.bieitosousa.ad04.Json.JSonMake;
import com.bieitosousa.ad04.Json.Provincia;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.OneToMany;

/**
 *
 * @author bieito
 */
public class Franquicia {

    // acceso a hibernate
    HibernateUtil h = HibernateUtil.getInstance();
    // Mapas donde guardamos los objetos
    HashMap<String, Tienda> mapTienda = new HashMap<String, Tienda>();
    HashMap<String, Producto> mapProducto = new HashMap<String, Producto>();
    HashMap<String, Empleado> mapEmpleado = new HashMap<String, Empleado>();
    HashMap<String, Cliente> mapCliente = new HashMap<String, Cliente>();
    HashMap<Integer, Provincia> mapProvincia = new HashMap<Integer, Provincia>();
    // validar  true se han producido cambios en la DB en los registros pertinentes
    boolean operacionTienda = true;
    boolean operacionProducto = true;
    boolean operacionEmpleado = true;
    boolean operacionCliente = true;
    // acceso a franquicia singleton
    private static Franquicia f = null;
    // nombre de franquicia por defecto
    private String name = "Mi Franquicia";

    //instancia franquicia singleton
    public static Franquicia getInstance() {
        if (f == null) {
            f = new Franquicia();
        }
        return f;
    }

    // constructor cargando las Provincias
    public Franquicia() {
        if ((mapProvincia = cargarProvincias()) == null) {
            System.err.println("ERROR : NO SE HAN CARGADO LA PROVINCIAS");
        }
    }

    /**
     * * ... METODOS PUBLICOS ... **
     */
    /**
     * ************************************************************
     * METODOS toString
     *
     **************************************************************
     */
    @Override
    public String toString() {
        return "Franquicia{" + "mapTienda=" + mapTienda + ", mapProd=" + mapProducto + ", mapEmp=" + mapEmpleado + ", mapCli=" + mapCliente + ", name=" + name + '}';
    }

    /**
     * ************************************************************
     * METODOS GET/SET
     *
     **************************************************************
     */
    public HashMap<String, Tienda> getMapTienda() {
        if (operacionTienda) {
            return mapTienda = cargarTienda();
        } else {
            return mapTienda;
        }
    }

    public HashMap<String, Producto> getMapProducto() {
        return mapProducto = cargarProducto();
    }

    public HashMap<String, Empleado> getMapEmpleado() {
        return mapEmpleado = cargarEmpleado();
    }

    public HashMap<String, Cliente> getMapCliente() {
        return mapCliente = cargarCliente();
    }

    public void setMapTienda(HashMap<String, Tienda> mapTienda) {
        this.mapTienda = mapTienda;
    }

    public void setMapProd(HashMap<String, Producto> mapProd) {
        this.mapProducto = mapProd;
    }

    public void setMapEmp(HashMap<String, Empleado> mapEmp) {
        this.mapEmpleado = mapEmp;
    }

    public void setListCli(HashMap<String, Cliente> mapCli) {
        this.mapCliente = mapCli;
    }

    /**
     * ************************************************************
     * METODOS ADD # llama a un metodo privado para inserta los Objetos datos en
     * la DB = addEmpleado => Obj Empleado = addClient => Obj Cliente =
     * addProdducto => Obj Producto = addTienda => Obj Tienda # En el metodo
     * privado se determina op{name} = true se ha escrito en la DB
     * **************************************************************
     */
    public void addCliente(Cliente cliente) {
        if (h.add(cliente)) {
            this.operacionCliente = true;

            System.out.println("[_SI_] se a AÑADIDO cliente [" + cliente.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a AÑADIDO cliente [" + cliente.toString() + "] a la Franquicia  ");
        }
    }

    public void deleteCliente(Cliente cliente) {
        if (h.delete(cliente)) {
            this.operacionCliente = true;

            System.out.println("[_SI_] se a ELIMINADO cliente [" + cliente.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a ELIMINADO cliente [" + cliente.toString() + "] a la Franquicia  ");
        }

    }
    
       public boolean deleteCliente(String nameCliente) {
        Cliente cliente;
        if ((cliente = mapCliente.get(nameCliente)) != null) {
            if (h.delete(cliente)) {
                this.operacionCliente = true;
                System.out.println("[_SI_] se a ELIMINADO cliente [" + cliente.toString() + "] a la Franquicia ");
                return true;
            } else {
                System.out.println("[_NO_] se a ELIMINADO cliente [" + cliente.toString() + "] a la Franquicia  ");
                return false;
            }
        } else {
            return false;
        }
    } 
       public boolean updateCliente(String nameCliente) {
        Cliente cliente;
        if ((cliente = mapCliente.get(nameCliente)) != null) {
            if (h.update(cliente)) {
                this.operacionCliente = true;
                System.out.println("[_SI_] se a ELIMINADO cliente [" + cliente.toString() + "] a la Franquicia ");
                return true;
            } else {
                System.out.println("[_NO_] se a ELIMINADO cliente [" + cliente.toString() + "] a la Franquicia  ");
                return false;
            }
        } else {
            return false;
        }
    } 

    public void updateCliente(Cliente cliente) {
        if (h.update(cliente)) {
            this.operacionCliente = true;

            System.out.println("[_SI_] se a MODIFICADO cliente [" + cliente.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a MODIFICADO cliente [" + cliente.toString() + "] a la Franquicia  ");
        }
    }

    public boolean viewCliente() {
        return h.view("from Cliente", Cliente.class);

    }

    public boolean addProvincia(Provincia provincia) {
        if (h.add(provincia)) {
            System.out.println("[_SI_] se a AÑADIDO provincia [" + provincia.toString() + "] a la Franquicia ");
            return true;
        } else {
            System.out.println("[_NO_] se a AÑADIDO provincia [" + provincia.toString() + "] a la Franquicia  ");
            return false;
        }
    }

    public void deleteProvincia(Provincia provincia) {
        if (h.delete(provincia)) {
            System.out.println("[_SI_] se a ELIMINADO provincia [" + provincia.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a ELIMINADO provincia [" + provincia.toString() + "] a la Franquicia  ");
        }

    }

    public void updateProvincia(Provincia provincia) {
        if (h.update(provincia)) {
            System.out.println("[_SI_] se a MODIFICADO provincia [" + provincia.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a MODIFICADO provincia [" + provincia.toString() + "] a la Franquicia  ");
        }
    }

    public boolean viewProvincia() {

        return h.view("from Provincia", Provincia.class);

    }

    public void addProducto(Producto producto) {
        if (h.add(producto)) {
            this.operacionProducto = true;

            System.out.println("[_SI_] se a AÑADIDO producto [" + producto.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a AÑADIDO producto [" + producto.toString() + "] a la Franquicia  ");
        }
    }

    public void deleteProducto(Producto producto) {
        if (h.delete(producto)) {
            this.operacionProducto = true;

            System.out.println("[_SI_] se a ELIMINADO producto [" + producto.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a ELIMINADO producto [" + producto.toString() + "] a la Franquicia  ");
        }

    }
    
       public boolean deleteProducto(String nameProducto) {
        Producto producto;
        if ((producto = mapProducto.get(nameProducto)) != null) {
            if (h.delete(producto)) {
                this.operacionProducto = true;
                System.out.println("[_SI_] se a ELIMINADO producto [" + producto.toString() + "] a la Franquicia ");
                return true;
            } else {
                System.out.println("[_NO_] se a ELIMINADO producto [" + producto.toString() + "] a la Franquicia  ");
                return false;
            }
        } else {
            return false;
        }
    } 
       public boolean updateProducto(String nameProducto) {
        Producto producto;
        if ((producto = mapProducto.get(nameProducto)) != null) {
            if (h.update(producto)) {
                this.operacionProducto = true;
                System.out.println("[_SI_] se a ELIMINADO producto [" + producto.toString() + "] a la Franquicia ");
                return true;
            } else {
                System.out.println("[_NO_] se a ELIMINADO producto [" + producto.toString() + "] a la Franquicia  ");
                return false;
            }
        } else {
            return false;
        }
    } 
    

    public void updateProducto(Producto producto) {
        if (h.update(producto)) {
            this.operacionProducto = true;

            System.out.println("[_SI_] se a MODIFICADO producto [" + producto.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a MODIFICADO producto [" + producto.toString() + "] a la Franquicia  ");
        }
    }

    public boolean viewProducto() {
        return h.view("from Producto", Producto.class);

    }

    public void addEmpleado(Empleado empleado) {
        if (h.add(empleado)) {
            this.operacionEmpleado = true;

            System.out.println("[_SI_] se a AÑADIDO empleado [" + empleado.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a AÑADIDO empleado [" + empleado.toString() + "] a la Franquicia  ");
        }
    }

    
    public void deleteEmpleado(Empleado empleado) {
        if (h.delete(empleado)) {
            this.operacionEmpleado = true;

            System.out.println("[_SI_] se a ELIMINADO empleado [" + empleado.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a ELIMINADO empleado [" + empleado.toString() + "] a la Franquicia  ");
        }

    }

    public boolean deleteEmpleado(String nameEmpleado) {
        Empleado empleado;
        if ((empleado = mapEmpleado.get(nameEmpleado)) != null) {
            if (h.delete(empleado)) {
                this.operacionEmpleado = true;
                System.out.println("[_SI_] se a ELIMINADO empleado [" + empleado.toString() + "] a la Franquicia ");
                return true;
            } else {
                System.out.println("[_NO_] se a ELIMINADO empleado [" + empleado.toString() + "] a la Franquicia  ");
                return false;
            }
        } else {
            return false;
        }
    }    
    
    public void updateEmpleado(Empleado empleado) {
        if (h.update(empleado)) {
            this.operacionEmpleado = true;

            System.out.println("[_SI_] se a MODIFICADO empleado [" + empleado.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a MODIFICADO empleado [" + empleado.toString() + "] a la Franquicia  ");
        }
    }
public boolean updateEmpleado(String nameEmpleado) {
        Empleado empleado;
        if ((empleado = mapEmpleado.get(nameEmpleado)) != null) {
            if (h.update(empleado)) {
                this.operacionEmpleado = true;
                System.out.println("[_SI_] se a MODIFICADO empleado [" + empleado.toString() + "] a la Franquicia ");
                return true;
            } else {
                System.out.println("[_NO_] se a MODIFICADO empleado [" + empleado.toString() + "] a la Franquicia  ");
                return false;
            }
        } else {
            return false;
        }
    }    
    public boolean viewEmpleado() {
        return h.view("from Empleado", Empleado.class);

    }

    public void addTienda(Tienda tienda) {
        if (h.add(tienda)) {
            this.operacionTienda = true;
            System.out.println("[_SI_] se a AÑADIDO tienda [" + tienda.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a AÑADIDO tienda [" + tienda.toString() + "] a la Franquicia  ");
        }
    }

   

    public void deleteTienda(Tienda tienda) {
        if (h.delete(tienda)) {
            this.operacionTienda = true;

            System.out.println("[_SI_] se a ELIMINADO tienda [" + tienda.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a ELIMINADO tienda [" + tienda.toString() + "] a la Franquicia  ");
        }

    }

 public boolean deleteTienda(String nameTienda) {
        Tienda tienda;
        if ((tienda = mapTienda.get(nameTienda)) != null) {
            if (h.delete(tienda)) {
                this.operacionTienda = true;
                System.out.println("[_SI_] se a ELIMINADO tienda [" + tienda.toString() + "] a la Franquicia ");
                return true;
            } else {
                System.out.println("[_NO_] se a ELIMINADO tienda [" + tienda.toString() + "] a la Franquicia  ");
                return false;
            }
        } else {
            return false;
        }
    }    
    
    
    public void updateTienda(Tienda tienda) {
        if (h.update(tienda)) {
            this.operacionTienda = true;

            System.out.println("[_SI_] se a MODIFICADO tienda [" + tienda.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a MODIFICADO tienda [" + tienda.toString() + "] a la Franquicia  ");
        }
    }

     public boolean updateTienda(String nameTienda) {
        Tienda tienda;
        if ((tienda = mapTienda.get(nameTienda)) != null) {
            if (h.update(tienda)) {
                this.operacionTienda = true;
                System.out.println("[_SI_] se a MODIFICADO tienda [" + tienda.toString() + "] a la Franquicia ");
                return true;
            } else {
                System.out.println("[_NO_] se a MODIFICADO tienda [" + tienda.toString() + "] a la Franquicia  ");
                return false;
            }
        } else {
            return false;
        }
    } 
    
    public boolean viewTienda() {
        return h.view("from Tienda", Tienda.class);

    }

    /**
     * * ... METODOS PRIVADOS ... **
     */
    /**
     * ************************************************************
     * CARGAR -> OBTIENE LOS VALORES DE LOS OBJETOS ATRAVES DE REGISTROS DE LA
     * DB
     *
     **************************************************************
     */
    private HashMap<Integer, Provincia> cargarProvincias() {// si estan cargadas menos de 50 Provincias
        List<Provincia> list = h.get("from Provincia", Provincia.class);
        if (list.size() == 25) {
            for (Provincia p : list) {
                mapProvincia.put(p.getId(), p);
            }

        } else if (list.size() == 0) { // carga las provincias 
            if (JSonMake.CargarFileProvincias()) {
                if ((list = h.get("from Provincia", Provincia.class)).size() == 25) {
                    for (Provincia p : list) {
                        mapProvincia.put(p.getId(), p);
                    }
                } else {
                    System.out.println("Provincias no cargadas CORRECTAMENTE");
                }

            }
        } else {
            System.out.println("Provincias no cargadas CORRECTAMENTE");
        }
        return mapProvincia;

    }

    private HashMap<String, Tienda> cargarTienda() {
        List<Tienda> listTienda = h.get("from Tienda", Tienda.class);
        if (listTienda.size() > 0) {
            for (Tienda t : listTienda) {
                mapTienda.put(t.getName(), t);
            }
            this.operacionTienda = false;
        }
        return mapTienda;
    }

    private HashMap<String, Producto> cargarProducto() {
        List<Producto> listProducto = h.get("from Producto", Producto.class);
        if (listProducto.size() > 0) {
            for (Producto p : listProducto) {
                mapProducto.put(p.getName(), p);
            }
            this.operacionProducto = false;
        }
        return mapProducto;
    }

    private HashMap<String, Empleado> cargarEmpleado() {
        List<Empleado> listEmpleado = h.get("from Empleado", Empleado.class);
        if (listEmpleado.size() > 0) {
            for (Empleado em : listEmpleado) {
                mapEmpleado.put(em.getName(), em);
            }
            this.operacionEmpleado = false;
        }
        return mapEmpleado;
    }

    private HashMap<String, Cliente> cargarCliente() {
        List<Cliente> listCliente = h.get("from Cliente", Cliente.class);
        if (listCliente.size() > 0) {
            for (Cliente cl : listCliente) {
                mapCliente.put(cl.getName(), cl);
            }
            this.operacionCliente = false;
        }
        return mapCliente;
    }
}
