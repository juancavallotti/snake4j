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

package parameters;

/**
 *
 * @author juan
 */
public class GameSettings {

    private boolean arrowControl;
    private boolean numberControl;
    private boolean soundEnabled;
    private boolean vibrationEnabled;
    private boolean wallsEnabled;
    private boolean hungryEnabled;
    private byte gameSpeed;

    public GameSettings() {
        arrowControl = true;
        soundEnabled = true;
        vibrationEnabled = true;
        gameSpeed = 7;
    }

    public GameSettings(boolean defVal) {
        arrowControl = defVal;
        numberControl = defVal;
        soundEnabled = defVal;
        vibrationEnabled = defVal;
        wallsEnabled = defVal;
        hungryEnabled = defVal;
        gameSpeed = 0;
    }

    public boolean isArrowControl() {
        return arrowControl;
    }

    public void setArrowControl(boolean arrowControl) {
        this.arrowControl = arrowControl;
    }

    public boolean isHungryEnabled() {
        return hungryEnabled;
    }

    public void setHungryEnabled(boolean hungryEnabled) {
        this.hungryEnabled = hungryEnabled;
    }

    public boolean isNumberControl() {
        return numberControl;
    }

    public void setNumberControl(boolean numberControl) {
        this.numberControl = numberControl;
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
    }

    public boolean isVibrationEnabled() {
        return vibrationEnabled;
    }

    public void setVibrationEnabled(boolean vibrationEnabled) {
        this.vibrationEnabled = vibrationEnabled;
    }

    public boolean isWallsEnabled() {
        return wallsEnabled;
    }

    public void setWallsEnabled(boolean wallsEnabled) {
        this.wallsEnabled = wallsEnabled;
    }

    public byte getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(byte gameSpeed) {
        this.gameSpeed = gameSpeed;
    }
    

}
