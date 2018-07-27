/**
 * esta clase llama todo los metodos de la clase tableroMorris
 * @author (Dilian) 
 * @version (1)
 */
public class Controlador
{
    public static void main(String args[]){
     
        TableroMorris prueba=new TableroMorris(7);
        System.out.println( prueba.muestreTab());
        prueba.jueguen();
        System.out.println( prueba.muestreTab());
        prueba.seguirjugando();  

    }

}
