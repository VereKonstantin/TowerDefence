//
//  UnitGroup.swift
//  TowerDefence
//
//  Created by koveres on 05.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation

public class CountsesArr{
    public var countses_:[CInt];
    public init (countses :[CInt])
    {
        countses_ = countses;
    }
}
public  class  UnitGroup  {
     
    public var deatachments:[Detachment] = [Detachment]();
    
    var currDet:Int = 0;
    var countses_:CountsesArr?;
    func getCountses() -> (CountsesArr)
    {
        if(countses_ == nil){
            initCountses();}
        return countses_!;
    }
    public   init(dtchs:[Detachment]){ deatachments = dtchs;}
     func initCountses()
    {
        var initarr:[CInt] = [CInt]();
        for detachment in deatachments
        {
            initarr.append(  detachment.getCount() );
        }
        countses_ =    CountsesArr(countses:initarr);
        
    }
    
    
    func getCurrentCount() ->(CInt) {
    
        return getCountses().countses_[currDet];
    }
    
    func minusCurrentCount() {
        getCountses().countses_[currDet]--;
    }
    
    public func getNextUnit() -> (SolderType)
    {
        var det:Detachment =  getCurrDet();
        nextCurrent();
        return det.type!;
    }
    
    
    public func isEnd()  -> (Bool){
   
        
        for var i = 0; i < deatachments.count; ++i {
            
            
            if(getCountses().countses_[i] != 0){
                return false;}
        }
        return true;
    }
    
      func getCurrDet() -> (Detachment){
    return deatachments[currDet];
    }
    
    
    
    func nextCurrent() {
    
    minusCurrentCount();
    currDet++;
        if(currDet == deatachments.count){currDet  = 0;}
    if(getCurrentCount() == 0){
    if(!isEnd()){
        nextCurrent();}}
    
    
    }
    
    
    
    
}
