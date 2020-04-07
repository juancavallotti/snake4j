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
 * @author Juan
 */
public class ContadorCiclos implements ObservadorCiclo {
    
    private int cuenta;
    private int ultimaCuenta;
    
    private static ContadorCiclos instancia = new ContadorCiclos();

    public static ContadorCiclos getInstancia() {
        return instancia;
    }
    
    private ContadorCiclos() {
        cuenta = 0;
        ultimaCuenta = 0;
    }
    
    public int getCuenta() {
        return cuenta;
    }
    
    public int getUltimaCuenta() {
        return ultimaCuenta;
    }
    
    public void cicloCompleto() {
        cuenta++;
    }
    
    public void reset() {
        ultimaCuenta = cuenta;
        cuenta = 0;
    }
}