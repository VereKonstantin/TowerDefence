//
//  Detachment.swift
//  TowerDefence
//
//  Created by koveres on 05.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public enum SizeGroupe{case small,medium,big}


public   class  Detachment
{
    public var  BIG_COUNT:CInt = 30;
    public var   MEDIUM_COUNT:CInt = 20;
    public var   SMALL_COUNT:CInt = 10;
    public func getCount() ->(CInt)
    {
        if(size == SizeGroupe.small){ return SMALL_COUNT;}
        if(size == SizeGroupe.medium){ return MEDIUM_COUNT;}
        if(size == SizeGroupe.big){ return BIG_COUNT;}
        return 0;
    }
    public init(){}
    public  var size:SizeGroupe?;
    public var  type:SolderType?;
    
    
}