package base;

import java.util.ArrayList;
import java.util.Collections;

public class NoteBook {
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);	
		return insertNote(folderName, note);
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title , content);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote (String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	public boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for(Folder f1: folders) {
			if(f1.getName().equals(folderName)) {
				f = f1;
			}
		}
		
		if(f == null) {
			f = new Folder(folderName);
			folders.add(f);
		}
		
		for(Note n: f.getNotes()) {
			if(n.equals(note)) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		
		f.addNote(note);
		return true;
	}
	
	public void sortFolders() {
		for(Folder f : folders) {
			f.sortNotes();
		}
		Collections.sort(folders);
	}
	
	public ArrayList<Note> searchNotes(String keywords){
		ArrayList<Note> n_note = new ArrayList<Note>();
		for(Folder f : folders) {
			n_note.addAll(f.searchNotes(keywords));
		}
		return n_note;
	}
}
