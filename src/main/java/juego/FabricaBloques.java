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
public class FabricaBloques {
    
    private static FabricaBloques instancia = new FabricaBloques();
    
    //tiene mapeados los 4 tipos de bloques de viboritas
    BloqueVibora[] bloquesPorDireccion;
    BloqueHuevo premio;
    
    private FabricaBloques() //singleton
    {
        bloquesPorDireccion = new BloqueVibora[4];
        //crear los 4 bloques fundamentales, patron FlyWeight
        bloquesPorDireccion[BloqueVibora.ABAJO -1] = new BloqueVibora();
        bloquesPorDireccion[BloqueVibora.ABAJO -1].setDireccion(BloqueVibora.ABAJO);        
        bloquesPorDireccion[BloqueVibora.ARRIBA -1] = new BloqueVibora();
        bloquesPorDireccion[BloqueVibora.ARRIBA -1].setDireccion(BloqueVibora.ARRIBA);        
        bloquesPorDireccion[BloqueVibora.DERECHA -1] = new BloqueVibora();
        bloquesPorDireccion[BloqueVibora.DERECHA -1].setDireccion(BloqueVibora.DERECHA);        
        bloquesPorDireccion[BloqueVibora.IZQUIERDA -1] = new BloqueVibora();
        bloquesPorDireccion[BloqueVibora.IZQUIERDA -1].setDireccion(BloqueVibora.IZQUIERDA);
        premio = new BloqueHuevo();
    }
    
    public static FabricaBloques getInstancia()
    {
        return instancia;
    }
    
    /**
     * Obtener la instancia del bloque de vibora correspondiente segun la direccion
     * @return el bloque necesario.
     */
    public BloqueVibora fabricarBloqueVibora(int direccion)
    {
        return bloquesPorDireccion[direccion -1];
    }
    public BloqueHuevo fabricarPremio()
    {
        return premio;
    }
    
}
