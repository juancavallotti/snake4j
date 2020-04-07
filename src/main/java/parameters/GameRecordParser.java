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

import java.nio.ByteBuffer;

/**
 * Parsear un record en el juego. <br />
 * El registro esta compuesto de: <br />
 * Primero el byte de tipo de registro. <br />
 * Luego 1 byte que representa la posición en la tabla <br />
 * Luego 4 bytes del record. <br />
 * Los bytes restantes para el nombre. <br />
 *
 * @author juan
 */
class GameRecordParser implements RecordParser {

    public GameRecordParser() {
    }

    /**
     * Parses buy doesnt return a game record, the parsed record goes directly to the top scores.
     * @param data
     * @return
     */
    public Object parse(byte[] data) {

        if (data.length < 6)
            return null;

        int pos = data[1];
        
        byte[] record = new byte[] { data[2], data[3], data[4], data[5] };
        int rec = byteToInt(record);
        String player = new String(data, 6, data.length - 6);
        GameRecord ret =  new GameRecord();

        ret.setPlayer(player);
        ret.setScore(rec);
        ret.setPosition(pos);

        TopScores.setRecord(ret);

        return null;

    }

    public byte[] convert(Object data) {

        GameRecord gr = (GameRecord) data;

        String strbuff = "######"+gr.getPlayer();
        byte[] ret = strbuff.getBytes();

        ret[0] = RecordType.GAME_RECORD;
        ret[1] = (byte) gr.getPosition();

        byte[] score = intToByte(gr.getScore());

        for(int i = 0; i < score.length ; i++)
            ret[2+i] = score[i];

        return ret;
    }


    public int byteToInt(byte[] b) {
        ByteBuffer bb = ByteBuffer.wrap(b);
        return bb.getInt();
    }

    public byte[] intToByte(int i) {
        byte[] ret = new byte[4];
        ByteBuffer bb = ByteBuffer.wrap(ret).putInt(i);
        return bb.array();
    }

}
