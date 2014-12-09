/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import Utils.Point2D;
import internalObject.SolderInternal.SolderType;
 
 
 
import java.util.List;
import java.util.Stack;

/**
 *
 * @author ver
 */
public class LevelDTO {

    public List<List<Point2D>> placesToSetTowerNew;

    public LevelDTO( List<Point2D> path, List<Point2D> placesToSetTower, List<UnitGroup> solders) {
        this.placesToSetTower = placesToSetTower;
        this.path = path;
        this.solders = solders;
    }
    
  public List<Point2D  >  placesToSetTower; // Место предпологается хранить в точках с окрестностью.
  public List<Point2D  > path;
    public List<UnitGroup>  solders;
public class BadLevelInfo extends Exception
{

}
    public void checked() throws BadLevelInfo {
        if(solders.isEmpty() ) {
            throw new BadLevelInfo();
        }
        if(path.isEmpty() ) {
            throw new BadLevelInfo();
        }
        if(placesToSetTower.isEmpty() ) {
            throw new BadLevelInfo();
        }
    }
 

    
   
   
}
