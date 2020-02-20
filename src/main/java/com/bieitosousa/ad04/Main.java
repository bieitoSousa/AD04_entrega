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
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 *
 * @author bieito
 */
public class Main {
    public static void main(String[] arg) {
       Franquicia f= Franquicia.getInstance();
     //SessionFactory  h = HibernateUtil.getSessionFactory();
     //CARGAR PROVINCIAS
//     JSonMake.setfProvincias(new File(".\\provincias.json"));
//     JSonMake.CargarFileProvincias();
     
    
     //Cliente(String name, String apellido, String email)
    Cliente cl1 = new Cliente("Cli1_name","Cli1_apel","Cli1_email"); 
    Cliente cl2 = new Cliente("Cli2_name","Cli2_apel","Cli2_email"); 
    Cliente cl3 = new Cliente("Cli3_name","Cli3_apel","Cli3_email"); 
    Cliente cl4 = new Cliente("Cli4_name","Cli4_apel","Cli4_email"); 
    
    Empleado em1 = new Empleado("Emi1_name","Emi1_apel"); 
    Empleado em2 = new Empleado("Emi2_name","Emi2_apel"); 
    Empleado em3 = new Empleado("Emi3_name","Emi3_apel"); 
    Empleado em4 = new Empleado("Emi4_name","Emi4_apel"); 
    
    Producto pr1 = new Producto("Pro1_name",12,"Pro1_descrip"); 
    Producto pr2 = new Producto("Pro2_name",14,"Pro2_descrip"); 
    Producto pr3 = new Producto("Pro3_name",16,"Pro3_descrip"); 
    Producto pr4 = new Producto("Pro4_name",19,"Pro4_descrip"); 
    
    Tienda ti1 = new Tienda("Tie1_name",new Provincia(1,"a1"),"Tie1_ciudad"); 
    Tienda ti2 = new Tienda("Tie2_name",new Provincia(2,"a2"),"Tie2_ciudad"); 
    Tienda ti3 = new Tienda("Tie3_name",new Provincia(3,"a3"),"Tie3_ciudad"); 
    Tienda ti4 = new Tienda("Tie4_name",new Provincia(4,"a4"),"Tie4_ciudad"); 
   
     //AÑADIR  CLIENTE
     
     f.addCliente(cl1);
     f.addCliente(cl1);
     f.addCliente(cl1);
     f.addCliente(cl2);
     f.addCliente(cl2);
     f.addCliente(cl2);
     f.addCliente(cl2);
     f.addCliente(cl3);
     f.addCliente(cl3);
     f.addCliente(cl3);
     f.addCliente(cl3);
     f.addCliente(cl4);
     f.addCliente(cl4);
     f.addCliente(cl4);
     f.addCliente(cl4);
     f.addCliente(cl4);

     f.addEmpleado(em1);
     f.addEmpleado(em1);
     f.addEmpleado(em1);
     f.addEmpleado(em2);
     f.addEmpleado(em2);
     f.addEmpleado(em2);
     f.addEmpleado(em2);
     f.addEmpleado(em3);
     f.addEmpleado(em3);
     f.addEmpleado(em3);
     f.addEmpleado(em3);
     f.addEmpleado(em4);
     f.addEmpleado(em4);
     f.addEmpleado(em4);
     f.addEmpleado(em4);
     f.addEmpleado(em4);

     f.addProducto(pr1);
     f.addProducto(pr1);
     f.addProducto(pr1);
     f.addProducto(pr2);
     f.addProducto(pr2);
     f.addProducto(pr2);
     f.addProducto(pr2);
     f.addProducto(pr3);
     f.addProducto(pr3);
     f.addProducto(pr3);
     f.addProducto(pr3);
     f.addProducto(pr4);
     f.addProducto(pr4);
     f.addProducto(pr4);
     f.addProducto(pr4);
     f.addProducto(pr4);

     f.addTienda(ti1);
     f.addTienda(ti1);
     f.addTienda(ti1);
     f.addTienda(ti2);
     f.addTienda(ti2);
     f.addTienda(ti2);
     f.addTienda(ti2);
     f.addTienda(ti3);
     f.addTienda(ti3);
     f.addTienda(ti3);
     f.addTienda(ti3);
     f.addTienda(ti4);
     f.addTienda(ti4);
     f.addTienda(ti4);
     f.addTienda(ti4);
     f.addTienda(ti4);

//    // VER CLIENTES
   f.viewProvincia();
    f.viewCliente();
    f.viewEmpleado();
    f.viewProducto();
    f.viewTienda();
 
    
//     // ELIMNAR CLIENTE
//    Cliente.delete(cl2);
//     // VER CLIENTES
//     Cliente.view();
    
    }
     
}
