
/**
 * Huffman trees and codes.
 *
 * Data Structures, Grado en Informatica. UMA.
 *
 *
 * Student's name:
 * Student's group:
 */

import dataStructures.dictionary.AVLDictionary;
import dataStructures.dictionary.Dictionary;
import dataStructures.dictionary.HashDictionary;
import dataStructures.list.LinkedList;
import dataStructures.list.List;
import dataStructures.priorityQueue.BinaryHeapPriorityQueue;
import dataStructures.priorityQueue.PriorityQueue;
import dataStructures.tuple.Tuple2;

public class Huffman {

    // Exercise 1
    public static Dictionary<Character, Integer> weights(String s) {
    	Dictionary<Character, Integer> ap = new HashDictionary<>();
    	for (int i = 0; i<s.length();i++) {
    	    if (!ap.isDefinedAt(s.charAt(i))) {
    	        ap.insert(s.charAt(i), 1);
            } else {
    	        int aux = ap.valueOf(s.charAt(i));
    	        ap.delete(s.charAt(i));
    	        ap.insert(s.charAt(i), aux+1);
            }
        }
        return ap;
    }

    // Exercise 2.a
    public static PriorityQueue<WLeafTree<Character>> huffmanLeaves(String s) {
        Dictionary<Character, Integer> ap = weights(s);
        PriorityQueue<WLeafTree<Character>> cola = new BinaryHeapPriorityQueue<>();
        for (Character c : ap.keys()) {
            cola.enqueue(new WLeafTree<>(c,ap.valueOf(c)));
        }
        return cola;
    }

    // Exercise 2.b
    public static WLeafTree<Character> huffmanTree(String s) {

        Dictionary<Character, Integer> ap = weights(s);
        PriorityQueue<WLeafTree<Character>> cola = huffmanLeaves(s);
        WLeafTree<Character> arbol = cola.first();
        cola.dequeue();
        WLeafTree<Character> arbol2 = new WLeafTree<Character>(arbol, cola.first());
        cola.dequeue();
        if (ap.size()>=2) {
            while (!cola.isEmpty()) {
                if (arbol2.weight() >= cola.first().weight()) {
                    arbol2 = new WLeafTree<>(arbol2, cola.first());
                } else {
                    arbol2 = new WLeafTree<>(cola.first(), arbol2);
                }
                cola.dequeue();
            }
        } else throw new HuffmanException("Tusmuerto");
    	return arbol2;
    }

    // Exercise 3.a
    public static Dictionary<Character, List<Integer>> joinDics(Dictionary<Character, List<Integer>> d1, Dictionary<Character, List<Integer>> d2) {
        Dictionary<Character, List<Integer>> aux = d1;
        for (Character c : d2.keys()) {
            aux.insert(c,d2.valueOf(c));
        }
    	return aux;
    }

    // Exercise 3.b
    public static Dictionary<Character, List<Integer>> prefixWith(int i, Dictionary<Character, List<Integer>> d) {
        //to do 
    	return null;
    }

    // Exercise 3.c
    public static Dictionary<Character, List<Integer>> huffmanCode(WLeafTree<Character> ht) {
        //to do 
    	return null;
    }

    // Exercise 4
    public static List<Integer> encode(String s, Dictionary<Character, List<Integer>> hc) {
        //to do 
    	return null;
    }

    // Exercise 5
    public static String decode(List<Integer> bits, WLeafTree<Character> ht) {
        //to do 
    	return null;
    }
}
