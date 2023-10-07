/**
 * Código generado para el curso de 
 * "Introducción  a los microprocesadores"
 * Laboratorio de Cómputo de Alto Rendimiento 
 * Universidad Veracruzana
 * @version 22 de octubre de 2017
 * @author Dr. Alfredo Cristóbal Salas.
 */
package almacenamiento;

/**
 * Esta clase implementa el funcionamiento básico de una celda de memoria. 
 */
public class memoria {
    /** direccionMaxima, es el máximo de registros en la memoria. */
    int direccionMaxima;
    /** vector, es el vector donde se almacenan los datos. */
    String[] vector;
    
    
    /**
     * Este es el método constructor de la clase donde se inicia todas las celdas
     * de memoria en ceros. 
     */
    public  memoria(int max){
        
        this.direccionMaxima=max;
        vector=new String[direccionMaxima];
        
    }
     
    
    public void inicioMemoria(){
        int i;
        for (i=0; i<this.direccionMaxima; i++){
            this.vector[i]="0000000000000000";
        }
    }
    
    
    /**
     * se ejecuta en este método de la clase el enlazamiento del código a 
     * ejecutarse. En este método se almacenan los datos y las instrucciones
     * a ejecutarse en el microprocesador. 
     */
    public void enlaceCodigo(){
        // cpu1 = x^2 + 7x + 10 = 0
        //cpu2 = 6x^2 + 11x + 4 = 0
        
        
        this.escritura(0,  "00000000-00000000-00000000-00000001");  // a=1
        this.escritura(1,  "00000000-00000000-00000000-00000111");  // b=7
        this.escritura(2,  "00000000-00000000-00000000-00001010");  // c=10
        this.escritura(3,  "00000000-00000000-00000000-00000000");  // cp1= 
        this.escritura(4,  "00000000-00000000-00000000-00000000");  
        this.escritura(5,  "00000000-00000000-00000000-00000000");
        this.escritura(6,  "00000000-00000000-00000000-00000000");
        this.escritura(7,  "00000000-00000000-00000000-00000000");
        this.escritura(8,  "00000000-00000000-00000000-00000000");
        this.escritura(9,  "00000000-00000000-00000000-00000000");
        
        this.escritura(10, "00000001-00000000-00000001-00000010");  // operaciones
        this.escritura(11, "00001111-00000000-0000000-000000000");
        this.escritura(12, "00000000-00000000-00000000-00000000");
        this.escritura(13, "00000000-00000000-00000000-00000000");  
        this.escritura(14, "00000000-00000000-00000000-00000000");  
        this.escritura(15, "00000000-00000000-00000000-00000000");
        this.escritura(16, "00000000-00000000-00000000-00000000");
        this.escritura(17, "00000000-00000000-00000000-00000000");
        this.escritura(18, "00000000-00000000-00000000-00000000");
        this.escritura(19, "00000000-00000000-00000000-00000000");
        
        this.escritura(20, "00000000-00000000-00000000-00000001");  // a=6
        this.escritura(21, "00000000-00000000-00000000-00000011");  // b=11
        this.escritura(22, "00000000-00000000-00000000-00000111");  // c=4
        this.escritura(23, "00000000-00000000-00000000-00000000"); 
        this.escritura(24, "00000000-00000000-00000000-00000000");
        this.escritura(25, "00000000-00000000-00000000-00000000");
        this.escritura(26, "00000000-00000000-00000000-00000000");
        this.escritura(27, "00000000-00000000-00000000-00000000");
        this.escritura(28, "00000000-00000000-00000000-00000000");
        this.escritura(29, "00000000-00000000-00000000-00000000");
        
        this.escritura(30, "00000001-00010100-00010101-00011001");  //operacion
        this.escritura(31, "00001111-00000000-00000000-00000000");  
        this.escritura(32, "00000000-00011010-00010111-00000000");  
        this.escritura(33, "00000000-00000000-00000000-00000000");  
        this.escritura(34, "00000000-00000000-00000000-00000000"); 
    }
    
    
    
    public void cargar_MEM_L1(int inicio, int fin, int inicioL1, int finL1){
        int i,j;
        
        j=inicioL1;
        for(i=inicio; i<fin; i++){
            integrador.principal.L1.escritura(inicioL1,integrador.principal.MEMORIA.lectura(i));
            j++;
        }
    }


    
    public void cargar_MEM_L2(int inicio, int fin, int inicioL2, int finL2){
        int i,j;
        
        j=inicioL2;
        for(i=inicio; i<fin; i++){
            integrador.principal.L2.escritura(inicioL2,integrador.principal.MEMORIA.lectura(i));
            j++;
        }
    }
    
        
    public void cargar_L1_MEM( int inicioL1, int finL1, int inicio, int fin){
        int i,j;
        
        j=inicio;
        for(i=inicioL1; i<finL1; i++){
            integrador.principal.L1.escritura(i,integrador.principal.MEMORIA.lectura(j));
            j++;
        }
    }
    

    public void cargar_L2_MEM( int inicioL2, int finL2, int inicio, int fin){
        int i,j;
        
        j=inicio;
        for(i=inicioL2; i<finL2; i++){
            integrador.principal.L2.escritura(i,integrador.principal.MEMORIA.lectura(j));
            j++;
        }
    }
    
    
    /**
     * Este método ejecuta una operación de lectura sobre la memoria.
     * @param direccion, es la dirección de memoria que se quiere recuperar. 
     * @return regresa el valor almancenado en la dirección solicitada. 
     */
    public String lectura(int direccion){
        return this.vector[direccion];
    }
    
    /**
     * Este método ejecuta la operación escritura sobre la memoria de la computadora. 
     * @param direccion, es la dirección donde se va almacenar el dato
     * @param valor, es el valor del dato que se quiere almacenar. 
     */
    public void escritura(int direccion, String valor){
        this.vector[direccion]=valor;
    }
    
    /**
     * Este método es solo para escribir en la línea de comando los valores internos de la memoria. 
     */
    public void muestraMemoria(){
        int i;
        String salida;
        for (i=0; i<this.direccionMaxima; i++){
            salida="["+i+"]="+this.vector[i];
            System.out.println(salida);
        }
    }
    
}
