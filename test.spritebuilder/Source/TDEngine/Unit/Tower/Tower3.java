/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Tower;

import Unit.Solder.Solder;
import Utils.Point2D;
import Utils.StackObject;
import Utils.StackObjectAbstractFabric;
import internalObject.TowerInternal;
import internalObject.TowerInternal.TowerType;
import java.util.Stack;
 

/**
 *
 * @author vereshchagin
 */
class Tower3 extends Tower {
    public class ShellFabric extends StackObjectAbstractFabric{
      
        @Override
        protected StackObject getNewObject(Integer idObject) {
            return new Shell();
        }
 
}
   
          static   ShellFabric shellFabric; 
ShellFabric getShellFabric()
{
if(Tower3.shellFabric == null) shellFabric = new ShellFabric();
return shellFabric;
}
          
    static Stack<Shell> shells = new Stack<Shell>();
  
    public Tower3(Point2D position,TargetFinder solderFinder) {
             super(position,solderFinder);
                  if(Tower.engineOptions != null) return;
             this.sizeTower = 5;
             this.timeToBuild = 7;
             this.attackDistance = 60;
                this.attackStrong = 300;
                this.SpeedAttack = 100;
                  this.price = 60;
                  this.SpeedShell = 7;
                  this.distanceToExploidShell = 5;
    }
    
    
    
     @Override
     protected Shell strike(Solder attackTarget)
     {   
          return getShell(attackTarget);
     }
      
    @Override
    public TowerType getType() {
        return TowerInternal.TowerType.Tower3;
    }
   
    private Shell getShell(Solder attackTarget) 
    {
        Shell shl = (Shell) getShellFabric().getNewObject(0);
        shl.init( this.attackStrong,attackTarget,this.position,this.SpeedShell,this.distanceToExploidShell);
        return shl;
    }
    
}
