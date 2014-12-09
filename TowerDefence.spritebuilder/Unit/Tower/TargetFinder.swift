//
//  TargetFinder.swift
//  TowerDefence
//
//  Created by koveres on 07.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public class TargetFinder {
    
    var solderEngine:SolderEngine?;
    var solders:[Solder]?;
    
    public init( solders1:[Solder]) {
     solders = solders1;
    }
    public init( solderEngine1:SolderEngine) {
     solderEngine = solderEngine1;
    }
    
 
    
    
    public func getSolder(  center:CGPoint,   distance:CDouble) -> ([Solder]) {
    if(solderEngine != nil) {
        solders = solderEngine!.GetSoldersOnMap();
    }
    
     var solderList:[Solder]  = [Solder]();
    for  solder in solders!
    {
        var centerSolder:CGPoint? =   solder.getCurrentState();
        var distanceToSolder:CGFloat = ccpDistance(centerSolder!, center);
    if(CDouble(distanceToSolder) <  distance ) {
    if(!solder.getIsDead()){
    if(!solder.isGoOut()){
        solderList.append(solder);}}
    }
    
    }
    return solderList;
    }
    
}
