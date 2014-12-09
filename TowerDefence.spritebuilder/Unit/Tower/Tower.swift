//
//  Tower.swift
//  TowerDefence
//
//  Created by koveres on 07.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation



public class Tower : StackObject , TowerInternal {
    
    var   timeToBuild:CDouble = 0;
    var   sizeTower:CGFloat = 0;
    var   attackDistance:CDouble = 0;
    var   attackStrong:CDouble = 0;
    var   SpeedAttack:CDouble = 1000;
    var   soldPercent:CDouble = 0.8;
    var   SpeedShell:CDouble = 0;
    var   distanceToExploidShell:CInt = 0;
    var   price:CInt = 0;
    
    var  position:CGPoint;
    var  solderFinder:TargetFinder;
    var  currentPointToStrike:CGPoint?;
    
    public init(  position1:CGPoint, solderFinder1:TargetFinder,grObj:AnyObject) {
        position = position1;
        solderFinder = solderFinder1;
   
        super.init(data: grObj);
        fillCharchteristics();
    }
    
    
    public override func clearObject() {
        fillCharchteristics();
        progress = 0;
         attackwait = 0;
    }
   
    public func  getType() -> (TowerType) {fatalError("Calling abstract function.");}
    
    
    public func getBulidProgress() -> (CDouble) {
        return  progress;
    }
    
 
    public func  getIsBuild() -> (Bool) {
        return isBuild();
    }
    
    
    public func getPosition() -> (CGPoint) {
        return  position;
    
    }
    
    
    public  func getNeedPlace() -> (CGRect){
        
        var halfSize:CGFloat = CGFloat(sizeTower/2.0);
        
        return   CGRect(x:(position.x - halfSize), y:(position.y - halfSize),   width: sizeTower, height:sizeTower)
        ;
    }
    
    public func  DoStep() -> (Shell?)
    {
        if(!isBuild()) {
            stepBuild();
        }
        else {
            return stepStrike();
        }
        return nil;
    }
    private func stepStrike() -> (Shell?){
        
        if(canAttack())
        {
            currentPointToStrike = nil;
            var canAttackSolder:[Solder] =  solderFinder.getSolder( position, distance: attackDistance);
           
            canAttackSolder = sorted(canAttackSolder, SolderSort(center1: position).compare);
            
            if(canAttackSolder.count > 0)
            {
                var attackTarget:Solder = canAttackSolder[0];
                currentPointToStrike = attackTarget.getCurrentState();
                return strike(attackTarget);
            }
        }
        return nil;
    }
    
    
    
    func   strike( attackTarget:Solder)-> (Shell?)
    {
        attackTarget.Hit(attackStrong);
        return nil;
    }
    
    func stepBuild()
    {
        var stepBuild:CDouble =  CDouble(1.0 / timeToBuild) ;
            progress += stepBuild;
            
    }
    public func isBuild() -> (Bool)
    {
        if(progress > 0.9999) {
            return true;
        }
        return false;
    }
    
    
    var  progress:CDouble = 0;
    public func getProgressBulid() -> (CDouble)
    {
        return progress;
    }
    
    public  func getCost() -> (CInt) {
        return price;
    }
    
    public func getAttackPoint() -> (CGPoint?) {
        return currentPointToStrike;
    }
    
    var attackwait:CInt = 0;
    private func canAttack() -> (Bool){
        attackwait++;
        var needToWait:CInt =    CInt(floor(1000.0 / SpeedAttack));
        if(attackwait > needToWait)
        {
            attackwait = 0;
            return true;
        }
        return false;
    }
    
    private func fillCharchteristics() {
        if( EngineOptions.instance_.value != nil)
        {
          
            var optionTower:TowerOption = EngineOptions.instance_.value!.towers[getType().rawValue]!;
            
            
             SpeedAttack = optionTower.SpeedAttack;
             attackStrong = optionTower.attackStrong;
             attackDistance = optionTower.attackDistance;
             attackwait = optionTower.attackwait;
             price = optionTower.price;
             sizeTower  = optionTower.sizeTower;
             timeToBuild = optionTower.timeToBuild;
             SpeedShell = optionTower.SpeedShell;
             distanceToExploidShell = optionTower.distanceToExploidShell;
        }
    }
    
    public func getSoldPrice() -> (CInt)
    {
        var soldpr:CDouble = (CDouble(price) * soldPercent)  ;
        return  CInt( round( soldpr) ) ;
    }
    
    
    
    
    
}

public class SolderSort   {
    
    public init(  center1:CGPoint) {
         center = center1;
        
    }
    var center:CGPoint;
    
   
    public  func compare( one:Solder,   two:Solder) -> Bool {
        if(one.getIsDead())
    {if(!two.getIsDead())
    {  return false;}}
        if(two.getIsDead())
    {if(!one.getIsDead())
    {return true;}}
    var distance1:CGFloat = ccpDistance(center, one.getCurrentState()!);
    var distance2:CGFloat = ccpDistance(center, two.getCurrentState()!);
        
        if(distance1 >  distance2){ return true;}
        if(distance1 <  distance2){ return false;}
        return true;
        
        
    }
}


public class Tower1 : Tower {
    
    public init(  position:CGPoint, solderFinder:TargetFinder,grObj:AnyObject) {
        super.init(position1: position,solderFinder1: solderFinder,grObj: grObj);
  
         sizeTower = 5;
         timeToBuild = 4;
         attackDistance = 40;
         attackStrong = 60;
         price = 30;
    }
    
    
    public override func getType() -> (TowerType) {
        return TowerType.Tower1;
    }
}


public class Tower2 : Tower {
    
    public init(  position:CGPoint, solderFinder:TargetFinder,grObj:AnyObject) {
        super.init(position1: position,solderFinder1: solderFinder,grObj: grObj);
        
          sizeTower = 20;
          timeToBuild = 8;
          attackDistance = 40;
          attackStrong = 100;
          price = 40;
    }
    
    
    public override func getType() -> (TowerType) {
        return TowerType.Tower2;
    }
}


public enum ShellTypes: CInt{case shell1 = 31,shell2 = 32};



public class ShellStackFabric: StackObjectAbstractFabric
{
    var creator:(( idObject:Int, grObj:AnyObject)  -> (StackObject))?;
    
    public init( )
    {
        var intlist:[CInt] = [ShellTypes.shell1.rawValue,ShellTypes.shell2.rawValue];
        super.init(intlist: intlist);
    }
    
    func  getNewObject( idObject:Int, grObj:AnyObject)  -> (StackObject) {
        return creator!(idObject: idObject,grObj: grObj);
    }
    
}
    
public class Tower3 : Tower  {
    
    var shellType:ShellTypes = ShellTypes.shell1;
    public init(  position:CGPoint, solderFinder:TargetFinder,grObj:AnyObject) {
        super.init(position1: position,solderFinder1: solderFinder,grObj: grObj);
        
          sizeTower = 5;
          timeToBuild = 7;
          attackDistance = 60;
          attackStrong = 300;
          SpeedAttack = 100;
          price = 60;
          SpeedShell = 7;
          distanceToExploidShell = 5;
    }
    
    
    public override func getType() -> (TowerType) {
        return TowerType.Tower3;
    }
    
    override func strike( attackTarget:Solder) -> (Shell)
    {
        return getShell(attackTarget);
    }
    
    struct shellStackFabric {
        static var inst:ShellStackFabric = ShellStackFabric();}

    var attackTarget_:Solder?;
    func  getShell( attackTarget:(Solder)) -> (Shell)
    {
        attackTarget_ = attackTarget;
        shellStackFabric.inst.creator =  getNewObject;
        var shl:Shell =  shellStackFabric.inst.getObject(shellType.rawValue) as Shell;
        return shl;
    }
    
    func  getNewObject( idObject:Int, grObj:AnyObject)  -> (StackObject) {
        return   Shell( attackStrong1: attackStrong,attackTarget: attackTarget_!, currentPlace1: position, speedShell1: SpeedShell, distanceToExploid1: distanceToExploidShell,grData1: grObj);
        
    }

}
