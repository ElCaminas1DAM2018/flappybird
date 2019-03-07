package Clases;

import Interfaz.Game;
import sounds.MySound;

public class PipeMovement extends Thread {

    static int deltaTime;
    private static int score;

    public PipeMovement() {
        deltaTime = 7;
        score = 0;
        hidePipes();
    }

    private static synchronized void addPoints() {
        int pipe1 = Game.jPipe_up1.getLocation().x;
        int pipe2 = Game.jPipe_up2.getLocation().x;
        if (pipe1 == 90) {
            MySound.points();
            score = score + 1;
            Game.labelScore.setText(score + "");
            if (score == 30 || score == 50 || score == 130 || score == 160 || score == 200 || score == 300) {
                deltaTime = deltaTime - 1;
            }
        } else if (pipe2 == 90) {
            MySound.points();
            score = score + 1;
            Game.labelScore.setText(score + "");
            if (score == 30 || score == 50 || score == 130 || score == 160 || score == 200 || score == 300) {
                deltaTime = deltaTime - 1;
            }
        }
    }

    @Override
    public void run() {
        int position1 = generateRandomPosition();
        int position2 = generateRandomPosition();
        int x1 = Game.jPipe_up1.getLocation().x;
        int x2 = Game.jPipe_up2.getLocation().x;
        while (true) {
            try {
                Thread.sleep(getDeltaTime());
                x1--;
                x2--;
                Game.jPipe_up1.setLocation(x1, position1);
                Game.jPipe_down1.setLocation(x1, (position1 + 425));
                Game.jPipe_up2.setLocation(x2, position2);
                Game.jPipe_down2.setLocation(x2, (position2 + 425));
                if (x1 <= -51) {
                    position1 = generateRandomPosition();
                    x1 = 425;
                }
                if (x2 <= -51) {
                    position2 = generateRandomPosition();
                    x2 = 425;
                }
                addPoints();
            } catch (InterruptedException ex) {
                break; //We quit the loop to stop moving pipes
            }
        }
    }

    private void hidePipes() {
        Game.jPipe_up1.setLocation(460, 500);
        Game.jPipe_down1.setLocation(460, 1000);
        Game.jPipe_up2.setLocation(700, 500);
        Game.jPipe_down2.setLocation(700, 1000);
    }

    public int getDeltaTime() {
        return deltaTime;
    }

    private int generateRandomPosition() {
        int numero = (int) (- Math.random() * 200);
        return numero;
    }

}
