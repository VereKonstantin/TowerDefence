//
//  TowerEngine.swift
//  TowerDefence
//
//  Created by koveres on 08.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public class TowerEngine {
 
    var towers:[Tower];
    var shells:[Shell];
 
    
    public  init( )
    {
        towers = [Tower]();
        shells = [Shell]();
    }
    
    public func getShells() -> ([Shell])
    {
        return shells;
    }
    
    public func   getTowers() -> ([Tower])
    {
        return towers;
    }
    
    
    
    func SetTower(  ID_Tower:TowerType,  position:CGPoint) -> (Bool) {
    
      var newTower:Tower = EngAndFabric.inst_.towerfabric.getTower(ID_Tower,position1: position,solderFinder1: EngAndFabric.inst_.solderFinder!);
        if(EngAndFabric.inst_.place!.canAddTower(newTower))
        {
        EngAndFabric.inst_.money!.Pay( newTower.getCost());
        towers.append(newTower);
        EngAndFabric.inst_.place!.addTower(newTower);
            return true;
        }
        return false;
    }
    
    func canSetTower( towerType:TowerType,   position:CGPoint) -> (Bool)
    {
    
        var newTower:Tower = EngAndFabric.inst_.towerfabric.getTower(towerType,position1: position,solderFinder1: EngAndFabric.inst_.solderFinder!);
    
        if(EngAndFabric.inst_.money!.canPay(newTower.getCost()))
        { return EngAndFabric.inst_.place!.canAddTower(newTower);}
        else
        {  return true;}
    }
    
    
    private func CheckShell() {
    
        for var i = (shells.count-1); i >= 0; --i
        {
       
            if( shells[i].isExploided())
            {
                    shells[i].FreeStackObject();
                shells.removeAtIndex( i );
            }
        }
    
    }
    
    func nextStep() {
    
    CheckShell();
        var shellCreated:Shell? = nil;
    for   tower in towers {
        shellCreated = tower.DoStep();
        if(shellCreated != nil) {
            shells.append(shellCreated!);
        }
    }
    
    for shell in shells
    {
        shell.DoStep(EngAndFabric.inst_.pathEng!,sold: EngAndFabric.inst_.soldeEng!);
    }
    
    }
    
    func soldTower(  ID:CInt) {
    
        for var i = 0; i < towers.count; ++i
    {
        if(towers[i].getIdObject() == ID)
        {
                EngAndFabric.inst_.money!.fillMoney(  towers[i].getSoldPrice()
                );
                towers[i].FreeStackObject();
                towers.removeAtIndex(i);
        }
    }
    }
    
}
