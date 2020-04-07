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
 * Esta clase facilita la creacion de niveles con distintos obstaculos para la viborita.
 * @author juan
 */
public class MapaNivel {
    
    private Bloque[][] bloques;
    
    /**
     * Obtener el mapa creado a traves de los distintos metodos de esta clase.
     * @return Devuelve un arreglo de bloques 
     */
    public Bloque[][] getBloques()
    {
        return bloques;
    }
    
    /**
     * Inicializar el mapa vacio, luego se puede llamar a metodos para dibujar sobre el.
     */
    public MapaNivel()
    {
        //crear un mapa determinado por el ancho y el alto del juego
        int alto = Parametros.getInstancia().getAltoBloques();
        int ancho = Parametros.getInstancia().getAnchoBloques();
        
        bloques = new Bloque[alto][ancho];
    }
}