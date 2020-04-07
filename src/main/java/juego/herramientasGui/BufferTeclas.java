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

import java.util.Vector;
import juego.LectorAcciones;
import juego.Parametros;

/**
 *
 * @author juan
 */
public class BufferTeclas  implements LectorAcciones {
    
    private Vector colaTeclas;
    
    private static BufferTeclas instancia = new BufferTeclas();
    
    
    /**
     * Leer con lectura destructiva la tecla que se ha presionado.
     * @return el codgo de accion en lenguaje de negocio, esto es una de las constantes definidas en la interfaz LectorAcciones
     */
    public int leerAccion() {
        Integer buferInt;
        int bufer;
        if (colaTeclas.size() > 0)
        {
            buferInt = (Integer) colaTeclas.firstElement();
            bufer = buferInt.intValue();
            //lectura destructiva
            colaTeclas.removeElementAt(0);
        } else {
            bufer = 0;
        }
            
        Parametros parm = Parametros.getInstancia();
        
        //System.out.println("La tecla presionada es "+tecla);
        
        
        //si estan desactivadas las teclas
        if (!parm.isTeclado() && !parm.isTecladoAlt())
            return SIN_ACCION;
        
        
        if (bufer == 0)
            return SIN_ACCION;
        else
        {
            return decodificarTecla(bufer);
        }
    }
    
    private BufferTeclas() //singleton
    {
        colaTeclas = new Vector(4);
    }
    public static BufferTeclas getInstancia()
    {
        return instancia;
    }
    
    public void vaciar() {
        colaTeclas.removeAllElements();
    }
    
    /**
     * Convertir la tecla del lenguaje de presentacion al lenguaje de negocio.
     * @param tecla es el codigo de tecla presionada.
     * @return el codigo de tecla definido por el lenguaje de negocio.
     */
    private int decodificarTecla(int tecla) {
        int t_arriba = Parametros.getInstancia().getTeclaArriba();
        int t_abajo = Parametros.getInstancia().getTeclaAbajo();
        int t_izquierda = Parametros.getInstancia().getTeclaIzquierda();
        int t_derecha = Parametros.getInstancia().getTeclaDerecha();
        int t_pausa = Parametros.getInstancia().getTeclaPausa();
        int t_salir = Parametros.getInstancia().getTeclaSalir();
        
        int t_arribaAlt = Parametros.getInstancia().getTeclaArribaAlt();
        int t_abajoAlt = Parametros.getInstancia().getTeclaAbajoAlt();
        int t_izquierdaAlt = Parametros.getInstancia().getTeclaIzquierdaAlt();
        int t_derechaAlt = Parametros.getInstancia().getTeclaDerechaAlt();


        boolean teclas = Parametros.getInstancia().isTeclado();
        boolean teclasAlt = Parametros.getInstancia().isTecladoAlt();
        
        if (tecla == t_arriba && teclas)
        {
            return ARRIBA;
        } else if(tecla == t_abajo && teclas)
        {
            return ABAJO;
        } else if(tecla == t_izquierda && teclas)
        {
            return IZQUIERDA;
        } else if (tecla == t_derecha && teclas)
        {
            return DERCHA;
        } else if (tecla == t_pausa && teclas)
        {
            return PAUSA;
        } else if (tecla == t_salir && teclas)
        {
            return FIN;
        } else if (tecla == t_arribaAlt && teclasAlt)
        {
            return ARRIBA;
        } else if(tecla == t_abajoAlt && teclasAlt)
        {
            return ABAJO;
        } else if(tecla == t_izquierdaAlt && teclasAlt)
        {
            return IZQUIERDA;
        } else if (tecla == t_derechaAlt && teclasAlt)
        {
            return DERCHA;
        } else {
            return SIN_ACCION;
        }
    }
    
    /**
     * Establecer en lenguaje de presentacion la tecla presionada con el usuario.
     * @param tecla
     */
    public void setTecla(int tecla)
    {
        colaTeclas.addElement(new Integer(tecla));
    }
}
