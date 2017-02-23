import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by qwe95 on 2/23/2017.
 */
public class Outcast {
    private WordNet network;

    public Outcast(WordNet wordnet)         // constructor takes a WordNet object
    {
        network = wordnet;
    }

    public String outcast(String[] nouns)   // given an array of WordNet nouns, return an outcast
    {
        int N = nouns.length; // length of the array
        int max = 0, index = 0; // maximal index and distance of the word.
        int[] distance = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j != i)
                    distance[i] += network.distance(nouns[i], nouns[j]); // there would be error if the distance is -1.
            }
            if (distance[i] > max) {
                max = distance[i];
                index = i;
            }
        }
        return nouns[index];

    }

    public static void main(String[] args)  // see test client below
    {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }

}
