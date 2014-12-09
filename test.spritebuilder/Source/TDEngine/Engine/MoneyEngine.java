/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

/**
 *
 * @author Константин
 */
class MoneyEngine {

    public MoneyEngine(int money) {
        this.money = money;
    }
int money;

    public boolean canPay(int cost)
    {
        return money > cost;
    }
    
    public void Pay(int cost)
    {
    money -= cost;
    }
    
    public void fillMoney(int count)
    {
      money += count;
    }

    int getMoney() {
       return money;
    }

 
    
    
}
