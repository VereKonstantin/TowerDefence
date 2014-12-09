//
//  MainEngine.swift
//  TowerDefence
//
//  Created by koveres on 08.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation

 
public class MainEngine {
    
    public struct colPassSolderToLoos{public static var value:CInt = 10;}
    
    var isGameEnd:Bool=false;
    var isGameWin:Bool=false;
    
    
    
    
    public init(  ) {
    
    
    }
    
    public func startLevel(    money:CInt,levelInfo:LevelDTO)
    {
         levelInfo.checked();
      EngAndFabric().resetEngineForLevel( money,levelInfo: levelInfo);
    }
    
    
    public var onGameEnd:EngineEvent?;
    
    public func getOnGameEnd() -> (EngineEvent?) {
        return onGameEnd;
    }
    
    public func setOnGameEnd( onGameEnd1: EngineEvent) {
        onGameEnd = onGameEnd1;
    }
    
    public func getState() -> (MapStateInternal)
    {
        return  MapStateInside(isEnd1: isGameEnd, isWin1: isGameWin);
    }
    
    public func doStep()  {
    
    if(!isGameEnd){
      EngAndFabric.inst_.soldeEng!.nextStep();
      EngAndFabric.inst_.towerEngine!.nextStep();
        checkGameEnd();
        }
    }
    
    
    func checkWin() -> (Bool)
    {
    
    return
     ( EngAndFabric.inst_.soldeEng!.isUnitEnd() && (!EngAndFabric.inst_.soldeEng!.isSoldersOnMapNotDead()) );
    
    }
    
    func checkLoose()-> (Bool)
    {
        return ( EngAndFabric.inst_.soldeEng!.getPassUnit() > MainEngine.colPassSolderToLoos.value);
    }
    public func checkGameEnd()
    {
    
    if(checkWin())
    {
    if(onGameEnd != nil) {
        onGameEnd!.execute();
    }
    isGameEnd = true;
    isGameWin = true;
    }
    
    if(checkLoose())
    {
    if(onGameEnd != nil) {
    onGameEnd!.execute();
    }
    isGameEnd = true;
    isGameWin = false;
    }
    }
    
    public func setTower(  ID_Tower:TowerType,   position:CGPoint) -> (Bool) {
    
    if(EngAndFabric.inst_.towerEngine == nil) {
     NSException(name: "Level is not started. You need startLevel befor using setTower.",reason: nil,userInfo: nil).raise();
    }
    return EngAndFabric.inst_.towerEngine!.SetTower(ID_Tower,position: position);
    }
    
    public func canSetTower( towerType:TowerType, position:CGPoint) -> (Bool) {
        return EngAndFabric.inst_.towerEngine!.canSetTower(towerType,position: position);
    
    }
    
    public   func isEnd() -> (Bool) {
    return isGameEnd;
    }
    
     
    
    public func soldTower(  ID:CInt) {
      EngAndFabric.inst_.towerEngine!.soldTower(ID);
    }
    
    
    
    
}
