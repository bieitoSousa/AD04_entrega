package com.bieitosousa.ad04.Json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.File;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map.Entry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class JSonMake {

//    public static void main(String[] args) throws Exception {
//    ReadObjJsonInFileProvincias();
//    }
    private static DbConnection connConf = null;
    private static Hibernate hConf = null;
    private static ConfigDB configDB = null;
    static File fProvincias = new File(".\\provincias.json");
    static File fconfig = new File(".\\config.json");

    public static File getfProvincias() {
        return fProvincias;
    }

    public static void setfProvincias(File fProvincias) {
        JSonMake.fProvincias = fProvincias;
    }

    public static File getFconfig() {
        return fconfig;
    }

    public static void setFconfig(File fconfig) {
        JSonMake.fconfig = fconfig;
    }



    public static DbConnection getDbConnection() {
        if (connConf != null) {
            return connConf;
        } else {
            ReadConfigDBJsonInFile(fconfig);
            return connConf;
        }
    }

    public static Hibernate getHibernate() {

        if (hConf != null) {
            return hConf;
        } else {
            ReadConfigDBJsonInFile(fconfig);
            return hConf;
        }

    }
    public static ConfigDB getConfigDB() {

        if (configDB != null) {
            return configDB;
        } else {
            ReadConfigDBJsonInFile(fconfig);
            return configDB;
        }

    }
    

    public static void readAsString_Json(File fileJson) throws java.io.IOException {
        JsonParser parser = new JsonParser();

        FileReader fr = new FileReader(fileJson);
        JsonElement datos = parser.parse(fr);
        dumpJSONElement(datos);
    }

    public static void dumpJSONElement(JsonElement elemento) {
        if (elemento.isJsonObject()) {
            System.out.println("Es objeto");
            JsonObject obj = elemento.getAsJsonObject();
            java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
            java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
            while (iter.hasNext()) {
                java.util.Map.Entry<String, JsonElement> entrada = iter.next();
                System.out.println("Clave: " + entrada.getKey());
                System.out.println("Valor:");
                dumpJSONElement(entrada.getValue());
            }

        } else if (elemento.isJsonArray()) {
            JsonArray array = elemento.getAsJsonArray();
            System.out.println("Es array. Numero de elementos: " + array.size());
            java.util.Iterator<JsonElement> iter = array.iterator();
            while (iter.hasNext()) {
                JsonElement entrada = iter.next();
                dumpJSONElement(entrada);
            }
        } else if (elemento.isJsonPrimitive()) {
            System.out.println("Es primitiva");
            JsonPrimitive valor = elemento.getAsJsonPrimitive();
            if (valor.isBoolean()) {
                System.out.println("Es booleano: " + valor.getAsBoolean());
            } else if (valor.isNumber()) {
                System.out.println("Es numero: " + valor.getAsNumber());
            } else if (valor.isString()) {
                System.out.println("Es texto: " + valor.getAsString());
            }
        } else if (elemento.isJsonNull()) {
            System.out.println("Es NULL");
        } else {
            System.out.println("Es otra cosa");
        }
    }

    public static void ObjJsontoString(Object obj) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(obj));
    }

    public static void WriteObjJsonInFile(File objectFile, Object obj) {
        Gson gson = new Gson();

        // Java objects to String
        // String json = gson.toJson(staff);
        // Java objects to File
        try (FileWriter writer = new FileWriter(objectFile)) {
            gson.toJson(obj, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println(gson.toJson(obj));
    }

    public static Object ReadObjJsonInFile(File objectFile) {
        Gson gson = new Gson();
        Object obj = null;
        try (Reader reader = new FileReader(objectFile)) {
            // Convert JSON File to Java Object
            obj = gson.fromJson(reader, Object.class);
            // print staff object
            System.out.println(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static Object ReadObjJsonInFile(File objectFile, Class cla) {
        Gson gson = new Gson();
        Object objaux = null;
        try (Reader reader = new FileReader(objectFile)) {
            // Convert JSON File to Java Object
            objaux = gson.fromJson(reader, cla);
            // print staff object
            System.out.println(objaux);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objaux;
    }
    
    
      public static Object ReadHibernateJsonInFile(File objectFile) {
        Gson gson = new Gson();
        Hibernate objaux = null;
        try (Reader reader = new FileReader(objectFile)) {
            // Convert JSON File to Java Object
            objaux = gson.fromJson(reader, Hibernate.class);
            // print staff object
            System.out.println(objaux);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objaux;
    }
      public static Object ReadConfigDBJsonInFile(File objectFile) {
        Gson gson = new Gson();
        ConfigDB objaux = null;
          System.out.println("mirando la configuracion");
        try (Reader reader = new FileReader(objectFile)) {
            System.out.println("extrallendo configuracion");
            // Convert JSON File to Java Object
            objaux = gson.fromJson(reader, ConfigDB.class);
            // print staff object
            System.out.println("mostrando objeto auxiliar");
            System.out.println(objaux);
            connConf = objaux.getDbc();
            hConf =objaux.getHib();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return objaux;
    }
      public static Object ReadDbConnectionJsonInFile(File objectFile) {
        Gson gson = new Gson();
        DbConnection objaux = null;
          System.out.println("estoy en ReadDbConnectionJsonInFile");
        try (Reader reader = new FileReader(objectFile)) {
            // Convert JSON File to Java Object
             objaux = (DbConnection)gson.fromJson(reader, DbConnection.class);
            // print staff object
            System.out.println("recupero el objeto : ReadDbConnectionJsonInFile");
            System.out.println(objaux);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objaux;
    }

    public static boolean GETReadArrayJsonInFile(File file) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(file));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;

            // A JSON array. JSONObject supports java.util.List interface.
            connConf = (DbConnection) jsonObject.get("dbConnection");;
            hConf = (Hibernate) jsonObject.get("hibernate");

            // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
            // Iterators differ from enumerations in two ways:
            // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
            // 2. Method names have been improved.
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

//    public static List<Provincia> ReadObjJsonInFileProvincias() {
//        Gson gson = new Gson();
//        Provincias obj = null;
//        List<Provincia> provinciasList = null;
//        try (Reader reader = new FileReader(f)) {
//            // Convert JSON File to Java Object
//            obj = gson.fromJson(reader, Provincias.class);
//            provinciasList = obj.getProvincias();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return provinciasList;
//    }
//    
    public static  boolean  CargarFileProvincias(){
    Gson gson = new Gson();
        Provincias obj = null;
        List<Provincia> provinciasList = null;
        System.out.println("=== INTENTADO CARGAR EL FICHERO ==== [ "+fProvincias.toString()+"]");
        try (Reader reader = new FileReader(fProvincias)) {
            System.out.println("-- INTETADO LEER PROVINCIAS");
            obj = gson.fromJson(reader, Provincias.class);
            if (obj != null){
                System.out.println("INSERTANDO PROVIENCIAS");
            provinciasList = obj.getProvincias();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
         System.out.println("=== INTENTADO LEER EL FICHERO ==== [ "+fProvincias.toString()+"]");
        if(provinciasList.size()>0){
            System.out.println("leyendo ..........................");
             for (Provincia p :  provinciasList){
                 System.out.print(p.getNome()+",");
        }
        }else{
            System.out.println("fallo al CARGAR fichero provincias "+fProvincias.toString());
        }
        return true;
    }
//public static Compania ReadObjJsonInFileCompania( File objectFile){
//    Gson gson = new Gson();
//    Compania obj = null ;
//    try (Reader reader = new FileReader(objectFile)) {
//        // Convert JSON File to Java Object
//        obj = gson.fromJson(reader,Compania.class );
//        // print staff object
//        System.out.println(obj);
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//    return obj;
//}
    public static void main(String[] arg) {
        System.out.println( getDbConnection().getName());
        System.out.println( getDbConnection().getAddress());
        System.out.println( getDbConnection().getPassword());
        System.out.println( getDbConnection().getPort());
        System.out.println( getDbConnection().getUser());

        System.out.println( getHibernate().getDialect());
        System.out.println( getHibernate().getDriver());
        System.out.println( getHibernate().getHBM2DDL_AUTO());
        System.out.println( getHibernate().getSHOW_SQL());
 

    }

    public static class DbConnection {

        private String address;
        private String port;
        private String name;
        private String user;
        private String password;

        public DbConnection(String address, String port, String name, String user, String password) {
            this.address = address;
            this.port = port;
            this.name = name;
            this.user = user;
            this.password = password;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "DbConnection{" + "address=" + address + ", port=" + port + ", name=" + name + ", user=" + user + ", password=" + password + '}';
        }

    }

    public static class Hibernate {

        private String driver;
        private String dialect;
        private String HBM2DDL_AUTO;
        private boolean SHOW_SQL;

        public Hibernate(String driver, String dialect, String HBM2DDL_AUTO, boolean SHOW_SQL) {
            this.driver = driver;
            this.dialect = dialect;
            this.HBM2DDL_AUTO = HBM2DDL_AUTO;
            this.SHOW_SQL = SHOW_SQL;
        }

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getDialect() {
            return dialect;
        }

        public void setDialect(String dialect) {
            this.dialect = dialect;
        }

        public String getHBM2DDL_AUTO() {
            return HBM2DDL_AUTO;
        }

        public void setHBM2DDL_AUTO(String HBM2DDL_AUTO) {
            this.HBM2DDL_AUTO = HBM2DDL_AUTO;
        }

        public String getSHOW_SQL() {
            return String.valueOf(SHOW_SQL);
        }
        public boolean isSHOW_SQL() {
            return SHOW_SQL;
        }

        public void setSHOW_SQL(boolean SHOW_SQL) {
            this.SHOW_SQL = SHOW_SQL;
        }

       

    

        @Override
        public String toString() {
            return "Hibernate{" + "driver=" + driver + ", dialect=" + dialect + ", HBM2DDL_AUTO=" + HBM2DDL_AUTO + ", SHOW_SQL=" + SHOW_SQL + '}';
        }

    }

    public static class ConfigDB {
    private DbConnection dbConnection ;
    private Hibernate hibernate;

        public ConfigDB(DbConnection dbConnection, Hibernate hibernate) {
            this.dbConnection = dbConnection;
            this.hibernate = hibernate;
        }

        public DbConnection getDbc() {
            return dbConnection;
        }

        public void setDbc(DbConnection dbConnection) {
            this.dbConnection = dbConnection;
        }

        public Hibernate getHib() {
            return hibernate;
        }

        public void setHib(Hibernate hibernate) {
            this.hibernate = hibernate;
        }

        @Override
        public String toString() {
            return "ConfigDB{" + "dbConnection=" + dbConnection + ", hibernate=" + hibernate + '}';
        }
       
    }

}
