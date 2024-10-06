import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanelll extends JPanel implements ActionListener {
    public static final long serialVersionUID = 1L;

    static final int WIDTH = 500;
    static final int HEIGHT = 500;
    static final int UNIT_SIZE = 20;
    static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / UNIT_SIZE;
    static final int DELAY = 400;
    
    final int x[] = new int[NUMBER_OF_UNITS];
    final int y[] = new int[NUMBER_OF_UNITS];

    int length = 5;
    int FoodEaten;
    int FoodX;
    int FoodY;
    char direction = 'D';
    boolean running = false;
    Random random;
    Timer timer;

    GamePanelll() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.darkGray);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        play();

    }
    public void play() {
        addFood();
        running = true;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

   

    public void draw(Graphics graphics) {
        if (running) {
            graphics.setColor(new Color(210, 115, 90));
            graphics.fillOval(FoodX, FoodY, UNIT_SIZE, UNIT_SIZE);

            graphics.setColor(Color.WHITE);
            graphics.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);

            for (int i = 1; i < length; i++) {
                graphics.setColor(new Color(40, 200, 150));
                graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + FoodEaten, (WIDTH - metrics.stringWidth("Score: " + FoodEaten)) / 2, graphics.getFont().getSize());

        }else {
            gameOver(graphics);
        }
    }
    
    public void move() {
        for (int i = length; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if (direction == 'L'){
            x[0] = x[0] - UNIT_SIZE;
        }else if (direction == 'R') {
            x[0] = x[0] + UNIT_SIZE;
        }else if (direction == 'U'){
            y[0] = y[0] - UNIT_SIZE;
        }else{
            y[0] = y[0] + UNIT_SIZE;
        }
    }

    public void checkFood() {
        if (x[0] == FoodX && y[0] == FoodY) {
            length++;
            FoodEaten++;
            addFood();
        }
    }

    public void addFood() {
        FoodX = random.nextInt((int)(WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        FoodY = random.nextInt((int)(HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void checkHit() {
        for (int i = length; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }

        if (x[0] < 0 || x[0] > WIDTH || y[0] < 0 || y[0] > HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 50));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over " + FoodEaten, (WIDTH - metrics.stringWidth("Game Over " + FoodEaten)) / 2, graphics.getFont().getSize());

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
        metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Score: " + FoodEaten, (WIDTH - metrics.stringWidth("Score: " + FoodEaten)) / 2, graphics.getFont().getSize());
 
    }

    public void actionPerformed(ActionEvent arg0) {
        if (running) {
            move();
            checkFood();
            checkHit();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R'){
                        direction = 'L';
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;

                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'O';
                    }
                    break;
            }
        }
    }  
}

