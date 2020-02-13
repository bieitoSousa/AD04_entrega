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
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
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
@Entity
@Table(name = "CLIENTE")

public class Cliente implements Serializable {

    static SessionFactory sessionFact = null;
    static Session session = null;
    static Transaction tran = null;
    @Id
    @Column(name = "CLIENTE_id")
    private int id;
    @Column(name = "CLIENTE_name")
    private String name;
    @Column(name = "CLIENTE_apellido")
    private String apellido;
    @Column(name = "CLIENTE_email")
    private String email;

    public Cliente() {

    }

    public Cliente(String name, String apellido, String email) {
        this.name = name;
        this.apellido = apellido;
        this.email = email;
    }

    public int getId() {
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", name=" + name + ", apellido=" + apellido + ", email=" + email + '}';
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        return true;
    }

    public static boolean view() {
        try {
            if ((sessionFact = HibernateUtil.getSessionFactory()) != null) {
                if ((session = sessionFact.openSession()) != null) {
                    Query q1 = session.createQuery("SELECT cli FROM CLIENTE cli");
                    List<Cliente> clientes = q1.list();
                    for (Cliente cli : clientes) {
                        System.out.println(cli.toString());
                    }
                    session.close();
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean add(Cliente cli) {
        try {
            //Collemos a sesión de Hibernate
            if ((sessionFact = HibernateUtil.getSessionFactory()) != null) {
                if ((session = sessionFact.openSession()) != null) {
                    //Comenzamos unha transacción
                    if ((tran = session.beginTransaction()) != null) {
                        //Gardamos o equipo
                        session.save(cli);
                        //Facemos un commit da transacción
                        tran.commit();
                        session.close();
                        return true;
                    }
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static  boolean delete(Cliente cli) {
        try {
            //Collemos a sesión de Hibernate
            if ((sessionFact = HibernateUtil.getSessionFactory()) != null) {
                if ((session = sessionFact.openSession()) != null) {
                    //Comenzamos unha transacción
                    if ((tran = session.beginTransaction()) != null) {
                        //Gardamos o equipo
                        session.delete(cli);
                        //Facemos un commit da transacción
                        tran.commit();
                        session.close();
                        return true;
                    }
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static  boolean update(Cliente cli) {
        try {
            //Collemos a sesión de Hibernate
            if ((sessionFact = HibernateUtil.getSessionFactory()) != null) {
                if ((session = sessionFact.openSession()) != null) {
                    //Comenzamos unha transacción
                    if ((tran = session.beginTransaction()) != null) {
                        //Gardamos o equipo
                        session.update(cli);
                        //Facemos un commit da transacción
                        tran.commit();
                        session.close();
                        return true;
                    }
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return false;
    }
    

}// fin cliente

