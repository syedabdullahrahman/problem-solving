package com.abdullah.ui;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

import java.util.ArrayList;

import java.util.Random;

public class SwingTowerDefense extends JPanel {

    private final int WIDTH = 800;

    private final int HEIGHT = 600;

    private final int TILE_SIZE = 50;

    private final int INITIAL_ENEMY_SPEED = 2;

    private ArrayList<Tower> towers = new ArrayList<>();

    private ArrayList<Enemy> enemies = new ArrayList<>();

    private int enemySpeed = INITIAL_ENEMY_SPEED;

    private Random random = new Random();

    public SwingTowerDefense() {

        

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {

            @Override

            public void mouseClicked(MouseEvent e) {

                int x = e.getX() - (e.getX() % TILE_SIZE);

                int y = e.getY() - (e.getY() % TILE_SIZE);

                towers.add(new Tower(x, y));

                repaint();

            }

        });

        Timer timer = new Timer(30, e -> {

            spawnEnemies();

            moveEnemies();

            towersAttack();

            increaseEnemySpeed();

            repaint();

        });

        timer.start();

    }

    private void spawnEnemies() {

        if (enemies.size() < 10) {

            int y = random.nextInt(HEIGHT - TILE_SIZE);

            enemies.add(new Enemy(0, y, enemySpeed));

        }

    }

    private void moveEnemies() {

        for (Enemy enemy : enemies) {

            enemy.move();

        }

        enemies.removeIf(enemy -> enemy.getX() > WIDTH);

    }

    private void towersAttack() {

        for (Tower tower : towers) {

            for (Enemy enemy : enemies) {

                if (tower.isInRange(enemy)) {

                    tower.shoot(enemy);

                    break;

                }

            }

        }

        enemies.removeIf(enemy -> enemy.getHealth() <= 0);

    }

    private void increaseEnemySpeed() {

        if (enemySpeed < 10) {

            enemySpeed += 0.001;

        }

    }

    @Override

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < WIDTH; i += TILE_SIZE) {

            for (int j = 0; j < HEIGHT; j += TILE_SIZE) {

                g.drawRect(i, j, TILE_SIZE, TILE_SIZE);

            }

        }

        g.setColor(Color.BLUE);

        for (Tower tower : towers) {

            g.fillRect(tower.getX(), tower.getY(), TILE_SIZE, TILE_SIZE);

        }

        g.setColor(Color.RED);

        for (Enemy enemy : enemies) {

            g.fillOval(enemy.getX(), enemy.getY(), TILE_SIZE / 2, TILE_SIZE / 2);

        }

    }

    class Tower {

        private int x, y;

        private final int range = 100;

        public Tower(int x, int y) {

            this.x = x;

            this.y = y;

        }

        public int getX() { return x; }

        public int getY() { return y; }

        public boolean isInRange(Enemy enemy) {

            double distance = Math.hypot(enemy.getX() - x, enemy.getY() - y);

            return distance <= range;

        }

        public void shoot(Enemy enemy) {

            enemy.takeDamage(1);

        }

    }

    class Enemy {

        private int x, y;

        private double speed;

        private int health = 5;

        public Enemy(int x, int y, double speed) {

            this.x = x;

            this.y = y;

            this.speed = speed;

        }

        public int getX() { return x; }

        public int getY() { return y; }

        public int getHealth() { return health; }

        public void move() {

            x += speed;

        }

        public void takeDamage(int damage) {

            health -= damage;

        }

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Swing Tower Defense");

        SwingTowerDefense game = new SwingTowerDefense();

        frame.add(game);

        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

    }

}
