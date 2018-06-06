package view;
import controller.Librarian;
/**Класс вью, обращающийся к {@link Librarian} контроллеру, для чтения списка книг
 * @version 1.0
 * @author Xolyspirit */
public class Reader {
    /**Контроллер, к которому будем обращаться*/
    private Librarian librarian;
    /**Конструктор, принимающий @param librarian экземпляр контроллера*/
    public Reader(Librarian librarian) {
        this.librarian = librarian;
    }
    /**Читаем с помощью SAX*/
    public void readBySAX(){
        System.out.println(librarian.readBySAX());
    }
    /**Читаем с помощью StAX*/
    public void readByStAX(){
        System.out.println(librarian.readByStAX());
    }
    /**Читаем с помощью DOM*/
    public void readByDOM(){
        System.out.println(librarian.readByDOM());
    }
    /**Читаем данные из БД и сохраняем XML-файл */
    public void readBDandSaveInXML(){
        librarian.readBDandSaveInXML();
    }
}
