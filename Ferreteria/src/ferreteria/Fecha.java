
package ferreteria;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Esta clase muestra la fecha actual
 * @author Luis Galicia
 * @version 1.0
 */
public class Fecha {
    private int dia;
    private int mes;
    private int anio;
    private int hora;
    private int minuto;
    private Calendar calendario = new GregorianCalendar(); 
    
    public Fecha() {
        
    }
    
    public int getDia() {
        dia = (calendario.get(Calendar.DATE));
        return dia;
    }
    /**
     * Modifica el día
     * @param dia nuevo 
     */
    public void setDia(int dia) {
        this.dia = dia;
    }
    /**
     * Devuelve el mes
     * @return mes 
     */
    public int getMes() {
        mes = (calendario.get(Calendar.MONTH));
        return mes;
    }
    /**
     * Modifica el mes
     * @param mes nuevo
     */
    public void setMes(int mes) {
        this.mes = mes;
    }
    /**
     * Devuelve el Año
     * @return año
     */
    public int getAnio() {
        anio = (calendario.get(Calendar.YEAR));
        return anio;
    }
    /**
     * Modifica el año
     * @param anio nuevo
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }
    /**
     * Devuelve la hora actual
     * @return hora
     */
    public int getHora() {
        hora = (calendario.get(Calendar.HOUR_OF_DAY));
        return hora;
    }
    /**
     * Modifica la hora
     * @param hora nueva
     */
    public void setHora(int hora) {
        this.hora = hora;
    }
    /**
     * Devuelve el mes actual
     * @return hora
     */
    public int getMinuto() {
        minuto = (calendario.get(Calendar.MINUTE));
        return minuto;
    }
    /**
     * Modifica los minutos
     * @param minuto nuevos
     */
    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
    
}
