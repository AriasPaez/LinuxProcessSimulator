/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class Menu extends Thread {

    PCB pcb;
    Proceso process_aux;
    ArrayList<Proceso> cola_aux;

    public Menu(PCB pcb) {
        this.pcb = pcb;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true) {
            input = sc.nextLine();
            String[] parts = input.split(" ");
            if (parts[0].equals("Zombie")) {
                if (parts.length == 2) {
                    for (int i = 0; i < pcb.getCola_procesos().size(); i++) {
                        if (parts[1].equals("" + pcb.getCola_procesos().get(i).getPID())) {
                            pcb.getCola_procesos().get(i).setZombie(true);
                        }
                    }
                }
                

            }
            if (parts[0].equalsIgnoreCase("exit")) {
                
                System.exit(0);
            }
            if (parts[0].equalsIgnoreCase("sleep")) {
                if (parts.length == 2) {
                    for (int i = 0; i < pcb.getCola_procesos().size(); i++) {
                        if (parts[1].equals("" + pcb.getCola_procesos().get(i).getPID())) {
                            pcb.getCola_procesos().get(i).setStatus(statusProceso.Sleep.toString());
                            pcb.getStop_procesos().add(pcb.getCola_procesos().get(i));
                            pcb.getCola_procesos().remove(i);
                        }
                    }
                }

            }
            if (parts[0].equalsIgnoreCase("stopped")) {
                
                if (parts.length == 2) {
                    for (int i = 0; i < pcb.getCola_procesos().size(); i++) {
                        if (parts[1].equals("" + pcb.getCola_procesos().get(i).getPID())) {
                            pcb.getCola_procesos().get(i).setStatus(statusProceso.Stopped.toString());
                            pcb.getStop_procesos().add(pcb.getCola_procesos().get(i));
                            pcb.getCola_procesos().remove(i);
                        }
                    }
                }

            }
            if (parts[0].equalsIgnoreCase("ready")) {
                
                if (parts.length == 2) {
                    for (int i = 0; i < pcb.getStop_procesos().size(); i++) {
                        if (parts[1].equals("" + pcb.getStop_procesos().get(i).getPID())) {
                            pcb.getStop_procesos().get(i).setStatus(statusProceso.Ready.toString());
                            pcb.getCola_procesos().add(pcb.getStop_procesos().get(i));
                            pcb.getStop_procesos().remove(i);
                        }
                    }
                }

            }
            if (parts[0].equalsIgnoreCase("add")) {
                if (parts.length == 5) {
                    process_aux = new Proceso(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Long.parseLong(parts[3]), parts[4]);
                    cola_aux = pcb.cola_procesos;

                    for (int i = 0; i < cola_aux.size(); i++) {

                        if (parts[2].equals("" + cola_aux.get(i).getPID())) {

                            cola_aux.add(process_aux);
                            pcb.setCola_procesos(cola_aux);

                        }
                    }

                }

            }
            if (parts[0].equalsIgnoreCase("kill")) {
                if (parts.length == 2) {
                    for (int i = 0; i < pcb.cola_procesos.size(); i++) {
                        if (parts[1].equals("" + pcb.cola_procesos.get(i).getPID())) {
                            pcb.cola_procesos.get(i).setStatus(statusProceso.Ended.toString());
                            
                            pcb.cola_procesos.remove(i);
                        }
                    }
                    for (int i = 0; i < pcb.cola_procesos.size(); i++) {
                        if (parts[1].equals("" + pcb.cola_procesos.get(i).getPPID())) {
                            pcb.cola_procesos.get(i).setStatus(statusProceso.Zombie.toString());
                            
                            pcb.stop_procesos.add(pcb.cola_procesos.get(i));
                            pcb.cola_procesos.remove(i);
                        }
                    }
                    for (int i = 0; i < pcb.stop_procesos.size(); i++) {
                        if (parts[1].equals("" + pcb.stop_procesos.get(i).getPPID())) {
                            pcb.stop_procesos.get(i).setStatus(statusProceso.Zombie.toString());
                                                     
                        }
                    }
                }
            }

        }
    }

}
