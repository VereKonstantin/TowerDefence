/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package internalObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ver
 */


public class MapStateInternal {

    public static List<SolderInternal> unit  = new ArrayList<SolderInternal>();
    public static List<TowerInternal> towers = new ArrayList<TowerInternal>();
    public static List<ShellInternal> shells = new ArrayList<ShellInternal>();
     
    public boolean isEnd;
    public boolean isWin;
    public int money;
 
    
  
}


