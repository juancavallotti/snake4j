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

import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 *
 * @author juan
 */
public class Parameters {
    private static Parameters instance = new Parameters();

    private RecordStore rs;
    private Hashtable params;

    private Parameters() {
        try {
            //por si no tenemos el record store creado.
            params = new Hashtable();
            setDefaultParameters();
            rs = RecordStore.openRecordStore("serpentina-m", true);
            readParameters();
            rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }

    private void readParameters() throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
        RecordEnumeration re = rs.enumerateRecords(null, null, false);

        while(re.hasNextElement()) {
            byte[] data = re.nextRecord();
            if (data.length > 1) {
                RecordParser rp = RecordParserFactory.getParser(data[0]);

                //si no conocemos el registro, continuamos por el siguiente
                if (rp == null)
                    continue;

                Object o = rp.parse(data);

                //si el parser nos devuelve nulo, pues continuamos con el siguiente
                if (o == null)
                    continue;

                params.put(new Byte(data[0]), o);
            }
        }

    }

    private void setDefaultParameters() {
        //el nombre del jugador por defecto es desconocido
        params.put(new Byte(RecordType.PLAYER_NAME), "Desconocido");

        //ponemos los default settings
        params.put(new Byte(RecordType.GAME_SETTINGS), new GameSettings());

        //ponemos los top scores en 0
        TopScores.setClear();
    }

    public static void store() {
        instance.storeData();
    }

    private void storeData() {
        try {
            RecordStore.deleteRecordStore("serpentina-m");
            rs = RecordStore.openRecordStore("serpentina-m", true);
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
            return;
        }

        Enumeration e = params.keys();
        while(e.hasMoreElements()) {
            try {
                Byte b = (Byte) e.nextElement();
                RecordParser rp = RecordParserFactory.getParser(b.byteValue());
                //si estamos guardando algo que no conocemos.
                if (rp == null) {
                    continue;
                }
                byte[] data = rp.convert(params.get(b));
                if (data != null) {
                    rs.addRecord(data, 0, data.length);
                }
            } catch (RecordStoreException ex) {
                ex.printStackTrace();
                break;
            }
        }

        RecordParser rp = new GameRecordParser();
        GameRecord[] gr = TopScores.getRecords();
        for (int i = 0; i < TopScores.getRecords().length; i++) {
            try {
                byte[] data = rp.convert(gr[i]);
                if (data == null) {
                    continue;
                }
                rs.addRecord(data, 0, data.length);
            } catch (RecordStoreException ex) {
                ex.printStackTrace();
                continue;
            }
        }
        try {
            rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }

    public static void setGameSettings(GameSettings gs) {
        instance.params.put(new Byte(RecordType.GAME_SETTINGS), gs);
    }

    public static void setPlayerName(String name) {
        instance.params.put(new Byte(RecordType.PLAYER_NAME), name);
    }

    public static GameSettings getGameSettings() {
        return (GameSettings) instance.params.get(new Byte(RecordType.GAME_SETTINGS));
    }

    public static String getPlayerName() {
        return (String) instance.params.get(new Byte(RecordType.PLAYER_NAME));
    }

}
