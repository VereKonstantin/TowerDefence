/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.MyGeom;

import Utils.Point2D;

/**
 *
 * @author Константин
 */
public class Rectangle2D extends Shape {

    double x;
    double y;
    double xSize;
    double ySize;
    public Rectangle2D(double x, double y, double xSize, double ySize) {
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
        initializePoint();
    }

    @Override
    protected void initializePoint() {
        this.perimetr.add(new Point2D(x,y));
        this.perimetr.add(new Point2D(x+xSize,y));
        this.perimetr.add(new Point2D(x+xSize,y+ySize));
        this.perimetr.add(new Point2D(x,y+ySize));
    }
    
}
