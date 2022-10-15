package com.abjust.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.abjust.helpers.errormessages.ErrorMessages;

/*
 *  The following helper class contains all necessary methods that help to process the user input. 
 */
public class InputHelper {
    int count=0;

    /*
     *  The following method obtains user input from the keyboard and returns it as a String.
     */
    public String getUserInput(String aUserInput)
    {
        count++;
        botSays(aUserInput);
        String inputLine=readLine();
       
        if(inputLine.length()==0)
        {
            if(count>10)
            {
                botSays(ErrorMessages.ERROR_SUSPICIOUS_BEHAVIOR);
                return null;
            }
            botSays(ErrorMessages.ERROR_NO_INPUT_WAS_PROVIDED);
            return getUserInput("Could you re-enter your answer?");
        }
        count=0;
         return convertIntoLowerCase(inputLine);
    }

    String convertIntoLowerCase(String inputLine) {
        return inputLine.toLowerCase();
    }

    /*
     *  The following method prints a bot sentence.
     */
    public void botSays(String aSentence)
    {
        System.out.println(aSentence);
    }

    public String readLine()
    {
        try {
        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            return is.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
