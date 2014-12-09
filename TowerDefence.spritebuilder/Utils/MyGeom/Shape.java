/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.MyGeom;

import Utils.Point2D;
import java.util.ArrayList;
import java.util.List;

     
    
/**
 *
 * @author Константин
 */
public   class Shape {
    protected List<Point2D> perimetr = new ArrayList<Point2D>(); 
    Shape(){}  
    public Shape(List<Point2D> perimetr)
    {
        for (Point2D point2D : perimetr) 
            this.perimetr.add(point2D); 
    }
    
    
    protected  void initializePoint(){} 
   
    
    public boolean contain(Point2D locPoint)
   {

       int countinter = 0;
       for (int i = 0; i < perimetr.size(); i++)
       {

           Point2D point1 = perimetr.get(i);
           Point2D point2= perimetr.get((i+1) % perimetr.size());
           if(point1.equals(locPoint))
               return true;
           countinter += Point2D.intersectRayHorizontalRightFromLocPoint(point1,point2,locPoint)?1:0;
       }
       if((countinter%2) == 1)
           return true;
       return false;

     /*  Сумма углов, медленный и неправельный.
       double sum = 0;
       double sum2 = 0;
       double EPS = 250;
       for (int i = 0; i < perimetr.size(); i++)
       {
           int nextI = i+1 < perimetr.size()? i+1 : 0;
           double angel2 =  Math.asin(
                 Point2D.rotate(  perimetr.get(nextI),locPoint, perimetr.get(i))
                /
                ( locPoint.distance( perimetr.get(i)) * locPoint.distance( perimetr.get(nextI)))
                                        );

            sum2 += Math.toDegrees(angel2);
     }

     if(Math.abs(sum2) < EPS)
           return false;


       return true;
       */

      // для выпуклого.
      /* int n = perimetr.size();
       if( 
               (Point2D.rotate(perimetr.get(0), perimetr.get(1), perimetr.get(n-1))   < 0)
               || 
               (Point2D.rotate(perimetr.get(0), perimetr.get(n-1), locPoint) ) > 0 )
               return false;
       int p = 1;
       int r = n-1; 
       
       while( (r-p) > 1)
       {
           int q = (p+r)/2;
           if(Point2D.rotate(perimetr.get(0), perimetr.get(q), locPoint) < 0 )
               r = q;
           else 
               p = q;
       }
       
       return ! Point2D.intersect(perimetr.get(0), locPoint, perimetr.get(p), perimetr.get(r));
   */
   }
    
   public boolean contain(Shape shape)    // только для прямоугольников верно работает
   {

       for (Point2D point2D : shape.perimetr) {
           if(!contain(point2D)) return false;
       }
       return  true;
   }

    public boolean intersect(Shape shape) {

        for (int i = 0; i < perimetr.size(); i++)
        {
            Point2D p1 = perimetr.get(i);
            Point2D p2 = perimetr.get(i+1 < perimetr.size()?i+1:0);
            for (int j = 0; j < shape.perimetr.size(); j++) {
                Point2D g1 = shape.perimetr.get(j);
                Point2D g2 = shape.perimetr.get(j+1 < shape.perimetr.size()?j+1:0);

                if(Point2D.intersect(p1,p2,g1,g2))
                    return true;
            }
        }
        return  false;
    }
   
 
    public class ListOfPoint2D
    {
        public ListOfPoint2D nextFirst;
        public ListOfPoint2D nextSecond;
        public ListOfPoint2D prevFirst;
        public ListOfPoint2D prevSecond;
        public Point2D current; 
    }
    
    boolean tryAddIfIntersect(Shape shape) {
         if(this.contain(shape)) return true; 
         if(!this.intersect(shape)) return false;
       
         ShapeAggregator newshape = new ShapeAggregator(this,shape);  
         this.perimetr = newshape.connect().perimetr; 
     
         return  true;
    
    }
}
 
