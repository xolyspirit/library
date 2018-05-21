package controller;

import dao.Library;

public class Librarian {
    private Library library;

    public Librarian(Library library) {
        this.library = library;
    }

    public String readBySAX(){
        return library.readBySAX();
    }
    public String readByStAX(){
        return library.readByStAX();
    }
    public String readByDOM(){
        return library.readByDOM();
    }
    public void readBDandSaveInXML(){
        library.saveWithXML(library.readBD());
    }
}
