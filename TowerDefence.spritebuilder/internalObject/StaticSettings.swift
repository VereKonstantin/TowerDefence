//
//  StaticSettings.swift
//  TowerDefence
//
//  Created by koveres on 02.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation


public class StaticSettings {
    
    private struct sizePlacePointStruct { static var sizePlacePoint: CDouble = 10.0 }
    public class var sizePlacePoint: CDouble
        {
        get { return sizePlacePointStruct.sizePlacePoint }
        set { sizePlacePointStruct.sizePlacePoint = newValue }
    }
}