/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploUso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import principal.Archivo;


public class EjemploXML {

    public static void main(String[] args) {
        Archivo arch = new Archivo();
        Document doc = arch.leerXML("C:\\Users\\guill\\Documents\\DAM\\Repositorios\\Archivo\\data\\Clientes.xml");
        NodeList nodoCliente = doc.getElementsByTagName("cliente");
        for(int i=0;i<nodoCliente.getLength();i++){
            // Obtenemos el elemento en posicion i de cliente
            Node nodo = nodoCliente.item(i);

            // Lo trasformamos (Casting) a una clase Elemento para poder trabajar con el
            Element elemento = (Element)nodo;

            /* Lo imprimimos. Para ello, indicamos que atributo queremos coger y que elemento de ese atrinuto. En nuestro caso
            * cogemos los elementos de la etiqueta "nombre" y dentro de ello, cogemos el elemento en la primera posición. 
            * E nuestro caso, nombre solo tiene un elemento. Despues, con el metodo getTexContent() obtenemos el texto que esta ubicado en
            * nombre[0] del cliente indicado en el FOR
            */
            System.out.println(elemento.getElementsByTagName("nombre").item(0).getTextContent());
        }
    }
    
}
