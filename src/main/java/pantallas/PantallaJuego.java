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

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;
import juego.Parametros;
import juego.herramientasGui.Bloque;
import juego.herramientasGui.ObservadorJuego;

/**
 *
 * @author juan
 */
public class PantallaJuego extends Canvas implements ObservadorJuego {

    private Display pantalla;
    private Displayable lugarRetorno;
    private AreaJuego area;
    
    private int puntos;
    private String cadenaExtra;
    private boolean flagFin = false;
    private boolean flagReproducir;
    private boolean flagInicioJuego = false;
    private boolean flagBloquesListos = false;
    
    private Player sonidoObjetivo;
    private Player sonidoFin;
    private Font fuente;
    
    
    //variables de control para evitar el sobredibujo
    private boolean limpiarPant = true;
    
    
    public PantallaJuego(Display pantalla, Displayable retorno)
    {
        this.pantalla = pantalla;
        this.lugarRetorno = retorno;
        
        
        Parametros parm = Parametros.getInstancia();
        //volver a setear las flechas de direccion
        parm.setTeclaAbajo(getKeyCode(DOWN));
        parm.setTeclaArriba(getKeyCode(UP));
        parm.setTeclaDerecha(getKeyCode(RIGHT));
        parm.setTeclaIzquierda(getKeyCode(LEFT));
        //setear los numeros
        parm.setTeclaAbajoAlt(KEY_NUM8);
        parm.setTeclaArribaAlt(KEY_NUM2);
        parm.setTeclaDerechaAlt(KEY_NUM6);
        parm.setTeclaIzquierdaAlt(KEY_NUM4);        
        
        //setear la pantalla completa
        super.setFullScreenMode(true);
        
        //inicializar el area
        int anchoPantalla = getWidth();
        int altoPantalla = getHeight();
        area = new AreaJuego(anchoPantalla -30, altoPantalla -30 , 15, 20);
        
        fuente = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        
        flagReproducir = parm.isSonido();
        
        sonidoObjetivo = iniciarSonido("pop.mp3", "audio/mpeg");
        sonidoFin = iniciarSonido("scream.mp3", "audio/mpeg");
    }
    
    protected void paint(Graphics g) {
        
        //solo limpiamos la pantalla cuando hay cadenas nuevas por dibujar
        if (limpiarPant) {
            limpiarPantalla(g);
            limpiarPant = false;
        }
        
        if (flagBloquesListos)
        area.pintar(g);
        
        //estas las dibujamos siempre
        dibujarCadenas(g);
        
    }

    protected void showNotify() {
        super.showNotify();
        //ya tenemos todo para empezar el juego, pero lo empezamos si no estaba empezado.
        if (!flagInicioJuego)
        {
            reproducirSonido(sonidoObjetivo);
            ControladorJuego.getInstancia().iniciarPartida();
            flagInicioJuego = true;
        }
    }

    protected void hideNotify() {
        //si esconden la pantalla entonces pausamos el juego
        super.hideNotify();
        ControladorJuego.getInstancia().pausar();
    }

    
    protected void keyPressed(int arg0) {
        
        if (flagFin && arg0 == KEY_STAR)
        {
            flagFin = false;
            terminarPartida();
        }
        if (arg0 == KEY_NUM0)
        {
            if (flagReproducir)
                apagarSonido();
            else
                encenderSonido();
        }
        
        ControladorJuego.getInstancia().setTecla(arg0);
    }

    public void bloquesCambiados(Bloque[][] cambos) {
        //no se hace nada
        flagBloquesListos = true;
        area.setBloques(cambos);
        repaint();
    }

    public void notificarFin() {
        flagFin = true;
        limpiarPant = true;
        cadenaExtra = "CAGASTE!!";
        repaint();
        
    }

    private Player iniciarSonido(String nombreArchivo, String tipo) {
        try {
            Player ret = null;

            InputStream is = getClass().getResourceAsStream(nombreArchivo);
            ret = Manager.createPlayer(is, tipo);
            ret.realize();
            VolumeControl vc = (VolumeControl) ret.getControl("VolumeControl");
            if (vc != null) {
                vc.setLevel(100);
            }
            ret.prefetch();
            return ret;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void reproducirSonido(Player p) {
        try {
            if (flagReproducir && p != null) {
                p.start();
            }
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }
    private void apagarSonido()
    {
        flagReproducir = false;
    }
    private void encenderSonido()
    {
        flagReproducir = true;
    }
    
    private void terminarPartida()
    {
        apagarSonido();
        pantalla.setCurrent(lugarRetorno);
    }
    public void notificarPausa() 
    {
        limpiarPant = true;
        cadenaExtra = "PAUSA";
    }

    public void notificarPuntos(int puntos) {
        limpiarPant = true;
        this.puntos = puntos;
    }

    private void limpiarPantalla(Graphics g) {
        g.setColor(0,0,0);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(fuente);
    }
    
    private void dibujarCadenas(Graphics g)
    {
        g.setColor(0,210,0);
        g.drawString("Puntos: "+puntos, 4, 4, Graphics.TOP|Graphics.LEFT);
        
        if (cadenaExtra != null)
        {
            int x = getWidth() / 2;
            g.drawString(cadenaExtra, x+14, 4, Graphics.TOP|Graphics.LEFT);
            cadenaExtra = null;
        }
        if (flagFin)
        {
            g.setColor(0,255,255);
            int x = getWidth();
            int y = getHeight() /2;
            int anchoCadena = g.getFont().stringWidth("PRESIONA * PARA SALIR");
            x = (x - anchoCadena) / 2; 
            g.drawString("PRESIONA * PARA SALIR", x, y, Graphics.TOP|Graphics.LEFT);
        }
    }

    public void notificarHit(boolean tipo) {
        boolean vibrar = Parametros.getInstancia().isVibrar();
        
        if (tipo)
        {
            if (vibrar)
                pantalla.vibrate(50);
            reproducirSonido(sonidoObjetivo);
        } else {
            if (vibrar)
                pantalla.vibrate(300);
            reproducirSonido(sonidoFin);
        }
    }
    
    //metodo que maneja la tecla xD
}
