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
public class TopScores {

    private GameRecord[] records;
    private static TopScores instance = new TopScores();

    private TopScores() {
        records = new GameRecord[10];
    }

    public static void addNewRecord(int score) {
        String name = Parameters.getPlayerName();

        instance.addRecord(name, score);
    }

    public static void setRecord(GameRecord gr) {
        if (gr == null)
            return;
        instance.setPreparedRecord(gr);
    }

    public static GameRecord[] getRecords() {
        return instance.records;
    }

    public static void setClear() {
        instance.initClear();
    }

    private void initClear() {
        for (int i = 0; i < records.length; i++) {
            records[i] = new GameRecord();
            records[i].setPlayer("NADIE");
            records[i].setScore(0);
            records[i].setPosition(i);
        }
    }

    private void setPreparedRecord(GameRecord gr) {
        records[gr.getPosition()] = gr;
    }

    private void addRecord(String name, int score) {
        int i = 0;

        int last = records.length - 1;

        //no califica para la tabla
        if (score <= records[last].getScore())
            return;

        //encontrar el record y ponerlo
        for(i = 0; i < records.length; i++) {
            if (records[i].getScore() < score) {
                break;
            }
        }

        GameRecord rec = new GameRecord();
        rec.setPlayer(name);
        rec.setPosition(i);
        rec.setScore(score);

        for (int j = records.length - 1 ; j >= i ; j--) {
            if (j > 0) {
                records[j] = records[j-1];
                records[j].setPosition(j);
            }
        }

        records[i] = rec;
    }

}
