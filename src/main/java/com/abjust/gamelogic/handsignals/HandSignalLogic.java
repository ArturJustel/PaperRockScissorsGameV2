package com.abjust.gamelogic.handsignals;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.abjust.helpers.Crypto;

/*
* The following class contains logic for hand signals.
*/
public class HandSignalLogic implements IHandSignals {

    private static String eSCISSORS;
    private static String eROCK;
    private static String ePAPER;

    /*
    * The following method compares the signals and determines which one wins.
    */
    private int compareSignals(String aUserSelection, String aBotSelection) {
        if(!(encryptSelection(aUserSelection).equals(aBotSelection))){
        switch (aUserSelection) {
            case ROCK_VALUE:
                return (aBotSelection.equals(eSCISSORS) ? 1 : -1);
            case PAPER_VALUE:
                return (aBotSelection.equals(eROCK) ? 1 : -1);
            case SCISSORS_VALUE:
                return (aBotSelection.equals(ePAPER) ? 1 : -1);
        }
    }
        return 0;
    }

    /*
    * The following method gets the signal label that is passed.
    */
    private String getAnswer(String aSelection)
    {
        if(aSelection.equals(eSCISSORS)||aSelection.equals(SCISSORS_VALUE))
        {
            return SCISSORS_LABEL;
        }else if(aSelection.equals(eROCK)||aSelection.equals(ROCK_VALUE))
        {
            return ROCK_LABEL;
        }else if(aSelection.equals(ePAPER)||aSelection.equals(PAPER_VALUE))
        {
            return PAPER_LABEL;
        }
        return null; // Never will be reached
    }

    /*
    * The following method reveals the signals and tells which one wins.
    */
    public int revealSignals(String aPlayerSignal, String aBotSignal)
    {
        regenerate();
        System.out.println("You decided to use "+getAnswer(aPlayerSignal)+" and I still used "+aBotSignal);
        System.out.println("You can check that I wasn't cheating, if you don't trust me. The salt in this game was "+getSalt());
        System.out.println("Let's reveal our hand signals");
        System.out.println("You had "+getAnswer(aPlayerSignal)+" and I had "+getAnswer(aBotSignal));

        return compareSignals(aPlayerSignal, aBotSignal);
        
    }

    /*
    * The following method encrypts the passed signal.
    */
    public String encryptSelection(String aSignal)
    {
            String encryptedSignal= null;
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-512");
                md.update(getSalt().getBytes(StandardCharsets.UTF_8));
                byte[] bytes = md.digest(aSignal.getBytes(StandardCharsets.UTF_8));
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< bytes.length ;i++){
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                encryptedSignal = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return encryptedSignal;
    }

    /*
    * The following method re-encrypts the global variables. (It is needed because different salt is used every game)
    */
    private void regenerate()
    {
        eSCISSORS = encryptSelection(SCISSORS_VALUE);
        eROCK = encryptSelection(ROCK_VALUE);
        ePAPER = encryptSelection(PAPER_VALUE);
    }


    /*
     *  Gets current salt value (Need for better test coverage).
     */
    public String getSalt()
    {
        return Crypto.getSalt();
    }
    
}
