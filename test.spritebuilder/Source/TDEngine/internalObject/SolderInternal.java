/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package internalObject;


import Utils.Point2D;
  
/**
 *
 * @author ver
 */
public interface SolderInternal extends ObjectWithID {
      enum SolderType{Solder1,Solder2,Solder3} 
      SolderType getType();
      Point2D  getCurrentState();
      double getHealth();
      boolean getIsDead(); 
}
