/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Tower;

import Engine.SolderEngine;
import Unit.Solder.Solder;
import Utils.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vereshchagin
 */
public class TargetFinder {

    SolderEngine solderEngine;
    public TargetFinder(List<Solder> solders) {
        this.solders = solders;
    }
    public TargetFinder(SolderEngine solderEngine) {
        this.solderEngine = solderEngine;
    }
    
    List<Solder> solders;
    
    
    public List<Solder> getSolder(Point2D center, double distance) {
        if(solderEngine != null) {
            solders = solderEngine.GetSoldersOnMap();
        }
        
        List<Solder> solderList = new ArrayList<Solder>();
        for (Solder solder : solders) {
          Point2D centerSolder =   solder.getCurrentState();
          double distanceToSolder = centerSolder.distance(center);
          if(distanceToSolder< distance) {
             if(!solder.getIsDead()) 
                if(!solder.isGoOut())  
                   solderList.add(solder);
            }
            
        }
        return solderList;
    }
    
}
