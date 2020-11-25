package ejemploUso;

import java.util.Properties;
import principal.Archivo;

public class EjemploJSON {

    public static void main(String[] args) {
        Properties[] clientes = (Properties[]) Archivo.leerJSON("C:\\Users\\guill\\Documents\\DAM\\Repositorios\\Archivo\\data\\Clientes.json", Properties[].class);
        for(Properties cliente : clientes){
            System.out.println(cliente.get("nombre"));
        }
    }
    
}
