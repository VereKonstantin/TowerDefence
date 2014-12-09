/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import Unit.Tower.Shell; 
import Unit.Tower.TargetFinder;
import Unit.Tower.Tower;
import Unit.Tower.TowerFabric;
import Utils.Point2D;
import internalObject.TowerInternal;
import internalObject.TowerInternal.TowerType;
 
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vereshchagin
 */
class TowerEngine { 
    TowerFabric fabric;
    PlaceEngine place;
    List<Tower> towers;
    List<Shell> shells;
    TargetFinder solderFinder;
    MoneyEngine money;
    PathEngine pathEng;
    SolderEngine soldeEng;
     
    public TowerEngine(TowerFabric fabric,PlaceEngine place, SolderEngine unitE, MoneyEngine money,PathEngine pathEng)
    {
       this.fabric = fabric;
       this.place = place;
       solderFinder = new TargetFinder(unitE);
       towers = new ArrayList<Tower>();
       shells= new ArrayList<Shell>();
       this.money = money; 
       this.pathEng = pathEng;
       soldeEng = unitE;
    }
    public List<Shell> getShells()
    {
       return shells;
    }
    
    public List<Tower> getTowers()
    {
       return towers;
    }
    
    
    
    boolean SetTower(TowerInternal.TowerType ID_Tower,Point2D  position) throws Exception {
        
        Tower newTower = fabric.getTower(ID_Tower,position,solderFinder);
        if(place.canAddTower(newTower))
        {  
            money.Pay(newTower.getCost());
            towers.add(newTower);
            place.addTower(newTower);
            return true;
        } 
        return false;
    }

    boolean canSetTower(TowerType towerType, Point2D  position) throws Exception  
    {
        
        Tower newTower = fabric.getTower(towerType,position,solderFinder);
        
        if(money.canPay(newTower.getCost())) 
            return place.canAddTower(newTower);
        else
            return true;
    }

    
    private void CheckShell() {
      
        for(int i = shells.size()-1; i >=0; i-- ) 
        { 
            if( shells.get( i ).isExploided()) 
           {
               shells.get(i).FreeStackObject();
               shells.remove( i ); 
           }     
        }
 
    }
    
    void nextStep() {
         
        CheckShell();
        Shell shellCreated = null;
        for (Tower tower : towers) {
         
            shellCreated = tower.DoStep();
            if(shellCreated != null) {
                shells.add(shellCreated);
            }
        } 
        
        for (Shell shell : shells) 
        {
            shell.DoStep(this.pathEng,this.soldeEng);
        }
        
    }

    void soldTower(int ID) {
        for (int i = 0; i < towers.size(); i++) 
        {
            if(towers.get(i).getIdObject() == ID)
            {
               this.money.fillMoney((int) towers.get(i).getSoldPrice());
               towers.get(i).FreeStackObject();
               towers.remove(i); 
            }
        } 
        
        
    }
    
}
