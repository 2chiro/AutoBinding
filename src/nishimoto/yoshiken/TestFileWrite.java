package nishimoto.yoshiken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestFileWrite {
	public static void output(String path){
		File file = new File(path);
		try {
			file.createNewFile();
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filewriter);
			PrintWriter pw = new PrintWriter(bw);

			int[][] reg = RegisterLEA.getRegs();

			boolean zerosearch = false;

			for(int i = 0; i < reg.length; i++){
				if(i < RegisterLEA.getLines()){
					int num = i + 1;
					pw.print(num);
					for(int j = 0; j < reg[i].length; j++){
						if(reg[i][j]==0){
							if(zerosearch == false){
								pw.print(" " + reg[i][j]);
								zerosearch = true;
							}
						}
						else{
							pw.print(" " + reg[i][j]);
						}
					}
					pw.print("\n");
				}
			}
			pw.close();
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
	}
}
