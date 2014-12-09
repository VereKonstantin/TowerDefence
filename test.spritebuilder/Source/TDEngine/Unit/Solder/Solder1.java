package Unit.Solder;

import Unit.Solder.Solder;
import Utils.Point2D;
import internalObject.SolderInternal.SolderType;
 
 

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ver
 */
class Solder1 extends Solder {

   public  Solder1(Point2D  p1) {
        super(p1);
        if(Solder.engineOptions != null) return;
        Speed = 1;
        Health = 400;
        reward = 1;
    }

    @Override
    public SolderType getType() {
        return SolderType.Solder1;
    }

   
    
}
