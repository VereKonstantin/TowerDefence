/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

 
import Utils.Point2D; 
import java.util.List;

/**
 *
 * @author vereshchagin
 */
public class PathEngine {
   
    public Point2D  getStartPoint(){
       return  path.get(0);
   }
 
   Point2D getEnd(Point2D x)
   {
       for (int i = 0; i < (path.size()-1); i++) {
          Point2D p1 = path.get(i);
          Point2D p2 = path.get(i+1);
          if((p1.x <= x.x) &&  (p2.x >=  x.x))
          {
              if((p1.y <= x.y) &&  (p2.y >=  x.y))
          {
            if(p2.x == x.x)
                if(p2.y == x.y) 
                  continue;
              return p2;
          }
          }  
       } 
       return null;
   }
  
     private Point2D getVec(Point2D start, Point2D end, double dist) {
        double dx=end.x-start.x;
        double dy= end.y-start.y; 
        double dxy =   Math.sqrt(dx*dx + dy*dy);
        double x1 = dist * dx/dxy;
        double x2 = dist * dy/dxy; 
        return new Point2D(x1,x2);
    }
   
   public Point2D  getNextDistance(Point2D  pointStart, float distanse)
   {
        if(path.get(path.size()-1).distance(pointStart) <= 0.0001)
            return null;
        
      Point2D d1=getEnd(pointStart );  

      
      if(d1 == null)
          return null;
      
      
      Point2D  vector = getVec(pointStart,d1,distanse);
  
      double dist = d1.distance(pointStart);
      if( dist < Math.sqrt(vector.x*vector.x + vector.y*vector.y))
          return d1;
      
      return new Point2D(
              pointStart.x + vector.x,
              pointStart.y + vector.y
              ); 
   }
   
    public PathEngine(List<Point2D> path) {
        this.path = path;
    }
List<Point2D > path;

    public Point2D calcNextDistanceForShell(Point2D currentPlace, Point2D targetPoint, double speedShell) {
        Point2D  vector = getVec(currentPlace,targetPoint,speedShell);
         return new Point2D (
              currentPlace.x + vector.x,
              currentPlace.y + vector.y
              ); 
    }

  
     
}
