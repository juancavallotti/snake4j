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
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.m2g.SVGImage;
import javax.microedition.m2g.ScalableGraphics;
import javax.microedition.m2g.ScalableImage;
import juego.Parametros;
import juego.herramientasGui.*;

/**
 *
 * @author juan
 */
public class AreaJuego {
    private int anchoPixeles;
    private int altoPixeles;
    
    private int alto;
    private int ancho;
    
    private int x;
    private int y;
    
    private int anchoCuadro;
    private int altoCuadro;
    
    Image img;
    
    Bloque[][] bloques;

    private ScalableImage huevo;
    private ScalableGraphics sg;
    private ScalableImage cabeza_up;
    private ScalableImage cabeza_down;
    private ScalableImage cabeza_left;
    private ScalableImage cabeza_right;
    private ScalableImage cola_up;
    private ScalableImage cola_down;
    private ScalableImage cola_left;
    private ScalableImage cola_right;

    public AreaJuego(int anchoPixeles, int altoPixeles, int x, int y) {
        System.out.println("Obteniendo el alto y el ancho del juego");
        alto = Parametros.getInstancia().getAltoBloques();
        ancho = Parametros.getInstancia().getAnchoBloques();
        
        this.anchoPixeles = anchoPixeles;
        this.altoPixeles = altoPixeles;
        this.x = x;
        this.y = y;
        
        //calcular las dimensiones del dibujo
        anchoCuadro = (int)  anchoPixeles / ancho; 
        altoCuadro = (int) altoPixeles / alto;
        
        //crear el lugar donde se dibuja
        img = Image.createImage(ancho*anchoCuadro, alto*altoCuadro);

        //cargar los svg de los elementos graficos
        //el huevo
        InputStream isHuevo = getClass().getResourceAsStream("huevo.svg");
        //las cabezas
        InputStream isCu = getClass().getResourceAsStream("cabeza_up.svg");
        InputStream isCd = getClass().getResourceAsStream("cabeza_down.svg");
        InputStream isCl = getClass().getResourceAsStream("cabeza_left.svg");
        InputStream isCr = getClass().getResourceAsStream("cabeza_right.svg");
        //las colas
        InputStream isCou = getClass().getResourceAsStream("cola_up.svg");
        InputStream isCod = getClass().getResourceAsStream("cola_down.svg");
        InputStream isCol = getClass().getResourceAsStream("cola_left.svg");
        InputStream isCor = getClass().getResourceAsStream("cola_right.svg");


        if (isHuevo == null)
            return;
        try {
            //el huevo
            huevo = SVGImage.createImage(isHuevo, null);
            huevo.setViewportWidth(anchoCuadro);
            huevo.setViewportHeight(altoCuadro);

            //la cabeza
            cabeza_up = SVGImage.createImage(isCu, null);
            cabeza_up.setViewportWidth(anchoCuadro + 8);
            cabeza_up.setViewportHeight(altoCuadro + 8);

            cabeza_down = SVGImage.createImage(isCd, null);
            cabeza_down.setViewportWidth(anchoCuadro + 8);
            cabeza_down.setViewportHeight(altoCuadro + 8);

            cabeza_left = SVGImage.createImage(isCl, null);
            cabeza_left.setViewportWidth(anchoCuadro + 8);
            cabeza_left.setViewportHeight(altoCuadro + 8);

            cabeza_right = SVGImage.createImage(isCr, null);
            cabeza_right.setViewportWidth(anchoCuadro + 8);
            cabeza_right.setViewportHeight(altoCuadro + 8);

            //la cola
            cola_up = SVGImage.createImage(isCou, null);
            cola_up.setViewportWidth(anchoCuadro + 4);
            cola_up.setViewportHeight(altoCuadro + 4);

            cola_down = SVGImage.createImage(isCod, null);
            cola_down.setViewportWidth(anchoCuadro + 4);
            cola_down.setViewportHeight(altoCuadro + 4);

            cola_left = SVGImage.createImage(isCol, null);
            cola_left.setViewportWidth(anchoCuadro + 4);
            cola_left.setViewportHeight(altoCuadro + 4);

            cola_right = SVGImage.createImage(isCor, null);
            cola_right.setViewportWidth(anchoCuadro + 4);
            cola_right.setViewportHeight(altoCuadro + 4);

            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setBloques(Bloque[][] bloques) {
        //System.out.println("Llamada a set bloques");
        this.bloques = bloques;
    }
    
    public void pintar(Graphics g) {
        
        //crear la imagen del area
        paint(img.getGraphics());
        //dibujar la imagen
        g.drawImage(img, x, y, 0);
        
        //dibujar un recuadro al rededor
        g.setColor(0,210,0);
        g.drawRect(x-1, y-1, ancho*anchoCuadro+1, alto*altoCuadro+1);
    }
    
    public void paint(Graphics g)
    {

        g.setColor(0, 0, 0);
        g.fillRect(0, 0, ancho*anchoCuadro, alto*altoCuadro);
        
        sg = ScalableGraphics.createInstance();
        sg.bindTarget(g);

        if (bloques == null) //si no hay bloques no se hace nada
            return;

        boolean dibujarCabeza = false;
        int iCab = 0;
        int jCab = 0;
        boolean dibujarHuevo = false;
        int ihue = 0;
        int jhue = 0;
        boolean dibujarCola = false;
        int iCola = 0;
        int jCola = 0;

        for (int i = 0; i < alto; i++)
        {
            for (int j = 0; j < ancho; j++)
            {
                decodificarColor(bloques[i][j], g);
                //dibujar el cuadro
                if (bloques[i][j] instanceof BloqueObjetivo) {
                    ihue = i;
                    jhue = j;
                    dibujarHuevo = true;
                } else if (bloques[i][j] instanceof BloqueCola) {
                     //retrasar el dibujo de la cabeza hasta el fin del loop
                    iCola = i;
                    jCola = j;
                    dibujarCola = true;
                } else if (bloques[i][j] instanceof BloqueCabezaVibora) {
                    //retrasar el dibujo de la cabeza hasta el fin del loop
                    iCab = i;
                    jCab = j;
                    dibujarCabeza = true;
                } else if (bloques[i][j] instanceof BloqueVibora) {
                    g.fillRect(j*anchoCuadro, i*altoCuadro, anchoCuadro, altoCuadro);
                }
            }
        }
        
        if (dibujarCabeza)
        {
            //decodificarColor(bloques[iCab][jCab], g);
            
            try {
                dibujarCabeza((BloqueCabezaVibora) bloques[iCab][jCab], g, jCab, iCab);
                
            } catch (ClassCastException ex) {
                System.out.println("i: "+iCab+", j: "+jCab);
            }
        }

        if (dibujarCola) {
            dibujarCola((BloqueCola) bloques[iCola][jCola], g, jCola, iCola);
        }

        if (dibujarHuevo) {
            dibujarHuevo( g, jhue, ihue);
        }

        sg.releaseTarget();
    }
    private void decodificarColor(Bloque bloque, Graphics g) {
        if (bloque instanceof BloqueFondo)
            g.setColor(0, 0, 0); //negro
        else if (bloque instanceof BloqueVibora)
            g.setColor(255,0,0); //rojo
        else if (bloque instanceof BloqueObjetivo)
            g.setColor(180,180,180); //blanco
        else if (bloque instanceof BloqueLimite)
            g.setColor(0, 0, 255); //azul
        else
            g.setColor(0, 0, 0); //negro
    }

    private void dibujarCabeza(BloqueCabezaVibora bloque, Graphics g, int x, int y) {
        int direccion = bloque.getDireccion();
        ScalableImage a_dibujar = null;

        switch (direccion) {
            case BloqueCabezaVibora.ABAJO:
                a_dibujar = cabeza_down;
                break;
            case BloqueCabezaVibora.ARRIBA:
                a_dibujar = cabeza_up;
                break;
            case BloqueCabezaVibora.IZQUIERDA:
                a_dibujar = cabeza_left;
                break;
            case BloqueCabezaVibora.DERECHA:
                a_dibujar = cabeza_right;
                break;
        }

        sg.render((x * anchoCuadro) - 4, (y * altoCuadro) - 4 , a_dibujar);

    }

    private void dibujarCola(BloqueCola bloque, Graphics g, int x, int y) {
        int direccion = bloque.getDireccion();
        ScalableImage a_dibujar = null;

        switch (direccion) {
            case BloqueCola.ABAJO:
                a_dibujar = cola_down;
                break;
            case BloqueCola.ARRIBA:
                a_dibujar = cola_up;
                break;
            case BloqueCola.IZQUIERDA:
                a_dibujar = cola_left;
                break;
            case BloqueCola.DERECHA:
                a_dibujar = cola_right;
                break;
        }

        sg.render((x * anchoCuadro) - 2, (y * altoCuadro) - 2 , a_dibujar);

    }
    public int getWidth() {
        return anchoPixeles;
    }

    public int getHeight() {
        return altoPixeles;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void dibujarHuevo(Graphics g, int j, int i) {

        sg.render(j * anchoCuadro, (i * altoCuadro) , huevo);
    }
    
    
}
