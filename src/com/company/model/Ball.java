package com.company.model;

public class Ball {
    private int x;
    private int y;
    private int radius;

    private int directionX;
    private int directionY;

    private boolean moveRight = false;
    private boolean moveLeft = false;


    private int beforeJumpY;
    private boolean jumped;
    private int jumpForce;
    private boolean onFloor;

    public Ball(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.directionX = 0;
        this.directionY = 0;
        jumped = false;
        onFloor = true;
        jumpForce = 100;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isJumped() {
        return jumped;
    }

    public void moveLeft() {
        if (!moveRight) moveLeft = true;
    }

    public void moveRight() {
        if (!moveLeft) moveRight = true;
    }

    public void stopMove() {
        directionX = 0;
        moveLeft = false;
        moveRight = false;
    }

    public boolean canMoveHorizontal(Field field) {
        if (field.getWidth() < x + radius ||
                0 > x - radius) return false;
        return true;
    }

    public void jump(int jumpForce) {
        if (jumped || !onFloor) {
            System.out.println(onFloor + " " + jumpForce);
            return;
        }
        this.beforeJumpY = y;
        directionY = -1;
        this.jumpForce = jumpForce;
        jumped = true;
        onFloor = false;
    }

    private void fall() {
        if (directionY <= 30) {
            if (directionY == 0) directionY = 1;
            else directionY *= 2;
        }
    }


    public void move(Field field) {
        if (moveRight || moveLeft) {
            if (directionX == 0) directionX = moveRight ? 1 : -1;
            else if (Math.abs(directionX) < 10) directionX *= 2;
        }
        x += directionX;
        if (!canMoveHorizontal(field)) {
            x -= directionX;
            directionX = 0;
        }
        y += directionY;
        onFloor = checkBottom(field);
        if (jumped && (beforeJumpY - y) > jumpForce) {
            jumped = false;
            directionY = 0;
        } else if (!onFloor) fall();
        else {
            if (jumpForce > 20) {
                jump(jumpForce / 4);
                System.out.println("jump");
            } else directionY = 0;
        }
    }

    private boolean checkBottom(Field field) {
        if (field.getHeight() <= y + radius) {
            y = field.getHeight() - radius;
            return true;
        }
        return false;
    }
}
