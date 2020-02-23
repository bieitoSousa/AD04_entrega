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

import com.bieitosousa.ad04.Data.*;
import com.bieitosousa.ad04.Help.*;
import com.bieitosousa.ad04.Json.*;
import com.bieitosousa.ad04.XML.*;
import java.io.File;
import java.util.InputMismatchException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 *
 * @author bieito
 */

   public class Main {

    static Franquicia f = Franquicia.getInstance();
    static Tienda t = null;

    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in); // Invocamos un método sobre un objeto Scanner
        int op;
        boolean s = true;
        boolean salir = false;
        //Compañia c = Compañia.getInstance();; 

        while (!salir) {
            String encabezado =
                    "=====================================================================\n "
                    + "=============================== Menu ==============================\n "
                    + "===================================================================\n";
            if (t != null) {
                encabezado += "\n=  # Tienda selecionada :";
                encabezado += "\n=  # Nombre    => [ " + t.getName()+"]";
                encabezado += "\n=  # Provincia => [ " + t.getProvincia()+"]";
                encabezado += "\n=  # Ciudad    => [ " + t.getCiudad()+"]";
                encabezado += "\n=  # Numero de Empleados    => [ " + t.getTiendaEmpleado().size()+"]";
                encabezado += "\n=  # Numero de Productos    => [ " + t.getTiendaProducto().size()+"]";
                encabezado += "\n=  # Tienda selecionada Nombre => [ " + t.getName()+"]";
            } else {
                encabezado += "\n = NO HAY NINGUNA TIENDA SELECCIONADA";
            }
             encabezado += "===================================================================\n";
            System.out.println(
                    encabezado
                    + "\n1. - Selecionar una tenda.\n"
                    + "2. - Engadir unha tenda.\n"
                    + "3. - Mostar as tendas.\n"
                    + "4. - Eliminar unha tenda (elimínanse tódolos productos e empragados desta).\n"
                    + "5. - Engadir un producto a franquicia.\n"
                    + "6. - Mostrar os producto da franquicia.\n"
                    + "7. - Engadir un producto a tenda.\n"
                    + "8. - Mostrar os productos da tenda\n"
                    + "9. - Actualizar o stock dun producto nunha determinada tenda..\n"
                    + "10. - Mostrar o stock dun producto dunha tenda..\n"
                    + "11. - Eliminiar un producto da franquicia.\n"
                    + "12. - Eliminiar un producto a tenda.\n"
                    + "13. - Engadir un empregado a Franquicia.\n"
                    + "14. - Mostar empregados a franquicia.\n"
                    + "15. - Eliminar un empregado da Franquicia.\n"
                    + "16. - Engadir horas a un empregado na tenda.\n"
                    + "17. - ver numero de horas dos empregados nunha tenda \n"
                    + "18. - Engadir un cliente.\n"
                    + "19. - Mostrar Clientes.\n"
                    + "20. - Eliminar un cliente.\n"
                    + "21. - Ler os titulares do periódico El País. \n"
                    + "0. - Sair do programa.\n"
            );

            try {
                op = reader.nextInt();
                String n;
                String a;
                String e;

                switch (op) {
                    case 0: // 0. - Sair do programa. 
                        salir = true;
                        break;
                    case 1: // 1. - Selecionar una tenda.
                        if (f.getMapTienda().size() > 0) {
                            System.out.println("SELECCIONE UNA TIENDA");
                            menuSelectTienda();
                        } else {
                            System.out.println("NO SE HA PODIDO SELLECIONAR UNA TIENDA : CREANDO TIENDA");
                            menuAddTienda();
                            System.out.println("se a seleccionado la tienda : " + t.toString());
                        }

                        break;
                    case 2: // 2. - Engadir unha tenda.
                        menuAddTienda();

                        break;
                    case 3: // 3. - Mostar as tendas. 
                        f.viewTienda();
                        break;

                    case 4: // 4. - Eliminar unha tenda . 
                        s = true;
                        n = "";
                        while (s && !"exit".equals(n)) {
                            f.viewTienda();
                            System.out.println("[para salir escribe exit] Seleccione una Tenda para eliminala : .\n");
                            n = HelpFunctions.inputString("digame el nombre de la Tienda ? ");
                            if ("exit".equals(n)) {
                                break;
                            }

                            if (f.getMapTienda().containsKey(n)) {
                                System.out.println("Tienda [" + n + "]  ha sido eliminada ");
                                f.deleteTienda(n);
                                s = false;

                            }
                        }
                        break;
                    case 5: // 5. - Engadir un producto a franquicia. 
                        menuAddProd();
                        break;
                    case 6: // 6. - Mostrar os producto da franquicia.
                        f.viewProducto();
                        break;
                    case 7: // 7. - Engadir un producto a tenda. 
                        if (getTienda()) {
                            if (f.getMapProducto().size() > 0) {
                                menuAddProdToTiend();
                            } else {
                                menuAddProd();
                                if (f.getMapTienda().size() > 0) {
                                    menuAddProdToTiend();
                                }
                            }
                        } else {
                            System.out.println("No se han encontrado tiendas disponibles : porfavor introduzca o seleccione una tienda");
                        }
                        break;
                    case 8: // 8. - Mostrar os Productos nunha determinada tenda.. 
                        if (getTienda()) {
                           t.viewProducto();
                        } else {
                            System.out.println("No se han encontrado tiendas disponibles : porfavor introduzca o seleccione una tienda");
                        }
                        break;
                    case 9: // 9. - Actualizar o stock dun producto nunha determinada tenda.. 
                        if (getTienda()) {
                            menuAddTiendaStock();
                        } else {
                            System.out.println("No se han encontrado tiendas disponibles : porfavor introduzca o seleccione una tienda");
                        }
                        break;
                    case 10: // 10. - Mostrar o stock dun producto dunha tenda.
                        if (getTienda()) {
                            t.viewProducto();
                        s = true;
                        n = "";
                        Producto producto=null;
                        while (s && !"exit".equals(n)) {
                            f.viewProducto();
                            System.out.println("[para salir escribe exit] Dime un Producto .\n");
                            n = HelpFunctions.inputString("nombre ? ");
                            if ("exit".equals(n)) {
                                break;
                            }
                            if ((producto=f.getMapProducto().get(n))!=null && t != null) {
                                t.getProductStock(producto);
                                s = false;
                            }

                        }
                        } else {
                            System.out.println("No se han encontrado tiendas disponibles : porfavor introduzca o seleccione una tienda");
                        }

                        break;
                    case 11: // 11. - Eliminiar un producto da franquicia.
                        s = true;
                        n = "";
                        while (s && !"exit".equals(n)) {
                            f.viewProducto();
                            System.out.println("[para salir escribe exit] Seleccione un Producto para eliminarlo : .\n");
                            n = HelpFunctions.inputString("digame el nombre del Producto ? ");
                            if ("exit".equals(n)) {
                                break;
                            }
                            if (f.getMapProducto().containsKey(n)) {
                                System.out.println("Producto [" + n + "]  ha sido eliminado ");
                                f.deleteProducto(n);
                                s = false;
                            }
                        }
                        break;
                    case 12: // 12. - Eliminiar un producto a tenda. 
                        if (getTienda()) {
                            s = true;
                            n = "";
                            Producto producto= null;
                            while (s && !"exit".equals(n)) {
                                t.viewProducto();
                                System.out.println("[para salir escribe exit] Seleccione un Producto para eliminarlo l .\n");
                                n = HelpFunctions.inputString("digame el nombre del Producto ? ");
                                if ("exit".equals(n)) {
                                    break;
                                }
                                if ((producto = t.getMapProducto().get(n))!=null) {
                                    System.out.println("Producto [" + n + "]  ha sido eliminado de " + t.getName());
                                    t.deleteProducto(producto);
                                    s = false;
                                }
                            }
                        } else {
                            System.out.println("No se han encontrado tiendas disponibles : porfavor introduzca o seleccione una tienda");
                        }
                        break;
                    case 13: // 13. - Engadir un empregado a Franquicia.  
                        s = true;
                        n = "";
                        a = "";
                        while (s && !"exit".equals(n) && !"exit".equals(a)) {
                            System.out.println("[para salir escribe exit] Creando un Empleado dime: .\n");
                            n = HelpFunctions.inputString("nombre ? ");
                            if ("exit".equals(n)) {
                                break;
                            }
                            a = HelpFunctions.inputString("apellidos ? ");
                            if ("exit".equals(n)) {
                                break;
                            }
                            if (HelpFunctions.whiteSpace(n)) {
                                f.addEmpleado(new Empleado(n, a));
                                System.out.print("Se a creado un Empleado:\n" + f.getMapEmpleado().get(n).toString());
                                s = false;

                            }
                        }
                        break;
                    case 14: // 14. - Mostar empregados a franquicia.  
                        f.viewEmpleado();
                        break;
                    case 15: // 15. - Eliminar un empregado da Franquicia.
                        s = true;
                        n = "";
                        while (s && !"exit".equals(n)) {
                            f.viewEmpleado();
                            System.out.println("[para salir escribe exit] Seleccione un Empleado para eliminarlo : .\n");
                            n = HelpFunctions.inputString("digame el nombre del Empleado ? ");

                            if ("exit".equals(n)) {
                                break;
                            }
                            if (f.getMapEmpleado().containsKey(n)) {
                                System.out.println("Empleado [" + n + "]  ha sido eliminado ");
                                f.deleteEmpleado(n);
                                s = false;
                            } else if (n.equals("exit")) {
                                s = false;
                            }
                        }
                        break;
                    case 16: // 16. - Engadir horas a un empregado na tenda. 
                        if (getTienda()) {

                            s = true;
                            n = "";
                            Empleado empleado = null;
                            while (s && !"exit".equals(n)) {
                                f.viewEmpleado();
                                System.out.println("[para salir escribe exit] Dime un Empleado .\n");
                                n = HelpFunctions.inputString("nombre ? ");

                                if ("exit".equals(n)) {
                                    break;
                                }
                                float horas = (float) HelpFunctions.inputFloat("numero de horas ? ");
                                if ((empleado =f.getMapEmpleado().get(n))!=null && t != null) {
                                    if (f.getMapEmpleado().containsKey(n)){
                                    t.updateEmpleado(empleado, horas);
                                    System.out.print("Se ha añadido un horario al empleado :\n" + n);
                                    s = false;
                                    }else {
                                     t.addEmpleado(empleado, horas);
                                    System.out.print("Se ha añadido un horario al empleado :\n" + n);
                                    s = false;
                                    }
                                }
                            }
                        } else {
                            System.out.println("No se han encontrado tiendas disponibles : porfavor introduzca o seleccione una tienda");
                        }
                        break;
                    case 17: // 17. - ver numero de horas dos empregados nunha tenda  
                        if (t != null) {
                            t.viewEmpleado();
                        } else {
                            System.out.println("No se han encontrado tiendas disponibles : porfavor introduzca o seleccione una tienda");
                        }
                        break;
                    case 18: // 18. - Engadir un cliente.  
                        s = true;
                        n = "";
                        a = "";
                        e = "";
                        while (s && !"exit".equals(n) && !"exit".equals(a) && !"exit".equals(e)) {
                            System.out.println("[para salir escribe exit] Creando un Cliente dime: .\n");
                            n = HelpFunctions.inputString("nombre ? ");
                            if ("exit".equals(n)) {
                                break;
                            }
                            a = HelpFunctions.inputString("apellidos ? ");
                            if ("exit".equals(a)) {
                                break;
                            }
                            e = HelpFunctions.inputString("email ? ");
                            if ("exit".equals(e)) {
                                break;
                            }
                            if (HelpFunctions.whiteSpace(n) && HelpFunctions.whiteSpace(a) && HelpFunctions.whiteSpace(e)) {
                                f.addCliente(new Cliente(n, a, e));
                                System.out.print("Se a creado un Empleado:\n" + f.getMapEmpleado().get(n).toString());
                                s = false;
                            }
                        }
                        break;
                    case 19: // 19. - Mostrar Clientes.  
                        f.viewCliente();
                        break;
                    case 20: // 20. - Eliminar un cliente. 
                        s = true;
                        n = "";
                        while (s && !"exit".equals(n)) {
                            f.viewCliente();
                            System.out.println("[para salir escribe exit] Seleccione un Cliente para eliminarlo : .\n");
                            n = HelpFunctions.inputString("digame el nombre del cliente ? ");
                            if ("exit".equals(n)) {
                                break;
                            }
                            if (f.getMapCliente().containsKey(n)) {
                                System.out.println("Cliente [" + n + "]  ha sido eliminado ");
                                f.deleteCliente(n);
                                s = false;
                            }
                        }
                        break;

                    case 21:// 21. - Ler os titulares do periódico El País.  
                        ReadXML.read();
                        break;
                    default:
                        System.out.print(" Opcion no valida inserte [0] si quiere cancelar ");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                reader.next();
            } catch (Exception e){
                System.out.println("excepcion : "+e.getMessage());
            }
        }
        reader.close(); // Cerramos el objeto Scanner

    }

    private static void menuAddTiendaStock() {
        t.viewProducto();
        boolean s = true;
        String n = "";
        Producto producto = null;
        while (s && !"exit".equals(n)) {
            f.viewProducto();
            System.out.println("[para salir escribe exit] Dime un Producto .\n");
            n = HelpFunctions.inputString("nombre ? ");
            if ("exit".equals(n)) {
                break;
            }
            int stock = HelpFunctions.inputInt("stock ? ");
            if ((producto =f.getMapProducto().get(n))!= null && t
                    != null) {
                if (t.getMapProducto().containsKey(n)){
                t.updateProducto(producto, stock);
                System.out.print("Se ha añadido un producto :\n" + t.toString());
                s = false;
            }else {
                   t.addProducto(producto, stock);
                System.out.print("Se ha añadido un producto :\n" + t.toString());
                s = false;  
                    
                }
            }
        }
    }

    private static void menuAddProdToTiend() {
        System.out.println("Vamos ha añadir un producto a la tienda : " + t.toString());
        boolean s = true;
        String n = "";
        Producto producto = null;
        while (s && !"exit".equals(n)) {
            f.viewProducto();
            System.out.println("[para salir escribe exit] Dime un Producto .\n");
            n = HelpFunctions.inputString("nombre ? ");
            if ("exit".equals(n)) {
                break;
            }
            if ((producto = f.getMapProducto().get(n))!= null && t != null) {
                t.addProducto(producto,1);
                System.out.println("Se ha añadido un producto :[" + n + "] a la tienda [" +t.getName() +"]" );
                s = false;
            }
        }
    }


    
    

    private static void menuAddTienda() {
        try{
        boolean s = true;
        String n = "";
        String c = "";
        Provincia pro =null;
        while (s && !"exit".equals(n) && !"exit".equals(c)) {
            System.out.println("[para salir escribe exit] Creando una tienda dime: .\n");
            n = HelpFunctions.inputString("nombre ? ");
            if ("exit".equals(n)) {
                break;
            }
             f.viewProvincia();
             
           if(( pro = f.getMapProvincia().get(( HelpFunctions.inputInt("provincia ? "))))!= null){
            c = HelpFunctions.inputString("cidade ? ");
            if ("exit".equals(c)) {
                break;
            }
            if (HelpFunctions.whiteSpace(n) && HelpFunctions.whiteSpace(c) && !f.getMapTienda().containsKey(n) && (pro != null)) {
                
                f.addTienda(new Tienda(n, pro, c));
                t=null;
               if ((t=f.getMapTienda().get(n))!=null){
                    System.out.println("Se a seleccionado la tienda"+t);
               }
                s = false;
            }
        }else{System.out.println("Provincia no valida");}
        }
    }catch (Exception e){
            System.out.println("Excepcion ..."+e.getMessage());
    }
    
    }

    private static void menuSelectTienda() {
        boolean s = true;
        String n = "";
        while (s && !"exit".equals(n)) {
            System.out.println("s vale " + s);
            System.out.println("n vale " + !"exit".equals(n));
            f.viewTienda();
            System.out.println("[para salir escribe exit] Seleccione una tienda : .\n");
            n = HelpFunctions.inputString("digame el nombre de la tienda ? ");
            if ("exit".equals(n)) {
                break;
            }
            if (f.getMapTienda().containsKey(n)) {
                t = f.getMapTienda().get(n);
                System.out.println("Tienda Seleccionada ["+t.getName()+"]");
                s = false;
            }
        }
    }

    private static void menuAddProd() {
        System.out.println("vamos a añadir un producto a la franquicia");
        boolean s = true;
        String n = "";
        String d = "";
        float prec = 0;
        while (s && !"exit".equals(n) && !"exit".equals(d)) {
            System.out.println("[para salir escribe exit] Creando un Producto dime: .\n");
            n = HelpFunctions.inputString("nombre ? ");
            if ("exit".equals(n)) {
                break;
            }
            prec = HelpFunctions.inputFloat("precio ? ");
            d = HelpFunctions.inputString("descripcion ? ");
            if ("exit".equals(d)) {
                break;
            }
            if (HelpFunctions.whiteSpace(n)) {
                f.addProducto(new Producto(n, prec, d));
                System.out.print("Se a creado un producto:\n" + f.getMapProducto().get(n).toString());
                s = false;
            }
        }
    }

    private static boolean getTienda() {
        while (t == null) {
            menuSelectTienda();
        }
        return t != null;

    }



     
}
