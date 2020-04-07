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
 *
 * @author juan
 */
public class BloqueVibora implements Bloque{
    
    public static final int ARRIBA = 1;
    public static final int ABAJO = 2;
    public static final int IZQUIERDA = 3;
    public static final int DERECHA = 4;
    
    //es la direccion que tiene ese fragmento de viborita
    private int direccion;
    
    /**
     * Obtiene la direccion para la cual tiene que ir el fragmento de vibora
     * 
     * @return devuelve enteros que representan la direccion para la cual va el segmento de vibora.
     */
    public int getDireccion()
    {
        return direccion;
    }
    /**
     * Establecer la direccion que tendra ese bloque de viborita
     * 
     * @param dir es quien dice que direccion tomara la vibora 
     */
    public void setDireccion(int dir)
    {
        direccion = dir;
    }
}