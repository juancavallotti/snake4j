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

import java.util.Random;
import juego.herramientasGui.BufferTeclas;
import juego.herramientasGui.ObservadorJuego;
import parameters.TopScores;

/**
 *
 * @author juan
 */
public class JuegoViborita extends Juego{

    
    private boolean flagObjetivo;
    private boolean flagColision;
    private int puntos;
    
    private AreaJuego area;
    private ObservadorJuego pantalla;
    private boolean flagFinJuego;
    private ObservadorCiclo observadorCiclo;
    
    
    /**
     * Inicializar genericamente el juego de la viborita
     */
    public JuegoViborita()
    {
        //inicialzar el juego
        BloqueVibora[] vibora = crearVibora(3, BloqueVibora.IZQUIERDA);
        
        //colocar la vibora a la mitad de la pantalla
        int alto = Parametros.getInstancia().getAltoBloques();
        int ancho = Parametros.getInstancia().getAnchoBloques();
        
        int xinicial, yinicial;
        
        xinicial = (ancho-1)/2;
        yinicial =  (alto - vibora.length)/2;
        
        area = new AreaJuego(vibora, xinicial, yinicial, AreaJuego.HORIZONTAL);
        
        setActivo(true);
        
        //vaciar el buffer de teclas
        BufferTeclas.getInstancia().vaciar();
        
        //obtener el nivel de dificultad seleccionado por el usuario
        int nivelDificultad = Parametros.getInstancia().getNivelDificultad();
        //leer la frecuencia normal
        long frecuencia = Parametros.getInstancia().getFrecuenciaJuego();
        
        //setear el contador de ciclos
        observadorCiclo = ContadorCiclos.getInstancia();
        
        //establecer el observador de colision
        area.addObservadorHit(
            //clase interna anonima
            new ObservadorHit() {

                public boolean hited(juego.Bloque fuente, juego.Bloque destino) {
                    if (destino instanceof BloqueHuevo)
                    {
                        flagObjetivo = true;
                        pantalla.notificarHit(true); //bueno
                        return true;
                    }
                    else
                    {
                        flagColision = true;
                        pantalla.notificarHit(false); //malo
                        return false;
                    }
                }
            }
        );
        
        area.addObservadorHit(new ObservadorHit() {

            public boolean hited(Bloque fuente, Bloque destino) {
                ContadorCiclos.getInstancia().reset();
                return true;
            }
        });
        
        colocarObjetivoNuevo();
        //establecer en falso los flags
        flagObjetivo = false;
        flagColision = false;
        flagFinJuego = false;
        //establecer el puntaje inicial
        puntos = 0;
        
        //establecer la frecuencia de este juego como el cociente de la frecuencia y el nivel
        setFrecuencia(frecuencia / nivelDificultad);
        
        
        
    }
    
    protected void leerEntrada() {
        
        LectorAcciones entrada = BufferTeclas.getInstancia();
        
        int lectura = entrada.leerAccion();
        
        switch (lectura)
        {
            case LectorAcciones.ABAJO:
                dirigirCabeza(BloqueVibora.ABAJO);
                break;
            case LectorAcciones.ARRIBA:
                dirigirCabeza(BloqueVibora.ARRIBA);
                break;
            case LectorAcciones.DERCHA:
                dirigirCabeza(BloqueVibora.DERECHA);
                break;
            case LectorAcciones.FIN:
                finalizarJuego();
                break;
            case LectorAcciones.IZQUIERDA:
                dirigirCabeza(BloqueVibora.IZQUIERDA);
                break;
            case LectorAcciones.PAUSA:
                setPausado(!isPausado());
                break;
            case LectorAcciones.SIN_ACCION:
                break;
        }
    }


    protected void procesarDatos() {
        //en base a la entrada anterior hacemos el movimiento.
        area.realizarMovimiento();
    }


    protected void actualizarEstadoJuego() {
        //si la vibora alcanzo un objetivo es este el momento de colocar uno nuevo
        if (flagObjetivo)
        {
            //colocar un nuevo objetivo
            colocarObjetivoNuevo();
            //notificarle a la vibora que debe crecer
            area.hacerCrecer();
        }
        
    }


    protected boolean aplicarCondicionesJuego() {
        //aca se deberian actualizar los puntajes y si es necesario dar por terminado el juego
        if (flagObjetivo)
        {
            flagObjetivo = false;
            actualizarPuntos();
        }
        
        if(flagColision)
            finalizarJuego();
        return true;
    }


    protected void notificarObservadores() {
        //finalmente actualizamos la pantalla y mostramos todos los mensajes que haya que mostrar
        Bloque[][] estado = area.getBloques();
        final juego.herramientasGui.Bloque[][] salida = convertirSalida(estado);
        if (pantalla != null)
        {
            new Thread() {
             public void run() {
                pantalla.bloquesCambiados(salida);
             }
            }.start();
            if(flagFinJuego)
            {
                flagFinJuego = false;
                TopScores.addNewRecord(puntos);
                pantalla.notificarFin();
            }
            if (isPausado())
                pantalla.notificarPausa();
            
            pantalla.notificarPuntos(puntos);
            if (observadorCiclo != null)
                observadorCiclo.cicloCompleto();
        }
    }

    private void actualizarPuntos() {
        //este es el momento de determinar el puntaje
        boolean paredes = Parametros.getInstancia().isParedes();
        boolean hambre = Parametros.getInstancia().isHambriento();
        
        double factor = 0.0;
        
        int nivelDificultad = Parametros.getInstancia().getNivelDificultad();
        //sumar al puntaje 10 * el nivel de dificultad
        int buferPuntos = 10*nivelDificultad;
        
        if (paredes)
            //le doy 10% mas de puntos
            buferPuntos *= 1.1;
        
        if (hambre) {
            //obtenemos la ultima cuenta
            int ultimaCuenta = ContadorCiclos.getInstancia().getUltimaCuenta();
            //calcular el maximo de tiempo en el que se ganan puntos extra
            int maxCiclos = 4*(11 - nivelDificultad);
            //si los ciclos que llevamos son mas que el maximo, no agregamos nada
            if (ultimaCuenta < maxCiclos)
            {
                //lo hacemos directamente proporcional
                factor = (double)ultimaCuenta / (double)maxCiclos;
            }
            
            buferPuntos *= 1 + factor;
        }
        
        
        puntos = puntos + buferPuntos;
    }
    /**
     * Metodo que coloca la comidita de la viborita.
     */
    private void colocarObjetivoNuevo() {
        Bloque objetivo = FabricaBloques.getInstancia().fabricarPremio();
        int anchoJuego = Parametros.getInstancia().getAnchoBloques();
        int altoJuego = Parametros.getInstancia().getAltoBloques(); 
        while (true) //intentar colocarlo en el mapa hasta que entre
        {
            int x = new Random().nextInt(anchoJuego);
            int y = new Random().nextInt(altoJuego);
            
            if (area.setBloque(objetivo, x, y))
                break;
        }
    }

    private juego.herramientasGui.Bloque[][] convertirSalida(Bloque[][] bloques) {
        
        int alto = Parametros.getInstancia().getAltoBloques();
        int ancho = Parametros.getInstancia().getAnchoBloques();
        
        Coordenada cordCabeza = area.getPosicionCabeza();
        Coordenada cordCola = area.getPosicionCola();

        int xCab, yCab, xCola, yCola;
        xCab = cordCabeza.getX();
        yCab = cordCabeza.getY();
        xCola = cordCola.getX();
        yCola = cordCola.getY();

        juego.herramientasGui.Bloque[][] ret = new juego.herramientasGui.Bloque[alto][ancho];

        try{
        //copiar los bloques teniendo en cuenta ciertos aspectos
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                Bloque bloque = bloques[i][j];
                if (j == xCab && i == yCab)
                    ret[i][j] = juego.herramientasGui.FabricaBloques.getInstancia()
                            .fabricarBloqueCabezaVibora((BloqueVibora)bloque); 
                else if (j == xCola && i == yCola)
                    ret[i][j] = juego.herramientasGui.FabricaBloques.getInstancia()
                            .fabricarBloqueCola((BloqueVibora) bloque);
                else if (bloque == null)
                    ret[i][j] = juego.herramientasGui.FabricaBloques.getInstancia()
                            .fabricarBloqueFondo();
                else if(bloque instanceof BloqueVibora)
                    ret[i][j] = juego.herramientasGui.FabricaBloques.getInstancia()
                            .fabricarBloqueVibora();
                else if(bloque instanceof BloqueLimite)
                    ret[i][j] = juego.herramientasGui.FabricaBloques.getInstancia()
                            .fabricarBloqueLimite();
                else if(bloque instanceof BloqueHuevo)
                    ret[i][j] = new juego.herramientasGui.BloqueObjetivo();
            }
        }
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
        return ret;
    }

    //metodos complementarios
    private BloqueVibora[] crearVibora(int cant, int direccion) {
        BloqueVibora[] ret = new BloqueVibora[cant];
        
        for (int i = 0; i < ret.length; i++) {
            ret[i] = new BloqueVibora();
            ret[i].setDireccion(direccion);
        }
        return ret;
    }

    
    private void dirigirCabeza(int direccion) {
        //obtener la cabeza.
        BloqueVibora cabeza = area.getCabeza();
        
        int dirActual = cabeza.getDireccion();
        
        //si la direccion es opuesta a la que vamos llevando entonces el movimiento
        //no es valido
        //si va para arriba no se puede bajar
        if (dirActual == BloqueVibora.ARRIBA && direccion != BloqueVibora.ABAJO)
        {
            area.setCabeza(FabricaBloques.getInstancia().fabricarBloqueVibora(direccion));
        } else if (dirActual == BloqueVibora.ABAJO && direccion != BloqueVibora.ARRIBA)
        {
            area.setCabeza(FabricaBloques.getInstancia().fabricarBloqueVibora(direccion));
        } else if (dirActual == BloqueVibora.DERECHA && direccion != BloqueVibora.IZQUIERDA)
        {
            area.setCabeza(FabricaBloques.getInstancia().fabricarBloqueVibora(direccion));
        } else if (dirActual == BloqueVibora.IZQUIERDA && direccion != BloqueVibora.DERECHA)
        {
            area.setCabeza(FabricaBloques.getInstancia().fabricarBloqueVibora(direccion));
        }
    }
    public void setObservadorJuego(ObservadorJuego o)
    {
        this.pantalla = o;
    }

    private void finalizarJuego() {
        setActivo(false);
        flagFinJuego = true;
    }
    public void pausar()
    {
        setPausado(true);
    }
}
