package com.JavaJonathanSite.SecretMessageApp;

import java.util.Random;

class MessageScrambler
{
    //holds unique code
    static String uniqueCode = "";

    //coded message variable to be used throughout the class
    public static String codedMessage = "";

    //Random number generator used throughout the class
    private static Random randomNumber = new Random();

    //Stores number so unique code can know which letter to append onto the unique code
    private static int implementationDecider;


    //Method to scramble the message
    // only needs to be accessed by main menu class, made default access level
    static void generateScrambledMessage(String userMessage)
    {
    	//clears coded message string for user to keep using since it is static
    	codedMessage = "";
    	
        //switch statement to decide which implementation of scramble should be used
        //and setting implementationDecider so we can append a specific letter onto it for decoding
        switch(randomNumber.nextInt(3))
        {
            case 0: firstUniqueScramble(userMessage); implementationDecider=0; break;
            case 1: secondUniqueScramble(userMessage); implementationDecider=1; break;
            case 2: thirdUniqueScramble(userMessage); implementationDecider=2; break;
        }
        
        generateUniqueCode();

        codedMessage = "Your scrambled message: " + codedMessage;
        
    }

    //Method to give the user a unique code each time they scramble a message to deter unwanted eyes on the message
    private static String generateUniqueCode()
    {
    	
    	//clears unique code after each use
    	uniqueCode = "";

        //for loop to cycle and generate 7 random numbers for unique code
        for(int i = 0; i < 7; i++)
        {
            //if statement to put letters inside the unique ID
            if(i == 2 || i == 3)
            {
                int rand = randomNumber.nextInt(7);

                //switch statement to decide which letter to be appended into unique ID
                switch(rand)
                {
                    case 0: uniqueCode += 'A'; break;
                    case 1: uniqueCode += 'Z'; break;
                    case 2: uniqueCode += 'D'; break;
                    case 3: uniqueCode += 'S'; break;
                    case 4: uniqueCode += 'L'; break;
                    case 5: uniqueCode += 'E'; break;
                    case 6: uniqueCode += 'H'; break;
                }
            } else {uniqueCode += randomNumber.nextInt(10);}

        }

        //Appending letter onto the unique code for decode to function by reading
        // this letter and the user will not know
        //needs to be able to read which implementation is being used, not just a random number
        switch(implementationDecider)
        {
            case 0: uniqueCode += 'F'; break; //first scramble implementation
            case 1: uniqueCode += 'S'; break; //second scramble implementation
            case 2: uniqueCode += 'T'; break; //third scramble implementation
        }
        return uniqueCode;
    }

    private static void firstUniqueScramble(String userMessage)
    {

        //cycling through each letter of users message to change each letter to desired coded letter
        //will need later for decoding,SECRET IMPLEMENTATIONS
        for(int i=0; i<userMessage.length();i++)
        {
            switch(userMessage.charAt(i))
            {
                //FIND A WAY TO REFACTOR!!!!
                case 'a': codedMessage = codedMessage.concat("s"); continue; // a = s
                case 'A': codedMessage = codedMessage.concat("S"); continue;
                case 'b': codedMessage = codedMessage.concat("g"); continue; // b = g
                case 'B': codedMessage = codedMessage.concat("G"); continue;
                case 'c': codedMessage = codedMessage.concat("o"); continue; // c = o
                case 'C': codedMessage = codedMessage.concat("O"); continue;
                case 'd': codedMessage = codedMessage.concat("w"); continue; // d = w
                case 'D': codedMessage = codedMessage.concat("W"); continue;
                case 'e': codedMessage = codedMessage.concat("l"); continue; // e = l
                case 'E': codedMessage = codedMessage.concat("L"); continue;
                case 'f': codedMessage = codedMessage.concat("a"); continue; // f = a
                case 'F': codedMessage = codedMessage.concat("A"); continue;
                case 'g': codedMessage = codedMessage.concat("v"); continue; // g = v
                case 'G': codedMessage = codedMessage.concat("V"); continue;
                case 'h': codedMessage = codedMessage.concat("u"); continue; // h = u
                case 'H': codedMessage = codedMessage.concat("U"); continue;
                case 'i': codedMessage = codedMessage.concat("b"); continue; // i = b
                case 'I': codedMessage = codedMessage.concat("B"); continue;
                case 'j': codedMessage = codedMessage.concat("z"); continue; // j = z
                case 'J': codedMessage = codedMessage.concat("Z"); continue;
                case 'k': codedMessage = codedMessage.concat("m"); continue; // k = m
                case 'K': codedMessage = codedMessage.concat("M"); continue;
                case 'l': codedMessage = codedMessage.concat("y"); continue; // l = y
                case 'L': codedMessage = codedMessage.concat("Y"); continue;
                case 'm': codedMessage = codedMessage.concat("t"); continue; // m = t
                case 'M': codedMessage = codedMessage.concat("T"); continue;
                case 'n': codedMessage = codedMessage.concat("c"); continue; // n = c
                case 'N': codedMessage = codedMessage.concat("C"); continue;
                case 'o': codedMessage = codedMessage.concat("n"); continue; // o = n
                case 'O': codedMessage = codedMessage.concat("N"); continue;
                case 'p': codedMessage = codedMessage.concat("p"); continue; //leaves letter as the same to trick any malicious intent
                case 'P': codedMessage = codedMessage.concat("P"); continue;
                case 'q': codedMessage = codedMessage.concat("d"); continue; // q = d
                case 'Q': codedMessage = codedMessage.concat("D"); continue;
                case 'r': codedMessage = codedMessage.concat("i"); continue; // r = i
                case 'R': codedMessage = codedMessage.concat("I"); continue;
                case 's': codedMessage = codedMessage.concat("h"); continue; // s = h
                case 'S': codedMessage = codedMessage.concat("H"); continue;
                case 't': codedMessage = codedMessage.concat("r"); continue; // t = r
                case 'T': codedMessage = codedMessage.concat("R"); continue;
                case 'u': codedMessage = codedMessage.concat("e"); continue; // u = e
                case 'U': codedMessage = codedMessage.concat("E"); continue;
                case 'v': codedMessage = codedMessage.concat("x"); continue; // v = x
                case 'V': codedMessage = codedMessage.concat("X"); continue;
                case 'w': codedMessage = codedMessage.concat("j"); continue; // w = j
                case 'W': codedMessage = codedMessage.concat("J"); continue;
                case 'x': codedMessage = codedMessage.concat("q"); continue; // x = q
                case 'X': codedMessage = codedMessage.concat("Q"); continue;
                case 'y': codedMessage = codedMessage.concat("k"); continue; // y = k
                case 'Y': codedMessage = codedMessage.concat("K"); continue;
                case 'z': codedMessage = codedMessage.concat("f"); continue; // z = f
                case 'Z': codedMessage = codedMessage.concat("F"); continue;
                    // make the default "continue" so any unspecified characters remain unchanged in user message
                default: codedMessage = codedMessage.concat(Character.toString(userMessage.charAt(i)));

            }
        }
    }
    //second and third scramble implementations
    private static void secondUniqueScramble(String userMessage)
    {

        for(int i=0; i<userMessage.length();i++)
        {
            switch(userMessage.charAt(i))
            {

                //FIND A WAY TO REFACTOR!!!!
                case 'a': codedMessage = codedMessage.concat("b"); continue; // a = b
                case 'A': codedMessage = codedMessage.concat("B"); continue;
                case 'b': codedMessage = codedMessage.concat("g"); continue; // b = g
                case 'B': codedMessage = codedMessage.concat("G"); continue;
                case 'c': codedMessage = codedMessage.concat("o"); continue; // c = o
                case 'C': codedMessage = codedMessage.concat("O"); continue;
                case 'd': codedMessage = codedMessage.concat("w"); continue; // d = w
                case 'D': codedMessage = codedMessage.concat("W"); continue;
                case 'e': codedMessage = codedMessage.concat("i"); continue; // e = i
                case 'E': codedMessage = codedMessage.concat("I"); continue;
                case 'f': codedMessage = codedMessage.concat("a"); continue; // f = a
                case 'F': codedMessage = codedMessage.concat("A"); continue;
                case 'g': codedMessage = codedMessage.concat("v"); continue; // g = v
                case 'G': codedMessage = codedMessage.concat("V"); continue;
                case 'h': codedMessage = codedMessage.concat("u"); continue; // h = u
                case 'H': codedMessage = codedMessage.concat("U"); continue;
                case 'i': codedMessage = codedMessage.concat("s"); continue; // i = s
                case 'I': codedMessage = codedMessage.concat("S"); continue;
                case 'j': codedMessage = codedMessage.concat("p"); continue; // j = p
                case 'J': codedMessage = codedMessage.concat("P"); continue;
                case 'k': codedMessage = codedMessage.concat("m"); continue; // k = m
                case 'K': codedMessage = codedMessage.concat("M"); continue;
                case 'l': codedMessage = codedMessage.concat("y"); continue; // l = y
                case 'L': codedMessage = codedMessage.concat("Y"); continue;
                case 'm': codedMessage = codedMessage.concat("t"); continue; // m = t
                case 'M': codedMessage = codedMessage.concat("T"); continue;
                case 'n': codedMessage = codedMessage.concat("c"); continue; // n = c
                case 'N': codedMessage = codedMessage.concat("C"); continue;
                case 'o': codedMessage = codedMessage.concat("j"); continue; // o = j
                case 'O': codedMessage = codedMessage.concat("J"); continue;
                case 'p': codedMessage = codedMessage.concat("f"); continue; // p = f
                case 'P': codedMessage = codedMessage.concat("F"); continue;
                case 'q': codedMessage = codedMessage.concat("d"); continue; // q = d
                case 'Q': codedMessage = codedMessage.concat("D"); continue;
                case 'r': codedMessage = codedMessage.concat("l"); continue; // r = l
                case 'R': codedMessage = codedMessage.concat("L"); continue;
                case 's': codedMessage = codedMessage.concat("h"); continue; // s = h
                case 'S': codedMessage = codedMessage.concat("H"); continue;
                case 't': codedMessage = codedMessage.concat("r"); continue; // t = r
                case 'T': codedMessage = codedMessage.concat("R"); continue;
                case 'u': codedMessage = codedMessage.concat("e"); continue; // u = e
                case 'U': codedMessage = codedMessage.concat("E"); continue;
                case 'v': codedMessage = codedMessage.concat("x"); continue; // v = x
                case 'V': codedMessage = codedMessage.concat("X"); continue;
                case 'w': codedMessage = codedMessage.concat("n"); continue; // w = n
                case 'W': codedMessage = codedMessage.concat("N"); continue;
                case 'x': codedMessage = codedMessage.concat("q"); continue; // x = q
                case 'X': codedMessage = codedMessage.concat("Q"); continue;
                case 'y': codedMessage = codedMessage.concat("k"); continue; // y = k
                case 'Y': codedMessage = codedMessage.concat("K"); continue;
                case 'z': codedMessage = codedMessage.concat("z"); continue; // leaves letter as the same to trick any malicious intent
                case 'Z': codedMessage = codedMessage.concat("Z"); continue;
                    // make the default "continue" so any unspecified characters remain unchanged in user message
                default: codedMessage = codedMessage.concat(Character.toString(userMessage.charAt(i)));
            }

        }

    }

    private static void thirdUniqueScramble(String userMessage)
    {


        for(int i=0; i<userMessage.length();i++)
        {
            switch(userMessage.charAt(i))
            {
                //FIND A WAY TO REFACTOR!!!!
                case 'a': codedMessage = codedMessage.concat("s"); continue; // a = s
                case 'A': codedMessage = codedMessage.concat("S"); continue;
                case 'b': codedMessage = codedMessage.concat("g"); continue; // b = g
                case 'B': codedMessage = codedMessage.concat("G"); continue;
                case 'c': codedMessage = codedMessage.concat("p"); continue; // c = p
                case 'C': codedMessage = codedMessage.concat("P"); continue;
                case 'd': codedMessage = codedMessage.concat("v"); continue; // d = v
                case 'D': codedMessage = codedMessage.concat("V"); continue;
                case 'e': codedMessage = codedMessage.concat("l"); continue; // e = l
                case 'E': codedMessage = codedMessage.concat("L"); continue;
                case 'f': codedMessage = codedMessage.concat("a"); continue; // f = a
                case 'F': codedMessage = codedMessage.concat("A"); continue;
                case 'g': codedMessage = codedMessage.concat("w"); continue; // g = w
                case 'G': codedMessage = codedMessage.concat("W"); continue;
                case 'h': codedMessage = codedMessage.concat("u"); continue; // h = u
                case 'H': codedMessage = codedMessage.concat("U"); continue;
                case 'i': codedMessage = codedMessage.concat("t"); continue; // i = t
                case 'I': codedMessage = codedMessage.concat("T"); continue;
                case 'j': codedMessage = codedMessage.concat("z"); continue; // j = z
                case 'J': codedMessage = codedMessage.concat("Z"); continue;
                case 'k': codedMessage = codedMessage.concat("y"); continue; // k = y
                case 'K': codedMessage = codedMessage.concat("Y"); continue;
                case 'l': codedMessage = codedMessage.concat("m"); continue; // l = m
                case 'L': codedMessage = codedMessage.concat("M"); continue;
                case 'm': codedMessage = codedMessage.concat("b"); continue; // m = b
                case 'M': codedMessage = codedMessage.concat("B"); continue;
                case 'n': codedMessage = codedMessage.concat("x"); continue; // n = x
                case 'N': codedMessage = codedMessage.concat("X"); continue;
                case 'o': codedMessage = codedMessage.concat("o"); continue; //leaves letter as the same to trick any malicious intent
                case 'O': codedMessage = codedMessage.concat("O"); continue;
                case 'p': codedMessage = codedMessage.concat("n"); continue; // p = n
                case 'P': codedMessage = codedMessage.concat("N"); continue;
                case 'q': codedMessage = codedMessage.concat("f"); continue; // q = f
                case 'Q': codedMessage = codedMessage.concat("F"); continue;
                case 'r': codedMessage = codedMessage.concat("i"); continue; // r = i
                case 'R': codedMessage = codedMessage.concat("I"); continue;
                case 's': codedMessage = codedMessage.concat("h"); continue; // s = h
                case 'S': codedMessage = codedMessage.concat("H"); continue;
                case 't': codedMessage = codedMessage.concat("r"); continue; // t = r
                case 'T': codedMessage = codedMessage.concat("R"); continue;
                case 'u': codedMessage = codedMessage.concat("c"); continue; // u = c
                case 'U': codedMessage = codedMessage.concat("C"); continue;
                case 'v': codedMessage = codedMessage.concat("e"); continue; // v = e
                case 'V': codedMessage = codedMessage.concat("E"); continue;
                case 'w': codedMessage = codedMessage.concat("j"); continue; // w = j
                case 'W': codedMessage = codedMessage.concat("J"); continue;
                case 'x': codedMessage = codedMessage.concat("q"); continue; // x = q
                case 'X': codedMessage = codedMessage.concat("Q"); continue;
                case 'y': codedMessage = codedMessage.concat("k"); continue; // y = k
                case 'Y': codedMessage = codedMessage.concat("K"); continue;
                case 'z': codedMessage = codedMessage.concat("d"); continue; // z = d
                case 'Z': codedMessage = codedMessage.concat("D"); continue;
                    // make the default "continue" so any unspecified characters remain unchanged in user message
                default: codedMessage = codedMessage.concat(Character.toString(userMessage.charAt(i)));
            }

        }
    }
}

