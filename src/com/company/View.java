package com.company;

import com.company.controller.Controller;
import com.company.model.Ball;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private static final Color BALL_COLOR = new Color(0x00ffbb);


    private Controller controller;

    public View(Controller controller) {
        setFocusable(true);
        this.controller = controller;
        addKeyListener(controller);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        drawField(g);
        drawPlayer(g);
    }

    private void drawField(Graphics g2) {
        g2.setColor(Color.BLACK);
        g2.drawRect(1, 1, controller.getField().getWidth(), controller.getField().getHeight());
    }

    private void drawPlayer(Graphics g2) {
        g2.setColor(BALL_COLOR);

        Ball ball = controller.getPlayer();

        g2.fillOval(ball.getX() - ball.getRadius(), ball.getY() - ball.getRadius(), ball.getRadius() * 2, ball.getRadius() * 2);

        g2.setColor(Color.BLACK);

        g2.drawOval(ball.getX() - ball.getRadius(), ball.getY() - ball.getRadius(), ball.getRadius() * 2, ball.getRadius() * 2);
    }

}
