/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package internalObject;

import Unit.Tower.Shell;
import Utils.Point2D;
 

/**
 *
 * @author Константин
 */
public interface ShellInternal extends ObjectWithID {
      
      Point2D  getTarget();
      Point2D  getCurrent();
      boolean getIsBlowedOut();

   
}
