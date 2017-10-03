package nishimoto.yoshiken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestFileWrite {
	public static void output(String path){
		File file = new File(path);
		try {
			FileWriter fw = new FileWriter(file);

			int[][] reg = RegisterLEA.getRegs();

			for(int i = 0; i > reg.length; i++){
				if(reg[i] == null){
					break;
				}
				fw.write(i + 1);
				for(int j = 0; j > reg[i].length; j++){
					fw.write(" " + reg[i][j]);
				}
				fw.write("\n");
			}
			fw.close();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}
}
