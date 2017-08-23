package nishimoto.yoshiken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {
	public String[] ims;
	public int[] verids;
	public char[] types;
	public int[] lifetimes;
	public int[] edgids;
	public int[] ver1s;
	public int[] ver2s;
	public char[] ports;

	public void fr(){
		imput();
		dataArrange();
	}

	public void imput(){
		ims = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("test.sdfg");
			br = new BufferedReader(fr);
			String line;
			int i = 0;
			while((line = br.readLine()) != null){
				ims[i] = line;
				i++;
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
	public void dataArrange(){
		String[] imars = null;
		verids = null;
		types = null;
		lifetimes = null;
		edgids = null;
		ver1s = null;
		ver2s = null;
		ports = null;
		boolean ve = false;
		boolean de = false;
		int ven = 0;
		int den = 0;
		for(int i = 0; i < ims.length; i++){
			String d = ims[i];

			if(d.equals("---vertex")){
				ve = true;
				de = false;
				continue;
			}
			else if(d.equals("--edge")){
				de = true;
				ve = false;
				continue;
			}
			if(ve == true){
				imars = d.split("\\s", 0);
				verids[ven] = Integer.parseInt(imars[0]);
				types[ven] = imars[1].charAt(0);
				lifetimes[ven] = Integer.parseInt(imars[2]);
				ven++;
			}
			else if(de == true){
				imars = d.split("\\s", 0);
				edgids[den] = Integer.parseInt(imars[0]);
				ver1s[den] = Integer.parseInt(imars[1]);
				ver2s[den] = Integer.parseInt(imars[2]);
				ports[den] = imars[3].charAt(0);
			}
		}
	}
}
