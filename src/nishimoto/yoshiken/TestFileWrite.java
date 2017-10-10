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
			int[][] addop = OperationClique.getAddops();
			int[][] subop = OperationClique.getAddops();
			int[][] multop = OperationClique.getMultops();
			int[][] compop = OperationClique.getCompops();

			//レジスタ＆演算器の数出力
			pw.println("Register." + String.format("%1$5d", RegisterLEA.getLines()));
			pw.println("add." + String.format("%1$10d", TestFileRead.getAdd()));
			pw.println("sub." + String.format("%1$10d", TestFileRead.getSub()));
			pw.println("mult." + String.format("%1$9d", TestFileRead.getMult()));
			pw.println("comp." + String.format("%1$9d", TestFileRead.getComp()));

			//レジスタ割当結果出力
			for(int i = 0; i < reg.length; i++){
				if(i < RegisterLEA.getLines()){
					int num = i + 1;
					pw.print(num);
					for(int j = 0; j < reg[i].length; j++){
						if(reg[i][j] != -1){
							pw.print(" " + reg[i][j]);
						}
					}
					pw.print("\n");
				}
			}

			//加算器割当出力
			if(TestFileRead.getAdd() != 0){
				for(int i = 0; i < addop.length; i++){
					int num = i + 1;
					pw.print("Add" + num);
					for(int j = 0; j < addop[i].length; j++){
						if(addop[i][j] != -1){
							pw.print(" " + addop[i][j]);
						}
					}
					pw.print("\n");
				}
			}

			//減算器割当出力
			if(TestFileRead.getSub() != 0){
				for(int i = 0; i < subop.length; i++){
					int num = i + 1;
					pw.print("Sub" + num);
					for(int j = 0; j < subop[i].length; j++){
						if(subop[i][j] != -1){
							pw.print(" " + subop[i][j]);
						}
					}
					pw.print("\n");
				}
			}

			//乗算器割当出力
			if(TestFileRead.getMult() != 0){
				for(int i = 0; i < multop.length; i++){
					int num = i + 1;
					pw.print("Mul" + num);
					for(int j = 0; j < multop[i].length; j++){
						if(multop[i][j] != -1){
							pw.print(" " + multop[i][j]);
						}
					}
					pw.print("\n");
				}
			}

			//除算器割当出力
			if(TestFileRead.getComp() != 0){
				for(int i = 0; i < compop.length; i++){
					int num = i + 1;
					pw.print("Com" + num);
					for(int j = 0; j < compop[i].length; j++){
						if(compop[i][j] != -1){
							pw.print(" " + compop[i][j]);
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
