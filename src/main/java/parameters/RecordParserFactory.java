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
public class RecordParserFactory {
    public static RecordParser getParser(byte fieldType) {
        switch (fieldType) {
            case RecordType.PLAYER_NAME:
                return new PlayerNameParser();
            case RecordType.GAME_SETTINGS:
                return new GameSettingsParser();
            case RecordType.GAME_RECORD:
                return new GameRecordParser();
            default:
                return null;

        }
    }
}
