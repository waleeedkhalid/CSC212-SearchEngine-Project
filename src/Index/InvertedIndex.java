package Index;

import Process.*;
import List.LinkedList;

public class InvertedIndex {

    LinkedList<Word> invertedindex;

    public InvertedIndex() {
        invertedindex = new LinkedList <Word>();
    }

    public boolean addNew (Document doc) {
        if (invertedindex.empty()) {
            Word word = new Word(doc.getWords().retrieve().getWord(), doc);
            word.addDoc(doc);
            invertedindex.insert(word);
            return true;
        }

        invertedindex.findFirst();
        for (int i = 0; i < invertedindex.size(); i++) {
            if (invertedindex.retrieve().getWord().compareTo(doc.getWords().retrieve().getWord()) == 0) {
                invertedindex.retrieve().addDoc(doc);
                return true;
            }
            invertedindex.findNext();
        }

        Word word = new Word(doc.getWords().retrieve().getWord(), doc);
        word.addDoc(doc);
        invertedindex.insert(word);
        return true;
    }

    public boolean found(String word)
    {
        if (invertedindex.empty())
            return false;

        invertedindex.findFirst();
        for ( int i = 0 ; i < invertedindex.size() ; i++)
        {
            if ( invertedindex.retrieve().getWord().compareTo(word) == 0)
                return true;
            invertedindex.findNext();
        }
        return false;
    }

    public boolean [] ANDORFunction(String str )
    {
        if (! str.contains(" OR ") && ! str.contains(" AND "))
        {
            boolean [] r1 = new boolean[50];
            str = str.toLowerCase().trim();

            if (this.found (str))
                r1 = this.invertedindex.retrieve().getDocs();
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
        LinkedList<Document> b1 = new LinkedList<Document>();

        if (this.found (ANDs[0].toLowerCase().trim()))
            b1 = this.invertedindex.retrieve().getDocs();

        for ( int i = 1 ; i< ANDs.length ; i++)
        {
            LinkedList<Document> b2 = new LinkedList<Document>();
            if (this.found (ANDs[i].toLowerCase().trim()))
                b2 = this.invertedindex.retrieve().getDocs();

            for (int j = 0; j < b1.size(); j++) {
                if (b2.find(b1.retrieve())) {
                    b1.insert(b1.retrieve());
                }
                b1.findNext();
            }
        }

        boolean [] r = new boolean[50];
        for (int i = 0; i < b1.size(); i++) {
            r[b1.retrieve().getDocId()] = true;
            b1.findNext();
        }
        return r;
    }

    public boolean [] ORFunction(String str)
    {
        String [] ORs = str.split(" OR ");
        boolean [] b1 = new boolean [50];

        if (this.found (ORs[0].toLowerCase().trim()))
            b1 = this.invertedindex.retrieve().getDocs();

        for ( int i = 1 ; i< ORs.length ; i++)
        {
            boolean [] b2 = new boolean [50];
            if (this.found (ORs[i].toLowerCase().trim()))
                b2 = this.invertedindex.retrieve().getDocs();

            for ( int j = 0 ; j < 50 ; j++)
                b1 [j] = b1[j] || b2[j];

        }
        return b1;
    }

    public void printDocment()
    {
        if (this.invertedindex.empty())
            System.out.println("Empty Inverted Index");
        else
        {
            this.invertedindex.findFirst();
            while ( ! this.invertedindex.last())
            {
                System.out.println(invertedindex.retrieve());
                this.invertedindex.findNext();
            }
            System.out.println(invertedindex.retrieve());
        }
    }
}