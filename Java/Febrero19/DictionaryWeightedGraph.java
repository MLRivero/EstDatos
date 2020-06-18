/**----------------------------------------------
 * -- Estructuras de Datos.  2018/19
 * -- 2º Curso del Grado en Ingeniería [Informática | del Software | de Computadores].
 * -- Escuela Técnica Superior de Ingeniería en Informática. UMA
 * --
 * -- Examen 4 de febrero de 2019
 * --
 * -- ALUMNO/NAME:
 * -- GRADO/STUDIES:
 * -- NÚM. MÁQUINA/MACHINE NUMBER:
 * --
 * ----------------------------------------------
 */

package dataStructures.graph;

import java.util.Iterator;

import dataStructures.dictionary.Dictionary;
import dataStructures.dictionary.HashDictionary;

import dataStructures.set.Set;
import dataStructures.set.HashSet;
import dataStructures.tuple.Tuple2;

public class DictionaryWeightedGraph<V, W extends Comparable<? super W>> implements WeightedGraph<V, W> {



    /**
     * Each vertex is associated to a dictionary containing associations
     * from each successor to its weight
     */
    protected Dictionary<V, Dictionary<V, W>> graph;

    public DictionaryWeightedGraph() {
        graph = new HashDictionary<>();
    }


    public void addVertex(V v) {
        graph.insert(v, new HashDictionary<>());
    }

    public void addEdge(V src, V dst, W w) {
        if (graph.isDefinedAt(src) && graph.isDefinedAt(dst)) {
            graph.valueOf(src).insert(dst, w);
        } else throw new GraphException("Neneneene");

    }

    public Set<Tuple2<V, W>> successors(V v) {
        Set<Tuple2<V,W>> sucesores = new HashSet<>();
        if (graph.isDefinedAt(v)) {

            Iterable<Tuple2<V, W>> t2 =  graph.valueOf(v).keysValues();
            Iterator<Tuple2<V,W>> it = t2.iterator();
            if (it.hasNext()) {
                sucesores.insert(it.next());
            }
        } else throw new GraphException("Neneneene");
        return sucesores;
    }


    public Set<WeightedEdge<V, W>> edges() {
        // COMPLETAR
        Set<WeightedEdge<V, W>> set = new HashSet<>();
        Set<Tuple2<V, W>> aux = null;
        Iterable<V> t = graph.keys();
        Iterator<V> it = t.iterator();
        Tuple2<V, W> aux2;
        Iterator<Tuple2<V, W>> i;

        while (it.hasNext()) {
            V a = it.next();
            aux = successors(a);
            i = aux.iterator();
            while (i.hasNext()) {
                aux2 = i.next();
                set.insert(new WE<>(a, aux2._1(), aux2._2()));
            }
        }

        return set;
    }






    /** DON'T EDIT ANYTHING BELOW THIS COMMENT **/


    public Set<V> vertices() {
        Set<V> vs = new HashSet<>();
        for (V v : graph.keys())
            vs.insert(v);
        return vs;
    }


    public boolean isEmpty() {
        return graph.isEmpty();
    }

    public int numVertices() {
        return graph.size();
    }


    public int numEdges() {
        int num = 0;
        for (Dictionary<V, W> d : graph.values())
            num += d.size();
        return num / 2;
    }


    public String toString() {
        String className = getClass().getSimpleName();
        String s = className + "(vertices=(";

        Iterator<V> it1 = vertices().iterator();
        while (it1.hasNext())
            s += it1.next() + (it1.hasNext() ? ", " : "");
        s += ")";

        s += ", edges=(";
        Iterator<WeightedEdge<V, W>> it2 = edges().iterator();
        while (it2.hasNext())
            s += it2.next() + (it2.hasNext() ? ", " : "");
        s += "))";

        return s;
    }

    static class WE<V1, W1 extends Comparable<? super W1>> implements WeightedEdge<V1, W1> {

        V1 src, dst;
        W1 wght;

        WE(V1 s, V1 d, W1 w) {
            src = s;
            dst = d;
            wght = w;
        }

        public V1 source() {
            return src;
        }

        public V1 destination() {
            return dst;
        }

        public W1 weight() {
            return wght;
        }

        public String toString() {
            return "WE(" + src + "," + dst + "," + wght + ")";
        }

        public int hashCode() {
            return src.hashCode()+dst.hashCode()+wght.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof WE) {
                if  (((WE<?, ?>) obj).destination() == dst && ((WE<?, ?>) obj).source() == src && ((WE<?, ?>) obj).weight() == weight()) {
                    return true;
                }
            }
            return false;
        }

        public int compareTo(WeightedEdge<V1, W1> o) {
/*            int res;
			if (this.weight() == o.weight()) {
			    res = 0;
            } else if ((int) weight() <= (int) o.weight()) {
			    res = -1;
            } 	else res = 1;

			return res;*/
            return this.wght.compareTo(o.weight());
        }
    }
}

