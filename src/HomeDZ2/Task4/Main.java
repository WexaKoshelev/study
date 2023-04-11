package HomeDZ2.Task4;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Document user = new Document();
        user.id = 1;
        user.name = "Valea";
        user.pageCount = 2;
        Document user2 = new  Document();
        user2.id = 2;
        user2.name = "Anton";
        user2.pageCount = 3;
        Document user3 = new  Document();
        user3.id = 3;
        user3.name = "Vika";
        user3.pageCount = 5;
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(user);
        documents.add(user2);
        documents.add(user3);
        Document document1 = new Document();
     System.out.print(document1.organizeDocuments(documents));
    }
}
