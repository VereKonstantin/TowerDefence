//
//  MapStateInside.swift
//  TowerDefence
//
//  Created by koveres on 08.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation



public class MapStateInside : MapStateInternal {
    
    
    public init( isEnd1:Bool,  isWin1:Bool ) {
         super.init();
        
       
        var solders:[Solder] = EngAndFabric.inst_.soldeEng!.GetSoldersOnMap();
        var towers:[Tower] = EngAndFabric.inst_.towerEngine!.getTowers();
        var shells:[Shell] = EngAndFabric.inst_.towerEngine!.getShells();
        
        
        parseUnit(&(solders));
        parseTower(&(towers));
        parseShells(&(shells));
        isEnd = isEnd1;
        isWin = isWin1;
        money = EngAndFabric.inst_.money!.getMoney();
       
    }
    
    private  func  parseUnit( inout unitsOnMap:[Solder]) {
       
        for  x in unitsOnMap {
            unit.append(x);}
    }
    
    private  func  parseTower(inout towersOnMap:[Tower]) {
        
        for x in towersOnMap{
            towers.append(x);}
    }
    
    private  func  parseShells(inout shells:[Shell]) {
         for   x in shells{
            shells.append(x);}
    }
    
    
}
