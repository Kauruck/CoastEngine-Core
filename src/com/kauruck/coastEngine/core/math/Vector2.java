package com.kauruck.coastEngine.core.math;
//Vector math from https://www.mathsisfun.com/algebra/vectors.html [12.0.2021]
//https://www.mathsisfun.com/algebra/vectors-dot-product.html [12.01.2021]
//https://www.mathsisfun.com/algebra/vectors-cross-product.html [12.01.2021]
//https://stackoverflow.com/questions/243945/calculating-a-2d-vectors-cross-product [12.01.2021]
//https://matthew-brett.github.io/teaching/rotation_2d.html[13.01.2021]
//https://en.wikipedia.org/wiki/Rotation_matrix[13.01.2021]

/**
 * A point in 2d space as offset from 0|0
 */
public class Vector2 {

    private double x;
    private double y;

    /**
     * Creates a zero vector2
     */
    public Vector2(){
        x = 0;
        y = 0;
    }

    /**
     * Creates a vector2 with x|y
     * @param x x
     * @param y y
     */
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Adds to Vectors
     * @param b The second vector
     * @return The result
     */
    public Vector2 add(Vector2 b){
        return new Vector2(this.x + b.x, this.y + b.y);
    }

    /**
     * Subtracts to vectors
     * @param b The vector to subtract
     * @return The result
     */
    public Vector2 subtract(Vector2 b){
        return new Vector2(this.x  - b.x, this.y - b.y);
    }

    /**
     * Calculates the crossProdct
     * @param b The second vector
     * @return The crossProduct
     */
    public Vector3 crossProduct(Vector2 b){
        return new Vector3(0, 0, this.x * b.y - this.y * b.x);
    }

    /**
     * Calculates the dotProduct
     * @param b The second vector
     * @return The dotProduct
     */
    public double dotProduct(Vector2 b){
        return this.x * b.x + this.y * b.y;
    }

    public Vector2 rotateAround(Vector2 center, double deg){
        double rad = Math.toRadians(deg);
        Vector2 centerd = this.subtract(center);
        double x1 = centerd.getX();
        double y1 = centerd.getY();
        double x2 = x1*Math.cos(rad) - y1*Math.sin(rad);
        double y2 = x1*Math.sin(rad) + y1*Math.cos(rad);
        return new Vector2(x2,y2).add(center);
    }

    /**
     * Scales the vector
     * @param scalar The scalar
     * @return The scaled vector
     */
    public Vector2 scale(double scalar){
        return new Vector2(x*scalar,y*scalar);
    }

    /**
     * Calculates the magnitude
     * @return The magnitude
     */
    public double magnitude(){
        return Math.sqrt(x*x+y*y);
    }

    /**
     * Gets x
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * Sets x
     * @param x value of x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets y
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y
     * @param y value of y
     */
    public void setY(double y) {
        this.y = y;
    }
}
