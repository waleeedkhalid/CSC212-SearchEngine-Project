public class DocScore {
    int docID;
    int score;


    public DocScore(int docID, int score) {
        this.docID = docID;
        this.score = score;
    }


    public boolean isGreaterThan(DocScore other) {
        return this.score > other.score;  // Return true if this score is greater
    }
}
