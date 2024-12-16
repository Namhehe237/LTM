package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CalClient {
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        CalInf cal =(CalInf) Naming.lookup("rmi://localhost/cal11");
        System.out.println();
        System.out.println("sum : "+ cal.add(100, 200));
    }
}
