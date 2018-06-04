/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merchantsmainclass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author elcot
 */
public class MERCHANTSMAINCLASS {

    /**
     * @param args the command line arguments
     */
    static int number_of_input;
    public static void main(String[] args) {
       Scanner scanner=new Scanner(System.in);
       number_of_input=scanner.nextInt();
        Getinput getinput=new Getinput();
        Processinput processinput=new Processinput();
        processinput.separete_randomnumber(getinput.string);
        
    }
    
}
class Getinput
{
ArrayList<String> string=new ArrayList<>();

Getinput(){
       Scanner scanner=new Scanner(System.in);
      
       
       for(int i=0;i<MERCHANTSMAINCLASS.number_of_input;i++)
       {
            string.add(scanner.nextLine());
       }  
     }
}
class Processinput
{
    HashMap<String,String> romanvar=new HashMap<>();
     void separete_randomnumber(ArrayList<String> string)
     {
         String outputdata;
         int result;
         Metals metals=new Metals();
         validate validate=new validate();
         Displayresult  displayresult=new Displayresult();      
         for(int i=0;i<MERCHANTSMAINCLASS.number_of_input;i++)
           {
           String str=string.get(i);
           String []strarr=str.split(" ");
           if(str.contains("is")&&!str.contains("Credits")&&!str.contains("much"))
            {
              romanvar.put(strarr[0],strarr[strarr.length-1]);
            }
           else if(str.contains("is")&&str.contains("Credits")&&!str.contains("many"))
            {
          
           String temp=str.replace("is",""); 
           String temp1=temp.replace("Credits","");
           outputdata=create_validsymbols(temp1); 
           String splite[]=temp1.split(" ");
           result=validate.validaterandom(outputdata+" "+"$");
           metals.setmetalvalue(splite[splite.length-3],(Integer.parseInt(splite[splite.length-1])/result));
           }
          else if(str.startsWith("how much is"))
           {
           String temp=str.replace("how much is","");  
           outputdata=create_validsymbols(temp); 
           result=validate.validaterandom(outputdata+" "+"$");
           displayresult.displayroman(temp, result);
           }
          else if(str.startsWith("how many Credits is")){
           String temp=str.replace("how many Credits is","");
           outputdata=create_validsymbols(temp);
           result=validate.validaterandom(outputdata+" "+"$");
           displayresult.displaysymbol(temp, result);
           }
          else{
              System.out.println("I have no idea what you are talking about");
          }
       
       }
         
    }
     String  create_validsymbols(String str)
     {
        
        String line[]=str.split(" ");
        String validsymbol="";
        for (String line1 : line) {
            if (romanvar.containsKey(line1)) {
                for (Map.Entry m : romanvar.entrySet()) {
                    if (m.getKey().equals(line1)) {
                        validsymbol+=m.getValue()+" ";
                    }
                }
            } 
            else {
                validsymbol += line1;
            }
        }
        return(validsymbol);
     }
}
class Roman_numerals
{
  HashMap<String,Integer> roman=new HashMap<>();
         Roman_numerals()
          {
              roman.put("I",1);
              roman.put("V",5);
              roman.put("X",10);
              roman.put("L",50);
              roman.put("C",100);
              roman.put("D", 500);
              roman.put("M", 1000);
          }
         int getcurrentvalue(String symbols)
         {
             for(Map.Entry m:roman.entrySet())
             {
                 if(m.getKey().equals(symbols))
                 {
                     return (int) m.getValue();
                 }
             }
             return 1;
         }
    }
    class Metals{
    static  HashMap<String,Integer> metals =new HashMap<>();
      void setmetalvalue(String symbol,Integer value)
      {
            metals.put(symbol, value);
          
      }
      static int getmetalvalue(String symbol)
      {
           for(Map.Entry m:metals.entrySet())
             {
                 if(m.getKey().equals(symbol))
                 {
                     return (int) m.getValue();
                 }
             }
             return 1;
      } 
    }
    class validate
    {
        ArrayList<String> non_sub=new ArrayList<>();
        ArrayList<String> repeate=new ArrayList<>();
        
        validate(){
            non_sub.add("V");
            non_sub.add("L");
            non_sub.add("D");
            repeate.add("I");
            repeate.add("X");
            repeate.add("C");
            repeate.add("M");
        }
        int validaterandom(String str)
        {
           Roman_numerals roman=new Roman_numerals();
            String checkrepeates=repeatcheck(str);
            String  []line=checkrepeates.split(" ");
            int result=0;
            int metals=Metals.getmetalvalue(line[line.length-2]);
            for(int i=0;i<line.length-2;i++)
            {
                if(roman.getcurrentvalue(line[i])<roman.getcurrentvalue(line[i+1])&&!non_sub.contains(line[i]))
                {
                    result+=roman.getcurrentvalue(line[i+1])-roman.getcurrentvalue(line[i]);
                    i=i+1;
                }
                else{
                    result+=roman.getcurrentvalue(line[i]);
                }
            }
        //    System.out.println(result*metals);
        return result*metals;
        }
        String repeatcheck(String str)
        {
            int count=1; 
            String str1="";
            String []line=str.split(" ");
            ArrayList<String> check=new ArrayList<>();
            check.addAll(Arrays.asList(line));
            for(int i=0;i<check.size()-1;i++)
            {
                if(check.get(i).equals(check.get(i+1))&& repeate.contains(check.get(i)))
                {
                    count++;
                    if(count>3)
                    {
                        check.add(i+1,"I");
                        count=1;
                    }
                }
                else
                {
                    count=1;
                }
            }
            for(int i=0;i<check.size();i++)
            {
                str1+=check.get(i)+" ";
            }
           return str1;
        }
    }
    class Displayresult
    {
        void displayroman(String s,int result)
        {
         System.out.println(s+" is "+result);    
        }
        void displaysymbol(String s,int result)
        {
         System.out.println(s+" is "+result+" credits");
        }
    }
