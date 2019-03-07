package Interfaz;

import Clases.FlappyMovement;
import Clases.PipeMovement;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.edisoncor.gui.panel.PanelImage;
import sounds.MySound;

public class Game extends javax.swing.JFrame {

    public static JLabel jFlappy;
    public static JLabel jLabel2;
    public static JLabel jLabel3;
    public static JLabel jLabel4;
    private final JPanel jPanelMain;
    public static JLabel labelScore;
    public PanelImage jFloor;
    public static JLabel jPipe_down1;
    public static JLabel jPipe_down2;
    public static JLabel jPipe_up1;
    public static JLabel jPipe_up2;
    public static PanelImage panelImageGame;
    private FlappyMovement mvnt_flappy;
    private PipeMovement mvnt_pipes;
    private boolean start = false;
    private Score score;
    private final Login login;
    private JPanel panelLogin;
    private JPanel panel2;
    public String name;
    Point flappyPosition;
    int deltaTime = 4;

    public Game() {
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanelMain = new javax.swing.JPanel();
        panelImageGame = new org.edisoncor.gui.panel.PanelImage();
        jFloor = new org.edisoncor.gui.panel.PanelImage();
        labelScore = new javax.swing.JLabel();
        jFlappy = new javax.swing.JLabel();
        jPipe_up1 = new javax.swing.JLabel();
        jPipe_down1 = new javax.swing.JLabel();
        jPipe_up2 = new javax.swing.JLabel();
        jPipe_down2 = new javax.swing.JLabel();
        initComponents();
        this.setLocationRelativeTo(null);
        hideObjects(false);
        login = new Login();
        showLogin();
        EventosExternos();
        this.setTitle("FLAPPY BIRD");
        score = new Score(this);
        flappyPosition = jFlappy.getLocation();
        this.jPanelMain.setSize(400, 607);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanelMain.setBackground(new java.awt.Color(255, 255, 255));

        panelImageGame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/background.png"))); // NOI18N
        panelImageGame.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelImage1MousePressed(evt);
            }
        });
        panelImageGame.setLayout(null);

        jFloor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/floor.png"))); // NOI18N

        javax.swing.GroupLayout gl_jFloor = new javax.swing.GroupLayout(jFloor);
        jFloor.setLayout(gl_jFloor);
        gl_jFloor.setHorizontalGroup(
                gl_jFloor.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 580, Short.MAX_VALUE)
        );
        gl_jFloor.setVerticalGroup(
                gl_jFloor.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 180, Short.MAX_VALUE)
        );

        panelImageGame.add(jFloor);
        jFloor.setBounds(-130, 470, 580, 180);

        labelScore.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        labelScore.setForeground(new java.awt.Color(255, 255, 255));
        labelScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelScore.setText("0");
        panelImageGame.add(labelScore);
        labelScore.setBounds(0, 10, 400, 49);

        jFlappy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/flappy.png"))); // NOI18N
        jFlappy.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFlappyKeyReleased(evt);
            }
        });
        panelImageGame.add(jFlappy);
        jFlappy.setBounds(70, 230, 34, 24);

        jPipe_up1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pipe1.png"))); // NOI18N
        panelImageGame.add(jPipe_up1);
        jPipe_up1.setBounds(70, -120, 52, 320);

        jPipe_down1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pipe2.png"))); // NOI18N
        panelImageGame.add(jPipe_down1);
        jPipe_down1.setBounds(70, 280, 52, 320);

        jPipe_up2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Pipe1.png"))); // NOI18N
        panelImageGame.add(jPipe_up2);
        jPipe_up2.setBounds(290, -120, 52, 320);

        jPipe_down2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pipe2.png"))); // NOI18N
        panelImageGame.add(jPipe_down2);
        jPipe_down2.setBounds(290, 280, 52, 320);

        javax.swing.GroupLayout gl_jPanelMain = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(gl_jPanelMain);
        gl_jPanelMain.setHorizontalGroup(
                gl_jPanelMain.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gl_jPanelMain.createSequentialGroup()
                        .addComponent(panelImageGame, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
        );
        gl_jPanelMain.setVerticalGroup(
                gl_jPanelMain.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelImageGame, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pack();
    }

    private void jFlappyKeyReleased(java.awt.event.KeyEvent evt) {
        if (start) {
            if (evt.getExtendedKeyCode() == 32) {
                this.mvnt_flappy.setDeltaTime(decreaseDeltaTime());
                this.mvnt_flappy.setJump(true);
                jFlappy.requestFocus(true);
                MySound.saltar();
            }
            validarChoqueTubos();
        }
    }

    private void panelImage1MousePressed(java.awt.event.MouseEvent evt) {
        if (start) {
            this.mvnt_flappy.setDeltaTime(decreaseDeltaTime());
            this.mvnt_flappy.setJump(true);
            jFlappy.requestFocus(true);
            MySound.saltar();
        }
    }

    private int decreaseDeltaTime() {
        int puntos = Integer.parseInt(labelScore.getText());
        if (puntos == 150 || puntos == 300) {
            deltaTime = deltaTime - 1;
        }
        return deltaTime;
    }

    private void EventosExternos() {
        login.jButton1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        name = this.login.jTextField1.getText();
        this.panelLogin.setVisible(false);
        Empezar();
        hideObjects(true);
    }

    public void Empezar() {
        mvnt_pipes = new PipeMovement();
        mvnt_flappy = new FlappyMovement(this);
        mvnt_pipes.start();
        mvnt_flappy.start();
        start = true;
        jFlappy.requestFocus();
        this.setTitle("FLAPPY BIRD - " + name);
    }

    public void validarChoqueTubos() {
        Point loclz_Flappy = jFlappy.getLocation();
        Point loclz_Tubo1 = jPipe_up1.getLocation();
        Point loclz_Tubo2 = jPipe_up2.getLocation();
        Point loclz_Tubo3 = jPipe_down1.getLocation();
        Point loclz_Tubo4 = jPipe_down2.getLocation();
        if (loclz_Flappy.x > (loclz_Tubo1.x - 32) && loclz_Flappy.x < ((loclz_Tubo1.x - 32) + 82) && loclz_Flappy.y < (loclz_Tubo1.y + 318)) {
            MySound.choque();
            this.mvnt_pipes.interrupt();
            this.mvnt_flappy.setJump(false);
            start = false;
            MySound.caida();
        } else if (loclz_Flappy.x > (loclz_Tubo2.x - 32) && loclz_Flappy.x < ((loclz_Tubo2.x - 32) + 82) && loclz_Flappy.y < (loclz_Tubo2.y + 318)) {
            MySound.choque();
            this.mvnt_pipes.interrupt();
            this.mvnt_flappy.setJump(false);
            start = false;
            MySound.caida();
        } else if (loclz_Flappy.x > (loclz_Tubo3.x - 32) && loclz_Flappy.x < ((loclz_Tubo3.x - 32) + 82) && loclz_Flappy.y > (loclz_Tubo3.y - 22)) {
            MySound.choque();
            this.mvnt_pipes.interrupt();
            this.mvnt_flappy.setJump(false);
            start = false;
            MySound.caida();
        } else if (loclz_Flappy.x > (loclz_Tubo4.x - 32) && loclz_Flappy.x < ((loclz_Tubo4.x - 32) + 82) && loclz_Flappy.y > (loclz_Tubo4.y - 22)) {
            MySound.choque();
            this.mvnt_pipes.interrupt();
            this.mvnt_flappy.setJump(false);
            start = false;
            MySound.caida();
        }
    }

    private void hideObjects(boolean action) {
        jFlappy.setVisible(action);
        jPipe_down1.setVisible(action);
        jPipe_down2.setVisible(action);
        jPipe_up1.setVisible(action);
        jPipe_up2.setVisible(action);
        labelScore.setVisible(action);
    }

    public synchronized void detectColision() {
        int y = jFlappy.getLocation().y;
        if (y == 448) {
            if (MySound.terminochoque) {
                MySound.choque();
            }
            GamerOver gamerover = new GamerOver(this, true);
            try {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        mvnt_pipes.interrupt();
                        FlappyMovement.stopThread = true;
                        interrupt();
                    }
                };
                thread.start();
            } catch (Exception e) {
            }
            gamerover.setVisible(true);
        }
    }

    public void showScore() {
        MySound.terminocaida = true;
        MySound.terminochoque = true;
        hideObjects(false);
        panel2 = new JPanel();
        panel2.setBounds(0, 0, this.getWidth(), 550);
        panel2.add(score);
        score.setBounds(0, 0, panel2.getWidth(), panel2.getHeight());
        panelImageGame.add(panel2);
        panel2.setBackground(new Color(255, 255, 153));
        score.setVisible(true);
        score.mostrar();
        panel2.setVisible(true);
    }

    public void startAgain() {
        jFlappy.setLocation(flappyPosition);
        labelScore.setText("0");
        this.panel2.setVisible(false);
        Empezar();
        hideObjects(true);
        score = new Score(this);
    }

    private void showLogin() {
        panelLogin = new JPanel();
        panelLogin.setBounds(10, 200, 380, 140);
        panelLogin.add(login);
        login.setBounds(0, 0, panelLogin.getWidth(), panelLogin.getHeight());
        panelImageGame.add(panelLogin);
        panelLogin.setBackground(new Color(255, 255, 153));
        login.setVisible(true);
        panelLogin.setVisible(true);
    }

}
