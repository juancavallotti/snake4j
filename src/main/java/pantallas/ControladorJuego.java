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

import juego.herramientasGui.ObservadorJuego;

/**
 *
 * @author juan
 */
public class ControladorJuego {
    private static ControladorJuego instancia = new ControladorJuego();
    private ExpertoJuego ep;
    
    private ControladorJuego() //singleton
    {
        //crear un administrador de juego
        ep = new ExpertoJuego();
    }
    public static ControladorJuego getInstancia()
    {
        return instancia;
    }
    public void setDificultad(int dif)
    {
        ep.setNivelDificultad(dif);
    }
    public void setPantalla(ObservadorJuego p)
    {
        ep.setPantalla(p);
    }
    public void iniciarPartida()
    {
        ep.iniciarJuego();
    }
    public void setTecla(int tecla)
    {
        ep.setTecla(tecla);
    }
    public void pausar()
    {
        ep.pausar();
    }
    public void setParametros(boolean teclado, boolean alternativo, boolean sonido, boolean vibracion, boolean paredes, boolean hambrienta)
    {
        ep.setParametros(teclado, alternativo, sonido, vibracion, paredes, hambrienta);
    }

    int getDificultad() {
        return ep.getNivelDificultad();
    }
}
