//
//  LevelDTO.swift
//  TowerDefence
//
//  Created by koveres on 05.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public class  BadLevelInfo : NSException
{
    
}

public class LevelDTO {
    
    
    public init( path1:[CGPoint],  placesToSetTower1:[CGPoint],  solders1:[UnitGroup]) {
        placesToSetTower = placesToSetTower1;
        path = path1;
        solders = solders1;
    }
    
    public var placesToSetTower:[CGPoint]?; // Место предпологается хранить в точках с окрестностью.
    public var path:[CGPoint]?;
    public var solders:[UnitGroup]?;
   
    
    public func checked() {
    if(solders == nil ) {
     BadLevelInfo().raise();
    }
        if(path == nil) {
            BadLevelInfo().raise();
    }
        if(placesToSetTower == nil ) {
            BadLevelInfo().raise();
    }
    }
    
    
    
    
    
}
