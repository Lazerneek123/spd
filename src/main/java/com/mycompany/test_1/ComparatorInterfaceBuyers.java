package com.mycompany.test_1;

import java.util.*;
  
class SortByNumberCost implements Comparator<Buyer> 
{       
    @Override
    public int compare(Buyer a, Buyer b) // компоратор (спершу сортує по активності, потім за місяцями і вже в кінці за загальною вартістю (за спаданням))
    {
        if(a.active && !b.active){
            return -1;
        }
        else if(b.active && !a.active)
        {
            return  1;
        }
        else
        {
            if(a.numberMonth == b.numberMonth)
            {
                return b.cost - a.cost;
            } else 
            {
                return a.numberMonth - b.numberMonth;
            }
        }     
    }
}
