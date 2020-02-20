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
import java.util.List;

/**
 *
 * @author bieito
 */
public class Franquicia {

    HibernateUtil h = HibernateUtil.getInstance();
    HashMap<String, Tienda> mapTienda = new HashMap<String, Tienda>();
    HashMap<String, Producto> mapProd = new HashMap<String, Producto>();
    HashMap<String, Empleado> mapEmp = new HashMap<String, Empleado>();
    HashMap<String, Cliente> mapCli = new HashMap<String, Cliente>();
    List<Provincia> listProv = new ArrayList<Provincia>();
    private static Franquicia f = null;
    private String name;
    protected static boolean opCli = true;//operaciones de escritura sobre Cliente
    protected static boolean opTi = true;//operaciones de escritura sobre Tienda
    protected static boolean opEmp = true;//operaciones de escritura sobre Empleado
    protected static boolean opProd = true;//operaciones de escritura sobre Producto
    protected static boolean opProv = true;//operaciones de escritura sobre Provincias
    private static boolean tablaProvinciasState = false;

    public static Franquicia getInstance()  {
        if (f == null) {
            f = new Franquicia();
        }
        return f;
    }

    public Franquicia()  {
        if ((listProv = CargarProvincias()) == null) {
            System.err.println("ERROR : NO SE HAN CARGADO LA PROVINCIAS");
        }
    }

    private List<Provincia> CargarProvincias()  {// si estan cargadas menos de 50 Provincias
        List<Provincia> list = h.get("from Provincia", Provincia.class);
        if (list.size() == 25) {
            return list;
        } else if (list.size() == 0) { // carga las provincias 
            if (JSonMake.CargarFileProvincias()) {
                return h.get("from Provincia", Provincia.class);
            } else {
                return null;
            }
        } else {
            return null;
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
        return "Franquicia{" + "mapTienda=" + mapTienda + ", mapProd=" + mapProd + ", mapEmp=" + mapEmp + ", mapCli=" + mapCli + ", name=" + name + '}';
    }

    /**
     * ************************************************************
     * METODOS GET # evalua op{name} : determina si hubo operaciones de
     * escritura en la DB # true : Carga los datos de la DB. # false : Carga los
     * datos de la memoria. = getMapEmp = getMapCli = getMapProd = getMapTienda
     *
     ***************************************************************
     */
//    public HashMap<String, Tienda> getMapTienda() {
//        if (opTi) {
//            cargarTiendas();
//        }
//        return mapTienda;
//    }
//    public ArrayList<String> getMapProvincia() {
//        if (opProv) {
//            cargarProv();
//        }
//        return listProv;
//    }
//    public HashMap<String, Producto> getMapProd() {
//        if (opProd) {
//            cargarProductos();
//        }
//        return mapProd;
//    }
//
//    public HashMap<String, Empleado> getMapEmp() {
//        if (opEmp) {
//            cargarEmpleados();
//        }
//        return mapEmp;
//    }
//
//    public HashMap<String, Cliente> getMapCli() {
//        if (opCli) {
//            cargarClientes();
//        }
//        return mapCli;
//    }
    /**
     * ************************************************************
     * METODOS SET # guarda los datos en memoria = setMapEmp = setMapCli =
     * setMapProd = setMapTienda
     *
     ***************************************************************
     */
    public void setMapTienda(HashMap<String, Tienda> mapTienda) {
        this.mapTienda = mapTienda;
    }

    public void setMapProd(HashMap<String, Producto> mapProd) {
        this.mapProd = mapProd;
    }

    public void setMapEmp(HashMap<String, Empleado> mapEmp) {
        this.mapEmp = mapEmp;
    }

    public void setListCli(HashMap<String, Cliente> mapCli) {
        this.mapCli = mapCli;
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
            System.out.println("[_SI_] se a AÑADIDO cliente [" + cliente.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a AÑADIDO cliente [" + cliente.toString() + "] a la Franquicia  ");
        }
    }

    public void deleteCliente(Cliente cliente) {
        if (h.delete(cliente)) {
            System.out.println("[_SI_] se a ELIMINADO cliente [" + cliente.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a ELIMINADO cliente [" + cliente.toString() + "] a la Franquicia  ");
        }

    }

    public void updateCliente(Cliente cliente) {
        if (h.update(cliente)) {
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
            System.out.println("[_SI_] se a AÑADIDO producto [" + producto.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a AÑADIDO producto [" + producto.toString() + "] a la Franquicia  ");
        }
    }

    public void deleteProducto(Producto producto) {
        if (h.delete(producto)) {
            System.out.println("[_SI_] se a ELIMINADO producto [" + producto.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a ELIMINADO producto [" + producto.toString() + "] a la Franquicia  ");
        }

    }

    public void updateProducto(Producto producto) {
        if (h.update(producto)) {
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
            System.out.println("[_SI_] se a AÑADIDO empleado [" + empleado.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a AÑADIDO empleado [" + empleado.toString() + "] a la Franquicia  ");
        }
    }

    public void deleteEmpleado(Empleado empleado) {
        if (h.delete(empleado)) {
            System.out.println("[_SI_] se a ELIMINADO empleado [" + empleado.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a ELIMINADO empleado [" + empleado.toString() + "] a la Franquicia  ");
        }

    }

    public void updateEmpleado(Empleado empleado) {
        if (h.update(empleado)) {
            System.out.println("[_SI_] se a MODIFICADO empleado [" + empleado.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a MODIFICADO empleado [" + empleado.toString() + "] a la Franquicia  ");
        }
    }

    public boolean viewEmpleado() {
        return h.view("from Empleado", Empleado.class);

    }
    public void addTienda(Tienda tienda) {
        if (h.add(tienda)) {
            System.out.println("[_SI_] se a AÑADIDO tienda [" + tienda.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a AÑADIDO tienda [" + tienda.toString() + "] a la Franquicia  ");
        }
    }

    public void deleteTienda(Tienda tienda) {
        if (h.delete(tienda)) {
            System.out.println("[_SI_] se a ELIMINADO tienda [" + tienda.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a ELIMINADO tienda [" + tienda.toString() + "] a la Franquicia  ");
        }

    }

    public void updateTienda(Tienda tienda) {
        if (h.update(tienda)) {
            System.out.println("[_SI_] se a MODIFICADO tienda [" + tienda.toString() + "] a la Franquicia ");
        } else {
            System.out.println("[_NO_] se a MODIFICADO tienda [" + tienda.toString() + "] a la Franquicia  ");
        }
    }

    public boolean viewTienda() {
        return h.view("from Tienda", Tienda.class);

    }

}
