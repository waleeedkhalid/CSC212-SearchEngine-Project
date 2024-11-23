
public class QueryProcessing {
    static InvertedIndex inverted;

    public QueryProcessing(InvertedIndex inverted) {
        this.inverted = inverted;
    }

    public static LinkedList<Integer> ANDQuery(String Query) {
        LinkedList<Integer> A = new LinkedList<Integer>();
        LinkedList<Integer> B = new LinkedList<Integer>();
        String Terms[] = Query.split("AND");
        if (Terms.length == 0)
            return A;

        boolean found = inverted.search_word_in_inverted(terms[0].trim().toLowerCase());
        if (found) {
            A = inverted.inverted index.retrieve().doc_IDS;
        }
        for (int i = 1; i < terms.length; i++) {
            found = inverted.search_word_in_inverted(terms[0].trim().toLowerCase());
            if (found)
                B = inverted.inverted_index.retrieve().doc_IDS;
            A = AndQuery(A, B);
        }

        return A;
    }

    public static LinkedList<Integer> AndQuery(LinkedList<Integer> A, LinkedList<Integer> B) {
        LinkedList<Integer> result = new LinkedList<Integer>();

        if (A.isEmpty() || B.isEmpty()) {
            return result;
        }

        A.findFirst();
        while (true) {
            boolean found = existsInResult(result, A.retrieve());
            if (!found) {
                B.findFirst();
                while (true) {
                    if (B.retrieve().equals(A.retrieve())) {
                        result.insert(A.retrieve());
                        break;
                    }
                    if (!B.isLast()) {
                        B.findNext();
                    } else {
                        break;
                    }
                }
            }
            if (!A.isLast()) {
                A.findNext();
            } else {
                break;
            }
        }

        return result;
    }

    public static LinkedList<Integer> ORQuery(String Query) {
        LinkedList<Integer> A = new LinkedList<Integer>();
        LinkedList<Integer> B = new LinkedList<Integer>();

        String[] terms = Query.split("OR");
        if (terms.length == 0) {
            return A;
        }

        // البحث باستخدام الكلمة الأولى في الاستعلام
        boolean found = inverted.search_word_in_inverted(terms[0].trim().toLowerCase());
        if (found) {
            A = inverted.inverted_index.retrieve().doc_IDS;
        }

        // البحث باستخدام بقية الكلمات
        for (int i = 1; i < terms.length; i++) {
            found = inverted.search_word_in_inverted(terms[i].trim().toLowerCase());
            if (found) {
                B = inverted.inverted_index.retrieve().doc_IDS;
            }
            A = ORQuery(A, B); // دمج النتائج باستخدام ORQuery
        }

        return A;
    }

    public static LinkedList<Integer> ORQuery(LinkedList<Integer> A, LinkedList<Integer> B) {
        LinkedList<Integer> result = new LinkedList<Integer>();

        if (A.isEmpty() && B.isEmpty()) {
            return result;
        }

        // معالجة العناصر في القائمة A
        A.findFirst();
        while (!A.isEmpty()) {
            boolean found = existsInResult(result, A.retrieve());
            if (!found) { // إذا لم يتم العثور على العنصر في النتيجة
                result.insert(A.retrieve());
            }
            if (!A.isLast()) {
                A.findNext();
            } else {
                break;
            }
        }

        // معالجة العناصر في القائمة B
        B.findFirst();
        while (!B.isEmpty()) {
            boolean found = existsInResult(result, B.retrieve());
            if (!found) { // إذا لم يتم العثور على العنصر في النتيجة
                result.insert(B.retrieve());
            }
            if (!B.isLast()) {
                B.findNext();
            } else {
                break;
            }
        }

        return result;
    }
    public static boolean existsInResult(LinkedList<Integer> result, Integer id) {
        if (result.isEmpty()) {
            return false;
        }

        result.findFirst();
        while (!result.isLast()) {
            if (result.retrieve().equals(id)) {
                return true;
            }
            result.findNext();
        }


        if (result.retrieve().equals(id)) {
            return true;
        }

        return false;
    }
}



