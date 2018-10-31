package books;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "library")
@SessionScoped
public class BookList {

	private HashMap<String, Book> bookList;
	private Book bookHandled;

	public String updateOrCreate() {
		String id = bookHandled.getId();
		// nv livre
		if (id == null) {
			id = createId(bookHandled.getTitle(), bookHandled.getAuthor());
			bookHandled.setId(id);
		}
		bookList.put(id, bookHandled);
		return "bookList";
	}

	public String updatePrep(Book book) {
		bookHandled = book;
		return "bookUpdate";
	}

	public String createPrep() {
		bookHandled = new Book(null, null, null);
		return "bookUpdate";
	}

	public String delete(Book book) {
		String id = book.getId();
		bookList.remove(id);
		return null;
	}

	public boolean emptyList() {
		boolean noBooks = false;
		if (bookList.size() == 0)
			noBooks = true;
		return noBooks;
	}

	public ArrayList<Book> toArray() {
		ArrayList<Book> books = new ArrayList<Book>();
		for (String id : bookList.keySet())
			books.add(bookList.get(id));
		return books;
	}

	public BookList() {
		bookList = new HashMap<String, Book>();
		addBook("Java", "Henry Javaman");
		addBook("ECMA6", "J.S. Ecma");
		addBook("Maven", "Maïwen Maven");
		addBook("Jquery", "J. Query");
	}

	private void addBook(String title, String author) {
		String id = createId(title, author);
		Book book = new Book(title, author, id);
		bookList.put(id, book);
	}

	private String createId(String title, String author) {
		String id = "book-" + author.replaceAll(" ", "-") + "-" + title.replaceAll(" ", "-");
		return id;
	}

	public HashMap<String, Book> getBookList() {
		return bookList;
	}

	public void setBookList(HashMap<String, Book> bookList) {
		this.bookList = bookList;
	}

	public Book getBookHandled() {
		return bookHandled;
	}

	public void setBookHandled(Book bookHandled) {
		this.bookHandled = bookHandled;
	}
}
