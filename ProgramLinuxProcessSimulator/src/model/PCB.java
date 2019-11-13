/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joham
 */
public class PCB extends Thread {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_RESET = "\u001B[0m";

    ArrayList<Proceso> cola_procesos = new ArrayList<Proceso>();
    ArrayList<Proceso> total_procesos = new ArrayList<Proceso>();
    ArrayList<Proceso> stop_procesos = new ArrayList<Proceso>();
    boolean finalizar_programa = true;
    Proceso proceso_init;
    Proceso proceso_running;

    public PCB() {
        proceso_init = new Proceso(1, 0);
        cola_procesos.add(new Proceso(2, 1, 5000, "Firefox"));
        cola_procesos.add(new Proceso(3, 1, 8000, "Reloj"));
        cola_procesos.add(new Proceso(4, 1, 4000, "Correo"));
        cola_procesos.add(new Proceso(5, 1, 7000, "SSH"));
        cola_procesos.add(new Proceso(6, 1, 10000, "Calendar"));

//        for(int i=0;i<cola_procesos.size();i++){
//            System.out.println("proceso "+cola_procesos.get(i).getPID());
//        }
    }

    @Override
    public void run() {

        while (finalizar_programa) {

            try {
                //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

                System.out.println("------------------------------------------------");
                System.out.println(ANSI_BLACK + ANSI_WHITE_BACKGROUND + "PID\tPPID\tStatus \tTimeExe\tTimeRun\tCommand" + ANSI_RESET);
                System.out.println("------------------------------------------------");

                System.out.println(proceso_init.getPID() + "\t"
                        + proceso_init.getPPID() + "\t"
                        + proceso_init.getStatus() + "\t"
                        + proceso_init.getTimeExecution() + "\t"
                        + proceso_init.getTime_running() + "\t"
                        + proceso_init.getCommand());

                for (int i = 0; i < cola_procesos.size(); i++) {
                    proceso_running = cola_procesos.get(i);
                    System.out.println(proceso_running.getPID() + "\t"
                            + proceso_running.getPPID() + "\t"
                            + proceso_running.getStatus() + "\t"
                            + proceso_running.getTimeExecution() + "\t"
                            + proceso_running.getTime_running() + "\t"
                            + proceso_running.getCommand());

                }
                for (int i = 0; i < stop_procesos.size(); i++) {
                    proceso_running = stop_procesos.get(i);
                    System.out.println(proceso_running.getPID() + "\t"
                            + proceso_running.getPPID() + "\t"
                            + proceso_running.getStatus() + "\t"
                            + proceso_running.getTimeExecution() + "\t"
                            + proceso_running.getTime_running() + "\t"
                            + proceso_running.getCommand());

                }
                if (cola_procesos.size() > 0) {

                    proceso_running = cola_procesos.get(0);
                    if (proceso_running.getStatus().equals(ANSI_GREEN + statusProceso.Ended.toString() + ANSI_RESET)) {

                        cola_procesos.remove(proceso_running);

                    } else if (proceso_running.getStatus().equals(statusProceso.Ready.toString())) {
                        proceso_running.setStatus(statusProceso.Running.toString());

                    } else if (proceso_running.getStatus().equals(statusProceso.Running.toString())) {

                        if (proceso_running.getTime_running() < proceso_running.getTimeExecution()) {
                            proceso_running.setTime_running(proceso_running.getTime_running() + 1000);
                        } else {
                            if (proceso_running.isZombie()) {
                                proceso_running.setStatus(statusProceso.Zombie.toString());
                                Proceso proc_temp = cola_procesos.remove(0);
                                stop_procesos.add(proc_temp);
                            } else {
                                proceso_running.setStatus(statusProceso.Ended.toString());
                                for (int i = 0; i < cola_procesos.size(); i++) {
                                    if (proceso_running.getPID()==cola_procesos.get(i).getPPID()) {
                                        cola_procesos.get(i).setStatus(statusProceso.Zombie.toString());
                                    }
                                }
                                for (int i = 0; i < stop_procesos.size(); i++) {
                                    if (proceso_running.getPID()==stop_procesos.get(i).getPPID()) {
                                        stop_procesos.get(i).setStatus(statusProceso.Zombie.toString());
                                    }
                                }

                            }

                        }

                    }

                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PCB.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public ArrayList<Proceso> getCola_procesos() {
        return cola_procesos;
    }

    public void setCola_procesos(ArrayList<Proceso> cola_procesos) {
        this.cola_procesos = cola_procesos;
    }

    public ArrayList<Proceso> getTotal_procesos() {
        return total_procesos;
    }

    public void setTotal_procesos(ArrayList<Proceso> total_procesos) {
        this.total_procesos = total_procesos;
    }

    public ArrayList<Proceso> getStop_procesos() {
        return stop_procesos;
    }

    public void setStop_procesos(ArrayList<Proceso> stop_procesos) {
        this.stop_procesos = stop_procesos;
    }

    public boolean isFinalizar_programa() {
        return finalizar_programa;
    }

    public void setFinalizar_programa(boolean finalizar_programa) {
        this.finalizar_programa = finalizar_programa;
    }

    public Proceso getProceso_init() {
        return proceso_init;
    }

    public void setProceso_init(Proceso proceso_init) {
        this.proceso_init = proceso_init;
    }

    public Proceso getProceso_running() {
        return proceso_running;
    }

    public void setProceso_running(Proceso proceso_running) {
        this.proceso_running = proceso_running;
    }

}
