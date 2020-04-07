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

import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author juan
 */
public class AreaJuego {
    Bloque[][] bloques;
    
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;
    
    
    /** Posicion  x de la cabeza */
    private int xCabeza;
    /** Posicion y de la cabeza */
    private int yCabeza;
    /** Posicion  x de la cola */
    private int xCola;
    /** Posicion y de la cola */
    private int yCola;
    
    /** Es el vector de observadores a los cuales se les notificara el hit de lacabeza con algo */
    private Vector observadoresHit = new Vector();
    private boolean crecer;
    
    
    /**
     * Crea un area de juego con una viborita inicial recta, y la coloca en x e y del tablero de juego, 
     * se toma como cabeza de la viborita el bloque 0 y como cola el bloque n siendo n el tamanio del arreglo
     * @param viborita es un arreglo de bloques que representan la viborita inicial recta.
     * @param x es la posicion x del primer bloque del arreglo
     * @param y es la posicion y del primr bloque del arreglo
     * @param direccion segun sea este parametro se coloca la viborita horizontal o verticalmente;
     */
    public AreaJuego(Bloque[] viborita, int x, int y, int direccion)
    {
        int alto = Parametros.getInstancia().getAltoBloques();
        int ancho = Parametros.getInstancia().getAnchoBloques();
        
        bloques = new Bloque[alto][ancho];

        //los x y iniciales
        int a = y, b = x;
        int c = a, d = b;
        //establecer la cabeza
        xCabeza = b;
        yCabeza = a;
        
        //colocar la viborita en la posicion inicial
        for (int i = 0; i < viborita.length; i++) {
            
            //determinamos cual variar
            if (direccion == VERTICAL)
                c = a + i;
            else
                d = b + i;
            //ponemos
            bloques[c][d] = viborita[i];
        }
        //establecer la cola
        xCola = d;
        yCola = c;
        System.out.println("Cola empieza en "+b+", "+a);
    }
    /**
     * Agregar observadores de hit de la cabeza contra algo.
     * @param obs Es el observador a agregar.
     */
    public void addObservadorHit(ObservadorHit obs)
    {
        observadoresHit.addElement(obs);
    }
    /**
     * Cambia la direccion de la cabeza.
     * @param direccion es la direccion que tomara la cabeza dependiendo de las 4 posibles direcciones declaradas en la clase BloqueVibora
     */
    public void cambiarDireccion(int direccion)
    {
        //le cambiamos la direccion a la cabeza
        BloqueVibora cabeza = (BloqueVibora) bloques[yCabeza][xCabeza];
        cabeza.setDireccion(direccion);
    }
    
    
    
    /**
     * Establecer en el area de juego el mapa con los obstaculos.
     * @param m es el mapa de nivel a agregar.
     */
    public void setMapaNivel(MapaNivel m)
    {
        //obtener los bloques
        Bloque[][] mapa = m.getBloques();
        //colocar el mapa.
        
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                mapa[i][j] = bloques[i][j]; //se copian los bloques
            }
        }
        //reemplazar el mapa actual se hace esto para no pisar
        //los datos de la viborita con el mapa en el caso de haber
        //superposicion.
        bloques = mapa;
    }
    
    /**
     * Este metodo es el que realiza el movimiento de la viborita en el mapa.
     */
    public void realizarMovimiento()
    {
        //System.out.println("LLAMADA");
        //primero comprobamos si la cabeza puede moverse en la direccion apropiada
        if (!comprobarMovimiento())
            return;
        
        Coordenada siguiente;
        //obtenemos la dimension del siguiente lugar de la cola
        BloqueVibora cola = (BloqueVibora) bloques[yCola][xCola];
        
        if (cola == null)
            System.out.println("COLA ES NULO???");
        //System.out.println("Obteniendo cola de "+xCola+", "+yCola);
        //System.out.println("direeccion de la cola es "+cola.getDireccion());
        
        siguiente = calcularCoordenada(xCola, yCola, cola.getDireccion());
        
        
        //si tiene que crecer entonces omitimos este paso
        if (!hayCrecimiento())
        {
            //poner en null el lugar donde estaba la cola
            bloques[yCola][xCola] = null;
            
            //System.out.println("Moviendo cola de "+xCola+", "+yCola+" a "+siguiente.getX()+", "+siguiente.getY());
            
            //decir que la cola es el siguiente
            xCola = siguiente.getX();
            yCola = siguiente.getY();
        
        }
        //mover la cabeza
        BloqueVibora cabeza = (BloqueVibora) bloques[yCabeza][xCabeza];
        siguiente = calcularCoordenada(xCabeza, yCabeza, cabeza.getDireccion());
        
        //crear un nuevo bloque con la direccion de la cabeza
        bloques[siguiente.getY()][siguiente.getX()] = FabricaBloques.getInstancia().fabricarBloqueVibora(cabeza.getDireccion());
        
        //System.out.println("Moviendo cabeza de "+xCabeza+", "+yCabeza+" a "+siguiente.getX()+", "+siguiente.getY());
        
        //actualizar la posicion de cabeza
        xCabeza  = siguiente.getX();
        yCabeza = siguiente.getY();
        
        //y eso es todo ya movimos la cabeza y la cola xD
        
    }
    /**
     * Obtener la coordenada del siguiente bloque.
     * @param xBloque x del bloque actual
     * @param yBloque y del bloque actual
     * @param direccion direccion para la cual se movera
     * @return una coordenada x y con el siguiente punto.
     */
    private Coordenada calcularCoordenada(int xBloque, int yBloque, int direccion) {
        //primero determinamos segun la direccion a que le debemos sumar o restar.
        
        
        Coordenada ret = new Coordenada(xBloque, yBloque);
        int ancho = Parametros.getInstancia().getAnchoBloques();
        int alto = Parametros.getInstancia().getAltoBloques();
        boolean pared = Parametros.getInstancia().isParedes();
        
        //arriba, incrementa y
        //abajo, decrementa y
        //derecha, increenta x
        //izquierda, decrementa x
        switch (direccion)
        {
            case BloqueVibora.ABAJO:
                ret.setY(ret.getY()+1 );
                break;
            case BloqueVibora.ARRIBA:
                ret.setY(ret.getY()-1);
                break;
            case BloqueVibora.DERECHA:
                ret.setX(ret.getX()+1);
                break;
            case BloqueVibora.IZQUIERDA:
                ret.setX(ret.getX()-1);
                break;
        }
        
        //por ultimo comprobar las 4 condiciones especiales
        if (ret.getY() < 0) //se ha salido por abajo
        {   
            if (pared)
                return new Coordenada(-1, -1);
            ret.setY(alto - 1);
        }else if(ret.getY() >= alto) //se salio por arriba
        {   
            if (pared)
                return new Coordenada(-1, -1);
            ret.setY(0);
        }else if(ret.getX() < 0) //por izquierda
        {   
            if (pared)
                return new Coordenada(-1, -1);
            ret.setX(ancho -1);
        }else if(ret.getX() >= ancho) //se salio por derecha
        {    
            if (pared)
                return new Coordenada(-1, -1);
            ret.setX(0);
        }
        //y listo
        return ret;
    }
    /**
     * Comprobar si la viborita se puede seguir moviendo.
     * @return true si la viborita se puede mover o false en el caso contrario.
     */
    private boolean comprobarMovimiento() {
        
        boolean ret = true;
        BloqueVibora cabeza = (BloqueVibora) bloques[yCabeza][xCabeza]; 
        Bloque siguiente = getSiguienteBloque(xCabeza, yCabeza, cabeza.getDireccion());
       
        if (siguiente != null) //si el proximo bloque tiene algo, disparar el evento hit
        {
            ret = notificarHit(cabeza, siguiente);
            
            //aca es un lugar ideal para comprobar si la vibora puede crecer, pero es el experto quien deberia decidir esto.
        
        } else {
            ret = true;
        }
        return ret;
    }
    
    /**
     * Obtener el siguiente bloque.
     * @param xCabeza la coordenada x del bloque actual
     * @param yCabeza la coordenada y del bloque actual
     * @param direccion la direccion del bloque actual
     * @return el bloque siguiente el cual puede ser un bloque o null
     */
    private Bloque getSiguienteBloque(int xCabeza, int yCabeza, int direccion) {
        
        
        Coordenada siguiente = calcularCoordenada(xCabeza, yCabeza, direccion);
        
        if (siguiente.getX() == -1 && siguiente.getY() == -1)
            return new BloqueLimite();
        
        return bloques[siguiente.getY()][siguiente.getX()];
    }

    private boolean hayCrecimiento() {
        boolean ret = crecer;
        if (crecer)
            crecer = false;
        return ret;
    }

    /**
     * Realizar una notificacion de hit a los observadores los cuales determinaran entre todos si el juego puede continuar.
     * @param cabeza es el bloque fuente
     * @param siguiente es el bloque destino
     * @return true si se puede seguir false si no.
     */
    private boolean notificarHit(Bloque cabeza, Bloque siguiente) {
        boolean ret = true; //inicializar el retorno
        
        Enumeration e = observadoresHit.elements();
        //se notifica a todos los observadores de hit. 
        while(e.hasMoreElements())
        {
            ObservadorHit o = (ObservadorHit) e.nextElement();
            ret = ret && o.hited(cabeza, siguiente);
        }
        return ret;
    }
    /**
     * Notificarle al area que la viborita debe crecer 1 bloque.
     */
    public void hacerCrecer()
    {
        this.crecer = true;
    }
    
    public BloqueVibora getCabeza()
    {
        return (BloqueVibora) bloques[yCabeza][xCabeza];
    }
    /**
     * Obtener la posicion de la cabeza.
     * @return una coordenada con la posicion x y de la cabeza.
     */
    public Coordenada getPosicionCabeza()
    {
        Coordenada ret = new Coordenada(xCabeza, yCabeza);
        return ret;
    }
    Coordenada getPosicionCola() {
        Coordenada ret = new Coordenada(xCola, yCola);
        return ret;
    }

    public void setCabeza(BloqueVibora cabeza)
    {
        bloques[yCabeza][xCabeza] = cabeza;
    }
    
    public BloqueVibora getCola()
    {
        return (BloqueVibora) bloques[yCola][xCola];
    }
    public Bloque[][] getBloques()
    {
        return bloques;
    }
    /**
     * Colocar un bloque en el mapa, en la posicion x e y especificadas, si ya hay algo en el destino este metodo no hace nada y devuelve false. 
     * @param b es el bloque a colocar
     * @param x es la posicion x donde se colocara el bloque
     * @param y es la posicion y donde se colocara el bloque
     * @return true si hubo exito y false en caso contrario.
     */
    public boolean setBloque(Bloque b, int x, int y)
    {
        Bloque bufer = bloques[y][x];
        if(bufer == null)
        {
            bloques[y][x] = b;
            return true;
        } else {
            return false;
        }
    }
}