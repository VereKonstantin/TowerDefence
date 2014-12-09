/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Solder;

import Engine.PathEngine;
import Map.LevelDTO;
import Utils.Point2D;
import Utils.StackObject;
import internalObject.EngineOptions;
import internalObject.SolderInternal;
import internalObject.SolderInternal.SolderType;
 

/**
 *
 * @author ver
 */
public abstract class Solder extends StackObject implements SolderInternal {
      
    public static EngineOptions engineOptions = null;
    
    protected int reward;
    protected float Speed = 1;
    protected float Health = 10;
    protected float leaveAfterDeadTime = 10;
    Point2D  currentPlace;
      
    int deadTime = 0;
    
   Solder(Point2D  currentPlace) {
        this.currentPlace = currentPlace;
        fillCharchteristics();
   }

   public boolean isGoOut()
   {
    if(currentPlace == null)
        return true;
        return false;
   }
   
    public void DoStep(PathEngine lp) {
        if(currentPlace!=null)
           currentPlace = lp.getNextDistance(currentPlace, Speed);
         
        if(getIsDead())
            deadTime++;
    }
 
    @Override
    public abstract SolderType getType();

    public void Hit(double attackStrong) {
        this.Health -= attackStrong;
       if(this.Health < 1) 
           this.Health = 0;
    }

    @Override
    public boolean getIsDead() {
        if(this.Health == 0 )return true;
        return false;
    }

    public int getReward() {
       return reward;
    }

    public boolean needToRemove() {
     
       if(deadTime >= this.leaveAfterDeadTime) 
           return true;
       return false;
    } 

    private void fillCharchteristics() {
         if(this.engineOptions != null)
        {
            EngineOptions.SolderOption optionSolder = (EngineOptions.SolderOption) engineOptions.solders.get(getType()); 
            this.Health = optionSolder.Health;
            this.Speed  = optionSolder.Speed; 
            this.leaveAfterDeadTime  = optionSolder.leaveAfterDeadTime;
            this.reward = optionSolder.reward;
        }  
    }
 
    void init(Point2D startPoint) {
        this.currentPlace = startPoint;

    }
    
    @Override
    public double getHealth() {
       return this.Health;
    } 
    
    @Override
    public void clearObject() {
               fillCharchteristics();
        this.deadTime = 0;
    }
    
    @Override
    public Point2D getCurrentState() {
        return currentPlace;
    }
     
}
//</editor-fold>
