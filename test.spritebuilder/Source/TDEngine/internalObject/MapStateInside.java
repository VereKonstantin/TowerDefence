/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package internalObject;

import Unit.Tower.Tower;
import Unit.Solder.Solder;
import Unit.Tower.Shell;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vereshchagin
 */
public class MapStateInside extends MapStateInternal {
     
    
    public MapStateInside(List<Solder> unitsOnMap,List<Tower> towersOnMap,List<Shell> shells,boolean isEnd,boolean isWin,int money) {
      super();
        parseUnit(unitsOnMap);
        parseTower(towersOnMap);  
        parseShells(shells); 
        this.isEnd = isEnd;
        this.isWin = isWin;
        this.money = money;  
    }

    private void parseUnit(List<Solder> unitsOnMap) { 
      unit.clear();
        for(Solder x:unitsOnMap) 
            unit.add(x);   
    }

    private void parseTower(List<Tower> towersOnMap) { 
         towers.clear();
         for(Tower  x:towersOnMap) 
            towers.add(x);  
    }

    private void parseShells(List<Shell> shells) { 
         shells.clear();
        for(Shell   x:shells) 
          shells.add(x);     
    }

    
}
