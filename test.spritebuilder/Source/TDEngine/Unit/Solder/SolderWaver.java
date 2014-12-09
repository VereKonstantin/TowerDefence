/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Solder;

 
import Engine.PathEngine;
import Map.UnitGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Константин
 */
    public class SolderWaver {

            public static int waitForNextWave = 100;
            public static int waitForNextSolder = 5;
        
         SolderFabric unitFabric;
         PathEngine path;
         Stack<UnitGroup> solderWaves;
         List<UnitGroup> currentWave = new ArrayList<UnitGroup>();
         
         
         public SolderWaver(SolderFabric unitFabric,  List<UnitGroup> solders,    PathEngine path)
         {
             this.unitFabric = unitFabric;
             this.path = path;
                 solderWaves = new Stack<UnitGroup>();
                 for (int i = (solders.size() - 1); i >= 0 ; i--)  
                 this.solderWaves.add(solders.get(i)); 
         
             
         }
     public boolean isUnitEnd() {
        return  this.solderWaves.empty() &&  (this.currentWave.size()== 0)  ;
     }
     public void nextWave() {
         if(!this.isUnitEnd())
             currentWave.add(getNextUnitGroupe()); 
     }
        
     
     int alreadyWaitForNextSolder = 0;
     public void addSolder( List<Solder> unitsOnMap) throws Exception {
           if(alreadyWaitForNextSolder > waitForNextSolder)
           {
                if(isUnitEnd() ) 
                {
                   return;
                }
            
            
                for (UnitGroup unitGroup : this.currentWave)  
                {  
                    addSolderFromWave( unitsOnMap, unitGroup ); 
                }
            
                
                clearEmptyWave();  
                alreadyWaitForNextSolder=0;
           }
           alreadyWaitForNextSolder++; 
           ifItNeedSendNewWave(); 
      }
          
        void addSolderFromWave(  List<Solder> unitsOnMap,UnitGroup unitGroup ) throws Exception {
        
          unitsOnMap.add(
                    unitFabric.getUnit(
                    unitGroup.getNextUnit()  , 
                    this.path.getStartPoint() )
                    ) ;
         
    }
       
          UnitGroup getNextUnitGroupe()
    {
        return solderWaves.pop();
    }

    
   void clearEmptyWave() {
        for (int i = 0; i < this.currentWave.size(); i++) {
            if(this.currentWave.get(i).isEnd())
                this.currentWave.remove(i);
        }
    }

   
   
   int alreadywaitForNextWave = 0;
    private void ifItNeedSendNewWave() {
    
       alreadywaitForNextWave++;
       if(alreadywaitForNextWave > this.waitForNextWave)
       {
          alreadywaitForNextWave = 0; 
          if( currentWave.size() == 0)
                nextWave();
       }
    }
   
}
