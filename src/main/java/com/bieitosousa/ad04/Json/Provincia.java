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
package com.bieitosousa.ad04.Json;

import com.bieitosousa.ad04.HibernateUtil;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author bieito
 */
@Entity
@Table(name = "PROVINCIA")

public class Provincia implements Serializable {

    static SessionFactory sessionFact = null;
    static Session session = null;
    static Transaction tran = null;

    @Id
    @Column(name = "PROVINCIA_id")
    private int id;
    @Column(name = "PROVINCIA_name")
    private String nome;

    public Provincia() {
    }

    public Provincia(int id, String nome) {
        this.id = id;
        this.nome = nome;
        // System.out.println("Se a creado una proviencia"+id +" nombre "+ nome);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean insert() {
        if (add(this)) {
            System.out.println(" Insertando  PROVINCIA : [" + nome + " CORRECTAMENTE");
            return true;
        } else {
            System.out.println("NO se ha insertado la provincia : " + nome);
            return false;
        }
    }

    @Override
    public String toString() {
        return "Provincia{" + "id=" + id + ", nome=" + nome + '}';
    }

    public static boolean add(Provincia pro) {
        try {
            //Collemos a sesión de Hibernate
            if ((sessionFact = HibernateUtil.getSessionFactory()) != null) {
                if ((session = sessionFact.openSession()) != null) {
                    //Comenzamos unha transacción
                    if ((tran = session.beginTransaction()) != null) {
                        //Gardamos o equipo
                        session.save(pro);
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

    public static boolean delete(Provincia pro) {
        try {
            //Collemos a sesión de Hibernate
            if ((sessionFact = HibernateUtil.getSessionFactory()) != null) {
                if ((session = sessionFact.openSession()) != null) {
                    //Comenzamos unha transacción
                    if ((tran = session.beginTransaction()) != null) {
                        //Gardamos o equipo
                        session.delete(pro);
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

    public static boolean update(Provincia pro) {
        try {
            //Collemos a sesión de Hibernate
            if ((sessionFact = HibernateUtil.getSessionFactory()) != null) {
                if ((session = sessionFact.openSession()) != null) {
                    //Comenzamos unha transacción
                    if ((tran = session.beginTransaction()) != null) {
                        //Gardamos o equipo
                        session.update(pro);
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

}
