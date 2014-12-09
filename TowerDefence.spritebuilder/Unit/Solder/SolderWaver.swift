//
//  SolderWaver.swift
//  TowerDefence
//
//  Created by koveres on 07.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation

public class SolderWaver {
    
    public struct waitForNextWave { static var value:CInt = 100;}
    public struct waitForNextSolder { static var value:CInt = 5;}
 
    var solderWaves:[UnitGroup];
    var currentWave:[UnitGroup] = [UnitGroup]();
    
    public init(  solders:[UnitGroup] )
    {
        
     
        solderWaves = [UnitGroup]();
        for solder in solders {
            solderWaves.append(solder);
        }
    }
    public func isUnitEnd() -> (Bool) {
        return   ( (solderWaves.count == 0 ) &&  ( currentWave.count == 0) ) ;
    }
    public func nextWave() {
        if(!isUnitEnd())
        {
            currentWave.append(getNextUnitGroupe());
        }
    }
    
    
    var alreadyWaitForNextSolder:CInt = 0;
    public func addSolder(inout   unitsOnMap:[Solder]) {
        if(alreadyWaitForNextSolder > waitForNextSolder.value)
        {
                if(isUnitEnd() )
                {
                    return;
                }
    
    
            for   unitGroup in currentWave
        {
                addSolderFromWave( &unitsOnMap, unitGroup: unitGroup );
            }
    
    
            clearEmptyWave();
            alreadyWaitForNextSolder=0;
    }
    alreadyWaitForNextSolder++;
    ifItNeedSendNewWave();
    }
    
    func addSolderFromWave(inout unitsOnMap:[Solder],  unitGroup:UnitGroup )  {
    
        var nextunit = unitGroup.getNextUnit();
    unitsOnMap.append(
  EngAndFabric.inst_.solderFabric.getUnit(
        nextunit  ,
     startPoint1: EngAndFabric.inst_.pathEng!.getStartPoint())
    ) ;
    
    }
    
    func getNextUnitGroupe() -> (UnitGroup)
    {
        return solderWaves.removeLast();
    }
    
    
    func clearEmptyWave() {
       
        for var  i = 0;  i < currentWave.count; ++i {
            if( currentWave[i].isEnd())
            {
                currentWave.removeAtIndex(i);
            }
        }
    }
    
    
    
    var alreadywaitForNextWave:CInt = 0;
    private func ifItNeedSendNewWave()
    {
        alreadywaitForNextWave++;
        if(alreadywaitForNextWave >  waitForNextWave.value)
        {
            alreadywaitForNextWave = 0;
            if( currentWave.count == 0)
            {
                nextWave();
            }
        }
    }
    
}
