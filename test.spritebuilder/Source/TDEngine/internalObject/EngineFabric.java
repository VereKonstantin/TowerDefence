/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package internalObject;

import Engine.MainEngine;
import Map.MapDAOmemoryImpl;
import Unit.Solder.SolderFabric;
import Unit.Tower.TowerFabric;
import internalObject.SolderInternal.SolderType;
import internalObject.TowerInternal.TowerType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Константин
 */
public class EngineFabric {
    public static MainEngine getEngine()
    {
        MapDAOmemoryImpl mdao=new MapDAOmemoryImpl();
        SolderFabric  untiFabric=new SolderFabric(getSolderTypeIntList());
        TowerFabric    towerFabric = new TowerFabric(getTowerTypeIntList());
        MainEngine engineLevel=new MainEngine(mdao,untiFabric,towerFabric);
        return engineLevel; 
    }
    
     public static MainEngine getEngine(EngineOptions option)
    {
        MapDAOmemoryImpl mdao=new MapDAOmemoryImpl();
        SolderFabric  untiFabric=new SolderFabric(getSolderTypeIntList());
        TowerFabric towerFabric = new TowerFabric(getTowerTypeIntList());
        MainEngine engineLevel=new MainEngine(mdao,untiFabric,towerFabric);
        engineLevel.setOption(option);
        return engineLevel; 
    }

    public static List<Integer> getSolderTypeIntList() {
       List<Integer> itypes = new ArrayList<Integer>();
        for (SolderType integer : SolderType.values()) {
            itypes.add(integer.ordinal());
        } 
       return itypes;
    }

    private static List<Integer> getTowerTypeIntList() {
        List<Integer> itypes = new ArrayList<Integer>();
        for (TowerType integer : TowerType.values()) {
            itypes.add(integer.ordinal());  } 
        return itypes;
    }
}
