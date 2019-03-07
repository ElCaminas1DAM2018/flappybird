package Clases;

import Interfaz.Game;

public class FlappyMovement extends Thread {

    private int deltaTime;
    private boolean jump;
    private boolean stopJump1 = false;
    private boolean stopJump2 = true;
    private boolean jumping = false;
    private final Game parent;
    public static boolean stopThread;

    public FlappyMovement(Game parent) {
        this.deltaTime = 10;
        this.parent = parent;
    }

    @Override
    public void run() {
        int varA = 1;
        stopThread = false;
        while (true) {
            if (stopThread) {
                break;
            }
            int x = Game.jFlappy.getLocation().x;
            if (!isJump()) {
                int y = Game.jFlappy.getLocation().y;
                try {
                    Thread.sleep(getDeltaTime());
                    Game.jFlappy.setLocation(x, (y + 1));
                    if (deltaTime > 3) {
                        if (varA % 15 == 0) {
                            deltaTime = deltaTime - 1;
                        }
                        varA = varA + 1;
                    }
                    parent.validarChoqueTubos();
                } catch (InterruptedException e) {
                    System.out.println("Ocurrio un problema " + e);
                }
            } else {
                if (!jumping) {
                    setStopJump1(false);
                    setStopJump2(true);
                    jumping = true;
                    jump1();
                } else {
                    setStopJump1(true);
                    setStopJump2(false);
                    jumping = false;
                    jump2();
                }
            }
            this.parent.detectColision();
        }
    }

    private void jump1() {
        int tiempo_salto = 1;
        while (true) {
            int y = Game.jFlappy.getLocation().y;
            int x = Game.jFlappy.getLocation().x;
            try {
                Thread.sleep(getDeltaTime());
                if (!isStopJump1()) {
                    tiempo_salto = tiempo_salto + 1;
                    if (tiempo_salto <= 60) {
                        Game.jFlappy.setLocation(x, (y - 1));
                        if (tiempo_salto % 20 == 0) {
                            deltaTime = deltaTime - 1;
                        }
                    } else if (tiempo_salto >= 70) {
                        setJump(false);
                        setDeltaTime(7);
                        break;
                    }
                    parent.validarChoqueTubos();
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                System.out.println("Ocurrio un error " + e);
            }
        }
    }

    private void jump2() {
        int jumpTime = 1;
        while (true) {
            int y = Game.jFlappy.getLocation().y;
            int x = Game.jFlappy.getLocation().x;
            try {
                Thread.sleep(getDeltaTime());
                if (!isStopJump2()) {
                    jumpTime = jumpTime + 1;
                    if (jumpTime <= 60) {
                        Game.jFlappy.setLocation(x, (y - 1));
                        if (jumpTime % 20 == 0) {
                            deltaTime = deltaTime - 1;
                        }
                    } else if (jumpTime >= 70) {
                        setJump(false);
                        setDeltaTime(7);
                        break;
                    }
                    parent.validarChoqueTubos();
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                System.out.println("Ocurrio un error " + e);
            }
        }
    }

    public boolean isStopJump1() {
        return stopJump1;
    }

    public void setStopJump1(boolean stopJump1) {
        this.stopJump1 = stopJump1;
    }

    public boolean isStopJump2() {
        return stopJump2;
    }

    public void setStopJump2(boolean stopJump2) {
        this.stopJump2 = stopJump2;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public int getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(int deltaTime) {
        this.deltaTime = deltaTime;
    }

    public boolean isStopThread() {
        return stopThread;
    }

}
