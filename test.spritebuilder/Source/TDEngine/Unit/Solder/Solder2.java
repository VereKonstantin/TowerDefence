/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Solder;

import Utils.Point2D;
import internalObject.SolderInternal.SolderType;
 
 

/**
 *
 * @author ver
 */
class Solder2 extends Solder {

    public Solder2(Point2D p1) {
        super(p1);
         if(Solder.engineOptions != null) return;
         Speed = 3;
         Health = 100;
          reward = 2;
        
    }

    @Override
    public SolderType getType() {
           return SolderType.Solder2;
    }
    
}
