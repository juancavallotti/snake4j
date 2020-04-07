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
 * Parsear el nombre del jugador al derecho y al reves.<br />
 * Primero tiene un byte que dice que tipo de registro es. <br/>
 * Después tiene n bytes que representan la cadena.
 * @author juan
 */
class PlayerNameParser implements RecordParser {

    public PlayerNameParser() {
    }

    public Object parse(byte[] data) {
        String s = new String(data, 1, data.length - 1);
        return s;
    }

    public byte[] convert(Object data) {
        String nombre = (String) data;
        nombre = "#"+nombre;
        byte[] ret = nombre.getBytes();
        ret[0] = RecordType.PLAYER_NAME;
        return ret;
    }

}
