package integrador;

public class principal {

    public static almacenamiento.memoria MEMORIA;
    public static almacenamiento.memoria L1;
    public static almacenamiento.memoria L2;
    public static procesador1.kernel p1;
    public static procesador2.kernel p2;

    
    public static void main(String[] args) {
        
        instanciar();
        
        p1.principal();
        p2.principal();

    }
    
    public static void instanciar(){
        
        MEMORIA = new almacenamiento.memoria(50);
        L1 = new almacenamiento.memoria(10);
        L2 = new almacenamiento.memoria(10);
        p1 = new procesador1.kernel(10);
        p2 = new procesador2.kernel(30);
        MEMORIA.inicioMemoria();
        MEMORIA.enlaceCodigo();
        
    }

}
