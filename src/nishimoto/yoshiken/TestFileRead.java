package nishimoto.yoshiken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestFileRead {
	private static String[] ims_a;
	private static int[] edge;
	private static int[] s;
	private static int[] e;

	public static int[] getEdge(){
		return edge;
	}

	public static int[] getStart(){
		return s;
	}

	public static int[] getEnd(){
		return e;
	}

	public static void imput(String path){
		ims_a = null;
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<String> ims = new ArrayList<String>();
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null){
				ims.add(line);
			}
			ims_a = new String[ims.size()];
			for(int i = 0; i < ims.size(); i++){
				ims_a[i] = ims.get(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try{
				br.close();
				fr.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	public static void dataArrange(){
		edge = null; s = null; e = null;
		edge = new int[ims_a.length];
		s = new int[ims_a.length];
		e = new int[ims_a.length];
		for(int i = 0; i < ims_a.length; i++){
			String d = ims_a[i];
			String[] imars = d.split("\\s", 0);
			edge[i] = Integer.parseInt(imars[0]);
			s[i] = Integer.parseInt(imars[1]);
			e[i] = Integer.parseInt(imars[2]);
		}
	}
}
