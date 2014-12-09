//
//  MoneyEngine.swift
//  TowerDefence
//
//  Created by koveres on 07.12.14.
//  Copyright (c) 2014 Apportable. All rights reserved.
//

import Foundation
public class MoneyEngine {
    
    public init( money_:CInt) {
         money = money_;
    }
    var money:CInt;
    
    public  func canPay(  cost:CInt) ->(Bool)
    {
        return (money > cost);
    }
    
    public func Pay(  cost:CInt)
    {
        money -= cost;
    }
    
    public func fillMoney(  count:CInt)
    {
        money += count;
    }
    
    func getMoney() -> (CInt){
        return money;
    }  
}
