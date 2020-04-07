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
 * Parsear los settings del juego al derecho y al reves. <br />
 * El registro se compone de la siguiente forma:<br />
 * Primero el byte de comando <br />
 * Luego un byte con flags: <br />
 * <blockquote>
 *  0 0 arrows numbers sound vibration walls hungry <br />
 *  0 0  32       16      8       4      2     1
 * </blockquote>
 * @author juan
 */
class GameSettingsParser implements RecordParser {

    private static final int HUNGRY = 0;
    private static final int WALLS = 1;
    private static final int VIBRATION = 2;
    private static final int SOUND = 3;
    private static final int NUMBERS = 4;
    private static final int ARROWS = 5;

    public GameSettingsParser() {
    }

    public Object parse(byte[] data) {

        GameSettings ret = new GameSettings(false);

        if (data.length != 3)
            return new GameSettings();

        byte flags = data[1];

        ret.setArrowControl(isFlagEnabled(ARROWS, flags));
        ret.setHungryEnabled(isFlagEnabled(HUNGRY, flags));
        ret.setNumberControl(isFlagEnabled(NUMBERS, flags));
        ret.setSoundEnabled(isFlagEnabled(SOUND, flags));
        ret.setVibrationEnabled(isFlagEnabled(VIBRATION, flags));
        ret.setWallsEnabled(isFlagEnabled(WALLS, flags));
        ret.setGameSpeed(data[2]);

        return ret;
    }

    public byte[] convert(Object data) {

        GameSettings sets = (GameSettings) data;

        byte flags = 0;

        if (sets.isArrowControl())
            flags = enableFlag(ARROWS, flags);
        if (sets.isHungryEnabled())
            flags = enableFlag(HUNGRY, flags);
        if (sets.isNumberControl())
            flags = enableFlag(NUMBERS, flags);
        if (sets.isSoundEnabled())
            flags = enableFlag(SOUND, flags);
        if (sets.isVibrationEnabled())
            flags = enableFlag(VIBRATION, flags);
        if (sets.isWallsEnabled())
            flags = enableFlag(WALLS, flags);

        byte[] ret = new byte[]{ RecordType.GAME_SETTINGS, flags, sets.getGameSpeed() };

        return ret;
    }

    private boolean isFlagEnabled(int pos, byte flags) {

        int val = pow2(pos);
        return (flags & val) == val;
    }

    /**
     * Recursivamente efectuar la exponenciacion de 2.
     * @param exp
     * @return
     */
    private int pow2(int exp) {
        //this should not happen
        if (exp < 0)
            return -1;
        if (exp == 0)
            return 1;
        else return 2 * pow2(exp-1);
    }

    private byte enableFlag(int pos, byte flags) {
        flags = (byte) (flags | pow2(pos));
        return flags;
    }

}
