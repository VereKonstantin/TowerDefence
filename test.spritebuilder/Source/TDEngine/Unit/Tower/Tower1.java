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
class Tower1 extends Tower {

    public Tower1(Point2D position,TargetFinder solderFinder) {
        super(position,solderFinder);
          if(Tower.engineOptions != null) return;
        this.sizeTower = 5;
        this.timeToBuild = 4;
        this.attackDistance = 40;
        this.attackStrong = 60;
        this.price = 30;
    }
      @Override
    public TowerType getType() {
        return TowerInternal.TowerType.Tower1;
    }

  
}
