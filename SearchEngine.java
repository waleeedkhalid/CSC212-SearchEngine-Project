import java.io.File;
import java.util.Scanner;
public class SearchEngine {
    int tokens = 0;
    int vocab = 0;

    Index index;
    InvertedIndex invertedindex;
    InvertedIndexBST invertedindexBST;
    InvertedIndexAVL invertedindexAVL;

    InvertedIndexAVLRanked invertedindexAVLranked;

    public SearchEngine()
    {
        index = new Index();
        invertedindex = new InvertedIndex();

        invertedindexBST = new InvertedIndexBST ();
        invertedindexAVL = new InvertedIndexAVL();

        invertedindexAVLranked = new InvertedIndexAVLRanked();
    }

    /*
    Document Processing:
    o Read documents from a CSV file.
    o Split the text into a list of words based on whitespace.
    o Convert all text to lowercase
    o Preprocess the text by removing stop-words (e.g., "the," "is," "and"). The list of
    stop-words will be given to you.
    o Remove all punctuations and non-alphanumerical characters
    o Proceed to build the index
     */
    public void LoadData (String stopFile, String fileName)
    {
        try{
            File stopfile = new File (stopFile);
            Scanner reader = new Scanner (stopfile).useDelimiter("\\Z");
            String stops = reader.next();

            stops = stops.replaceAll("\n", " ");
            //String punc = " ; , . + - / % , = == => > < != <= && || !  \\ \" \' ( ) { } [ ] ";

            File docsfile = new File(fileName);
            Scanner reader1 = new Scanner (docsfile);
            String line = reader1.nextLine();

            for ( int lineID = 0 ; lineID <50 ; lineID ++ )
            {
                line = reader1.nextLine().toLowerCase();

                int pos = line.indexOf(',');
                int docID = Integer.parseInt( line .substring(0, pos));

                String data = line.substring(pos+1, line.length() - pos).trim();
                System.out.println(data);

                data = data.toLowerCase();
                data =  data.replaceAll("[;,.]", " ");
                data =  data.replaceAll("-to-", "");
                data =  data.replaceAll("-", "");
                data =  data.replaceAll("[\"\']", "");
                //data =  data.replaceAll("[^a-zA-Z0-9]", " ");

                String [] words = data.split(" "); // --1

                for (int i = 0; i < words.length ; i++)
                {
                    tokens ++;

                    String word = words[i].trim(); //--2
                    if ( ! stops.contains(word + " ")) //--3
                    {

                        // word = word.replaceAll("[^a-zA-Z0-9]", " ");

                        if (word.length() >= 1)
                        {
                            this.index.addDocument(docID, word);
                            this.invertedindex.addNew(docID, word);
                            this.invertedindexBST.addNew(docID, word);
                            this.invertedindexAVL.addNew(docID, word);
                            this.invertedindexAVLranked.addNew(docID, word);
                        }
                    }
                }
                // this.index.printDocment(docID);
            }
            //this.invertedindex.printDocment();
            //this.invertedindexBST.printDocument();
            //this.invertedindexAVL.printDocument();
            //this.invertedindexAVLranked.printDocument();

            vocab = invertedindexAVL.invertedindexAVL.size();

            System.out.println("tokens " + tokens);
            System.out.println("vocabs " + vocab);


            reader.close();
            reader1.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean [] booleanRetrieval(String str , int DSType)
    {
        boolean [] docs = new boolean [50] ;
        for ( int i = 0 ; i < docs.length ; i++)
            docs[i] = false;

        switch (DSType)
        {
            case 1 :
                docs = this.invertedindex.ANDORFunction(str);
                break;
            case 2:
                docs = this.invertedindexAVL.ANDORFunction(str);
                break;
            default :
                System.out.println("Bad data structure");

        }
        return docs;
    }

    public void rankedRetrieval(String str)
    {
        this.invertedindexAVLranked.TF(str);
    }


}