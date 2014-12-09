//
//  Solder.swift
//  TowerDefence
//
//  Created by koveres on 07.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public   class Solder : StackObject , SolderInternal {
    
  
    
    var reward:CInt = 0;
    var   Speed:CDouble = 1;
    var   Health:CDouble = 10;
    var leaveAfterDeadTime:CInt = 10;
    var currentPlace:CGPoint?;
    
    var deadTime:CInt = 0;
    
    init(  startPoint:CGPoint,gobj:AnyObject) {
       
      
           super.init(data: gobj);
        fillCharchteristics();
     
          currentPlace = startPoint;
    }
    
     
    
    public func isGoOut() -> (Bool)
    {
        if(currentPlace == nil){
            return true;}
        return false;
    }
    
    public func DoStep( ) {
        if(currentPlace != nil){
            currentPlace = EngAndFabric.inst_.pathEng!.getNextDistance( currentPlace!, distanse: Speed);
        }
        
        if(getIsDead()){
            deadTime++;}
    }
    
   
    public func  getType() ->(SolderType) {fatalError("Calling abstract function.");} 
    
    
  
    
    
    public func Hit(  attackStrong:CDouble) {
        Health -= attackStrong;
        if( Health < 1)
        { Health = 0;}
    }
    
 
    public func getIsDead() -> (Bool)  {
        if( Health == 0 ){return true;}
        return false;
    }
    
    public func getReward() -> (CInt) {
        return reward;
    }
    
    public func needToRemove() -> (Bool)
    {
        if( deadTime  >= leaveAfterDeadTime )
        {
            return true;
        }
        return false;
    }
    
    private func fillCharchteristics() {
        if( EngineOptions.instance_.value != nil)
        {
            var optionSolder:SolderOption = EngineOptions.instance_.value!.solders[getType().rawValue]!;
            
            
             Health = optionSolder.Health;
             Speed  = optionSolder.Speed;
             leaveAfterDeadTime  = optionSolder.leaveAfterDeadTime;
             reward = optionSolder.reward;
        }
       
    }
    
   
    public func getHealth() -> (CDouble){
        return  Health;
    }
    
    public override func clearObject()
    {
        fillCharchteristics();
        deadTime = 0;
    }
    
    public  func getCurrentState() -> (CGPoint?) {
        return currentPlace;
    }
    
    
}


public class Solder1: Solder {
    
     public init(p1:CGPoint,gobj: AnyObject) {
        super.init(startPoint: p1, gobj: gobj);
        Speed = 1;
        Health = 400;
        reward = 1;
    }
    
 
    public override func getType() -> SolderType {
        return SolderType.Solder1;
    }
}

public class Solder2: Solder {
    
    public init(p1:CGPoint,gobj: AnyObject) {
        super.init(startPoint: p1, gobj: gobj);
        Speed = 3;
        Health = 100;
        reward = 2;
       
    }
    
    
    public override func getType() -> SolderType {
        return SolderType.Solder2;
    }
}



public class Solder3: Solder {
    
    public init(p1:CGPoint,gobj: AnyObject) {
        super.init(startPoint: p1, gobj: gobj);
        Speed = 5;
        Health = 50; reward = 3;
    }
    
    
    public override func getType() -> SolderType {
        return SolderType.Solder3;
    }
}





//</editor-fold>
