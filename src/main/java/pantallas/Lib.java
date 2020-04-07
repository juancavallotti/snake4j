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

import javax.microedition.lcdui.Image;

/**
 *
 * @author Juan
 */
public class Lib {
    //sacado de http://willperone.net/Code/codescaling.php
    public static Image scaleImage(Image original, int newWidth, int newHeight) {        
            int[] rawInput = new int[original.getHeight() * original.getWidth()];
            original.getRGB(rawInput, 0, original.getWidth(), 0, 0, original.getWidth(), original.getHeight());

            int[] rawOutput = new int[newWidth*newHeight];        

            // YD compensates for the x loop by subtracting the width back out
            int YD = (original.getHeight() / newHeight) * original.getWidth() - original.getWidth(); 
            int YR = original.getHeight() % newHeight;
            int XD = original.getWidth() / newWidth;
            int XR = original.getWidth() % newWidth;        
            int outOffset= 0;
            int inOffset=  0;

            for (int y= newHeight, YE= 0; y > 0; y--) {            
                for (int x= newWidth, XE= 0; x > 0; x--) {
                    rawOutput[outOffset++]= rawInput[inOffset];
                    inOffset+=XD;
                    XE+=XR;
                    if (XE >= newWidth) {
                        XE-= newWidth;
                        inOffset++;
                    }
                }            
                inOffset+= YD;
                YE+= YR;
                if (YE >= newHeight) {
                    YE -= newHeight;     
                    inOffset+=original.getWidth();
                }
            }               
            return Image.createRGBImage(rawOutput, newWidth, newHeight, false);        
    }
}
