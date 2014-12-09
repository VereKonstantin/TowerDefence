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
class Solder3 extends Solder {

     public Solder3(Point2D p1) {
        super(p1);
          if(Solder.engineOptions != null) {
         return;
         }
        Speed = 5;
        Health = 50; reward = 3;
    }

    @Override
    public SolderType getType() {
        return SolderType.Solder3;
    }
}
