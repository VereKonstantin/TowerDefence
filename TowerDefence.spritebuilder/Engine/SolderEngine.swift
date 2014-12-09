//
//  SolderEngine.swift
//  TowerDefence
//
//  Created by koveres on 07.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public class SolderEngine {
    
    
    
    var unitsOnMap:[Solder] = [Solder]();
    
    
    var waveEngine:SolderWaver;
    var  colPassUnit:CInt = 0;
    
    public init(  solders:[UnitGroup] ) {
      waveEngine =   SolderWaver( solders: solders  );
    
    }
    
    
    
    public func nextStep()
    {
        addSolder();
    
    for   j in unitsOnMap
    {
        j.DoStep( );
    }
    
    CheckSolders();
    }
    
    public  func GetSoldersOnMap() -> ([Solder])
    {
        return unitsOnMap;
    }
    
    
    
    
    
    private func addSolder()  {
        waveEngine.addSolder(&unitsOnMap);
    }
    
    func getPassUnit() -> (CInt) {
        return colPassUnit;
    }
    
    private func CheckSolders() {
    
        for var i = (unitsOnMap.count - 1 ); i >= 0 ;  --i
        {
            if( unitsOnMap[i].isGoOut())
            {
                    unitsOnMap[i].FreeStackObject();
                    unitsOnMap.removeAtIndex( i );
                    colPassUnit++;
            }
        }
        
        for var i = (unitsOnMap.count - 1 ); i >= 0 ;  --i {
                if( unitsOnMap[i].getIsDead())
                {
                        EngAndFabric.inst_.money!.fillMoney(
                           unitsOnMap[i].getReward() 
                    );
    
                }
        }
    
        for var i = (unitsOnMap.count - 1 ); i >= 0 ;  --i {
            if( unitsOnMap[i].needToRemove())
            {
                unitsOnMap[i].FreeStackObject();
                unitsOnMap.removeAtIndex( i );
            }
            }
    
    
    
    }
    
    
    
    func isSoldersOnMapNotDead() -> (Bool) {
        for var i = (unitsOnMap.count - 1 ); i >= 0 ;  --i
    {
        if( !unitsOnMap[i].getIsDead())
        {
            return true;
        }
    }
        return false;
    }
    
    public func getSolderNearPlace(  currentPlace:CGPoint,   distanceToExploid:CInt) -> ([Solder]) {
        var solds:[Solder] =    [Solder]();
    for  solder  in unitsOnMap
    {
        if(ccpDistance( solder.getCurrentState()!, currentPlace) < CGFloat(distanceToExploid))
        { if(!solder.getIsDead()){ solds.append(solder);}}
    }
    return solds;
    }
    
    func isUnitEnd() -> (Bool) {
        return  waveEngine.isUnitEnd();
    }
    
    
}
