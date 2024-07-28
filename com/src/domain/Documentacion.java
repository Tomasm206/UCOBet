package domain;

public class Documentacion {
    public void documentacion() {
        System.out.println("""
                                        
                ╔╗─╔╦═══╦═══╦══╗───╔╗                               
                ║║─║║╔═╗║╔═╗║╔╗║──╔╝╚╗  === Funciones de Gerente ===                              === Funciones del Usuario ===
                ║║─║║║─╚╣║─║║╚╝╚╦═╩╗╔╝  Al ingresar como gerente este tendrá la posibilidad       Al ingresar como usuario este deberá ingresar sus
                ║║─║║║─╔╣║─║║╔═╗║║═╣║   de definir la fecha del sorteo y restringir un numero.    datos y seleccionar un numero para el sorteo.                                                    
                ║╚═╝║╚═╝║╚═╝║╚═╝║║═╣╚╗
                ╚═══╩═══╩═══╩═══╩══╩═╝  Cada actor obtendrá un menú con todo lo descrito anteriormente al ingresar""");
        System.out.print("Presiona cualquier tecla para ingresar...");
        new java.util.Scanner(System.in).nextLine();
    }
}
