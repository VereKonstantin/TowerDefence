//
//  MapStateInternal.swift
//  TowerDefence
//
//  Created by koveres on 02.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public class MapStateInternal {
    
    public   var  unit:[SolderInternal] =  [SolderInternal]();
   
    public   var  towers:[TowerInternal] =  [TowerInternal]();
    public   var  shells:[ShellInternal] =  [ShellInternal]();
    
    public   var isEnd = false;
    public   var isWin = false;
    public   var money:CInt = 0;
}
