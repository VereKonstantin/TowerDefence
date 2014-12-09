/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit.Tower;

import Utils.Point2D;
import internalObject.TowerInternal;
import internalObject.TowerInternal.TowerType;
 

/**
 *
 * @author vereshchagin
 */
class Tower2 extends Tower {

    public Tower2(Point2D position,TargetFinder solderFinder) {
             super(position,solderFinder);
                  if(Tower.engineOptions != null) return;
             this.sizeTower = 20;
             this.timeToBuild = 8;
             this.attackDistance = 40;
              this.attackStrong = 100;
                this.price = 40;
    }
     @Override
    public TowerType getType() {
        return TowerInternal.TowerType.Tower2;
    } 
}
