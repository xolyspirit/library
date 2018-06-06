import controller.Librarian;
import dao.Library;
import view.Reader;

/**Главный класс, запускающий выполнение программы
 * @version 1.0
 * @author Xolyspirit */
public class Main {
    /**Создание библиотеки {@link Library}, подключение к ней билиотекаря {@link Librarian}.
     * Создание читателя {@link Reader} и подключение его к библиотекарю {@link Librarian}
     * запуск всех операций в нужном порядке*/
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
