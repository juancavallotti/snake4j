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
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.m2g.SVGImage;
import javax.microedition.m2g.ScalableGraphics;
import javax.microedition.m2g.ScalableImage;

/**
 *
 * @author Juan
 */
public class Splash extends Canvas {
    
    private Displayable proximo = null;
    private Display pantalla = null;
    private ScalableImage imagen;

    public Splash(Displayable proximo, Display pantalla) {
        this.proximo = proximo;
        this.pantalla = pantalla;

        InputStream isImagen = getClass().getResourceAsStream("splash.svg");
        try {
            imagen = SVGImage.createImage(isImagen, null);
            imagen.setViewportWidth(getWidth());
            imagen.setViewportHeight(getHeight());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setFullScreenMode(true);
        //redibujar la pantalla
        repaint();
    }
    
    protected void paint(Graphics g) {
//        g.drawImage(getImagenSplash(), 0, 0, 0);

        g.setColor(0, 0, 0);
        g.fillRect(0, 0, getWidth(), getHeight());

        ScalableGraphics sg = ScalableGraphics.createInstance();
        sg.bindTarget(g);

        sg.render(0, 0, imagen);
        sg.releaseTarget();
        
    }
//    private Image getImagenSplash() {
//        try {
//
//            InputStream is = getClass().getResourceAsStream("splash.jpg");
//            Image imagenReal = Image.createImage(is);
//            return Lib.scaleImage(imagenReal, getWidth(), getHeight());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return Image.createImage(20, 20);
//    }

    protected void showNotify() {
        super.showNotify();
        new HiloCambio().start();
    }
    
    private class HiloCambio extends Thread {
        public void run(){
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("saliendo del splash");
            pantalla.setCurrent(proximo);
        }
    }
    
}
