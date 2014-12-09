/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Tower;

import Unit.Solder.Solder;
import Utils.Point2D;
import Utils.StackObject;
import internalObject.EngineOptions;
import internalObject.TowerInternal;
import internalObject.TowerInternal.TowerType;
import Utils.MyGeom.Rectangle2D;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author vereshchagin
 */
public abstract class Tower extends StackObject implements TowerInternal{
 
    public static EngineOptions engineOptions = null;
    protected double timeToBuild;
    protected double sizeTower;
    protected double attackDistance;
    protected double attackStrong;
    protected double SpeedAttack = 1000;
    protected final double  soldPercent = 0.8; 
    protected double SpeedShell;
    protected int distanceToExploidShell;
    protected int price;
        
    Point2D  position;
     TargetFinder solderFinder;
    private Point2D currentPointToStrike;

    
 void init(Point2D position,TargetFinder solderFinder) {
          this.position = position; 
          this.solderFinder = solderFinder;
          fillCharchteristics();
    } 
 
     @Override
    public void clearObject() {
               fillCharchteristics();
        this.progress = 0;
        this.attackwait = 0;
    }
    Tower(Point2D position,TargetFinder solderFinder) 
    {
        this.position = position;
        this.solderFinder = solderFinder;
        
        fillCharchteristics();
    }

    @Override
    public abstract TowerType getType();
     
    @Override
    public double getBulidProgress() {
       return this.progress;
    }

    @Override
    public Boolean getIsBuild() {
        return this.isBuild();
    }

    @Override
    public Point2D getPosition() {
        return this.position;
    }
    
  
    public Rectangle2D  getNeedPlace(){
        
        double halfSize = sizeTower/2;
         
       return new Rectangle2D(position.x - halfSize, position.y - halfSize,
                sizeTower, sizeTower) 
                ; 
    }
    
    public Shell DoStep()
    {
      if(!isBuild()) {
            stepBuild();
        }
      else {
           return stepStrike();   
        }
      
       return null;
    }
      private Shell stepStrike() {
       
        if(canAttack())
        {
            currentPointToStrike = null;       
            List<Solder> canAttackSolder = this.solderFinder.getSolder(this.position, attackDistance); 
            Collections.sort(canAttackSolder,new SolderSort(this.position));
            if(canAttackSolder.size() > 0)
            {
                Solder attackTarget = canAttackSolder.get(0);
                currentPointToStrike = attackTarget.getCurrentState();
                return strike(attackTarget);
            }
        }
        return null;
    }
     
   
   
    protected Shell strike(Solder attackTarget)
     {       
          attackTarget.Hit(attackStrong);
          return null;
     }
    
    void stepBuild()
    { double stepBuild =  1/ timeToBuild ;
    progress += stepBuild;
     
    }
    public boolean isBuild()
    
  {
      if(progress > 0.9999) {
          return true;
      }
      return false;
    }
    
    
     double progress = 0;
     public double getProgressBulid()
     {
        return progress;
     }

    public int getCost() {
        return price;
    }

    public Point2D getAttackPoint() {
         return currentPointToStrike;
    }

    int attackwait = 0;
    private boolean canAttack() {
        attackwait++;
        int needToWait = (int) Math.floor(1000.0/this.SpeedAttack);
        if(attackwait > needToWait)
        {
            attackwait = 0;
            return true;
        }  
        return false;      
    }

    private void fillCharchteristics() {
        if(this.engineOptions != null)
        {
           EngineOptions.TowerOption optionTower = (EngineOptions.TowerOption) engineOptions.towers.get(getType());
           this.SpeedAttack = optionTower.SpeedAttack;
           this.attackStrong = optionTower.attackStrong;
           this.attackDistance = optionTower.attackDistance;
           this.attackwait = optionTower.attackwait;
           this.price = optionTower.price;
           this.sizeTower  = optionTower.sizeTower;
           this.timeToBuild = optionTower.timeToBuild;
           this.SpeedShell = optionTower.SpeedShell;
           this.distanceToExploidShell = optionTower.distanceToExploidShell;
        }
    }

    public long getSoldPrice() 
    {
       return Math.round(this.price*this.soldPercent) ;
    }

   

     
     public class SolderSort implements Comparator<Solder> {

        public SolderSort(Point2D center) {
            this.center = center;
            
        }
        Point2D center;  
         
        @Override
 	public int compare(Solder one, Solder two) {
            if(one.getIsDead())
                if(!two.getIsDead())
                    return -1;
             if(two.getIsDead())
                if(!one.getIsDead())
                    return 1;
 	    double distance1 = center.distance(one.getCurrentState());
            double distance2 = center.distance(two.getCurrentState());
            
            if(distance1 >  distance2) return 1;
             if(distance1 <  distance2) return- 1;
             return 0;
            
            
     }
     }

     
   
  
     
    
}
