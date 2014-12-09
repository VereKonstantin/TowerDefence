/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import Map.UnitGroup;
import Unit.Solder.Solder;
import Unit.Solder.SolderFabric;
import Unit.Solder.SolderWaver;
import Utils.Point2D;
import internalObject.SolderInternal.SolderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author vereshchagin
 */
public class SolderEngine {
     
  
     
        PathEngine path;
        MoneyEngine money;
        SolderFabric unitFabric;
        List<Solder> unitsOnMap;
      
        
        SolderWaver waveEngine;
    private int colPassUnit;

    SolderEngine(SolderFabric unitFabric, PathEngine path, List<UnitGroup> solders,MoneyEngine money) {
         this.unitFabric= unitFabric;
           this.path = path;     
           this.money = money;
           unitsOnMap= new ArrayList<Solder>(); 
           waveEngine = new SolderWaver(unitFabric,solders, path ); 
        
    }
   
    
       
    public void nextStep() throws Exception
    {
        addSolder();
        
        for(Solder j:unitsOnMap)
        {
            j.DoStep(path);  
        }
        
        CheckSolders();
    }
        
     public List<Solder> GetSoldersOnMap()
     {
        
         return unitsOnMap;
     }
         
        
     
    
    
    private void addSolder() throws Exception {
            waveEngine.addSolder(unitsOnMap);   
    }

    int getPassUnit() {
        return colPassUnit;
    }

    private void CheckSolders() {
   
        for(int i = unitsOnMap.size()-1; i >=0; i-- ) {
            if( unitsOnMap.get( i ).isGoOut()) 
           {
               unitsOnMap.get( i ).FreeStackObject();
               unitsOnMap.remove( i ); 
           colPassUnit++;
           }
        }
        
         for(int i = unitsOnMap.size()-1; i >=0; i-- ) {
            if( unitsOnMap.get( i ).getIsDead()) 
           {
               money.fillMoney(
                       unitsOnMap.get( i ).getReward()
                       );
             
           }
         }
            
        for(int i = unitsOnMap.size()-1; i >=0; i-- ) 
        { 
            if( unitsOnMap.get( i ).needToRemove()) 
           {
               unitsOnMap.get( i ).FreeStackObject();
               unitsOnMap.remove( i ); 
           }     
        }

 
   
    }

   

    boolean isSoldersOnMapNotDead() {
        for(int i = unitsOnMap.size()-1; i >=0; i-- ) 
        { 
            if( !unitsOnMap.get( i ).getIsDead()) 
           {
               return true;
           }     
        }
        return false;
    }

    public List<Solder> getSolderNearPlace(Point2D currentPlace, int distanceToExploid) {
        List<Solder>  solds = new  ArrayList<Solder>();
        for (Solder solder : unitsOnMap) {
           if(solder.getCurrentState().distance(currentPlace) < distanceToExploid)
              if(!solder.getIsDead()) solds.add(solder);
        } 
        return solds;
    }

    boolean isUnitEnd() {
       return this.waveEngine.isUnitEnd();
    }
  
        
}
