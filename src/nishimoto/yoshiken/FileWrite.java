package nishimoto.yoshiken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWrite {
	public static void output(String path){
		File file = new File(path);
		try {
			file.createNewFile();
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(filewriter);
			PrintWriter pw = new PrintWriter(bw);

			int[][] reg = RegisterLEA.getRegs();
			int[][] addop = OperationLEA.getAddops();
			int[][] subop = OperationLEA.getAddops();
			int[][] multop = OperationLEA.getMultops();
			int[][] divop = OperationLEA.getDivops();

			//レジスタ＆演算器の数出力
			pw.println("Register. " + String.format("%1$4d", RegisterLEA.getLines()));
			pw.println("add. " + String.format("%1$9d", FileRead.getAdd()));
			pw.println("sub. " + String.format("%1$9d", FileRead.getSub()));
			pw.println("mult. " + String.format("%1$8d", FileRead.getMult()));
			pw.println("div. " + String.format("%1$9d", FileRead.getDiv()));

			//レジスタ割当結果出力
			for(int i = 0; i < reg.length; i++){
				if(i < RegisterLEA.getLines()){
					int num = i + 1;
					pw.print(String.format("%-2d",num));
					for(int j = 0; j < reg[i].length; j++){
						if(reg[i][j] != -1){
							pw.print(" " + String.format("%1$4d", reg[i][j]));
						}
					}
					pw.print("\n");
				}
			}

			pw.println("Calculator");

			//加算器割当出力
			if(FileRead.getAdd() != 0){
				for(int i = 0; i < addop.length; i++){
					int num = i + 1;
					pw.print("Add" + num);
					for(int j = 0; j < addop[i].length; j++){
						if(addop[i][j] != -1){
							pw.print(" " + String.format("%1$4d", addop[i][j]));
						}
					}
					pw.print("\n");
				}
			}

			//減算器割当出力
			if(FileRead.getSub() != 0){
				for(int i = 0; i < subop.length; i++){
					int num = i + 1;
					pw.print("Sub" + num);
					for(int j = 0; j < subop[i].length; j++){
						if(subop[i][j] != -1){
							pw.print(" " + String.format("%1$4d", subop[i][j]));
						}
					}
					pw.print("\n");
				}
			}

			//乗算器割当出力
			if(FileRead.getMult() != 0){
				for(int i = 0; i < multop.length; i++){
					int num = i + 1;
					pw.print("Mul" + num);
					for(int j = 0; j < multop[i].length; j++){
						if(multop[i][j] != -1){
							pw.print(" " + String.format("%1$4d", multop[i][j]));
						}
					}
					pw.print("\n");
				}
			}

			//除算器割当出力
			if(FileRead.getDiv() != 0){
				for(int i = 0; i < divop.length; i++){
					int num = i + 1;
					pw.print("Div" + num);
					for(int j = 0; j < divop[i].length; j++){
						if(divop[i][j] != -1){
							pw.print(" " + String.format("%1$4d", divop[i][j]));
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
