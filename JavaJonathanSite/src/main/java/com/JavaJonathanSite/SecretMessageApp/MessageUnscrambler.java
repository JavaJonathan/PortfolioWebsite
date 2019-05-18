package com.JavaJonathanSite.SecretMessageApp;

public class MessageUnscrambler {


    //String to hold and invoke methods onto and hold the decoded message
    public static String decodedMessage = "";

    //method to unscramble message, takes in message and passcode and checks it, sets instance variable and return isInvalid
    // boolean for checks
    public static boolean unscrambleMessage(String userMessage, String uniqueID) {


        //boolean to hold it input is valid or not
        boolean isInvalid;
        
        //clears message after each use
        decodedMessage = "";



            //storing the user Unique code
            String userUniqueId = uniqueID;

            //if statement for additional security check
            if(userUniqueId.length() == 8)
            {

                //reading the last letter in the unique id to know which unscramble implementation to invoke
                //user has no idea the rest of the characters are simple to scare off unwanted eyes
                if (userUniqueId.endsWith("F")) {

                    isInvalid = false;
                    //storing user message then calling it into the proper unscramble method
                    //Main Menu also checks if payment will be required
                    firstUnscrambleImplementation(userMessage);

                } else if (userUniqueId.endsWith("S")) {

                    isInvalid = false;
                    secondUnscrambleImplementation(userMessage);

                } else if (userUniqueId.endsWith(("T"))) {

                    isInvalid = false;
                    thirdUnscrambleImplementation(userMessage);

                }
                //setting isInvalid to true
                else {isInvalid = true;}

            }
            //setting isInvalid to true
            else{isInvalid = true;}

        return isInvalid;
    }



    //the unscramble implementation methods
    private static void firstUnscrambleImplementation(String userScrambledMessage)
    {
        //looping through all of the letters in the users message to decode
        for(int i = 0; i < userScrambledMessage.length(); i++)
        {
            switch(userScrambledMessage.charAt(i))
            {
                case 's': decodedMessage = decodedMessage.concat("a"); continue; // s = a
                case 'S': decodedMessage = decodedMessage.concat("A"); continue;
                case 'g': decodedMessage = decodedMessage.concat("b"); continue; // g = b
                case 'G': decodedMessage = decodedMessage.concat("B"); continue;
                case 'o': decodedMessage = decodedMessage.concat("c"); continue; // o = c
                case 'O': decodedMessage = decodedMessage.concat("C"); continue;
                case 'w': decodedMessage = decodedMessage.concat("d"); continue; // w = d
                case 'W': decodedMessage = decodedMessage.concat("D"); continue;
                case 'l': decodedMessage = decodedMessage.concat("e"); continue; // l = e
                case 'L': decodedMessage = decodedMessage.concat("E"); continue;
                case 'a': decodedMessage = decodedMessage.concat("f"); continue; // a = f
                case 'A': decodedMessage = decodedMessage.concat("F"); continue;
                case 'v': decodedMessage = decodedMessage.concat("g"); continue; // v = g
                case 'V': decodedMessage = decodedMessage.concat("G"); continue;
                case 'u': decodedMessage = decodedMessage.concat("h"); continue; // u = h
                case 'U': decodedMessage = decodedMessage.concat("H"); continue;
                case 'b': decodedMessage = decodedMessage.concat("i"); continue; // b = i
                case 'B': decodedMessage = decodedMessage.concat("I"); continue;
                case 'z': decodedMessage = decodedMessage.concat("j"); continue; // z = j
                case 'Z': decodedMessage = decodedMessage.concat("J"); continue;
                case 'm': decodedMessage = decodedMessage.concat("k"); continue; // m = k
                case 'M': decodedMessage = decodedMessage.concat("K"); continue;
                case 'y': decodedMessage = decodedMessage.concat("l"); continue; // y = l
                case 'Y': decodedMessage = decodedMessage.concat("L"); continue;
                case 't': decodedMessage = decodedMessage.concat("m"); continue; // t = m
                case 'T': decodedMessage = decodedMessage.concat("M"); continue;
                case 'c': decodedMessage = decodedMessage.concat("n"); continue; // c = n
                case 'C': decodedMessage = decodedMessage.concat("N"); continue;
                case 'n': decodedMessage = decodedMessage.concat("o"); continue; // n = o
                case 'N': decodedMessage = decodedMessage.concat("O"); continue;
                case 'p': decodedMessage = decodedMessage.concat("p"); continue; //leaves letter as the same to trick any malicious intent
                case 'P': decodedMessage = decodedMessage.concat("P"); continue;
                case 'd': decodedMessage = decodedMessage.concat("q"); continue; // d = q
                case 'D': decodedMessage = decodedMessage.concat("Q"); continue;
                case 'i': decodedMessage = decodedMessage.concat("r"); continue; // i = r
                case 'I': decodedMessage = decodedMessage.concat("R"); continue;
                case 'h': decodedMessage = decodedMessage.concat("s"); continue; // h = s
                case 'H': decodedMessage = decodedMessage.concat("S"); continue;
                case 'r': decodedMessage = decodedMessage.concat("t"); continue; // r = t
                case 'R': decodedMessage = decodedMessage.concat("T"); continue;
                case 'e': decodedMessage = decodedMessage.concat("u"); continue; // e = u
                case 'E': decodedMessage = decodedMessage.concat("U"); continue;
                case 'x': decodedMessage = decodedMessage.concat("v"); continue; // x = v
                case 'X': decodedMessage = decodedMessage.concat("V"); continue;
                case 'j': decodedMessage = decodedMessage.concat("w"); continue; // j = w
                case 'J': decodedMessage = decodedMessage.concat("W"); continue;
                case 'q': decodedMessage = decodedMessage.concat("x"); continue; // q = x
                case 'Q': decodedMessage = decodedMessage.concat("X"); continue;
                case 'k': decodedMessage = decodedMessage.concat("y"); continue; // k = y
                case 'K': decodedMessage = decodedMessage.concat("Y"); continue;
                case 'f': decodedMessage = decodedMessage.concat("z"); continue; // f = z
                case 'F': decodedMessage = decodedMessage.concat("Z"); continue;
                default: decodedMessage = decodedMessage.concat(Character.toString(userScrambledMessage.charAt(i)));
            }
        }
    }

    private static void secondUnscrambleImplementation(String userScrambledMessage)
    {

        //looping through all of the letters in the users message to decode
        for(int i = 0; i < userScrambledMessage.length(); i++)
        {
            switch (userScrambledMessage.charAt(i))
            {
                //FIND A WAY TO REFACTOR!!!!
                case 'b': decodedMessage = decodedMessage.concat("a"); continue; // b = a
                case 'B': decodedMessage = decodedMessage.concat("A"); continue;
                case 'g': decodedMessage = decodedMessage.concat("b"); continue; // g = b
                case 'G': decodedMessage = decodedMessage.concat("B"); continue;
                case 'o': decodedMessage = decodedMessage.concat("c"); continue; // o = c
                case 'O': decodedMessage = decodedMessage.concat("C"); continue;
                case 'w': decodedMessage = decodedMessage.concat("d"); continue; // w = d
                case 'W': decodedMessage = decodedMessage.concat("D"); continue;
                case 'i': decodedMessage = decodedMessage.concat("e"); continue; // i = e
                case 'I': decodedMessage = decodedMessage.concat("E"); continue;
                case 'a': decodedMessage = decodedMessage.concat("f"); continue; // a = f
                case 'A': decodedMessage = decodedMessage.concat("F"); continue;
                case 'v': decodedMessage = decodedMessage.concat("g"); continue; // v = g
                case 'V': decodedMessage = decodedMessage.concat("G"); continue;
                case 'u': decodedMessage = decodedMessage.concat("h"); continue; // u = h
                case 'U': decodedMessage = decodedMessage.concat("H"); continue;
                case 's': decodedMessage = decodedMessage.concat("i"); continue; // s = i
                case 'S': decodedMessage = decodedMessage.concat("I"); continue;
                case 'p': decodedMessage = decodedMessage.concat("j"); continue; // p = j
                case 'P': decodedMessage = decodedMessage.concat("J"); continue;
                case 'm': decodedMessage = decodedMessage.concat("k"); continue; // m = k
                case 'M': decodedMessage = decodedMessage.concat("K"); continue;
                case 'y': decodedMessage = decodedMessage.concat("l"); continue; // y = l
                case 'Y': decodedMessage = decodedMessage.concat("L"); continue;
                case 't': decodedMessage = decodedMessage.concat("m"); continue; // t = m
                case 'T': decodedMessage = decodedMessage.concat("M"); continue;
                case 'c': decodedMessage = decodedMessage.concat("n"); continue; // c = n
                case 'C': decodedMessage = decodedMessage.concat("N"); continue;
                case 'j': decodedMessage = decodedMessage.concat("o"); continue; // j = o
                case 'J': decodedMessage = decodedMessage.concat("O"); continue;
                case 'f': decodedMessage = decodedMessage.concat("p"); continue; // f = p
                case 'F': decodedMessage = decodedMessage.concat("P"); continue;
                case 'd': decodedMessage = decodedMessage.concat("q"); continue; // d = q
                case 'D': decodedMessage = decodedMessage.concat("Q"); continue;
                case 'l': decodedMessage = decodedMessage.concat("r"); continue; // l = r
                case 'L': decodedMessage = decodedMessage.concat("R"); continue;
                case 'h': decodedMessage = decodedMessage.concat("s"); continue; // h = s
                case 'H': decodedMessage = decodedMessage.concat("S"); continue;
                case 'r': decodedMessage = decodedMessage.concat("t"); continue; // r = t
                case 'R': decodedMessage = decodedMessage.concat("T"); continue;
                case 'e': decodedMessage = decodedMessage.concat("u"); continue; // e = u
                case 'E': decodedMessage = decodedMessage.concat("U"); continue;
                case 'x': decodedMessage = decodedMessage.concat("v"); continue; // x = v
                case 'X': decodedMessage = decodedMessage.concat("V"); continue;
                case 'n': decodedMessage = decodedMessage.concat("w"); continue; // n = w
                case 'N': decodedMessage = decodedMessage.concat("W"); continue;
                case 'q': decodedMessage = decodedMessage.concat("x"); continue; // q = x
                case 'Q': decodedMessage = decodedMessage.concat("X"); continue;
                case 'k': decodedMessage = decodedMessage.concat("y"); continue; // k = y
                case 'K': decodedMessage = decodedMessage.concat("Y"); continue;
                case 'z': decodedMessage = decodedMessage.concat("z"); continue; // leaves letter as the same to trick any malicious intent
                case 'Z': decodedMessage = decodedMessage.concat("Z"); continue;
                    // make the default "continue" so any unspecified characters remain unchanged in user message
                default: decodedMessage = decodedMessage.concat(Character.toString(userScrambledMessage.charAt(i)));
            }
        }

    }

    private static void thirdUnscrambleImplementation(String userScrambledMessage)
    {

        //looping through all of the letters in the users message to decode
        for(int i = 0; i < userScrambledMessage.length(); i++)
        {
            switch (userScrambledMessage.charAt(i))
            {
                //FIND A WAY TO REFACTOR!!!!
                case 's': decodedMessage = decodedMessage.concat("a"); continue; // s = a
                case 'S': decodedMessage = decodedMessage.concat("A"); continue;
                case 'g': decodedMessage = decodedMessage.concat("b"); continue; // g = b
                case 'G': decodedMessage = decodedMessage.concat("B"); continue;
                case 'p': decodedMessage = decodedMessage.concat("c"); continue; // p = c
                case 'P': decodedMessage = decodedMessage.concat("C"); continue;
                case 'v': decodedMessage = decodedMessage.concat("d"); continue; // v = d
                case 'V': decodedMessage = decodedMessage.concat("D"); continue;
                case 'l': decodedMessage = decodedMessage.concat("e"); continue; // l = e
                case 'L': decodedMessage = decodedMessage.concat("E"); continue;
                case 'a': decodedMessage = decodedMessage.concat("f"); continue; // a = f
                case 'A': decodedMessage = decodedMessage.concat("F"); continue;
                case 'w': decodedMessage = decodedMessage.concat("g"); continue; // w = g
                case 'W': decodedMessage = decodedMessage.concat("G"); continue;
                case 'u': decodedMessage = decodedMessage.concat("h"); continue; // u = h
                case 'U': decodedMessage = decodedMessage.concat("H"); continue;
                case 't': decodedMessage = decodedMessage.concat("i"); continue; // t = i
                case 'T': decodedMessage = decodedMessage.concat("I"); continue;
                case 'z': decodedMessage = decodedMessage.concat("j"); continue; // z = j
                case 'Z': decodedMessage = decodedMessage.concat("J"); continue;
                case 'y': decodedMessage = decodedMessage.concat("k"); continue; // y = k
                case 'Y': decodedMessage = decodedMessage.concat("K"); continue;
                case 'm': decodedMessage = decodedMessage.concat("l"); continue; // m = l
                case 'M': decodedMessage = decodedMessage.concat("L"); continue;
                case 'b': decodedMessage = decodedMessage.concat("m"); continue; // b = m
                case 'B': decodedMessage = decodedMessage.concat("M"); continue;
                case 'x': decodedMessage = decodedMessage.concat("n"); continue; // x = n
                case 'X': decodedMessage = decodedMessage.concat("N"); continue;
                case 'o': decodedMessage = decodedMessage.concat("o"); continue; //leaves letter as the same to trick any malicious intent
                case 'O': decodedMessage = decodedMessage.concat("O"); continue;
                case 'n': decodedMessage = decodedMessage.concat("p"); continue; // n = p
                case 'N': decodedMessage = decodedMessage.concat("P"); continue;
                case 'f': decodedMessage = decodedMessage.concat("q"); continue; // f = q
                case 'F': decodedMessage = decodedMessage.concat("Q"); continue;
                case 'i': decodedMessage = decodedMessage.concat("r"); continue; // i = r
                case 'I': decodedMessage = decodedMessage.concat("R"); continue;
                case 'h': decodedMessage = decodedMessage.concat("s"); continue; // h = s
                case 'H': decodedMessage = decodedMessage.concat("S"); continue;
                case 'r': decodedMessage = decodedMessage.concat("t"); continue; // r = t
                case 'R': decodedMessage = decodedMessage.concat("T"); continue;
                case 'c': decodedMessage = decodedMessage.concat("u"); continue; // c = u
                case 'C': decodedMessage = decodedMessage.concat("U"); continue;
                case 'e': decodedMessage = decodedMessage.concat("v"); continue; // e = v
                case 'E': decodedMessage = decodedMessage.concat("V"); continue;
                case 'j': decodedMessage = decodedMessage.concat("w"); continue; // j = w
                case 'J': decodedMessage = decodedMessage.concat("W"); continue;
                case 'q': decodedMessage = decodedMessage.concat("x"); continue; // q = x
                case 'Q': decodedMessage = decodedMessage.concat("X"); continue;
                case 'k': decodedMessage = decodedMessage.concat("y"); continue; // k = y
                case 'K': decodedMessage = decodedMessage.concat("Y"); continue;
                case 'd': decodedMessage = decodedMessage.concat("z"); continue; // d = z
                case 'D': decodedMessage = decodedMessage.concat("Z"); continue;
                    // make the default "continue" so any unspecified characters remain unchanged in user message
                default: decodedMessage = decodedMessage.concat(Character.toString(userScrambledMessage.charAt(i)));
            }
        }

    }

}

