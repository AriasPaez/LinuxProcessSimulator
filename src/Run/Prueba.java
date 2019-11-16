package Run;

import model.Menu;
import model.PCB;

/**
 *
 * @author joham
 */
public class Prueba {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) throws InterruptedException {

        System.out.println(""
                +ANSI_WHITE_BACKGROUND + ANSI_BLACK + "++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"+ANSI_RESET
                +ANSI_WHITE_BACKGROUND + ANSI_BLACK + "+                   Bienvenid@s Al                   +\n"+ANSI_RESET
                +ANSI_WHITE_BACKGROUND + ANSI_BLACK + "+      Simulador de Estados de Procesos en Linux     +\n"+ANSI_RESET
                +ANSI_WHITE_BACKGROUND + ANSI_BLACK + "+                Sistemas Operativos                 +\n"+ANSI_RESET
                +ANSI_WHITE_BACKGROUND + ANSI_BLACK + "+                       2019                         +\n"+ANSI_RESET
                +ANSI_WHITE_BACKGROUND + ANSI_BLACK + "+  Universidad Pedagógica y Tecnológica de Colombia  +\n"+ANSI_RESET
                +ANSI_WHITE_BACKGROUND + ANSI_BLACK + "+                        UPTC                        +\n"+ANSI_RESET
                +ANSI_WHITE_BACKGROUND + ANSI_BLACK + "++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n"+ANSI_RESET);

        PCB pcb = new PCB();
        Menu menu = new Menu(pcb);

        menu.start();
        pcb.start();

    }

}
