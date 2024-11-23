package Index;

import Process.*;
import Trees.*;
import Trees.BST.BST;


public class InvertedIndexBST {
    BST<Word> invertedindexBST;
    int count = 0;

    public InvertedIndexBST() {
        invertedindexBST = new BST<> ();
    }

    public boolean addNew (Document doc)
    {
        String [] words = doc.getContent().split(" ");
        for ( int i = 0 ; i < words.length ; i++)
        {
            words[i] = words[i].toLowerCase().trim();
            if (this.found(words[i]))
            {
                Word word = invertedindexBST.retrieve();
                word.addDoc(doc);
                invertedindexBST.update(words[i]);
            }
            else
            {
                Word word = new Word(words[i]);
                word.addDoc(doc);
                invertedindexBST.insert(words[i]);
            }
        }
        return true;
    }

    public boolean found(String word)
    {
        return invertedindexBST.find(word);
    }

    public void printDocument()
    {
        invertedindexBST.Traverse();
    }
    //=====================================================================
    public boolean [] AND_OR_Function (String str )
    {
        if (! str.contains(" OR ") && ! str.contains(" AND "))
        {
            boolean [] r1 = new boolean[50];
            str = str.toLowerCase().trim();

            if (this.found (str))
                r1 =  this.invertedindexBST.retrieve().getDocs();
            return r1;
        }

        else if (str.contains(" OR ") && str.contains(" AND "))
        {
            String [] AND_ORs = str.split(" OR ");
            boolean []  r1 = ANDFunction(AND_ORs[0]);

            for ( int i = 1 ; i < AND_ORs.length ; i++  )
            {
                boolean [] r2 = ANDFunction(AND_ORs[i]);

                for ( int j = 0 ; j < 50 ; j++ )
                    r1 [j] = r1[j] || r2[j];
            }
            return r1;
        }

        else  if (str.contains(" AND "))
            return ANDFunction(str);

        return ORFunction(str);
    }

    public boolean [] ANDFunction(String str)
    {
        String [] ANDs = str.split(" AND ");
        boolean [] b1 = new boolean [50];

        if (this.found (ANDs[0].toLowerCase().trim()))
            b1 = this.invertedindexBST.retrieve().getDocs();

        for ( int i = 1 ; i< ANDs.length ; i++)
        {
            boolean [] b2 = new boolean [50];
            if (this.found (ANDs[i].toLowerCase().trim()))
                b2 = this.invertedindexBST.retrieve().getDocs();

            for ( int j = 0 ; j < 50 ; j++)
                b1 [j] = b1[j] && b2[j];
        }
        return b1;
    }

    public boolean [] ORFunction(String str)
    {
        String [] ORs = str.split(" OR ");
        boolean [] b1 = new boolean [50];

        if (this.found (ORs[0].toLowerCase().trim()))
            b1 = this.invertedindexBST.retrieve().getDocs();

        for ( int i = 1 ; i< ORs.length ; i++)
        {
            boolean [] b2 = new boolean [50];
            if (this.found (ORs[i].toLowerCase().trim()))
                b2 = this.invertedindexBST.retrieve().getDocs();

            for ( int j = 0 ; j < 50 ; j++)
                b1 [j] = b1[j] || b2[j];

        }
        return b1;
    }


}
