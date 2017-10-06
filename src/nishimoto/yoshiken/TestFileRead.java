package nishimoto.yoshiken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestFileRead {
	private static String[] ims_a;
	private static int[] vertex;
	private static String[] type;
	private static int[] life;
	private static int[] edge;
	private static int[] s;
	private static int[] e;
	private static int add = 0;
	private static int sub = 0;
	private static int mult = 0;
	private static int comp = 0;

	public static int[] getVertex(){
		return vertex;
	}
	public static String[] getType(){
		return type;
	}
	public static int[] getLife(){
		return life;
	}
	public static int[] getEdge(){
		return edge;
	}
	public static int[] getStart(){
		return s;
	}
	public static int[] getEnd(){
		return e;
	}
	public static int getAdd(){
		return add;
	}
	public static int getSub(){
		return sub;
	}
	public static int getMult(){
		return mult;
	}
	public static int getComp(){
		return comp;
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
		boolean v = false;
		boolean l = false;
		int vi = 0;
		int li = 0;
		for(int i = 0; i < ims_a.length; i++){
			String a = ims_a[i];
			char d = 0;
			if(a.length() > 0){
				d = a.charAt(0);
			}
			boolean ha = false;
			if(a.equals("--vertex")){
				v = true;
				l = false;
				ha = true;
			}
			else if(a.equals("--lifetime")){
				v = false;
				l = true;
				ha = true;
			}
			if(!ha){
				if(d != '#'){
					if(v){
						vi = vi + 1;
					}
					else if(l){
						li = li + 1;
					}
				}
			}
		}

		vertex = new int[vi];
		type = new String[vi];
		life = new int[vi];
		edge = new int[li];
		s = new int[li];
		e = new int[li];

		int ve = 0;
		int le = 0;

		for(int i = 0; i < ims_a.length; i++){
			String d = ims_a[i];
			boolean ha = false;
			char b = 0;
			if(d.length() > 0){
				b = d.charAt(0);
			}
			else{
				ha = true;
			}
			String[] imars = d.split("\\s", 0);
			if(imars[0].equals("--vertex")){
				v = true;
				l = false;
				ha = true;
			}
			else if(imars[0].equals("--lifetime")){
				v = false;
				l = true;
				ha = true;
			}
			if(b == '#'){
				for(int k = 0; k < imars.length; k++){
					String[] imarse = imars[k].split(">",0);
					if(imarse[0].equals("#adder=")){
						add = Integer.parseInt(imarse[1]);
					}
				}
				ha = true;
			}
			if(!ha){
				if(v){
					vertex[ve] = Integer.parseInt(imars[0]);
					type[ve] = imars[1];
					life[ve] = Integer.parseInt(imars[2]);
					ve = ve + 1;
				}
				else if(l){
					edge[le] = Integer.parseInt(imars[0]);
					s[le] = Integer.parseInt(imars[1]);
					e[le] = Integer.parseInt(imars[2]);
					le = le + 1;
				}
			}
		}
	}

	public static void resetRC(){
		add = 0;
		sub = 0;
		mult = 0;
		comp = 0;
	}
}
