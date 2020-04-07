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

package juego.herramientasGui;

/**
 *
 * @author juan
 */
public class FabricaBloques {
    private static FabricaBloques instancia = new FabricaBloques();
    
    //aplicar el patron flyweight
    private BloqueFondo instanciaFondo;
    private BloqueLimite instanciaLimite;
    private BloqueVibora instanciaVibora;

    private FabricaBloques()
    {
        instanciaFondo = new BloqueFondo();
        instanciaLimite = new BloqueLimite();
        instanciaVibora = new BloqueVibora();
    }
    public static FabricaBloques getInstancia()
    {
        return instancia;
    }
    public Bloque fabricarBloqueFondo()
    {
        return instanciaFondo;
    }
    public Bloque fabricarBloqueVibora()
    {
        return instanciaVibora;
    }
    public Bloque fabricarBloqueLimite()
    {
        return instanciaLimite;
    }
    public Bloque fabricarBloqueCabezaVibora(juego.BloqueVibora original)
    {
        BloqueCabezaVibora ret = new BloqueCabezaVibora();
        ret.setDireccion(original.getDireccion());
        return ret;
    }
    public Bloque fabricarBloqueCola(juego.BloqueVibora original)
    {
        BloqueCola ret = new BloqueCola();
        ret.setDireccion(original.getDireccion());
        return ret;
    }
}
