import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;

/**
 * Created by qwe95 on 2/20/2017.
 * <p>
 * Damn, a word can be in many synsets, so a word can has many numbers, so that the data structure must be changed.
 * Also, a synset has many synonyms.
 */
public class WordNet {
    private RedBlackBST synsetsBST;
    private RedBlackBST synsetsBST_number;
    private Digraph hypernymsGraph;
    private int parent;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        synsetsBST = new RedBlackBST();  // read in all the synsets in a redblack tree. No need to use Integer here, just ignore the generic type declaration.
        synsetsBST_number = new RedBlackBST();  // read in all the synsets in a redblack tree.
        In synsetIn = new In(synsets);
        int n = 0;
        while (synsetIn.hasNextLine()) {
            String i = synsetIn.readLine();
            String[] parts = i.split(",");
            String[] words = parts[1].split("\\s+"); // the set of the synonyms.
            int number = Integer.parseInt(parts[0]); // the id number.
            Bag bag_number = new Bag();
            for (int k = 0; k < words.length ; k++) { // multiple words for one id.
                String word = words[k];
                if (synsetsBST.contains(word)) {
                    Bag container = (Bag) synsetsBST.get(word); // multiple ids for one word.
                    container.add(number);
                } else {
                    Bag bag = new Bag();
                    bag.add(number);
                    synsetsBST.put(word, bag); // the first one is always the number, the second one is the string itself.
                }
                bag_number.add(word);
            }
            synsetsBST_number.put(number, bag_number); // the first one is always the number, the second one is the string itself.
            n++;
        }

        hypernymsGraph = new Digraph(n);  // show the word net in a digraph.
        In hypernymsIn = new In(hypernyms);
        while (hypernymsIn.hasNextLine()) {
            String j = hypernymsIn.readLine();
            String[] parts = j.split(",");
            int node = Integer.parseInt(parts[0]);
            for (int k = 1; k < parts.length; k++)
                hypernymsGraph.addEdge(node, Integer.parseInt(parts[k]));
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return synsetsBST.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return synsetsBST.contains(word);
    }
   /* public boolean isNoun(String word, int number) {
        int i = (int) synsetsBST.get(word);
        boolean j = synsetsBST.contains(word);
        if (synsetsBST.contains(word) && (number == (int) synsetsBST.get(word))) return true ;
        return false;
    }
    public String getKey(String word) {
        return (String) synsetsBST_number.get(getValue(word));
    }
    public int getValue(String word) {
        return (int)synsetsBST.get(word);
    }*/

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        SAP s = new SAP(hypernymsGraph);
        Bag v = (Bag) synsetsBST.get(nounA);
        Bag w = (Bag) synsetsBST.get(nounB);
        int distance = s.length(v, w);
        parent = s.ancestor(v, w);
        return distance;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return (String) synsetsBST_number.get(parent); // you have to record the information of the numbers. there is no other way.
    }


    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
/*In in = new In(args[0]);
         while (in.hasNextLine()) {
            String j = in.readLine();
            String[] parts = j.split(",");
            String noun = parts[1];
            int number = Integer.parseInt(parts[0]);
            if (!wordnet.isNoun(noun, number)) {
             StdOut.printf("fake word = %s, fake number = %d, word = %s, number = %d\n", wordnet.getKey(noun), wordnet.getValue(noun), noun, number);
            }
         }*/
    }
}
