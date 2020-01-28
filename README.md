# CS-2852_Lab8 From 10/25/2017

Overview

In this lab, you write and test two classes: MorseEncoder and LookupTable.
Procedure

Your program must read in a file and produce an encoded version (using Morse Code) of that file (written to an output file). Your program must make use of the LookupTable class to store Morse Code table. The lookup table must implement the Map<K, V> interface to store each code in a key/value pair where the key is the character to be encoded and the value is the code associated with the key. Your implementation of the LookupTable class must include implementations of the following methods (the remaining methods may throw an UnsupportedOperationException).

    clear()
    containsKey(Object key)
    get(Object key)
    isEmpty()
    put(K key, V value)
    remove(Object key)
    size()

Your program should be implemented in the MorseEncoder class. The design for this class is your responsibility.

The LookupTable will consist of a sorted list of key/value mappings. For example, one element in the table will store a mapping from A (the key) to .- (the value); Another element will store a mapping from B (the key) to -... (the value); Etc… The elements in the table will be sorted based on the keys. E.g., A, B, etc…

In particular, your LookupTable class must use a List<Map.Entry<K extends Comparable<? super K>, V>> to store the morse code table. Each Entry should hold a key/value pair. The entries must be stored in sorted order based on the keys (you may not use the SortedArrayList class). Since you are maintaining a sorted list of Entry objects, your Entry class must implement the Comparable<Entry<K, V>> interface. A number of methods require the use of the Collections.binarySearch() method in order to find the location of the entry in the lookup table. Note that since the LookupTable class has a list of Entry objects in it, you’ll need to pass an Entry object to binary search. This Entry object can have a value attribute of null.

Additional details:

    The Morse code table should be read in from the morsecode.txt file (download).
    Your program should convert all characters to uppercase before looking up the corresponding Morse code.
    Your program should not encode characters that are not found in the lookup table. A warning message should be displayed to the console for any character encountered that is not in the Morse code table.
    A space should be placed between each encoded character.
    You should not call .sort(). If you are tempted to do so, get help!

Sample Results

Running your program on this file:

    A space should be placed between each encoded character.
     
    A **'' ''** should be placed between each word.
     
    Line breaks in the input file should be replicated in the encoded file.

should display something like this to the console:

    Enter an input filename:
    input.txt
    Enter an output filename
    encoded.txt
    Warning: skipping: *
    Warning: skipping: *
    Warning: skipping: '
    Warning: skipping: '
    Warning: skipping: '
    Warning: skipping: '
    Warning: skipping: *
    Warning: skipping: *

and produce the following output file:

    .- .-... ... .--. .- -.-. . .-... ... .... --- ..- .-.. -.. .-... -... . .-... .--. .-.. .- -.-. . -.. .-... -... . - .-- . . -. .-... . .- -.-. .... .-... . -. -.-. --- -.. . -.. .-... -.-. .... .- .-. .- -.-. - . .-. .-.-.- .-.-
    .-.-
    .- .-... .-... .-... ... .... --- ..- .-.. -.. .-... -... . .-... .--. .-.. .- -.-. . -.. .-... -... . - .-- . . -. .-... . .- -.-. .... .-... .-- --- .-. -.. .-.-.- .-.-
    .-.-
    .-.. .. -. . .-... -... .-. . .- -.- ... .-... .. -. .-... - .... . .-... .. -. .--. ..- - .-... ..-. .. .-.. . .-... ... .... --- ..- .-.. -.. .-... -... . .-... .-. . .--. .-.. .. -.-. .- - . -.. .-... .. -. .-... - .... . .-... . -. -.-. --- -.. . -.. .-... ..-. .. .-.. . .-.-.- .-.-

Lab Deliverables

    See your professor’s instructions for details on submission guidelines and due dates.

        Dr. Taylor’s students: See below
        All other students should refer to Blackboard

    If you have any questions, consult your instructor.

Acknowledgements

This assignment was developed by Dr. Jay Urbain and Dr. Chris Taylor.
