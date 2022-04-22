package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.io.Serializable;

public class Folder implements Comparable<Folder>, Serializable {
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note n) {	
		notes.add(n);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (Note n: notes) {
			if(n instanceof TextNote) {
				nText++;
			}
			if(n instanceof ImageNote) {
				nImage++;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}
	
	@Override
	public int compareTo(Folder f) {
		if(this.name.compareTo(f.name) > 0) {
			return 1;
		}
		else if(this.name.compareTo(f.name) < 0) {
			return -1;
		}
		else {
			return 0;
		}
		
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public ArrayList<Note> searchNotes(String keywords){
		keywords = keywords.toLowerCase();
		String[] keys = keywords.split(" ");
		ArrayList<Note> new_note = new ArrayList<Note>();
		new_note.addAll(notes);
		String OR = "or";
		for(int i = 0; i < keys.length ; i++) {
			if(i+1 < keys.length && keys[i+1].equals(OR)) {
				String k1 = keys[i];
				String k2 = keys[i+2];
				for(Note n : notes) {
					if(n instanceof TextNote) {
						TextNote t_note = (TextNote) n;
						String n1 = t_note.getTitle().toLowerCase();
						String n2 = t_note.content.toLowerCase();
						if(!(n1.contains(k1) || n1.contains(k2) || n2.contains(k1) || n2.contains(k2))) {
							if(new_note.contains(n)) {
								new_note.remove(n);
							}
						}
					}
					else {
						String n1 = n.getTitle().toLowerCase();
						if(!(n1.contains(k1) || n1.contains(k2))) {
							if(new_note.contains(n)) {
								new_note.remove(n);
							}
						}
					}
				}
				i += 2;
			}
			else {
				String k = keys[i];
				for(Note n : notes) {
					if(n instanceof TextNote) {
						TextNote t_note = (TextNote)n;
						String n1 = t_note.getTitle().toLowerCase();
						String n2 = t_note.content.toLowerCase();
						if(!(n1.contains(k) || n2.contains(k))) {
							if(new_note.contains(n)) {
								new_note.remove(n);
							}
						}
					}
					else {
						String n1 = n.getTitle().toLowerCase();
						if(!(n1.contains(k))) {
							if(new_note.contains(n)) {
								new_note.remove(n);
							}
						}
					}
				}
			}
		}
		return new_note;
	}
	
	public boolean removeNotes(String title) {
		   // TODO
		   // Given the title of the note, delete it from the folder.
		   // Return true if it is deleted successfully, otherwise return false. 
		int n = 0;
		for (Note note: notes){
			if (note.getTitle().compareTo(title) == 0){
				notes.remove(n);
				return true;
			}
			n++;
		}
		return false;
		}
}
