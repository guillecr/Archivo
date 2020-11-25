package principal;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Clase para gestionar archivos, tanto lectura como escritura
 * @version 0.9.2
 * @author Guillermo Casas Reche
 * @author g.casas.r94@gmail.com
 */
public abstract class Archivo {
    
    /**
     * Método para crear un archivo en la dirección y con el nombre indicado 
     * @param url Direccion absoluta del archivo
     * @return Boleano indicando si el proceso ha sido satisfactorio
     */
    static public boolean crear(String url){
        File f = null;
        FileWriter fw = null;
        boolean bandera = true;
        try {
            f = new File(url);
            fw = new FileWriter(f,true);
            fw.close(); 
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: " + ex);
            bandera = false;
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex);
        }
        return bandera;
    }
    
    /**
     * Método para escribir en un archivo. La escritura es por tipo String de un
     * objeto. Si el archivo no existe se creará
     * @param url Directorio absoluto del archivo
     * @param ty Tipo de escritura. True para añadir o False para sobreescribir
     * @param objeto Cualquier objeto java a escribir en modo String. Se 
     * aplicará al objeto el método toString
     * @return boleano indicando si el procedimiento ha sido correcto
     */
    static public boolean escribir(String url, Object objeto,boolean ty){
        File f = null;
        FileWriter fw = null;
        PrintWriter pw = null;
        boolean bandera = true;
        try{
            f = new File(url);
            fw = new FileWriter(f,ty);
            pw = new PrintWriter(fw);
            pw.println(objeto.toString());
            pw.close();
        }catch(IOException e){
            bandera = false;
            System.out.println("[ERROR ESCRITURA] " + e);
        }
        return bandera;
    }
    
    /**
     * Método para escribir en un archivo un objeto como binario. Si el archivo 
     * no existe se creará
     * @param url Dirección absoluta del archivo
     * @param objeto Cualquier Objeto de java
     * @param ty Tipo de escritura. True para añadir o False para sobreescribir
     * @return boleano indicando si el procedimiento ha sido correcto
     */
    static public boolean escribirBinario(String url, Object objeto, boolean ty) {
        boolean bandera = true;
        try {
            FileOutputStream fos = new FileOutputStream(url,ty);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objeto);
            oos.close();
            
        }catch(IOException e){
            bandera = false;
        }
        return bandera;
    }
    
    /**
     * Método para leer un archivo de texto y trasformarlo en un String
     * @param url Dirección absoluta del archivo de texto
     * @return String con el contenido del archivo leído
     */
    static public String leer(String url){
        StringBuilder salida = new StringBuilder();
        File f = new File(url);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String aux;
            while((aux = br.readLine())!=null)
                salida.append(aux);
            
        } catch (FileNotFoundException ex) {
            System.out.println("[ERROR-LECTURA]: " + ex);
        } catch (IOException ex) {
            System.out.println("[ERROR-LECTURA]: " + ex);
        }
        return salida.toString();
    }
    
    /**
     * Método para leer un archivo binario de Java y trasformarlo a un Object
     * @param url Dirección absoluta del archivo a leer
     * @return Object del archivo leído
     */
    static public Object leerBinario(String url){
       Object lectura = null;
        try{
            FileInputStream fis = new FileInputStream(url);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lectura = ois.readObject();
            System.out.println("Lectura");
            ois.close();
            
        }catch (EOFException e) {
            System.out.println("LECTURA CORRECTA");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[ERROR-LECTURA]: " + e);
            lectura = null;
        }
        return lectura;
    }
    
    /**
     * Este método nos devuelve en formato <code>Document</code> el contenido 
     * de un fichero .xml.
     * A la función hay que entregarle la dirección y nombre del archivo
     * Si este genera algun error, devolverá <code>null</code>
     * @param url
     * @return 
     */
    static public Document leerXML(String url) {
        Document salida = null;
        try {
            FileInputStream fis = new FileInputStream(url);
            DocumentBuilder dBuilder = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder();
            
            salida = dBuilder.parse(fis);
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            System.out.println(ex);
        }
        return salida;
    }
    
    /**
     * Método para leer un archivo binario de Java y trasformarlo en una lista 
     * de objetos. Si el objeto leido no es una lista se le añadirá en un 
     * ArrayList vacío.
     * @param url Dirección absoluta del archivo
     * @return List con los objetos obtenidos en el archivo
     */
    static public List leerBinarioListas(String url){
        Object aux = leerBinario(url);
        List salida;
        if(aux instanceof List == false){
            System.out.println("[ADVERTENCIA]: El elemento leido no es una lista // " + aux.getClass());
            salida = new ArrayList<>();
            salida.add(aux);
        }else{
            salida = (List)aux;
        }
        return salida;
    }
    
    /**
     * Método para transformar un archivo JSON en un objeto de java compatible.
     * Hay que indicar que clase se quiere trasformar pero este devuelve un 
     * <code>Object</code>, por lo que a la salida de esta función hay que 
     * trasformarlo a la clase indicada. 
     * ej: Pelicula peli_1 = (Pelicula) archivo.parsearJSON(fichero,Pelicula.class);
     * @param fichero String con formato JSON a trasformar.
     * @param clase Clase a la que trasformar la información del JSON
     * @return Objecto con el formato de la clase para parsear
     */
    static public Object parsearJSON(String fichero, Class clase){
        Gson gson = new Gson();
        return gson.fromJson(fichero, clase);
    }
    
    /**
     * Método similar al parsearJSON pero en vez de entregar el texto con 
     * formarto JSON, se entrega un archivo .JSON.
     * Hay que indicar que clase se quiere obtener, pero hay que trasformar
     * la salida del método (Object) a la clase indicada. 
     * ej: Pelicula peli_1 = (Pelicula) archivo.parsearJSON(url,Pelicula.class);
     * @param url Dirección del archivo .json
     * @param clase Clase a la que trasformar la información del JSON
     * @return Objecto con el formato de la clase para parsear
     */
    static public Object leerJSON(String url, Class clase){
        return parsearJSON(leer(url), clase);
    }
    
    /**
     * Método que nos devuelve un String en formato JSON de un objeto entregado
     * 
     * @param objeto Objeto a convertir
     * @return String del objeto en formato JSON
     */
    static public String StringToJson(Object objeto){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(objeto);
    }
}