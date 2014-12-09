//
//  EngineOptions.swift
//  TowerDefence
//
//  Created by koveres on 07.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public class EngAndFabric{
    
    
    public struct inst_{
    

    public static var  towerfabric:TowerFabric  =   TowerFabric(intlist:EngAndFabric.inst_.getTowerTypeIntList());
    public static var  solderFabric:SolderFabric = SolderFabric(intlist: EngAndFabric.inst_.getSolderTypeIntList());
    public static var mdao:MapDAO =  MapDAOmemoryImpl();
    public static var  mainEngine:MainEngine = MainEngine();
    
    public static var  place:PlaceEngine?;
    public static var  solderFinder:TargetFinder? ;
    public static var  money:MoneyEngine? ;
    public static var  pathEng:PathEngine?;
    public static var  soldeEng:SolderEngine?;
    public static var  towerEngine:TowerEngine?;
        
        public  static func getSolderTypeIntList() -> ([CInt]) {
            var itypes:[CInt]  = [CInt]();
            for  integer in SolderType.allValues  {
                itypes.append(integer.rawValue);
            }
            return itypes;
        }
        
        private static func getTowerTypeIntList() -> ([CInt]) {
            var itypes:[CInt]  = [CInt]();
            for  integer in  TowerType.allValues {
                itypes.append(integer.rawValue);  }
            return itypes;
        }
    }
    
    
    
    public func resetEngineForLevel(startmoney:CInt,levelInfo:LevelDTO)
    {
        EngAndFabric.inst_.money = MoneyEngine(money_:   startmoney );
        EngAndFabric.inst_.pathEng =  PathEngine(path1: levelInfo.path!);
        EngAndFabric.inst_.place =  PlaceEngine(placesToSetTower: levelInfo.placesToSetTower!);
        EngAndFabric.inst_.soldeEng =  SolderEngine(solders: levelInfo.solders!);
        EngAndFabric.inst_.towerEngine =  TowerEngine();
    }
    
    public func setOption( option:EngineOptions?)
    {
        EngineOptions.instance_.value = option;
        
    }
    
    public init(){}
    
    
    
  
    
 
}






public protocol GraphObjCreator
{
    func getGrObjectUnit(idObjectType:CInt) -> (()->(AnyObject))
}

public class SolderOption{
    public var Health:CDouble = 0.0;
    public var Speed:CDouble = 0.0;
    public var leaveAfterDeadTime:CInt =  0;
    public var reward:CInt = 0 ;
}

public class TowerOption{
    public var SpeedAttack:CDouble = 0.0;
    public var attackDistance:CDouble = 0.0;
    public var attackwait:CInt = 0 ;
    public var price:CInt = 0 ;
    public var sizeTower:CGFloat = 0.0;
    public var timeToBuild:CDouble = 0.0;
    public var SpeedShell:CDouble = 0.0;
    public var distanceToExploidShell:CInt = 0 ;
    public var attackStrong:CDouble = 0.0;
}




public class EngineOptions {
  
    
    public struct instance_{public static var value:EngineOptions?;}
    
    
    public var graphObjCreator:GraphObjCreator;
    public init(graphObjCreator1:GraphObjCreator)
    {
        graphObjCreator = graphObjCreator1;
    }
    
    
    public var solders:[CInt:SolderOption] = [CInt:SolderOption]();
    public func addSolder( idType:SolderType,  solder:SolderOption)
    {
        solders.updateValue(solder, forKey: idType.rawValue  );
    }
    public func getSolderOption() -> (SolderOption){
        return   SolderOption();
    }
    
    public func  getTowerOption() -> (TowerOption)
    {
        return   TowerOption();
    }
  
    
    public var towers:[CInt:TowerOption] = [CInt:TowerOption]();
    
    public func addTower( idType:TowerType,  tower:TowerOption)
    {
     
        towers.updateValue(tower, forKey:idType.rawValue);
      
        
    }
    
}
