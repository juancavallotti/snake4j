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
public interface LectorAcciones {
    
    public static final int SIN_ACCION = 0;
    public static final int ARRIBA = 1;
    public static final int ABAJO = 2;
    public static final int DERCHA = 3;
    public static final int IZQUIERDA = 4;
    public static final int PAUSA = 5;
    public static final int FIN = 6;
    
    
    /**
     * Debe devolver codigos de accion que le indiquen al juego que hacer.
     * @return uno de los codigos de accion declarados en esta interface.
     */
    public int leerAccion();
}
