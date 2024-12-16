package RMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalServer {
    public static void main(String[] args)throws Exception {
        LocateRegistry.createRegistry(1099);
        Calculator calculator = new Calculator();
        Naming.rebind("rmi://localhost/cal11", calculator);
        System.out.println("success");
    }
}
