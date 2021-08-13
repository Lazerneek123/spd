package com.mycompany.test_1;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;

public class Main 
{
    private static final Random random = new Random();
    
    public static int newBuyersRandom() // метод для створення рандомного (випадкового) покупця для тестингу
    {
        String[] namesBuyers = {"Adam", "Robert", "Laren", "Karen", "Moren", "Saly", "Lara", "Steve", "Steven", "Susan"};
        String[] surnamesBuyers = {"Brown", "Tir", "Tro", "Ly", "Night", "Sert", "Ghost", "Hort", "Virt", "Dart"};
        String[] dateOfBirthsBuyers = {"01.04.2001", "07.14.2002", "10.15.2003", "07.04.2001", "08.05.2003", "01.03.2004", "02.04.2005", "09.24.2000", "04.18.2006", "02.25.2001"};
        String[] phoneNumberBuyers = {"(097 111 22 33)", "(097 101 82 10)", "(097 791 22 33)", "(097 111 02 33)", "(097 901 22 83)", "(097 111 22 30)", "(097 171 22 33)", "(097 911 32 33)", "(097 111 22 33)", "(097 011 22 03)"};
        String[] Months = {"June", "July", "August"};     
       
        int cost = (random.nextInt(10) + 1) * 100, theTotalCost = 0, paymentMethod = random.nextInt(10);
   
        if(paymentMethod < 4)
        {            
            theTotalCost = 0;             
            paymentMethod = cost - theTotalCost;
        }
        if(paymentMethod < 7 && paymentMethod >= 4)
        {            
            theTotalCost = cost / ((random.nextInt(10) + 1) * 10);            
            paymentMethod = cost - theTotalCost; 
        }
        
        if(paymentMethod <= 9 && paymentMethod >= 7)
        {            
            paymentMethod = 0;
            theTotalCost = cost - paymentMethod;          
        }  
        
        int numberMonth = random.nextInt(3);                
        buyersList.add(new Buyer(namesBuyers[random.nextInt(10)], surnamesBuyers[random.nextInt(10)], dateOfBirthsBuyers[random.nextInt(10)], phoneNumberBuyers[random.nextInt(10)], cost, Months[numberMonth], theTotalCost, paymentMethod, numberMonth));          
        System.out.println("\nNew buyer added:" + buyersList.get(buyersList.size() - 1));
        
        return numberMonth;
    }
 
    private static ArrayList<Buyer> buyersList = new ArrayList<>();      
    
    public static void main(String[] args) throws IOException
    {     
       try
       {  
           buyersList = deserializeFromXML();             
       }
       catch(IOException e)
       {
        System.out.println("Не вдалося найти указанний файл (він створився автоматично)!");   // перевірка на наявність файлу (створюється, якщо його нема)     
       } 
       finally
       {   
        newBuyersRandom();  
        
        for (int i = 0; i < buyersList.size(); i++) // цикл на перевірку однакових користувачів
        {
           if((buyersList.get(buyersList.size() - 1).name == null ? buyersList.get(i).name == null : buyersList.get(buyersList.size() - 1).name.equals(buyersList.get(i).name)) && (buyersList.get(buyersList.size() - 1).surname == null ? buyersList.get(i).surname == null : buyersList.get(buyersList.size() - 1).surname.equals(buyersList.get(i).surname)))
           {    
               if(buyersList.get(buyersList.size() - 1).numberMonth == 2)
               {
                   buyersList.get(buyersList.size() - 1).dateOfBirth = buyersList.get(i).dateOfBirth;
                   buyersList.get(buyersList.size() - 1).phoneNumber = buyersList.get(i).phoneNumber;                   
                   buyersList.get(buyersList.size() - 1).active = true;                  
               }           
            }
        }
        
        Collections.sort(buyersList, new SortByNumberCost()); // сортування об'єкту за певними типами даних (використовується компоратор)
        
        int index = 0; String buyerTemp = "";
        for (int i = 0; i < buyersList.size(); i++) // вивід в консоль
        {      
            if(buyersList.get(i).active == true)
            {
                if(i == 0)
                {
                    System.out.println("\nActive buyers:");
                }                       
                
                if(buyerTemp == null ? buyersList.get(i).month != null : !buyerTemp.equals(buyersList.get(i).month))
                {                   
                    System.out.println("\n" + buyersList.get(i).month);
                    buyerTemp = buyersList.get(i).month;
                }              
            }
            else
            {                                   
                if(index == 0)
                {
                    System.out.println("\nInactive buyers:");
                    index = 1;
                    buyerTemp = "";                    
                }                               
                
                if(buyerTemp == null ? buyersList.get(i).month != null : !buyerTemp.equals(buyersList.get(i).month))
                {                   
                    System.out.println("\n" + buyersList.get(i).month);
                    buyerTemp = buyersList.get(i).month;
                }              
            }            
            
            System.out.println(buyersList.get(i)/* + " " + buyersList.get(i).active + " " + buyersList.get(i).month*/);   
            serializeToXML();
        }     
       }          
    }
    
    private static void serializeToXML () throws IOException  //серіалізация (тобто запис даних у файл buyers.xml) у форматі .xml
    {
        try (FileOutputStream fos = new FileOutputStream("buyers.xml"); XMLEncoder encoder = new XMLEncoder(fos)) 
        {          
            encoder.setExceptionListener((Exception e) -> {
                System.out.println("Exception :" + e.toString());
            });
            encoder.writeObject(buyersList);
        }
    }
    
    private static ArrayList<Buyer> deserializeFromXML() throws IOException //десеріалізация (зчитування даних із файлу buyers.xml)
    {               
        try (FileInputStream fis = new FileInputStream("buyers.xml"); XMLDecoder decoder = new XMLDecoder(fis)) {      
            buyersList =  (ArrayList<Buyer>) decoder.readObject();
        }       
     return buyersList;
    }
}