//
//  ObjectWithID.swift
//  TowerDefence
//
//  Created by koveres on 02.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation

public protocol ObjectWithID {
    func  getIdObject() -> (CInt);
    func  getUserData() -> (AnyObject);
    func setUserData(data : AnyObject);
}