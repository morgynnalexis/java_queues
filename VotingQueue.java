/* --------------------------------
 * 
    Author: Morgynn Nevels
    Date: 4/12/19
    Class: Java II
    Purpose: This program takes a csv file and creates a queue of voters, and assigns
    every voter a voter number. It then generates a report of the voter number,
    voting time and the totals and average voting time.

  ---------------------------------  */


import jsjf.CircularArrayQueue;
import java.util.Scanner;
import java.util.Random;
import java.io.*;
import java.lang.Math.*;

public class VotingQueue
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
      int voterNumber = 1, votingTime, totalVoters = 0, totalTime = 0;
      double avgTime, totalVotingTimeInMins;
      String votingNumber, votingTimeString, totalVoterString, avgVoteTime, totalVoteTimeString;
      
      //----------------------------------------------------
      //  Open CSV file and create queue of voters
      //----------------------------------------------------

      try{
        
          inToken = new Scanner(new BufferedReader(new FileReader(inFile)));
          
          while(inToken.hasNextLine()){
            
               line = inToken.next();
               String[] data = line.split(",");
               String voter = data[1];
               queue.enqueue(voter);
               
          }//end of while
      }//end of try
      catch(FileNotFoundException e){
        
          e.printStackTrace();

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
      
      while(!queue.isEmpty()){
        
          voterCar = queue.dequeue();
          eachVoter = Integer.parseInt(voterCar);
          
          if(eachVoter > 1){
            
              while(eachVoter >= 1){
                  totalVoters++;
                  eachVoter--;
              }//end of while
              
          }//end of if
          else{
            
              totalVoters++;
              
          }//end of else
      }//end of while
      
      totalVoterString = Integer.toString(totalVoters);
      
      //-----------------------------------------------------------------------
      //  Create two arrays, one creates voter numbers, the second creates a corresponding
      //  array that holds that voters vote time
      //-----------------------------------------------------------------------
      
      int voterNumbers[] = new int[totalVoters];
      int votersTime[] = new int[totalVoters];
      int high = 600, low = 10;
      Random random = new Random();
      
      for(int i = 0; i < voterNumbers.length; i++){
           voterNumbers[i] = voterNumber;
           voterNumber++;
           votingTime = random.nextInt((high - low) + 1) + low;
           votersTime[i] = votingTime;
      }//end of for
      
      //---------------------------------------------------------------------
      //  get the total amount of time it took all the voters to vote
      //  and then calculates the average time it took to vote
      //----------------------------------------------------------------------
      
      for(int j = 0; j < votersTime.length; j++){
              totalTime = totalTime + votersTime[j];
      }//end of for
      
      avgTime = totalTime / totalVoters;
      avgVoteTime = Double.toString(avgTime);
      
      totalVotingTimeInMins = totalTime / 60;
      totalVoteTimeString = Double.toString(totalVotingTimeInMins);
      
      //----------------------------------------------------------------------
      //  Create a report file that has all of the voters, the time it took them to vote 
      //  and the end has totals and average vote time
      //----------------------------------------------------------------------
      
      
      try{
          outFile = new PrintWriter(new FileWriter("VoterReport.txt"));
          outFile.println("Voter Number\tVoting Time(Minutes)");  
          for(int k = 0; k < voterNumbers.length; k++){
              voterNumber = voterNumbers[k];
              votingNumber = Integer.toString(voterNumber);
              votingTime = votersTime[k];
              votingTimeString = Integer.toString(votingTime);
              outFile.println("\t" + votingNumber + "\t\t" + votingTimeString);
          }//end of for
          
          outFile.println("");
          outFile.println("Total Numbers of Voters: " + totalVoterString);
          outFile.println("Total Voting Time(in minutes): " + totalVoteTimeString);
          outFile.println("Average Voting Time(in seconds): " + avgVoteTime);
          
      }//end of try
      catch(IOException e){
          e.printStackTrace();
          System.out.println("Input/Output Error!");
      }//end of catch
      finally{
          if(outFile != null){
              outFile.close();
          }
      }//end of finally

   }
}
