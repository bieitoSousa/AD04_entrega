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
     SessionFactory  h = HibernateUtil.getSessionFactory();
     //CARGAR PROVINCIAS
     JSonMake.setfProvincias(new File(".\\provincias.json"));
     JSonMake.CargarFileProvincias();
     
     //AÑADIR  CLIENTE
     //Cliente(String name, String apellido, String email)
    Cliente cl1 = new Cliente("Cli1_name","Cli1_apel","Cli1_email"); 
    Cliente cl2 = new Cliente("Cli2_name","Cli2_apel","Cli2_email"); 
    Cliente cl3 = new Cliente("Cli3_name","Cli3_apel","Cli3_email"); 
    Cliente cl4 = new Cliente("Cli4_name","Cli4_apel","Cli4_email"); 
    // VER CLIENTES
    Cliente.view();
     // ELIMNAR CLIENTE
    Cliente.delete(cl2);
     // VER CLIENTES
     Cliente.view();
    
    }
     
}
