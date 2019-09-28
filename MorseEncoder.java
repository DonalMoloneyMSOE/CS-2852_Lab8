/*
 *CS 2852 - 011
 *Fall 2017
 *Lab * - Morse Code Encoder
 *Name: Donal Moloney
 *Created: 10/25/2017
 */
package Moloneyda;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the driver for the program its in charge of reading files, decoding and encoding
 * it to morse code and saving the encoded file
 */
public class MorseEncoder {
    private LookupTable<String, String> lookupTable;

    /**
     * This is the morseEncoder constructor it initializes a lookup table and loads it with the
     * key, value pairs enabling it to decode messages
     */
    public MorseEncoder() {
        lookupTable = new LookupTable();
        loadTable("src/master.txt");
    }

    /**
     * This is MorseEncoders main method it handles asking the user for a file to encode, it then
     * encodes it and saves it
     */
    public static void main(String[] args) {
        try {
            MorseEncoder morseEncoder = new MorseEncoder();
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter in the file you want to decode: exclude src/ and " +
                                       ".txt extensions");
            String fileToReadName = in.nextLine();
            File input = checkValidFile(fileToReadName);
            ArrayList<String> toEncode = morseEncoder.parseFile(input);
            System.out.print("Please create the file that you want to write to: exclude src/ and " +
                                     ".txt extensions");
            String outPutFileName = in.nextLine();
            File fileToRight = createWriteFile(outPutFileName);
            ArrayList<String> encoded = morseEncoder.encodeFile(toEncode);
            morseEncoder.saveFile(encoded, fileToRight);
        } catch (IOException e) {
            System.err.println("The file your trying to access not exist");

        }
    }

    /**
     * This method convert human readable text into morse code
     *
     * @param toEncode the character to encode
     * @return The encoded morse code characters
     */
    private ArrayList<String> encodeFile(ArrayList<String> toEncode) {
        ArrayList<String> encoded = new ArrayList<String>();
        for (int i = 0; i < toEncode.size(); i++) {
            if (toEncode.get(i).equals("\t")) {
                encoded.add("| ");
            } else {
                encoded.add(lookupTable.get(toEncode.get(i)));
                encoded.add(" ");
            }
        }
        return encoded;
    }

    /**
     * This message saves the encoded message to a file
     *
     * @param encoded     - arrayList of the encoded characters
     * @param fileToRight -  the file the encoded characters are being saved to
     */
    private void saveFile(ArrayList<String> encoded, File fileToRight) {
        try {
            FileWriter writer = new FileWriter(fileToRight);
            for (int i = 0; i < encoded.size(); i++) {
                writer.write(encoded.get(i));
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred writing to the file");
        }
    }

    /**
     * This message parses the file message into an array to be latter decoded
     *
     * @param input the human readable text file
     * @return arraylist of the characters to encode
     */
    private ArrayList<String> parseFile(File input) {
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader bufferedReader;
        FileReader fileReader;
        String currentLine;
        String[] characters;
        boolean contains;
        String aWords = "";
        String letter = "";
        try {
            fileReader = new FileReader(input);
            bufferedReader = new BufferedReader(fileReader);
            while ((currentLine = bufferedReader.readLine()) != null) {
                characters = currentLine.split(" ");
                for (int i = 0; i < characters.length; i++) {
                    aWords = characters[i];
                    for (int j = 0; j < aWords.length(); j++) {
                        letter = aWords.substring(j, j + 1);
                        letter = letter.toUpperCase();
                        contains = lookupTable.containsKey(letter);
                        if (contains != false) {
                            lines.add(letter);

                        } else {
                            System.err.println("Warning: skipping" + letter);
                        }
                    }
                    lines.add("\t");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Warning: the file you are trying to write to could not be found");
        } catch (IOException e) {
            System.err.println("An error occurred reading from the buffered reader");
        }
        return lines;

    }

    /**
     * This method creates the file to save to
     *
     * @param outPutFileName the name of the file to write to
     * @return the created file to write to
     */
    public static File createWriteFile(String outPutFileName) {
        String filePath = "src/" + outPutFileName + ".txt";
        File file = new File(filePath);
        return file;
    }

    /**
     * This method checks if the file to encoded exists
     *
     * @param fileToReadName -  the name of the file to check if valid
     * @throws IOException - if the file does not exist
     */
    public static File checkValidFile(String fileToReadName) throws IOException {
        String filePathName = "src/" + fileToReadName + ".txt";
        File testFile = new File(filePathName);
        if (!testFile.exists()) {
            throw new IOException();
        } else {
            return new File(filePathName);
        }
    }

    /**
     * This method loads the lookupTable with the master file
     *
     * @param file master file with the key, value pair allowing human readable text to be encoded
     */
    private void loadTable(String file) {
        try {
            BufferedReader bufferedReader;
            FileReader fileReader;
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] parts = currentLine.split("\t");
                String object = parts[0];
                String value = parts[1];
                lookupTable.put(object, value);
            }
        } catch (FileNotFoundException e) {
            System.err.println("The fileReader could not find the file to read from");
        } catch (IOException e) {
            System.err.println(
                    "An error regarding the input and output of the buffered reader " + "occurred");
        }
    }
}
