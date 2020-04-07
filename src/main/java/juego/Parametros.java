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

package juego;

import parameters.GameSettings;

/**
 *
 * @author juan
 */
public class Parametros {
    private Parametros()
    {
        //singleton
    }
    private static Parametros instancia = new Parametros();
    
    public static Parametros getInstancia()
    {
        return instancia;
    }

    //configuracion del juego
    private int altoBloques;
    private int anchoBloques;
    private long frecuenciaJuego;
    private int nivelDificultad;
    
    //principal
    private int teclaAbajo;
    private int teclaArriba;
    private int teclaDerecha;
    private int teclaIzquierda;
    private int teclaPausa;
    private int teclaSalir;
    
    //teclas alternativas
    private int teclaAbajoAlt;
    private int teclaArribaAlt;
    private int teclaDerechaAlt;
    private int teclaIzquierdaAlt;
    private int teclaPausaAlt;
    private int teclaSalirAlt;    
    
    private boolean sonido;
    private boolean vibrar;
    private boolean paredes;
    private boolean hambriento;
    private boolean teclado;
    private boolean tecladoAlt;

    private String nombreJugador;

    public void initGameSets(GameSettings gs) {
        sonido = gs.isSoundEnabled();
        vibrar = gs.isVibrationEnabled();
        paredes = gs.isWallsEnabled();
        hambriento = gs.isHungryEnabled();
        teclado = gs.isArrowControl();
        tecladoAlt = gs.isNumberControl();
        nivelDificultad = gs.getGameSpeed();
    }

    public GameSettings getGameSets() {
        GameSettings gs = new GameSettings();
        gs.setArrowControl(teclado);
        gs.setHungryEnabled(hambriento);
        gs.setNumberControl(tecladoAlt);
        gs.setSoundEnabled(sonido);
        gs.setVibrationEnabled(vibrar);
        gs.setWallsEnabled(paredes);
        gs.setGameSpeed((byte) nivelDificultad);

        return gs;
    }


    public int getAltoBloques() {
        return altoBloques;
    }
    public void setAltoBloques(int altoBloques) {
        this.altoBloques = altoBloques;
    }

    public int getAnchoBloques() {
        return anchoBloques;
    }

    public void setAnchoBloques(int anchoBloques) {
        this.anchoBloques = anchoBloques;
    }

    public long getFrecuenciaJuego() {
        return frecuenciaJuego;
    }

    public void setFrecuenciaJuego(long frecuenciaJuego) {
        this.frecuenciaJuego = frecuenciaJuego;
    }

    public int getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(int nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public int getTeclaAbajo() {
        return teclaAbajo;
    }

    public void setTeclaAbajo(int teclaAbajo) {
        this.teclaAbajo = teclaAbajo;
    }

    public int getTeclaArriba() {
        return teclaArriba;
    }

    public void setTeclaArriba(int teclaArriba) {
        this.teclaArriba = teclaArriba;
    }

    public int getTeclaDerecha() {
        return teclaDerecha;
    }

    public void setTeclaDerecha(int teclaDerecha) {
        this.teclaDerecha = teclaDerecha;
    }

    public int getTeclaIzquierda() {
        return teclaIzquierda;
    }

    public void setTeclaIzquierda(int teclaIzquireda) {
        this.teclaIzquierda = teclaIzquireda;
    }

    public int getTeclaPausa() {
        return teclaPausa;
    }

    public void setTeclaPausa(int teclaPausa) {
        this.teclaPausa = teclaPausa;
    }

    public int getTeclaSalir() {
        return teclaSalir;
    }

    public void setTeclaSalir(int teclaSalir) {
        this.teclaSalir = teclaSalir;
    }

    public boolean isHambriento() {
        return hambriento;
    }

    public void setHambriento(boolean hambriento) {
        this.hambriento = hambriento;
    }

    public boolean isParedes() {
        return paredes;
    }

    public void setParedes(boolean paredes) {
        this.paredes = paredes;
    }

    public boolean isSonido() {
        return sonido;
    }

    public void setSonido(boolean sonido) {
        this.sonido = sonido;
    }

    public boolean isVibrar() {
        return vibrar;
    }

    public void setVibrar(boolean vibrar) {
        this.vibrar = vibrar;
    }

    public int getTeclaAbajoAlt() {
        return teclaAbajoAlt;
    }

    public void setTeclaAbajoAlt(int teclaAbajoAlt) {
        this.teclaAbajoAlt = teclaAbajoAlt;
    }

    public int getTeclaArribaAlt() {
        return teclaArribaAlt;
    }

    public void setTeclaArribaAlt(int teclaArribaAlt) {
        this.teclaArribaAlt = teclaArribaAlt;
    }

    public int getTeclaDerechaAlt() {
        return teclaDerechaAlt;
    }

    public void setTeclaDerechaAlt(int teclaDerechaAlt) {
        this.teclaDerechaAlt = teclaDerechaAlt;
    }

    public int getTeclaIzquierdaAlt() {
        return teclaIzquierdaAlt;
    }

    public void setTeclaIzquierdaAlt(int teclaIzquierdaAlt) {
        this.teclaIzquierdaAlt = teclaIzquierdaAlt;
    }

    public int getTeclaPausaAlt() {
        return teclaPausaAlt;
    }

    public void setTeclaPausaAlt(int teclaPausaAlt) {
        this.teclaPausaAlt = teclaPausaAlt;
    }

    public int getTeclaSalirAlt() {
        return teclaSalirAlt;
    }

    public void setTeclaSalirAlt(int teclaSalirAlt) {
        this.teclaSalirAlt = teclaSalirAlt;
    }

    public boolean isTeclado() {
        return teclado;
    }

    public void setTeclado(boolean teclado) {
        this.teclado = teclado;
    }

    public boolean isTecladoAlt() {
        return tecladoAlt;
    }

    public void setTecladoAlt(boolean tecladoAlt) {
        this.tecladoAlt = tecladoAlt;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
    
}
