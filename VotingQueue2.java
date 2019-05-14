/* --------------------------------
 * 
    Author: Morgynn Nevels
    Date: 4/12/19
    Class: Java II
    Purpose: This program takes a csv file and creates a queue of voters from the first ten lines.
    After this the user can add another car of voters, process one entry from the queue
    or display current voter statistics.

  ---------------------------------  */


import jsjf.CircularArrayQueue;
import java.util.Scanner;
import java.util.Random;
import java.io.*;
import java.lang.Math.*;

public class VotingQueue2
{
   /*  This program should use the Scanner class to input the number of voters arriving at a 
    voting precinct. There are 2 items on each line (record) in the file:
   (1)  A sequential car number
   (2)  The number of voters arriving in the car
  

    This program will be used to process the voters in a queue.             */
   
   public static void main (String[] args)
   {
      CircularArrayQueue<String> queue = new CircularArrayQueue<String>();
      String inFile = "Voters.csv";
      PrintWriter outFile = null;
      Scanner inToken = null;
      String line = "";
      int votingTime = 0, totalVoters = 0, totalTime = 0, itemsRemaining;
      double avgTime;
      
      //----------------------------------------------------
      //  Open CSV file and create queue of voters from the 
      //  first ten records of the file
      //----------------------------------------------------

      try{
        
          inToken = new Scanner(new BufferedReader(new FileReader(inFile)));
          int loadTen = 0;
          
          while(loadTen < 10){
            
               line = inToken.next();
               String[] data = line.split(",");
               String voter = data[1];
               queue.enqueue(voter);
               loadTen++;
               
          }//end of while
      }//end of try
      catch(FileNotFoundException e){
        
          e.printStackTrace();
          System.out.println("File Not Found!");
          
      }//end of catch
      finally{
        
         if(inToken != null){
             inToken.close();
         }
         
      }//end of finally
      
      
      //----------------------------------------------------------------
      //  Get each voter from the car, if the car has more than one voter
      //  add all of the voters to the total voters
      //----------------------------------------------------------------
   
      String voterCar = "";
      int eachVoter;
      int votersTime[] = new int[100];
      int high = 600, low = 10;
      Random random = new Random();
      
      while(!queue.isEmpty()){
        
          voterCar = queue.dequeue();
          eachVoter = Integer.parseInt(voterCar);
          
          if(eachVoter > 1){
            
              while(eachVoter >= 1){
                  votingTime = random.nextInt((high - low) + 1) + low;
                  votersTime[totalVoters] = votingTime;
                  totalVoters++;
                  eachVoter--;
              }//end of while
              
          }//end of if
          else{
              votingTime = random.nextInt((high - low) + 1) + low;
              votersTime[totalVoters] = votingTime;
              totalVoters++;
              
          }//end of else
      }//end of while
      

      //---------------------------------------------------------------
      //  Lets the user enter a selection on what they would like to do next
      //  or four to end the program
      //----------------------------------------------------------------
      
      Scanner scan = new Scanner(System.in);
      String numOfVoters;
      
      System.out.println("Enter a number selection from the following:");
      System.out.println("1. Add a carload of voters");
      System.out.println("2. Process the voters from a car");
      System.out.println("3. Display statistics");
      System.out.println("4. Quit the program");
      System.out.println("Please enter 1, 2, 3 or 4: ");
      int selection = scan.nextInt();
      
      while(selection != 4){
                  
          if(selection == 1){
               System.out.println("Enter the number of voters in the car: ");
               numOfVoters = scan.next();
               queue.enqueue(numOfVoters);
          }
          else if(selection == 2){
                
            if(!queue.isEmpty()){
                  voterCar = queue.dequeue();
                  eachVoter = Integer.parseInt(voterCar);
          
                  if(eachVoter >= 1){
            
                      while(eachVoter >= 1){
                          votingTime = random.nextInt((high - low) + 1) + low;
                          votersTime[totalVoters] = votingTime;
                          totalVoters++;
                          eachVoter--;
                      }//end of while
              
                  }//end of if
                  else{
                      votingTime = random.nextInt((high - low) + 1) + low;
                      votersTime[totalVoters] = votingTime;
                      totalVoters++;
              
                  }//end of else
                  
                  System.out.println("Your selection has been processed");
            }
            else{
                System.out.println("The queue is empty! "); 
            }

          }
          else if(selection == 3){
            
              System.out.println("The current total of voters is: " + totalVoters);
              for(int i = 0; i < totalVoters - 1; i++){
                  totalTime = totalTime + votersTime[i];
              }
              System.out.println("The total time it took the voters to vote(in seconds): " + totalTime);
              avgTime = totalTime / totalVoters;
              System.out.println("The average voting time was(in seconds): " + avgTime);
              itemsRemaining = queue.size();
              System.out.println("Items remaining in the queue: " + itemsRemaining);
              
          }
          else{
              System.out.println("Invalid Selection! Please try again!");
          }
          
          System.out.println("Please enter 1, 2, 3 or 4(based on selection from menu above): ");
          selection = scan.nextInt();
      }
        
      if(selection == 4){
           System.out.println("The program is ending, goodbye!");
           System.exit(0);
      }
      

   }
}