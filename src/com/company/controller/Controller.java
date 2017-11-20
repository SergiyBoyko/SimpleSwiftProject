package com.company.controller;

import com.company.View;
import com.company.model.Ball;
import com.company.model.Field;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Controller extends KeyAdapter {

    private View view;
    private Field field;
    private Ball player;

    private Queue<KeyEvent> keyEvents = new ArrayBlockingQueue<KeyEvent>(50);

    public Controller() {
        view = new View(this);
        field = new Field(400, 465);
        player = new Ball(50, 50, 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (hasKeyEvents())
            keyEvents.add(e);
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.jump(100);
            System.out.println("jump");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.moveLeft();
            System.out.println("left");
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.moveRight();
            System.out.println("right");
        }
//        System.out.print(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println(e.getKeyChar());
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.stopMove();
            System.out.println("stop");
        }
    }

    public void startGame() {
        boolean exit = false;
        while (!exit) {
            AskForExit();

            view.repaint();
            sleep(60);
            player.move(field);
        }

    }

    private void AskForExit() {
        if (this.hasKeyEvents()) {
            if (this.getEventFromTop().getKeyCode() == KeyEvent.VK_ESCAPE) {
                sleep(500);
                if (this.hasKeyEvents() && this.getEventFromTop().getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        }
    }

    public static void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }
    }


    public boolean hasKeyEvents() {
        return !keyEvents.isEmpty();
    }

    public KeyEvent getEventFromTop() {
        return keyEvents.poll();
    }


    public Field getField() {
        return field;
    }

    public Ball getPlayer() {
        return player;
    }

    public View getView() {
        return view;
    }
}
