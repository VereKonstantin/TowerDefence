/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.MyGeom;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Константин
 */
public class Area {
 public Area( ) {}
    
 
        
         
 List<Shape> shapes = new ArrayList<Shape>();
 List<Shape> compressedShapes = new ArrayList<Shape>();
 public Area(Shape p) {
        shapes.add(p);
        compressedShapes.add(p);
    }


    public boolean contains(Rectangle2D rect) {
        for (Shape shape : compressedShapes) {
           if(shape.contain(rect)) return true;
        }
        return false;
    }

    public void add(Shape shp) {
        List<Shape> shpes = new ArrayList<Shape>();
        shpes.add(shp);
        shapes.addAll(shpes);
        compressShapes(shpes);
    }
    public void add(Area area) {
       shapes.addAll(area.shapes); 
       compressShapes(area.shapes);
    }

    public boolean intersects(Shape rect)
    {
        for (Shape shape : compressedShapes)
          if(shape.intersect(rect))
              return true;

        return false;

    }

    private void compressShapes(List<Shape> shapes) {
        boolean newShape = true;
        for (Shape shape : shapes) {
            for (Shape shape1 : this.compressedShapes) {
                if(shape1.tryAddIfIntersect(shape))
                    newShape = false;
            }
            if(newShape)
                this.compressedShapes.add(shape);
        }
    }

    

  
    
}
