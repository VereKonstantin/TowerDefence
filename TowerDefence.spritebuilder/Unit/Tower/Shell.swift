//
//  Shell.swift
//  TowerDefence
//
//  Created by koveres on 07.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation



public class Shell : StackObject , ShellInternal{
     var speedShell:CDouble = 8;
    var distanceToExploid:CInt = 7;
    var currentPlace:CGPoint?;
    var attackStrong:CDouble;
    var  target:Solder;
    
    
    
    public  init( attackStrong1:CDouble,  attackTarget:Solder, currentPlace1:CGPoint,  speedShell1:CDouble, distanceToExploid1:CInt,grData1:AnyObject) {
        
         attackStrong = attackStrong1;
         target = attackTarget;
         currentPlace = currentPlace1;
         speedShell = speedShell1;
         distanceToExploid = distanceToExploid1;
        
        super.init(data: grData1);
        
    }
    
    public func DoStep( lp:PathEngine, sold:SolderEngine) {
        
        if( !exploided )
        {
            var pointToNext:CGPoint? = target.getCurrentState();
            if(pointToNext == nil)
            {    exploid(sold);
                return;
            }
            
            if(ccpDistance(currentPlace!, pointToNext!) < CGFloat(distanceToExploid)) {
                exploid(sold);
                return;
            }
            
            currentPlace =   lp.calcNextDistanceForShell( currentPlace!,
                targetPoint: target.getCurrentState()!,speedShell: speedShell);
            if( currentPlace == nil){
                exploid(sold);}
        }
        
    }
    var exploided:Bool = false;
    public func isExploided() -> (Bool){return exploided;}
    private  func exploid( sold:SolderEngine) {
        exploided = true;
        var targets:[Solder] =  sold.getSolderNearPlace( currentPlace!,distanceToExploid: distanceToExploid);
        for  solder in targets  {
            solder.Hit(attackStrong);
        }
    }
    
    
 
    public func getCurrent() -> (CGPoint?) {
        return  currentPlace;
    }
    
   
    public func getTarget() -> (CGPoint?) {
        return target.getCurrentState();
    }
    
 
    public override func clearObject()  {
        exploided = false;
    }
    
    
    
 
    public func getIsBlowedOut() -> (Bool) {
        return isExploided();
    }
    
    
    
    
    
}
