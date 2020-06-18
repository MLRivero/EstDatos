package dataStructures.dictionary;
import dataStructures.list.List;

import dataStructures.list.ArrayList;
import dataStructures.set.AVLSet;
import dataStructures.set.Set;
import dataStructures.tuple.Tuple2;

import java.util.Iterator;

/**
 * Estructuras de Datos. Grados en Informatica. UMA.
 * Examen de septiembre de 2018.
 *
 * Apellidos, Nombre:
 * Titulacion, Grupo:
 */
public class HashBiDictionary<K,V> implements BiDictionary<K,V>{
	private Dictionary<K,V> bKeys;
	private Dictionary<V,K> bValues;
	
	public HashBiDictionary() {
		bKeys =  new HashDictionary<>();
		bValues = new HashDictionary<>();

	}
	
	public boolean isEmpty() {
		return bKeys.isEmpty();
	}
	
	public int size() {
		return bKeys.size();
	}
	
	public void insert(K k, V v) {
		if (!bKeys.isDefinedAt(k)) {
			bKeys.insert(k, v);
			bValues.insert(v,k);
		} else {
			bKeys.delete(k);
			bKeys.insert(k, v);
			bValues.delete(v);
			bValues.insert(v,k);
		}
	}
	
	public V valueOf(K k) {
		return bKeys.valueOf(k);
	}
	
	public K keyOf(V v) {
		return bValues.valueOf(v);
	}
	
	public boolean isDefinedKeyAt(K k) {
		return bKeys.isDefinedAt(k);
	}
	
	public boolean isDefinedValueAt(V v) {
		return bValues.isDefinedAt(v);
	}
	
	public void deleteByKey(K k) {
		if (bKeys.isDefinedAt(k)) {
			bValues.delete(this.valueOf(k));
			bKeys.delete(k);

		}
	}
	
	public void deleteByValue(V v) {
		if (bValues.isDefinedAt(v)) {
			bKeys.delete(this.keyOf(v));
			bValues.delete(v);
		}
	}
	
	public Iterable<K> keys() {
		return bKeys.keys();
	}
	
	public Iterable<V> values() {
		return bValues.keys();
	}
	
	public Iterable<Tuple2<K, V>> keysValues() {
		return bKeys.keysValues();
	}
	
		
	public static <K,V extends Comparable<? super V>> BiDictionary<K, V> toBiDictionary(Dictionary<K,V> dict) {
		Iterable<Tuple2<K, V>> t2 = dict.keysValues();
		Iterator<Tuple2<K,V>> it = t2.iterator();
		BiDictionary<K,V> res = new HashBiDictionary<>();
		while (it.hasNext()) {
			Tuple2<K,V> aux = it.next();
			if (res.isDefinedValueAt(aux._2())) {
				throw new IllegalArgumentException("Illo");
			}
			res.insert(aux._1(), aux._2());
		}
		return res;
	}
	
	public <W> BiDictionary<K, W> compose(BiDictionary<V,W> bdic) {
		Iterable<Tuple2<V, W>> t2 = bdic.keysValues();
		Iterator<Tuple2<V,W>> it = t2.iterator();
		BiDictionary<K,W> res = new HashBiDictionary<>();
		while (it.hasNext()) {
			Tuple2<V,W> aux = it.next();
			if (this.isDefinedValueAt(aux._1())) {
				res.insert(keyOf(aux._1()), aux._2());
			}
		}
		return res;
	}
		
	public static <K extends Comparable<? super K>> boolean isPermutation(BiDictionary<K,K> bd) {
		Iterable<Tuple2<K, K>> t2 = bd.keysValues();
		Iterator<Tuple2<K,K>> it = t2.iterator();
		while (it.hasNext()) {
			if (bd.keyOf(it.next()._1()) == null) {
				return false;
			}
		}
		return true;
	}
	
	// Solo alumnos con evaluación por examen final.
    // =====================================
	
	public static <K extends Comparable<? super K>> List<K> orbitOf(K k, BiDictionary<K,K> bd) {
		// TODO
		return null;
	}
	
	public static <K extends Comparable<? super K>> List<List<K>> cyclesOf(BiDictionary<K,K> bd) {
		// TODO
		return null;
	}

    // =====================================
	
	
	@Override
	public String toString() {
		return "HashBiDictionary [bKeys=" + bKeys + ", bValues=" + bValues + "]";
	}
	
	
}
