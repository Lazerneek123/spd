package com.mycompany.test_1;

public final class Buyer 
{   
    public String name; public String surname; public String dateOfBirth; 
    public String phoneNumber; public String month;
    public int cost; public int theTotalCost; 
    public int paymentMethod; public int numberMonth;
    public boolean active;
    
    public Buyer() // конструктор без параметрів, для того якщо користувач нічого не введе в параметри ( = new Buyers()), заповнеться автоматично!
    {
        name = "Adam";
        surname = "Brown";
        dateOfBirth = "01.04.2001";
        phoneNumber = "(097 111 22 33)";
        cost = 100; 
        month = "August";
        theTotalCost = 40;
        paymentMethod = 60;
        numberMonth = 8;
        active = true;
    }
  
    // Конструктор з параметрами
    public Buyer(String name, String surname, String dateOfBirth, String phoneNumber, int cost, String month, int theTotalCost, int paymentMethod, int numberMonth)
    {       
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.cost = cost; 
        this.month = month;
        this.theTotalCost = theTotalCost;
        this.paymentMethod = paymentMethod;
        this.numberMonth = numberMonth;
        active = IsActive();
    }
    
    public boolean IsActive()
    {        
        return numberMonth == 2;
    }
    
    // можна було зробити і так для  кожного поля, інкапсуляцію
    /*public String getName()   методи get та set, щоб зробити поля приватними; get - для зчитування, set - для запису (я передав їх в конструктор з параметрами)
    {
        return name;
    }
 
    public void setName(String name) 
    {
        this.name = name;
    }*/
  
    // Виводить дані (строка з даними) перевантажений метод
    @Override
    public String toString()
    {
        if(theTotalCost == 0 && paymentMethod != 0)
        {            
            return "\t" + name + " " + surname + " " + dateOfBirth + " " + phoneNumber + " : $" + cost + " (card:$" + paymentMethod + ")";
        }
        
        if(paymentMethod == 0 && theTotalCost != 0)
        {
            return "\t" + name + " " + surname + " " + dateOfBirth + " " + phoneNumber + " : $" + cost + " (cash:$" + theTotalCost + ")";
        }               
          return "\t" + name + " " + surname + " " + dateOfBirth + " " + phoneNumber + " : $" + cost + " (cash:$" + theTotalCost + ", card:$" + paymentMethod + ")";
      
    }
}
