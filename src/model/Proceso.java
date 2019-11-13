/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joham
 */
public class Proceso {//extends Thread {

    private int PID;
    private int PPID;
    private String status;
    private String command;
    private boolean ejecucion;
    private long timeExecution;
    private long time_running;
    private boolean zombie = false;

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    //private cronometro crono;
    public Proceso(int PID, int PPID, long timeExecution, String command) {
        this.PID = PID;
        this.PPID = PPID;
        this.status = statusProceso.Ready.toString();
        this.timeExecution = timeExecution;
        this.command = command;
        ejecucion = true;
        time_running = 0;
        //crono=new cronometro(timeExecution);
    }

    public Proceso(int PID, int PPID) {
        this.PID = PID;
        this.PPID = PPID;
        this.status = statusProceso.Running.toString();
        this.command = "---";
        ejecucion = false;

    }

    public void stopProceso() {
        status = statusProceso.Stopped.toString();
        ejecucion = false;
    }

    public void restartProceso() {
        ejecucion = true;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getPPID() {
        return PPID;
    }

    public void setPPID(int PPID) {
        this.PPID = PPID;
    }

    public String getStatus() {
        if (status.equals(statusProceso.Stopped.toString())) {
            return ANSI_RED + status + ANSI_RESET;
        } else if (status.equals(statusProceso.Ended.toString())) {
            return ANSI_GREEN + status + ANSI_RESET;
        }else if (status.equals(statusProceso.Zombie.toString())) {
            return ANSI_YELLOW + status + ANSI_RESET;
        }else if (status.equals(statusProceso.Sleep.toString())) {
            return ANSI_CYAN + status + ANSI_RESET;
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(boolean ejecucion) {
        this.ejecucion = ejecucion;
    }

    public long getTimeExecution() {
        return timeExecution;
    }

    public void setTimeExecution(long timeExecution) {
        this.timeExecution = timeExecution;
    }

    public long getTime_running() {
        return time_running;
    }

    public void setTime_running(long time_running) {
        this.time_running = time_running;
    }

    public boolean isZombie() {
        return zombie;
    }

    public void setZombie(boolean zombie) {
        this.zombie = zombie;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
