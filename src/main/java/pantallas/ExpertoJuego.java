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
package pantallas;

import juego.JuegoViborita;
import juego.Parametros;
import juego.herramientasGui.BufferTeclas;
import juego.herramientasGui.ObservadorJuego;

/**
 *
 * @author juan
 */
public class ExpertoJuego {
    private JuegoViborita juego;
    private ObservadorJuego pantalla;
    
    public ExpertoJuego()
    {
        //parametros por defecto
        Parametros.getInstancia().setAltoBloques(20); //originalmente 24, 18
        Parametros.getInstancia().setAnchoBloques(15); 
        Parametros.getInstancia().setFrecuenciaJuego(800);
        Parametros.getInstancia().setNivelDificultad(8);
    }
    
    public void iniciarJuego()
    {
        //limpiar el buffer del teclado
        BufferTeclas.getInstancia().leerAccion();
        juego = new JuegoViborita();
        juego.setObservadorJuego(pantalla);
        juego.start();
    }
    public void setPantalla(ObservadorJuego p) {
        pantalla = p;
    }
    public void setNivelDificultad(int dificultad)
    {
        Parametros.getInstancia().setNivelDificultad(dificultad);
    }
    public void setTecla(int tecla)
    {
        BufferTeclas.getInstancia().setTecla(tecla);
    }
    public void pausar()
    {
        juego.pausar();
    }
    public void setParametros(boolean teclado, boolean alternativo, boolean sonido, boolean vibracion, boolean paredes, boolean hambrienta)
    {
        Parametros p = Parametros.getInstancia();
        p.setTeclado(teclado);
        p.setTecladoAlt(teclado);
        p.setSonido(sonido);
        p.setVibrar(vibracion);
        p.setParedes(paredes);
        p.setHambriento(hambrienta);
    }

    int getNivelDificultad() {
        return Parametros.getInstancia().getNivelDificultad();
    }
    
    
}