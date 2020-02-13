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
/**
 *
 * @author bieito
 */
//import  com.bieitosousa.ad03_db.Json.JSonMake;
import  com.bieitosousa.ad04.Json.JSonMake;
import com.bieitosousa.ad04.Json.Provincia;
//import com.bieitosousa.ad03_db.Json.Provincia;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
    //Este método devolve a sesión para poder facer operacións coa base de datos
    public static SessionFactory getSessionFactory(){
        
        //Se a sesion non se configurou, creamolo
        if(sessionFactory == null){
            try{
                Configuration conf = new Configuration();
                
                //Engadimos as propiedades
                Properties settings = new Properties();
                
                //Indicamos o conector da base de datos que vamos a usar
//                settings.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
                Object put = settings.put(Environment.DRIVER,JSonMake.getHibernate().getDriver());
                
                //Indicamos a localización da base de datos que vamos a utilizar
//                settings.put(Environment.URL,"jdbc:mysql://192.168.56.101:3306/hibernate");
                settings.put(Environment.URL,"jdbc:mysql://"+JSonMake.getDbConnection().getAddress()+":"+JSonMake.getDbConnection().getPort()+"/"+JSonMake.getDbConnection().getName());
                
                //Indicamos o usuario da base de datos con cal nos vamos conectar e o seu contrasinal
//                settings.put(Environment.USER,"userhibernate");
                settings.put(Environment.USER,JSonMake.getDbConnection().getUser());
//                settings.put(Environment.PASS,"abc123.");
                settings.put(Environment.PASS,JSonMake.getDbConnection().getPassword());
                
                //Indicamos o dialecto que ten que usar Hibernate 
//                settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.DIALECT,JSonMake.getHibernate().getDialect());
                
                //Indicamos que se as táboas todas se borren e se volvan crear
                settings.put(Environment.HBM2DDL_AUTO, JSonMake.getHibernate().getHBM2DDL_AUTO());
                
                //Indicamos que se mostre as operacións SQL que Hibernate leva a cabo
//                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.SHOW_SQL, JSonMake.getHibernate().getSHOW_SQL());
                conf.setProperties(settings);
                
                //Engaidmos aquelas clases nas que queremos facer persistencia
                conf.addAnnotatedClass(Cliente.class);
                conf.addAnnotatedClass(Provincia.class);
                conf.addAnnotatedClass(Empleado.class);
                conf.addAnnotatedClass(Franquicia.class);
                conf.addAnnotatedClass(Producto.class);
                conf.addAnnotatedClass(Tienda.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
                sessionFactory = conf.buildSessionFactory(serviceRegistry);
            }catch(HibernateException e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
        
    }

    static boolean getSession() {
        if (sessionFactory == null){
             sessionFactory = getSessionFactory();
        }
      if (sessionFactory == null)return false;
              else return true;
    }
    
}
