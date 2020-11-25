package ejemploUso;

import java.util.ArrayList;
import java.util.List;
import principal.Archivo;

public class Ejemplo {
    public static void main(String[] args){
        Alumno a1 = new Alumno(0, "Guille", 26);
        Alumno a2 = new Alumno(1, "Sandra", 24);
        List<Alumno> alumnos_al = new ArrayList<>();
        alumnos_al.add(a1);
        alumnos_al.add(a2);
        
        String url1 = "/home/guille_linux/NetBeansProjects/ARCHIVOS/data/datos.ser";
        String url2 = "/home/guille_linux/NetBeansProjects/ARCHIVOS/data/datos.txt";
        
        
        // Ensayo con BINARIOS
        System.out.println("\n\nBINARIO");
        if(Archivo.crear(url1)){
            System.out.println("OK creación");
        }else{
            System.out.println("ERROR en la creación");
        }
        
        
        if(Archivo.escribirBinario(url1, a1,false)){
            System.out.println("OK escritura");
        }else{
            System.out.println("ERROR en la escritura");
        }
        
        
        List lista = Archivo.leerBinarioListas(url1);
        if(lista!=null)
            System.out.println("Alumno: " + lista.get(0).toString());
        
        //Ensayo con TEXTO
        System.out.println("\n\nTEXTO");
        if(Archivo.escribir(url2, alumnos_al,false)){
            System.out.println("OK escritura");
        }else{
            System.out.println("ERROR de escritura");
        }
        
        String listaS = Archivo.leer(url2);
        System.out.println("Lista recuperada: " + listaS);
        System.out.println("Lista original: " + alumnos_al);   
    }    
}