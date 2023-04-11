package HomeDZ2.Task4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {
    public int id;
    public String name;
    public int pageCount;
    public Map<Integer, Document> organizeDocuments(List<Document> documents) {
        HashMap <Integer,Document> res = new HashMap<>();
        for (Document d : documents) {
            res.put(d.id, d);
        }
        return res;
    }
}
