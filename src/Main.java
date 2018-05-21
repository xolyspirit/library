import controller.Librarian;
import dao.Library;
import view.Reader;

public class Main {

    public static void main(String[] args) {
        Library library  = new Library("Books.xml");
        Librarian librarian = new Librarian(library);
        Reader reader = new Reader(librarian);

        reader.readBDandSaveInXML();
        reader.readBySAX();
        reader.readByStAX();
        reader.readByDOM();
    }
}
