
package procesador2;

import integrador.principal;


public class kernel {
    /**MEMORIA, es la clase que implementa una memoria fuera del microprocesador. */
     //static almacenamiento.memoria MEMORIA;  
     /** UC, es la clase que implementa la funcionalidad de una Unidad de Control. */
     static unidadControl  UC;
     /** ALU, es la clase que implementa una unidad aritmética-lógica. */
     static unidadAritmeticaLogica  ALU;

     
     /** numeroOperaciones, es el atributo que cuenta el número de operaciones realizadas. */
     int numeroOperaciones;
     /** numeroNucleos, es el atributo que cuenta el número de núcleos en el microprocesador. */
     int numeroNucleos;
     /** usoHilos, este atributo decide que la implementación usa hilos o no. */
     int usoHilos;
     
     /**
      * Este es el método constructor de la clase que inicia los atributos de
      * la misma para llevar una estadística de las operaciones realizadas. 
      */
     
     static principal PRINCIPAL;
     
     public kernel(int PC){
         //MEMORIA=new almacenamiento.memoria(50);
         UC= new unidadControl();
         ALU= new unidadAritmeticaLogica();
         UC.contadorPrograma=PC;
     }
     
     
    /**
     * Este método controla un ciclo de reloj en el microprocesador ejecutando
     * las instrucciones Instruction fetch, decode, data fetch, execute y store
     * sin aplicar técnicas de alto rendimiento. 
     * @return salida, es la variable que detecta el fin de programa.
     */
    public static int cicloDeComputo(){
        /** salida, es una variable que declara si el microprocesador debe terminar o no. */
        int salida;
        
            UC.instructionFetch();
            UC.decode();
            UC.dataFetch();
            salida=UC.execute();
            UC.store();
            return salida;
    }
    
    /**
     * Este método es para uso de depuración del programa. 
     * @return salida, es para saber cuando el microprocesador debe detenerse. 
     */
    public static int cicloDeComputoDEBUG(){
        /** salida, es una variable que declara si el microprocesador debe terminar o no. */
        int salida;
        
            System.out.println("-----------Procesador 2------------");
            System.out.println("INICIA---------------------");
            System.out.println("---------------------");
            System.out.println("INSTRUCTION FETCH");
            System.out.println("---------------------");
            UC.instructionFetch();
            UC.muestraUC();
            ALU.muestraALU();
        
            System.out.println("---------------------");
            System.out.println("DECODE");
            System.out.println("---------------------");
            UC.decode();
            UC.muestraUC();
            ALU.muestraALU();
        
            System.out.println("---------------------");
            System.out.println("DATA FETCH");
            System.out.println("---------------------");
            UC.dataFetch();
            UC.muestraUC();
            ALU.muestraALU();
        
            System.out.println("---------------------");
            System.out.println("EXECUTE");
            System.out.println("---------------------");
            salida=UC.execute();
            UC.muestraUC();
            ALU.muestraALU();
            integrador.principal.MEMORIA.muestraMemoria();
            
        
            System.out.println("---------------------");
            System.out.println("STORE");
            System.out.println("---------------------");
            UC.store();
            UC.muestraUC();
            ALU.muestraALU();
            integrador.principal.MEMORIA.muestraMemoria();
            System.out.println("FINALIZA---------------------");
            return salida;
    }
    
    
    public static void principal(){  
        int salida;
        //integrador.principal.MEMORIA.enlaceCodigo();
   
        
        do{
            salida=procesador2.kernel.cicloDeComputoDEBUG();
        }while(salida==0);
    }   
        
    
    
    /**
     * Esta es la función principal del código donde se controla el llamado 
     * a las demás funciones del microprocesador. 
     * @param args, el parámetro tomado desde la línea de comandos. 
     */
    
    public static void main(String[] args) {
        //integrador.principal.MEMORIA.enlaceCodigo();
        
        
        UC= new unidadControl();
        ALU= new unidadAritmeticaLogica();
        integrador.principal.instanciar();
        int salida;
        
   
        //UC.contadorPrograma=10;
        
        
        do{
            salida=cicloDeComputoDEBUG();
        }while(salida==0);
    }   
}
