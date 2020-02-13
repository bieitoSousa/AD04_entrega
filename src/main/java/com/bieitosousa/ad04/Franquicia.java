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

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author bieito
 */

 public class Franquicia {

    HashMap<String, Tienda> mapTienda = new HashMap<String, Tienda>();
    HashMap<String, Producto> mapProd = new HashMap<String, Producto>();
    HashMap<String, Empleado> mapEmp = new HashMap<String, Empleado>();
    HashMap<String, Cliente> mapCli = new HashMap<String, Cliente>();
    ArrayList<String> listProv = new ArrayList<String>();
    private static Franquicia f = null;
    private String name;
    protected static boolean opCli = true;//operaciones de escritura sobre Cliente
    protected static boolean opTi = true;//operaciones de escritura sobre Tienda
    protected static boolean opEmp = true;//operaciones de escritura sobre Empleado
    protected static boolean opProd = true;//operaciones de escritura sobre Producto
    protected static boolean opProv = true;//operaciones de escritura sobre Provincias

    public static Franquicia getInstance() {
        if (f == null) {
            f = new Franquicia();
        }
        return f;
    }

    public Franquicia() {
    }

    /**
     * * ... METODOS PUBLICOS ...    **
     */
    /**
     * ************************************************************
     * METODOS toString
     *
     **************************************************************
     */
    @Override
    public String toString() {
        return "Franquicia{" + "mapTienda=" + mapTienda + ", mapProd=" + mapProd + ", mapEmp=" + mapEmp + ", mapCli=" + mapCli + ", name=" + name +  '}';
    }

    /**
     * ************************************************************
     * METODOS GET # evalua op{name} : determina si hubo operaciones de
     * escritura en la DB # true : Carga los datos de la DB. # false : Carga los
     * datos de la memoria. = getMapEmp = getMapCli = getMapProd = getMapTienda
     *
     ***************************************************************
     */
    public HashMap<String, Tienda> getMapTienda() {
        if (opTi) {
            cargarTiendas();
        }
        return mapTienda;
    }
    public ArrayList<String> getMapProvincia() {
        if (opProv) {
            cargarProv();
        }
        return listProv;
    }
    public HashMap<String, Producto> getMapProd() {
        if (opProd) {
            cargarProductos();
        }
        return mapProd;
    }

    public HashMap<String, Empleado> getMapEmp() {
        if (opEmp) {
            cargarEmpleados();
        }
        return mapEmp;
    }

    public HashMap<String, Cliente> getMapCli() {
        if (opCli) {
            cargarClientes();
        }
        return mapCli;
    }

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
     ***************************************************************
     */
    public void addClient(Cliente cliente) {
        Cliente cli = getMapCli().get(cliente.getName());
        if (cli == null || (cli != null && !cli.equals(cliente))) {
            insertCliente(cliente);
        } else {
            System.out.println("El cliente " + cli.toString() + " ya existe, introduzca un nuevo cliente");
        }
    }

    public void addEmpleado(Empleado empleado) {
        Empleado em = getMapEmp().get(empleado.getName());
        if (em == null || (em != null && !em.equals(empleado))) {
            insertEmpleado(empleado);
        } else {
            System.out.println("El Empleado " + em.toString() + " ya existe, introduzca un nuevo Empleado");
        }
    }

    public void addProducto(Producto producto) {
        Producto p = getMapProd().get(producto.getName());
        if (p == null || (p != null && !p.equals(producto))) {
            insertProducto(producto);
        } else {
            System.out.println("El producto " + p.toString() + " ya existe, introduzca un nuevo Producto");
        }
    }

    public void addTienda(Tienda tienda) {
        Tienda t = getMapTienda().get(tienda.getName());
        if (t == null || (t != null && !t.equals(tienda))) {
            if (t != null) {
                System.out.println(" name  => " + t.getName() + " ; " + tienda.getName());
                System.out.println(" ciudad  => " + t.getCiudad() + " ; " + tienda.getCiudad());
                System.out.println(" provincia  => " + t.getProvincia() + " ; " + tienda.getProvincia());
            }
            insertTienda(tienda);
        } else {
            System.out.println("La tienda " + t.toString() + " ya existe, introduzca una nueva Tienda");
        }
    }

    /**
     * ************************************************************
     * METODOS DEL # llama a un metodo privado para eliminar los Objetos datos
     * en la DB = delEmpleado => Obj Empleado = delClient => Obj Cliente =
     * delProducto => Obj Producto = delTienda => Obj Tienda # En el metodo
     * privado se determina op{name} = true se ha escrito en la DB 
     ***************************************************************
     */

    public void delClient(Cliente cliente) {
        Cliente cli = getMapCli().get(cliente.getName());
        if (cli != null && cli.equals(cliente)) {
            deleteCliente(cliente);
        } else {
            System.out.println("seleccione un Cliente de la Franquicia");
        }
    }

    public void delClient(String name) {
        Cliente cli = getMapCli().get(name);
        if (cli != null) {
            deleteCliente(cli);
        } else {
            System.out.println("seleccione un Cliente de la Franquicia");
        }
    }

    public void delEmpleado(Empleado empleado) {
        Empleado em = getMapEmp().get(empleado.getName());
        if (em != null && em.equals(empleado)) {
            deleteEmpleado(empleado);
        } else {
            System.out.println("seleccione un Empleado de la Franquicia");
        }
    }

    public void delEmpleado(String name) {
        Empleado em = getMapEmp().get(name);
        if (em != null) {
            deleteEmpleado(em);
        } else {
            System.out.println("seleccione un Empleado de la Franquicia");
        }
    }

    public void delProducto(Producto producto) {
        Producto p = getMapProd().get(producto.getName());
        if (p != null && p.equals(producto)) {
            deleteProducto(producto);
        } else {
            System.out.println("seleccione un Producto de la Franquicia");
        }
    }

    public void delProducto(String name) {
        Producto p = getMapProd().get(name);
        if (p != null) {
            deleteProducto(p);
        } else {
            System.out.println("seleccione un Producto de la Franquicia");
        }
    }

    public void delTienda(Tienda tienda) {
        Tienda t = getMapTienda().get(tienda.getName());
        if (t != null && t.equals(tienda)) {
            deleteTienda(tienda);
        } else {
            System.out.println("seleccione una Tienda de la Franquicia");
        }
    }

    public void delTienda(String name) {
        Tienda t = getMapTienda().get(name);
        if (t != null) {
            deleteTienda(t);
        } else {
            System.out.println("seleccione una Tienda de la Franquicia");
        }
    }

    /**
     * ************************************************************
     * METODOS VIEW VIEW{Name} recorre e imprimir los mapas = mapEmp = mapCli =
     * mapProd = mapTienda =================== evalua : op{Name} = true --> Los
     * datos se han modificado : \-> hay que cargar los datos en memoria. false
     * --> Los datos no se modificaron : \-> no hay que cargar datos en memoria.
     ***************************************************************
     */
    public void viewEmpleados() {
        if (opEmp) {
            cargarEmpleados();
            System.out.println("Cargando Empleados [........]");
        }
        System.out.println("_____________ FRANQUICIA : EMPLEADOS _____________");
        for (Empleado em : mapEmp.values()) {
            System.out.println(em.toString());
        }
        System.out.println("===================================");
    }

    public void viewClientes() {
        if (opCli) {
            cargarClientes();
            System.out.println("Cargando Clientes [........]");
        }
        System.out.println("_____________ FRANQUICIA : CLIENTES_____________");
        for (Cliente cli : mapCli.values()) {
            System.out.println(cli.toString());
        }
        System.out.println("===================================");
    }

    public void viewProductos() {
        if (opProd) {
            cargarProductos();
            System.out.println("Cargando Productos [........]");
        }
        System.out.println("_____________ FRANQUICIA : PRODUCTOS_____________");
        for (Producto p : mapProd.values()) {
            System.out.println(p.toString());
        }
        System.out.println("===================================");
    }

    public void viewTiendas() {
        if (opTi) {
            cargarTiendas();
            System.out.println("Cargando Tiendas [........]");
        }
        System.out.println("_____________ FRANQUICIA : TIENDAS_____________");
        for (Tienda t : mapTienda.values()) {
            System.out.println(t.toString());
        }
        System.out.println("===================================");
    }

        private void cargarTiendas() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    private void cargarProv() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarProductos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void insertCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void insertEmpleado(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void insertProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void insertTienda(Tienda tienda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteEmpleado(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteTienda(Tienda tienda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarEmpleados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarClientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
