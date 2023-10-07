/**
 * Código generado para el curso de 
 * "Introducción  a los microprocesadores"
 * Laboratorio de Cómputo de Alto Rendimiento 
 * Universidad Veracruzana
 * @version 22 de octubre de 2017
 * @author Dr. Alfredo Cristóbal Salas.
 */
package procesador2;

/**
 * Esta clase implementa el diseño de una unidad aritmética lógica de un 
 * microprocesador genérico simple. 
 */
public class unidadAritmeticaLogica {
    /** operacion, muestra la operación que está en ejecución. Esta operación 
     * está ya en número decimal. 
     */
    int operacion;
    /** registroEntrada1, es el registro de entrada 1 de la ALU. */
    String registroEntrada1;
    /** registroEntrada1, es el registro de entrada 2 de la ALU. */
    String registroEntrada2;
    
    String registroEntrada3;
    /** acumulador, es el registro donde se almacena el resultado de la operación
     * recién ejecutada. 
     */
    String acumulador;
    /** banderas, es el vector que almacenan las banderas del sistema, los valores son:
     * 0-InstruccionLista, 1-Dato1Listo, 2-Dato2Listo, 3-ResultadoListo
     */
    int[] banderas=new int[5];
    
    /**
     * este método constructor es para iniciar los atributos de la clase. 
     */
    public unidadAritmeticaLogica(){
        acumulador="";
        operacion=0;
        registroEntrada1="";
        registroEntrada2="";
    }
    
    
    /**
     * Este método muestra solamente los valores de los atributos de la clase 
     * con fines de depuración del sistema. 
     */
    public void muestraALU(){
        System.out.println("operacion="+this.operacion);
        System.out.println("registroEntrada1="+this.registroEntrada1);
        System.out.println("registroEntrada2="+this.registroEntrada2);
        System.out.println("registroEntrada3="+this.registroEntrada3);
        System.out.println("acumulador="+this.acumulador);
        System.out.println("Banderas="+this.banderas[0]+","+this.banderas[1]+","+this.banderas[2]+","+this.banderas[3]);
    }
    
    /**
     * Es una de las operaciones de la ALU. La operación es la SUMA de los elementos. 
     * @param a, es el primero valor de la operación.
     * @param b, es el segundo valor de la operación.
     * @return regresa el valor de la suma de los elementos.
     */
    public String ecuacion (String a, String b, String c){
        int tmp1=Integer.parseInt(a,2);
        int tmp2=Integer.parseInt(b,2);
        int tmp3=Integer.parseInt(c,2);
       
        System.out.println("a: " + tmp1 + ", b: " + tmp2 + ", c: " + tmp3);
        
        int discriminante = (tmp2 * tmp2) - 4 * tmp1 * tmp3;
        
        System.out.println("discriminante " + discriminante);
        
        if (discriminante > 0) {
            // Dos soluciones reales
            int x1 = (int)(-tmp2 + Math.sqrt(discriminante)) / (2 * tmp1);
            int x2 = (int )(-tmp2 - Math.sqrt(discriminante)) / (2 * tmp1);
            
            
            
            return "x1 = " + x1 +", x2 = " + x2;
            
        } else if (discriminante == 0) {
            // Una solución real
            int x = -tmp2 / (2 * tmp1);
            
            return "x = " + x;
        }else{
            
            return "";
        }
        
    }
    
    
    /**
     * Es una de las operaciones de la ALU. La operación es la MULTIPLICACION de los elementos. 
     * @param a, es el primero valor de la operación.
     * @param b, es el segundo valor de la operación.
     * @return regresa el valor de la multiplicación de los elementos.
     */
    public int multiplicacion (String a, String b){
        int tmp1=Integer.parseInt(a,2);
        int tmp2=Integer.parseInt(b,2);
        
        return tmp1*tmp2;
    }
    
    /**
     * Este método es otra operación de la ALU, busca mover un dato de un
     * registro a otro registro.
     * @return regresa el valor a mover al otro registro. 
     */
    public int mover(){

    int valor;
    
    valor=Integer.parseInt(procesador2.kernel.ALU.registroEntrada1,2);
    return valor;
    }
    
    
    /**
     * Este método busca realizar la ejecución de una operación con los datos en los
     * registros de entrada; Por cada instrucción del sistema es necesario agregar 
     * otro CASE dentro de este método. 
     * @return regresa si el microprocesador debe detenerse o continuar. 
     */
    public int ejecutarInstruccion(){
        int salida=0;
        
        switch(this.operacion){
            case 0:
                salida=0;
                break;
            case 1:
                this.acumulador=this.ecuacion(this.registroEntrada1, this.registroEntrada2, this.registroEntrada3);
                this.banderas[3]=1;
                salida=0;
                break;
            /*case 2:
                this.acumulador=this.multiplicacion(this.registroEntrada1, this.registroEntrada2);
                this.banderas[3]=1;
                salida=0;
                break;
            case 3:
                this.acumulador=this.mover();
                this.banderas[3]=1;
                salida=0;
                break;*/
            case 15:
                salida=1;
                break;
        }
        return salida;
    }
    
}
