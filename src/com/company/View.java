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
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        drawField(g);
        drawPlayer(g);
        g.setFont(new Font("Arial", Font.ITALIC, 26));
        g.drawString("Use Arrows", 420, 200);
        g.drawString("for control", 420, 250);
    }

    private void drawField(Graphics g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(1, 1, controller.getField().getWidth(), controller.getField().getHeight());
    }

    private void drawPlayer(Graphics g2) {
        g2.setColor(BALL_COLOR);

        Ball ball = controller.getPlayer();

        g2.fillOval(ball.getX() - ball.getRadius(), ball.getY() - ball.getRadius(), ball.getRadius() * 2, ball.getRadius() * 2);

        g2.setColor(Color.BLACK);

        g2.drawOval(ball.getX() - ball.getRadius(), ball.getY() - ball.getRadius(), ball.getRadius() * 2, ball.getRadius() * 2);
    }

}
