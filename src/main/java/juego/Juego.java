/*
 * Copyright (C) 2008 Juan Alberto López Cavallotti
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; version 2
 * of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package juego;

/**
 * Este es el experto que va a llevar la logica del juego.
 * @author juan
 */
public abstract class Juego extends Thread {
    private long frecuencia; //determina la frecuencia en milisegundos del game loop
    private boolean pausado; //determina si el juego esta pausado
    private boolean activo; //determina si el juego sigue funcionando
    
    
    public void run()
    {
        
        while(true) //game loop
        {
            if (!activo)
                break;
            leerEntrada();
            if (!pausado)
            {
                procesarDatos(); 
                actualizarEstadoJuego();
                aplicarCondicionesJuego();
            }
            notificarObservadores(); //esto sigue pasando
            try {
            sleep(frecuencia);
            } catch(InterruptedException ex)
            {
                ex.printStackTrace();
                break;
            }
        }
        //System.out.println("Se ha terminado el juego");
    }
    
    //metodos estandar de los juegos
    
    /**
     * A implementar: se realizan las acciones necesarias para obtener la entrada
     * por parte del usuario.
     */
    protected abstract void leerEntrada();
    /**
     * A implementar: este metodo deberia ser quien dice que se va a hacer a partir
     * de la entrada leida.
     */
    protected abstract void procesarDatos();
    /**
     * A implementar: este metodo es el encargado de pasar de un estado del juego a otro
     */
    protected abstract void actualizarEstadoJuego();
    /**
     * A implementar: aca se calculan los puntajes y se comprueba si se ha terminado el juego.
     */
    protected abstract boolean aplicarCondicionesJuego();
    /**
     * Por ultimo se le comunica a los observadores el estado del juego.
     */
    protected abstract void notificarObservadores();

    protected long getFrecuencia() {
        return frecuencia;
    }

    protected void setFrecuencia(long frecuencia) {
        this.frecuencia = frecuencia;
    }

    protected boolean isPausado() {
        return pausado;
    }

    protected void setPausado(boolean pausado) {
        this.pausado = pausado;
    }
    
    public void tooglePausa()
    {
        setPausado(!isPausado());
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
