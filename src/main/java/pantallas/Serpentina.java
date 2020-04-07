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

package pantallas;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import juego.Parametros;
import org.netbeans.microedition.lcdui.SplashScreen;
import parameters.GameSettings;
import parameters.Parameters;

/**
 * @author juan
 */
public class Serpentina extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Form PantallaInicial;
    private StringItem stringItem1;
    private Form SelectorVelocidades;
    private Gauge barra;
    private StringItem stringItem;
    private Form Opciones;
    private ChoiceGroup optSonido;
    private ChoiceGroup optControles;
    private ChoiceGroup optParedes;
    private ChoiceGroup optVibracion;
    private ChoiceGroup optHambrienta;
    private TextField campoNombre;
    private SplashScreen splashScreen;
    private Command botDificultad;
    private Command botAceptar;
    private Command botIniciar;
    private Command exitCommand;
    private Command botAtras;
    private Command botOpciones;
    private Command botRecords;
    private Font font;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The Serpentina constructor.
     */
    public Serpentina() {
    }

    /**
     * Leer los valores de los campos del formulario
     */
    private void configurarJuego() {
        boolean[] controles = new boolean[2];
        boolean[] sonido = new boolean[1];
        boolean[] vibracion = new boolean[1];
        boolean[] paredes =  new boolean[1];
        boolean[] hambrienta = new boolean[1];
        String nombre = getCampoNombre().getString();

        getOptControles().getSelectedFlags(controles);
        getOptHambrienta().getSelectedFlags(hambrienta);
        getOptParedes().getSelectedFlags(paredes);
        getOptSonido().getSelectedFlags(sonido);
        getOptVibracion().getSelectedFlags(vibracion);

        //que lammer soy!! MVC deberia ser!
        ControladorJuego.getInstancia().setParametros(controles[0], controles[1], sonido[0], vibracion[0], paredes[0], hambrienta[0]);
        Parametros.getInstancia().setNombreJugador(nombre);
        Parameters.setPlayerName(nombre);
    }

    /**
     * Escribir los valores en los campos del formulario
     */

    private void configurarPantalla() {
        Parametros p = Parametros.getInstancia();
        
        boolean[] controles = {p.isTeclado(), p.isTecladoAlt()};
        boolean[] sonido =  {p.isSonido()};
        boolean[] vibracion = {p.isVibrar()};
        boolean[] paredes = {p.isParedes()};
        boolean[] hambrienta = {p.isHambriento()};
        String nombre = p.getNombreJugador();

        getOptControles().setSelectedFlags(controles);
        getOptHambrienta().setSelectedFlags(hambrienta);
        getOptParedes().setSelectedFlags(paredes);
        getOptSonido().setSelectedFlags(sonido);
        getOptVibracion().setSelectedFlags(vibracion);
        getCampoNombre().setString(nombre);
        
    }



    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
        //establecer las teclas
        GameSettings gs = Parameters.getGameSettings();
        String nombreJugador = Parameters.getPlayerName();
        //tratar de obtener la instancia para forzar la creacion del experto
        ControladorJuego.getInstancia();

        Parametros p = Parametros.getInstancia();

        p.setTeclaAbajo(Canvas.DOWN);
        p.setTeclaArriba(Canvas.UP);
        p.setTeclaDerecha(Canvas.RIGHT);
        p.setTeclaIzquierda(Canvas.LEFT);
        p.setTeclaPausa(Canvas.KEY_POUND);
        p.setTeclaSalir(Canvas.KEY_STAR);

        p.initGameSets(gs);
        p.setNombreJugador(nombreJugador);

        configurarPantalla();

//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
     getDisplay().setCurrent(new Splash(getPantallaInicial(), getDisplay()));

//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == Opciones) {//GEN-BEGIN:|7-commandAction|1|44-preAction
            if (command == botAtras) {//GEN-END:|7-commandAction|1|44-preAction
                // write pre-action user code here
                switchDisplayable(null, getPantallaInicial());//GEN-LINE:|7-commandAction|2|44-postAction
                // write post-action user code here
                configurarJuego();
            }//GEN-BEGIN:|7-commandAction|3|18-preAction
        } else if (displayable == PantallaInicial) {
            if (command == botDificultad) {//GEN-END:|7-commandAction|3|18-preAction

    // write pre-action user code here

                switchDisplayable(null, getSelectorVelocidades());//GEN-LINE:|7-commandAction|4|18-postAction
    if (barra != null)
        barra.setValue(ControladorJuego.getInstancia().getDificultad()-1);
            } else if (command == botIniciar) {//GEN-LINE:|7-commandAction|5|26-preAction
                // write pre-action user code here
                mostrarPantallaJuego();
//GEN-LINE:|7-commandAction|6|26-postAction
                // write post-action user code here
            } else if (command == botOpciones) {//GEN-LINE:|7-commandAction|7|41-preAction
                // write pre-action user code here
                switchDisplayable(null, getOpciones());//GEN-LINE:|7-commandAction|8|41-postAction
                // write post-action user code here
            } else if (command == botRecords) {//GEN-LINE:|7-commandAction|9|72-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|10|72-postAction
                verRecords();
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|11|29-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|12|29-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|13|21-preAction
        } else if (displayable == SelectorVelocidades) {
            if (command == botAceptar) {//GEN-END:|7-commandAction|13|21-preAction
                // write pre-action user code here
                int dificultad = barra.getValue();
                //aumentar en 1 el nivel de dificultad
                dificultad += 1;
                ControladorJuego.getInstancia().setDificultad(dificultad);

                switchDisplayable(null, getPantallaInicial());//GEN-LINE:|7-commandAction|14|21-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|68-preAction
        } else if (displayable == splashScreen) {
            if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|15|68-preAction
 // write pre-action user code here
//GEN-LINE:|7-commandAction|16|68-postAction
 // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|17|7-postCommandAction
        }//GEN-END:|7-commandAction|17|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|18|
    //</editor-fold>//GEN-END:|7-commandAction|18|


    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: PantallaInicial ">//GEN-BEGIN:|13-getter|0|13-preInit
    /**
     * Returns an initiliazed instance of PantallaInicial component.
     * @return the initialized component instance
     */
    public Form getPantallaInicial() {
        if (PantallaInicial == null) {//GEN-END:|13-getter|0|13-preInit
            // write pre-init user code here
            PantallaInicial = new Form("Serpentina", new Item[] { getStringItem1() });//GEN-BEGIN:|13-getter|1|13-postInit
            PantallaInicial.addCommand(getBotDificultad());
            PantallaInicial.addCommand(getBotIniciar());
            PantallaInicial.addCommand(getExitCommand());
            PantallaInicial.addCommand(getBotOpciones());
            PantallaInicial.addCommand(getBotRecords());
            PantallaInicial.setCommandListener(this);//GEN-END:|13-getter|1|13-postInit
            // write post-init user code here
        }//GEN-BEGIN:|13-getter|2|
        return PantallaInicial;
    }
    //</editor-fold>//GEN-END:|13-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: SelectorVelocidades ">//GEN-BEGIN:|15-getter|0|15-preInit
    /**
     * Returns an initiliazed instance of SelectorVelocidades component.
     * @return the initialized component instance
     */
    public Form getSelectorVelocidades() {
        if (SelectorVelocidades == null) {//GEN-END:|15-getter|0|15-preInit
            // write pre-init user code here
            SelectorVelocidades = new Form("Seleccione dificultad del Juego", new Item[] { getStringItem(), getBarra() });//GEN-BEGIN:|15-getter|1|15-postInit
            SelectorVelocidades.addCommand(getBotAceptar());
            SelectorVelocidades.setCommandListener(this);//GEN-END:|15-getter|1|15-postInit
            // write post-init user code here
        }//GEN-BEGIN:|15-getter|2|
        return SelectorVelocidades;
    }
    //</editor-fold>//GEN-END:|15-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: botDificultad ">//GEN-BEGIN:|17-getter|0|17-preInit
    /**
     * Returns an initiliazed instance of botDificultad component.
     * @return the initialized component instance
     */
    public Command getBotDificultad() {
        if (botDificultad == null) {//GEN-END:|17-getter|0|17-preInit
            // write pre-init user code here
            botDificultad = new Command("Dificultad", "Seleccionar Dificultad", Command.ITEM, 1);//GEN-LINE:|17-getter|1|17-postInit
            // write post-init user code here
        }//GEN-BEGIN:|17-getter|2|
        return botDificultad;
    }
    //</editor-fold>//GEN-END:|17-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: barra ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initiliazed instance of barra component.
     * @return the initialized component instance
     */
    public Gauge getBarra() {
        if (barra == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            barra = new Gauge("Dificultad:", true, 9, 8);//GEN-BEGIN:|23-getter|1|23-postInit
            barra.setLayout(ImageItem.LAYOUT_CENTER | Item.LAYOUT_TOP | Item.LAYOUT_BOTTOM | Item.LAYOUT_VCENTER | Item.LAYOUT_2);
            barra.setPreferredSize(-1, -1);//GEN-END:|23-getter|1|23-postInit
            // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return barra;
    }
    //</editor-fold>//GEN-END:|23-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: botAceptar ">//GEN-BEGIN:|20-getter|0|20-preInit
    /**
     * Returns an initiliazed instance of botAceptar component.
     * @return the initialized component instance
     */
    public Command getBotAceptar() {
        if (botAceptar == null) {//GEN-END:|20-getter|0|20-preInit
            // write pre-init user code here
            botAceptar = new Command("Aceptar", "Aceptar Cambios", Command.OK, 0);//GEN-LINE:|20-getter|1|20-postInit
            // write post-init user code here
        }//GEN-BEGIN:|20-getter|2|
        return botAceptar;
    }
    //</editor-fold>//GEN-END:|20-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: botIniciar ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initiliazed instance of botIniciar component.
     * @return the initialized component instance
     */
    public Command getBotIniciar() {
        if (botIniciar == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            botIniciar = new Command("Iniciar", "Iniciar Partida", Command.ITEM, 0);//GEN-LINE:|25-getter|1|25-postInit
            // write post-init user code here
        }//GEN-BEGIN:|25-getter|2|
        return botIniciar;
    }
    //</editor-fold>//GEN-END:|25-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            stringItem = new StringItem("Ayuda:", "Seleccione el nivel de dificultad que desea para el juego.", Item.PLAIN);//GEN-LINE:|27-getter|1|27-postInit
            // write post-init user code here
        }//GEN-BEGIN:|27-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|27-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            exitCommand = new Command("Salir", "Salir del Juego", Command.EXIT, 0);//GEN-LINE:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|28-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem1 ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initiliazed instance of stringItem1 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem1() {
        if (stringItem1 == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            stringItem1 = new StringItem("Serpentina Version 0.3.1:", "Serpentina es un clon del juego snake que suelen tener instalados los primeros celulares nokia. Si activa las Paredes recibir\u00E1 puntos extra. Si activa el modo \"Vibora Hambrienta\" se bonificar\u00E1 el alcance r\u00E1pido de los objetivos. Gr\u00E1ficos SVG: Diego Soppelsa. Autor: Juan Alberto L\u00F3pez Cavallotti.", Item.PLAIN);//GEN-BEGIN:|32-getter|1|32-postInit
            stringItem1.setFont(getFont());//GEN-END:|32-getter|1|32-postInit
            // write post-init user code here
        }//GEN-BEGIN:|32-getter|2|
        return stringItem1;
    }
    //</editor-fold>//GEN-END:|32-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: Opciones ">//GEN-BEGIN:|33-getter|0|33-preInit
    /**
     * Returns an initiliazed instance of Opciones component.
     * @return the initialized component instance
     */
    public Form getOpciones() {
        if (Opciones == null) {//GEN-END:|33-getter|0|33-preInit
            // write pre-init user code here
            Opciones = new Form("Opciones de Juego", new Item[] { getCampoNombre(), getOptControles(), getOptSonido(), getOptVibracion(), getOptParedes(), getOptHambrienta() });//GEN-BEGIN:|33-getter|1|33-postInit
            Opciones.addCommand(getBotAtras());
            Opciones.setCommandListener(this);//GEN-END:|33-getter|1|33-postInit
            // write post-init user code here



        }//GEN-BEGIN:|33-getter|2|
        return Opciones;
    }
    //</editor-fold>//GEN-END:|33-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: botOpciones ">//GEN-BEGIN:|40-getter|0|40-preInit
    /**
     * Returns an initiliazed instance of botOpciones component.
     * @return the initialized component instance
     */
    public Command getBotOpciones() {
        if (botOpciones == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
            botOpciones = new Command("Opciones", "Configuraciones del Juego", Command.ITEM, 0);//GEN-LINE:|40-getter|1|40-postInit
            // write post-init user code here
        }//GEN-BEGIN:|40-getter|2|
        return botOpciones;
    }
    //</editor-fold>//GEN-END:|40-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: botAtras ">//GEN-BEGIN:|43-getter|0|43-preInit
    /**
     * Returns an initiliazed instance of botAtras component.
     * @return the initialized component instance
     */
    public Command getBotAtras() {
        if (botAtras == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
            botAtras = new Command("Volver", Command.BACK, 0);//GEN-LINE:|43-getter|1|43-postInit


            configurarJuego();


            // write post-init user code here
        }//GEN-BEGIN:|43-getter|2|
        return botAtras;
    }
    //</editor-fold>//GEN-END:|43-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: optControles ">//GEN-BEGIN:|48-getter|0|48-preInit
    /**
     * Returns an initiliazed instance of optControles component.
     * @return the initialized component instance
     */
    public ChoiceGroup getOptControles() {
        if (optControles == null) {//GEN-END:|48-getter|0|48-preInit
            // write pre-init user code here
            optControles = new ChoiceGroup("Controles:", Choice.MULTIPLE);//GEN-BEGIN:|48-getter|1|48-postInit
            optControles.append("Flechas", null);
            optControles.append("Numeros", null);
            optControles.setSelectedFlags(new boolean[] { true, false });//GEN-END:|48-getter|1|48-postInit
            // write post-init user code here
        }//GEN-BEGIN:|48-getter|2|
        return optControles;
    }
    //</editor-fold>//GEN-END:|48-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: optSonido ">//GEN-BEGIN:|51-getter|0|51-preInit
    /**
     * Returns an initiliazed instance of optSonido component.
     * @return the initialized component instance
     */
    public ChoiceGroup getOptSonido() {
        if (optSonido == null) {//GEN-END:|51-getter|0|51-preInit
            // write pre-init user code here
            optSonido = new ChoiceGroup("Sonido:", Choice.MULTIPLE);//GEN-BEGIN:|51-getter|1|51-postInit
            optSonido.append("Activado", null);
            optSonido.setSelectedFlags(new boolean[] { true });//GEN-END:|51-getter|1|51-postInit
            // write post-init user code here
        }//GEN-BEGIN:|51-getter|2|
        return optSonido;
    }
    //</editor-fold>//GEN-END:|51-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: optVibracion ">//GEN-BEGIN:|53-getter|0|53-preInit
    /**
     * Returns an initiliazed instance of optVibracion component.
     * @return the initialized component instance
     */
    public ChoiceGroup getOptVibracion() {
        if (optVibracion == null) {//GEN-END:|53-getter|0|53-preInit
            // write pre-init user code here
            optVibracion = new ChoiceGroup("Vibracion:", Choice.MULTIPLE);//GEN-BEGIN:|53-getter|1|53-postInit
            optVibracion.append("Activado", null);
            optVibracion.setSelectedFlags(new boolean[] { true });//GEN-END:|53-getter|1|53-postInit
            // write post-init user code here
        }//GEN-BEGIN:|53-getter|2|
        return optVibracion;
    }
    //</editor-fold>//GEN-END:|53-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: optParedes ">//GEN-BEGIN:|55-getter|0|55-preInit
    /**
     * Returns an initiliazed instance of optParedes component.
     * @return the initialized component instance
     */
    public ChoiceGroup getOptParedes() {
        if (optParedes == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
            optParedes = new ChoiceGroup("Paredes:", Choice.MULTIPLE);//GEN-BEGIN:|55-getter|1|55-postInit
            optParedes.append("Activado", null);
            optParedes.setSelectedFlags(new boolean[] { false });//GEN-END:|55-getter|1|55-postInit
            // write post-init user code here
        }//GEN-BEGIN:|55-getter|2|
        return optParedes;
    }
    //</editor-fold>//GEN-END:|55-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: optHambrienta ">//GEN-BEGIN:|57-getter|0|57-preInit
    /**
     * Returns an initiliazed instance of optHambrienta component.
     * @return the initialized component instance
     */
    public ChoiceGroup getOptHambrienta() {
        if (optHambrienta == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
            optHambrienta = new ChoiceGroup("Modo Vibora Hambrienta:", Choice.MULTIPLE);//GEN-BEGIN:|57-getter|1|57-postInit
            optHambrienta.append("Activado", null);
            optHambrienta.setSelectedFlags(new boolean[] { false });//GEN-END:|57-getter|1|57-postInit
            // write post-init user code here
        }//GEN-BEGIN:|57-getter|2|
        return optHambrienta;
    }
    //</editor-fold>//GEN-END:|57-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: font ">//GEN-BEGIN:|59-getter|0|59-preInit
    /**
     * Returns an initiliazed instance of font component.
     * @return the initialized component instance
     */
    public Font getFont() {
        if (font == null) {//GEN-END:|59-getter|0|59-preInit
 // write pre-init user code here
            font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);//GEN-LINE:|59-getter|1|59-postInit
 // write post-init user code here
        }//GEN-BEGIN:|59-getter|2|
        return font;
    }
    //</editor-fold>//GEN-END:|59-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreen ">//GEN-BEGIN:|67-getter|0|67-preInit
    /**
     * Returns an initiliazed instance of splashScreen component.
     * @return the initialized component instance
     */
    public SplashScreen getSplashScreen() {
        if (splashScreen == null) {//GEN-END:|67-getter|0|67-preInit
 // write pre-init user code here
            splashScreen = new SplashScreen(getDisplay());//GEN-BEGIN:|67-getter|1|67-postInit
            splashScreen.setTitle("splashScreen");
            splashScreen.setCommandListener(this);//GEN-END:|67-getter|1|67-postInit
 // write post-init user code here
        }//GEN-BEGIN:|67-getter|2|
        return splashScreen;
    }
    //</editor-fold>//GEN-END:|67-getter|2|
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: campoNombre ">//GEN-BEGIN:|70-getter|0|70-preInit
    /**
     * Returns an initiliazed instance of campoNombre component.
     * @return the initialized component instance
     */
    public TextField getCampoNombre() {
        if (campoNombre == null) {//GEN-END:|70-getter|0|70-preInit
        // write pre-init user code here
            campoNombre = new TextField("Jugador", null, 32, TextField.ANY);//GEN-LINE:|70-getter|1|70-postInit
        // write post-init user code here
        }//GEN-BEGIN:|70-getter|2|
        return campoNombre;
    }
    //</editor-fold>//GEN-END:|70-getter|2|
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: botRecords ">//GEN-BEGIN:|71-getter|0|71-preInit
    /**
     * Returns an initiliazed instance of botRecords component.
     * @return the initialized component instance
     */
    public Command getBotRecords() {
        if (botRecords == null) {//GEN-END:|71-getter|0|71-preInit
        // write pre-init user code here
            botRecords = new Command("Ver Records", Command.ITEM, 0);//GEN-LINE:|71-getter|1|71-postInit
        // write post-init user code here
        }//GEN-BEGIN:|71-getter|2|
        return botRecords;
    }
    //</editor-fold>//GEN-END:|71-getter|2|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
        GameSettings gs = Parametros.getInstancia().getGameSets();
        String nombreJugador = Parametros.getInstancia().getNombreJugador();

        Parameters.setGameSettings(gs);
        Parameters.setPlayerName(nombreJugador);

        Parameters.store();
    }
    
    public void mostrarPantallaJuego()
    {
        
        Displayable actual = getDisplay().getCurrent();
        PantallaJuego p = new PantallaJuego(getDisplay(), actual);
        ControladorJuego.getInstancia().setPantalla(p);
        getDisplay().setCurrent(p);
    }

    private void verRecords() {
        Displayable actual = getDisplay().getCurrent();
        PantallaRecords pr = new PantallaRecords(getDisplay(), actual);
        getDisplay().setCurrent(pr);
    }
}
