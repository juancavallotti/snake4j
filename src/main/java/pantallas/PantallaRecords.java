/*
 * Copyright (C) 2009 Juan Alberto López Cavallotti
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

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import parameters.GameRecord;
import parameters.TopScores;

/**
 *
 * @author juan
 */
public class PantallaRecords extends Canvas {

    private Display display;
    private Displayable ret;

    public PantallaRecords(Display display, Displayable ret) {
        this.display = display;
        this.ret = ret;
        setFullScreenMode(true);
    }


    protected void paint(Graphics g) {
        g.setColor(0, 0, 0);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(0, 255, 0);

        GameRecord[] gr = TopScores.getRecords();

        int alto = getHeight() / gr.length;
        int x_puntos = getWidth()-45;
        int x = 20;


        for (int i = 0; i < gr.length; i++) {
            String cad = (i + 1)+". "+gr[i].getPlayer();
            String puntos = gr[i].getScore()+"";
            g.drawString(cad, x, i*alto, Graphics.TOP | Graphics.LEFT);
            g.drawString(puntos, x_puntos, i*alto, Graphics.TOP | Graphics.LEFT);
        }

    }

    protected void keyPressed(int arg0) {
        display.setCurrent(ret);
    }

}
