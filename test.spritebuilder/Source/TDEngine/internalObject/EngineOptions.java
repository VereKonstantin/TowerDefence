/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package internalObject;
 
import internalObject.SolderInternal.SolderType;
import internalObject.TowerInternal.TowerType;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;


/**
 *
 * @author Константин
 */
public class EngineOptions {

  
    public class SolderOption{
        public float Health;
        public float Speed;
        public float leaveAfterDeadTime;
        public int reward;
        
     }
      
    public HashMap solders = new HashMap(); 
    public void addSolder(SolderType idType, SolderOption solder)
    {
        solders.put(idType,    solder);
    }
      public SolderOption getSolderOption() {
      return new SolderOption();
    }
    public TowerOption getTowerOption()
    {return new TowerOption();}
     public class TowerOption{
        public double SpeedAttack;
        public double attackDistance;
        public int attackwait;
        public int price;
        public double sizeTower;
        public double timeToBuild;
        public double SpeedShell;
        public int distanceToExploidShell;
        public double attackStrong;
    }
   
    public HashMap towers = new HashMap(); 
    public void addTower(TowerType idType, TowerOption solder)
    {
        towers.put(idType, solder);
    }
    
}
