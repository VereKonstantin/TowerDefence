//
//  MapDAO.swift
//  TowerDefence
//
//  Created by koveres on 06.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

 

public protocol   MapDAO {
     func    getMap( ID_LEVEL:CInt) -> (LevelDTO);
}
