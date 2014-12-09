/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package internalObject;

import Unit.Tower.Tower;
import Utils.Point2D;
 

/**
 *
 * @author vereshchagin
 */
public interface TowerInternal extends ObjectWithID {
 
      double getBulidProgress();
      Boolean getIsBuild();
      enum TowerType{Tower1 ,Tower2,Tower3}
      TowerType getType();
      Point2D  getPosition();
      Point2D  getAttackPoint();
    
    
           
    
}
