//
//  TowerDefence_iOS_Tests.swift
//  TowerDefence iOS Tests
//
//  Created by koveres on 02.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import TowerDefence
import UIKit
import XCTest


class TowerDefence_iOS_Tests: XCTestCase {
    
    var engineLevel:MainEngine = EngAndFabric.inst_.mainEngine;
    override func setUp() {
        super.setUp()
        // Put setup code here. This method is called before the invocation of each test method in the class.
        
        EngAndFabric().setOption(getOption());
        
        var startMoney:CInt = 400;
        
        var idLevel:CInt = 1; // В мап мемори одна тестовая карта, так что все равно какой айди.
        
        var levelInfo:LevelDTO = EngAndFabric.inst_.mdao.getMap(idLevel);
        EngAndFabric.inst_.mainEngine.startLevel(startMoney,levelInfo: levelInfo);
        
        
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
    
    func testExample() {
        var dd:LineABC;
        dd = LineABC(p1:CGPoint(),p2:CGPoint());
        
        // This is an example of a functional test case.
        XCTAssert(true, "Pass")
      
        
    }
    
       func testGetMoveObject()  {
     println("testGetMoveObject");
 
        doSteps(104);
        PrintResult();
       
       XCTAssert(true, "Pass")
    }
    
    func doSteps(count:Int)
    {
        for var i = 0; i < count; ++i {
            
            engineLevel.doStep();
        }
    }
    
    func PrintResult()
    {
    
        var mapstate:MapStateInternal = engineLevel.getState();
        
    println("unit.Count =  \(mapstate.unit.count)" );
        
        
        if(mapstate.unit.count != 0 )
        {
            for var i = (mapstate.unit.count - 1); i < mapstate.unit.count; ++i {
          
                println(" unit(\(i)).type =  \(mapstate.unit[i].getType())   id = \(mapstate.unit[i].getIdObject())");
                println(" unit(\(i)) .x = \(mapstate.unit[i].getCurrentState()!.x)");
                println(" unit(\(i)) .y = \(mapstate.unit[i].getCurrentState()!.y)");
    }}
      println();
    }
    
                   
                    
                    
    private func getOption() -> (EngineOptions) {
    
    var option:EngineOptions = EngineOptions(graphObjCreator1: CreatorGRObj());
    
    var  tower1:TowerOption;
    tower1 = option.getTowerOption();
    tower1.SpeedAttack = 1000; // сколько выстрелов в 1000 итераций.
    tower1.attackDistance = 40;
    tower1.attackStrong = 60;
    tower1.price = 30;
    tower1.sizeTower = 5;
    tower1.timeToBuild = 4;
    option.addTower( TowerType.Tower1, tower: tower1);
    
    var  tower2:TowerOption;
    tower2 = option.getTowerOption();
    tower2.SpeedAttack = 1000; // сколько выстрелов в 1000 итераций.
    tower2.attackDistance = 40;
    tower2.attackStrong = 100;
    tower2.price = 40;
    tower2.sizeTower = 40;
    tower2.timeToBuild = 8;
    option.addTower( TowerType.Tower2, tower: tower2);
    
    var  tower3:TowerOption;
    tower3 = option.getTowerOption();
    tower3.SpeedAttack = 1000; // сколько выстрелов в 1000 итераций.
    tower3.attackDistance = 60;
    tower3.attackStrong = 300;
    tower3.price = 60;
    tower3.sizeTower = 5;
    tower3.timeToBuild = 7;
    tower3.SpeedShell = 7;
    tower3.distanceToExploidShell = 5;
    option.addTower( TowerType.Tower3, tower: tower3);
    
    var solder1:SolderOption;
    solder1 = option.getSolderOption();
    solder1.Health = 400;
    solder1.Speed = 1;
    solder1.leaveAfterDeadTime = 10;
    solder1.reward = 1;
    option.addSolder(SolderType.Solder1, solder: solder1);
    
    var solder2:SolderOption;
    solder2 = option.getSolderOption();
    solder2.Health = 100;
    solder2.Speed = 3;
    solder2.leaveAfterDeadTime = 10;
    solder2.reward = 2;
    option.addSolder(SolderType.Solder2, solder: solder2);
    
    var solder3:SolderOption;
    solder3 = option.getSolderOption();
    solder3.Health = 50;
    solder3.Speed = 5;
    solder3.leaveAfterDeadTime = 10;
    solder3.reward = 3;
    option.addSolder(SolderType.Solder3, solder: solder3);
    
    return option;
    }
    
}

public class CreatorGRObj: GraphObjCreator{
    
    public func getGrObjectUnit(idObjectType:CInt) -> (()->(AnyObject))
    {
        return getObj;
    }
    
    func getObj()-> (AnyObject)
    {
        return 1;
    }
    
}
