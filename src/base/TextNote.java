package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class TextNote extends Note {
	String content;

	public TextNote(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	public String getTextFromFile(String absolutePath) {
		String result = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {
			fis = new FileInputStream(absolutePath);
			isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			try {
				while(br.readLine() != null) 
				{
					result += br.readLine();
				}
				br.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void exportTextToFile(String pathFolder) {
		BufferedWriter bw = null;
		try {
			File file = new File(pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") + ".txt");
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(this.content);
			bw.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void newContent(String content) {
		this.content = content;
	}
}
