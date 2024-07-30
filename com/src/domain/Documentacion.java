package domain;

public class Documentacion {
    public void documentacion() {
        System.out.println("""
                ============================================================ BIENVENIDO A UCOBET ============================================================
                ╔╗─╔╦═══╦═══╦══╗───╔╗   === Funciones de Gerente ===                              === Funciones del Usuario ===
                ║║─║║╔═╗║╔═╗║╔╗║──╔╝╚╗  Al ingresar como gerente este tendrá la posibilidad       Al ingresar como usuario este podrá ingresar sus
                ║║─║║║─╚╣║─║║╚╝╚╦═╩╗╔╝  de definir fecha, restringir numero, elegir numero        datos y seleccionar un numero para el sorteo, la fecha
                ║║─║║║─╔╣║─║║╔═╗║║═╣║   y realizar sorteo.                                        del sorteo ya tiene que esat definida por el gerente.
                ║╚═╝║╚═╝║╚═╝║╚═╝║║═╣╚╗
                ╚═══╩═══╩═══╩═══╩══╩═╝  Cada actor obtendrá un menú con todo lo descrito anteriormente al ingresar a la aplicacion.
                =============================================================================================================================================""");
        System.out.print("Presiona cualquier tecla para ingresar...");
        new java.util.Scanner(System.in).nextLine();
    }
}
