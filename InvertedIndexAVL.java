public class InvertedIndexAVL {

    AVLTree <String, Term> invertedindexAVL;

    public InvertedIndexAVL() {
        invertedindexAVL = new AVLTree <String, Term>();
    }

    public boolean addNew (int docID, String word)
    {
        if (invertedindexAVL.empty())
        {
            Term t = new Term ();
            t.setVocabulary(new Vocabulary (word));
            t.addDocID(docID);
            invertedindexAVL.insert(word, t);
            return true;
        }
        else
        {
            if (invertedindexAVL.find(word))
            {
                Term t = invertedindexAVL.retrieve();
                t.addDocID(docID);
                invertedindexAVL.update(t);
                return false;

            }

            Term t = new Term ();
            t.setVocabulary(new Vocabulary (word));
            t.addDocID(docID);
            invertedindexAVL.insert(word, t);
            return true;
        }
    }

    public boolean found(String word)
    {
        return invertedindexAVL.find(word);
    }

    public void printDocument()
    {
        invertedindexAVL.Traverse();
    }


    public boolean [] ANDORFunction(String str )
    {
        if (! str.contains(" OR ") && ! str.contains(" AND "))
        {
            boolean [] r1 = new boolean[50];
            str = str.toLowerCase().trim();

            if (this.found (str))
                r1 =  this.invertedindexAVL.retrieve().getDocs();
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
            b1 = this.invertedindexAVL.retrieve().getDocs();

        for ( int i = 1 ; i< ANDs.length ; i++)
        {
            boolean [] b2 = new boolean [50];
            if (this.found (ANDs[i].toLowerCase().trim()))
                b2 = this.invertedindexAVL.retrieve().getDocs();

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
            b1 = this.invertedindexAVL.retrieve().getDocs();

        for ( int i = 1 ; i< ORs.length ; i++)
        {
            boolean [] b2 = new boolean [50];
            if (this.found (ORs[i].toLowerCase().trim()))
                b2 = this.invertedindexAVL.retrieve().getDocs();

            for ( int j = 0 ; j < 50 ; j++)
                b1 [j] = b1[j] || b2[j];

        }
        return b1;
    }

}