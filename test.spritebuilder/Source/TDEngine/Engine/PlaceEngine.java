/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import Unit.Tower.Tower;
import Utils.Point2D;
import internalObject.StaticSettings;
import Utils.MyGeom.Polygon;
import Utils.MyGeom.Shape;
import Utils.MyGeom.Area;
 
 
import Utils.MyGeom.Rectangle2D;
import java.util.List;

/**
 *
 * @author vereshchagin
 */
    class PlaceEngine {

    
   // List<Double> placesToSetTower;
    Utils.MyGeom.Area areaToSetTower;
    Utils.MyGeom.Area areaSetEdTower;
   
    public PlaceEngine(List<List<Point2D>> placesToSetTower,byte notneedParamForUndestendThatThisIsThePOligonCOnstructor) {
        areaToSetTower = new  Area();
        areaSetEdTower = new  Area();
        for (List<Point2D> list : placesToSetTower) {
           addPoligon(list);  
        }
    }
    
    public PlaceEngine(List<Point2D> placesToSetTower) {
        areaToSetTower = new  Area();
        areaSetEdTower = new  Area();
        calcArea(placesToSetTower);
    }
  

    boolean canAddTower(Tower newTower) {
        if(!testOnEmptyPlace(newTower))
            return  false;
        if(!testOnNearTower(newTower))
            return false;
        return true;
    }

    private boolean testOnEmptyPlace(Tower newTower) {
        
        Utils.MyGeom.Rectangle2D rect = newTower.getNeedPlace();
        
        return areaToSetTower.contains(rect);
         
    }
    private void addPoligon(List<Point2D> placesToSetTower) {
        int size = placesToSetTower.size();
       int[] arrx = new int[size]; 
       int[] arry = new int[size]; 
 
              int i = 0; 
        for (Point2D point2D : placesToSetTower) {
            arrx[i] = (int) point2D.x;
            arry[i] = (int) point2D.y; 
            i++;
        }
        Shape p;
            p = new Polygon(arrx,arry,size);
         areaToSetTower.add(new Area(p));   
    }
    private void calcArea(List<Point2D> placesToSetTower) {
        double sizePlacePoint = StaticSettings.sizePlacePoint;
       
       
        for (Point2D double1 : placesToSetTower) {
            Area newarea = new  Area(new Rectangle2D(
                     double1.x - sizePlacePoint/2,double1.y - sizePlacePoint/2,
                      sizePlacePoint, sizePlacePoint
                     ));
            areaToSetTower.add(newarea); 
        }
    }

    void addTower(Tower newTower) {
       
        Area newarea = new  Area( 
                 newTower.getNeedPlace()
                    );
      areaSetEdTower.add(newarea);
    }

    private boolean testOnNearTower(Tower newTower) {
        Rectangle2D rect = newTower.getNeedPlace();       
        return !areaSetEdTower.intersects(rect);
    }

    
    
    

     
    
}
