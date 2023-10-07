/**
 * Código generado para el curso de
 * "Introducción  a los microprocesadores"
 * Laboratorio de Cómputo de Alto Rendimiento
 * Universidad Veracruzana
 *
 * @version 22 de octubre de 2017
 * @author Dr. Alfredo Cristóbal Salas.
 */
package procesador1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase es para definir las acciones que realiza una unidad de control de
 * un microprocesador simple sin modificaciones de alto rendimiento.
 */
public class unidadControl {

    /**
     * simula el registro de instrucción del microprocesador.
     */
    String registroInstruccion = new String();
    /**
     * es el registro que sabe la instrucción que se está ejecutando.
     */
    int contadorPrograma;
    /**
     * esta variable es parte del decodificador y almacena la instrucción a
     * ejecutarse.
     */
    String decodificarInstruccion = new String();
    /**
     * esta variable es parte del decodificador y almacena la dirección del
     * primer dato de la instruccion.
     */
    String decodificarDato1 = new String();
    /**
     * esta variable es parte del decodificador y almacena la dirección del
     * segundo dato de la instruccion.
     */
    String decodificarDato2 = new String();
    /**
     * esta variable es parte del decodificador y almacena la dirección del
     * tercer dato de la instruccion.
     */
    String decodificarDato3 = new String();
    /**
     * es la variable que contiene la dirección de donde se debe almacenar el
     * resultado.
     */
    String decodificarResultado = new String();

    /**
     * Esta es el método constructor que se encarga de iniciar los atributos de
     * la clase.
     */
    public unidadControl() {
        registroInstruccion = "";
        contadorPrograma = 0;
        decodificarInstruccion = "";
        decodificarDato1 = "";
        decodificarDato2 = "";
        decodificarDato3 = "";
        decodificarResultado = "";
    }

    /**
     * Este método muestra el contenido de los atributos de la clase y las
     * imprime en la línea de comandos.
     */
    public void muestraUC() {
        System.out.println("Registro de Instruccion=" + this.registroInstruccion);
        System.out.println("contadorPrograma=" + this.contadorPrograma);
        System.out.println("decodificarInstruccion=" + this.decodificarInstruccion);
        System.out.println("decodificarDato1=" + this.decodificarDato1);
        System.out.println("decodificarDato2=" + this.decodificarDato2);
        System.out.println("decodificarDato3=" + this.decodificarDato3);
        System.out.println("decodificarResultado=" + this.decodificarResultado);
    }

    /**
     * Este método recupera la instrucción siguiente apuntada por el "contador
     * de programa" y lo pasa al registro de instrucción como lo indica la
     * teoría del concepto de microprocesador.
     */
    public void instructionFetch() {
        procesador1.kernel.UC.registroInstruccion = integrador.principal.MEMORIA.lectura(this.contadorPrograma);
        procesador1.kernel.UC.registroInstruccion = procesador1.kernel.UC.registroInstruccion.replace("-", "");
    }

    /**
     * Este método recupera de memoria los datos necesarios para completar la
     * instrucción en funciones y los valores son pasados a la ALU para que sean
     * procesados.
     */
    public void dataFetch() {
        int tmp;

        tmp = Integer.parseInt(this.decodificarDato1, 2);
        procesador1.kernel.ALU.registroEntrada1 = integrador.principal.MEMORIA.lectura(tmp);
        procesador1.kernel.ALU.registroEntrada1 = procesador1.kernel.ALU.registroEntrada1.replace("-", "");
        procesador1.kernel.ALU.banderas[1] = 1;

        tmp = Integer.parseInt(this.decodificarDato2, 2);
        procesador1.kernel.ALU.registroEntrada2 = integrador.principal.MEMORIA.lectura(tmp);
        procesador1.kernel.ALU.registroEntrada2 = procesador1.kernel.ALU.registroEntrada2.replace("-", "");
        procesador1.kernel.ALU.banderas[2] = 1;

        tmp = Integer.parseInt(this.decodificarDato3, 2);
        procesador1.kernel.ALU.registroEntrada3 = integrador.principal.MEMORIA.lectura(tmp);
        procesador1.kernel.ALU.registroEntrada3 = procesador1.kernel.ALU.registroEntrada3.replace("-", "");
        procesador1.kernel.ALU.banderas[2] = 1;
    }

    /**
     * Este método sirve para realizar las acciones del decodificador de
     * instrucciones de la memoria.
     */
    public void decode() {
        this.decodificarInstruccion = this.registroInstruccion.substring(0, 8);
        this.decodificarDato1 = this.registroInstruccion.substring(8, 16);
        this.decodificarDato2 = this.registroInstruccion.substring(16, 24);
        this.decodificarDato3 = this.registroInstruccion.substring(24, 32);
        int tmp = Integer.parseInt(this.registroInstruccion.substring(24, 32),2);
        tmp = tmp + 1;
        this.decodificarResultado = Integer.toBinaryString(tmp);
        procesador1.kernel.ALU.operacion = Integer.parseInt(this.decodificarInstruccion, 2);
        procesador1.kernel.ALU.banderas[0] = 1;
    }

    /**
     * Este método implementa la ejecución de la instrucción de una instrucción
     * en el microprocesador.
     *
     * @return salida, es una variable que sirve para detectar cuando el
     * microprocesador debe terminar.
     */
    public int execute() {
        int salida;
        salida = procesador1.kernel.ALU.ejecutarInstruccion();
        return salida;
    }

    /**
     * Este método almacena el resultado de la ejecución de una instrucción en
     * la memoria donde diga el Registro decodificador.
     */
    public void store() {
        String tmp;
        int direccion;
        String dato;
        boolean x1andx2;

        if ((procesador1.kernel.ALU.operacion != 15) && (procesador1.kernel.ALU.operacion != 0)) {
            tmp = procesador1.kernel.ALU.acumulador;
            direccion = Integer.parseInt(procesador1.kernel.UC.decodificarResultado, 2);

            String patron = "x1[^\\d]*";

            Pattern pattern = Pattern.compile(patron);
            Matcher matcher = pattern.matcher(tmp);
            
            if(matcher.find()){
                
                integrador.principal.MEMORIA.escritura(direccion, tmp);
                
            }else{
                integrador.principal.MEMORIA.escritura(direccion, tmp);
            }

            
            
            procesador1.kernel.UC.contadorPrograma++;
        }
    }

}
