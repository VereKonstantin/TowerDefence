/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import Map.LevelDTO;
import Map.LevelDTO.BadLevelInfo;
import Map.MapDAO;
import Unit.Solder.Solder;
import Unit.Solder.SolderFabric;
import Unit.Tower.Tower;
import Unit.Tower.TowerFabric;
import Utils.Point2D;
import internalObject.EngineOptions;
import internalObject.MapStateInside;
import internalObject.MapStateInternal;
import internalObject.TowerInternal;
import internalObject.TowerInternal.TowerType;
 
 

/**
 *
 * @author verS
 */
public class MainEngine {
     

    SolderEngine unitE;
    private TowerEngine towerEngine;
    SolderFabric unitFabric;
    TowerFabric towerFabric;
    LevelDTO levelInfo;
    MapDAO mapdao;
    PathEngine path;
    PlaceEngine place;
    MoneyEngine money;
    
   public static int colPassSolderToLoos = 10;
 
boolean isGameEnd=false;
    private boolean isGameWin;
    
   
  

    public MainEngine(MapDAO mapdao,SolderFabric unitFabric, TowerFabric towerFabric ) {
        this.mapdao = mapdao;
        this.unitFabric = unitFabric;
          this.towerFabric = towerFabric;
      
         
    }
    
    public void startLevel(LevelDTO levelInfo, int money) throws BadLevelInfo
    {  
         levelInfo.checked();
         this.money = new MoneyEngine(money);
       
          
         this.path = new PathEngine(levelInfo.path); 
         
         if(levelInfo.placesToSetTower != null)
            this.place = new PlaceEngine(levelInfo.placesToSetTower);
          if(levelInfo.placesToSetTowerNew != null) {
            this.place = new PlaceEngine(levelInfo.placesToSetTowerNew,(byte)0);
        }
         
         unitE = new SolderEngine(unitFabric,this.path,levelInfo.solders,this.money); 
          
         towerEngine = new TowerEngine(this.towerFabric,
                 this.place,unitE,this.money,this.path); 
         
        
    }
    
   
    public EngineEvent onGameEnd;

    public EngineEvent getOnGameEnd() {
        return onGameEnd;
    }

    public void setOnGameEnd(EngineEvent onGameEnd) {
        this.onGameEnd = onGameEnd;
    }


  
    
   
  
    
    
    public MapStateInternal getState()
    {
        return new MapStateInside(this.unitE.GetSoldersOnMap(),this.towerEngine.getTowers(),this.towerEngine.getShells(),
                
                
            this.isGameEnd,this.isGameWin,this.money.getMoney());
    }

    public void doStep() throws Exception {
        
     if(!isGameEnd){
        this.unitE.nextStep();
        this.towerEngine.nextStep();
        checkGameEnd();
        }
    }
    
    
    boolean checkWin()
    {
        
      return 
              this.unitE.isUnitEnd() && (!this.unitE.isSoldersOnMapNotDead());
      
    }
    
    boolean checkLoose()
    {
       return (this.unitE.getPassUnit() > MainEngine.colPassSolderToLoos);
    }
public void checkGameEnd()
{
    
    if(checkWin())
    {
             if(onGameEnd != null) {
            onGameEnd.execute();
        }
       isGameEnd = true;
       isGameWin = true;
    }
    
    if(checkLoose())
    {
      if(onGameEnd != null) {
            onGameEnd.execute();
        }
       isGameEnd = true;
       isGameWin = false;
    }
}

    public boolean setTower(TowerInternal.TowerType ID_Tower,Point2D  position) throws Exception {
        
        if(towerEngine == null) {
            throw new Exception("Level is not started. You need startLevel befor using setTower.");
        }
        return towerEngine.SetTower(ID_Tower,position);
    }

    public boolean canSetTower(TowerType towerType, Point2D position) {
        try {
            return towerEngine.canSetTower(towerType,position);
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isEnd() {
       return isGameEnd;
    }

    public void setOption(EngineOptions option) {
        Tower.engineOptions = option;
        Solder.engineOptions = option;
    }

    public void soldTower(int ID) {
        this.towerEngine.soldTower(ID);
    }

    

  
}
