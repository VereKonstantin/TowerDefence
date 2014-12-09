/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Tower;

import Engine.PathEngine;
import Engine.SolderEngine;
import Unit.Solder.Solder;
import Utils.Point2D;
import Utils.StackObject;
import internalObject.ShellInternal;
 
 
 
import java.util.List;

/**
 *
 * @author Константин
 */
public class Shell extends StackObject implements ShellInternal{
      public Object userData;
    double speedShell = 8;
    int distanceToExploid = 7;
    Point2D  currentPlace;
    double attackStrong;
    Solder  target;
    public void init(double attackStrong, Solder attackTarget,Point2D currentPlace,double speedShell,int distanceToExploid) {
       this.attackStrong = attackStrong;
       this.target = attackTarget;
       this.currentPlace = currentPlace;
       this.speedShell = speedShell;
       this.distanceToExploid = distanceToExploid; 
    }

    public void DoStep(PathEngine lp,SolderEngine sold) {
     
        if( !exploided )
        { 
         Point2D pointToNext = target.getCurrentState();
        if(pointToNext == null)
        {    exploid(sold);
             return;
        }
        
        if(this.currentPlace.distance(pointToNext) < distanceToExploid) {
            exploid(sold);
            return;
        }
        
        this.currentPlace =   lp.calcNextDistanceForShell( this.currentPlace,
              target.getCurrentState(),speedShell);
           if(this.currentPlace == null)
                  exploid(sold);
        }
      
    }
      boolean exploided = false;
      public boolean isExploided(){return exploided;}
    private void exploid(SolderEngine sold) {
        exploided = true;
      List<Solder> targets =  sold.getSolderNearPlace(this.currentPlace,distanceToExploid);
        for (Solder solder : targets) {
            solder.Hit(attackStrong);
        }
    }

    
    @Override
    public Point2D getCurrent() {
        return this.currentPlace;
    }

    @Override
    public Point2D getTarget() {
     return target.getCurrentState();
    }

    @Override
    public void clearObject() {
       exploided = false;
    }

  

    @Override
    public boolean getIsBlowedOut() {
     return this.isExploided();
    }

  
  

    
}
