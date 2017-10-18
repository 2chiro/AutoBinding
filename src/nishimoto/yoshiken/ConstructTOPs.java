package nishimoto.yoshiken;

import java.util.ArrayList;

public class ConstructTOPs {
	private static int[][] addtop;
	private static int[][] subtop;
	private static int[][] multop;
	private static int[][] divtop;
	private static ArrayList<Integer> toplist;

	public static int[][] getAddTOP(){
		return addtop;
	}
	public static int[][] getSubTOP(){
		return subtop;
	}
	public static int[][] getMulTOP(){
		return multop;
	}
	public static int[][] getDivTOP(){
		return divtop;
	}

	public static void Basic(ArrayList<Integer> co, String[] type, int[] lifetime, int add, int sub, int mult, int div, int max){
		addtop = new int[add][2];
		subtop = new int[sub][2];
		multop = new int[mult][2];
		divtop = new int[div][2];
		toplist = new ArrayList<Integer>();

		//加算器TOP・VTOP生成
		for(int i = 0; i < add; i++){
			boolean ft = false;
			for(int j = 0; j < co.size(); j++){
				if(type[co.get(j)].equals("A") && !toplist.contains(co.get(j))){
					for(int k = 0; k < co.size(); k++){
						if(type[co.get(k)].equals("A") && !toplist.contains(co.get(k))){
							if(lifetime[co.get(k)] == lifetime[co.get(j)] + 1){
								addtop[i][0] = co.get(j);
								addtop[i][1] = co.get(k);
								toplist.add(co.get(j));
								toplist.add(co.get(k));
								ft = true;
								break;
							}
						}
					}
				}
				if(ft){
					break;
				}
			}
			if(!ft){
				for(int j = 0; j < co.size(); j++){
					if(type[co.get(j)].equals("A") && !toplist.contains(co.get(j))){
						if(lifetime[co.get(j)] == 1){
							addtop[i][0] = co.get(j);
							addtop[i][1] = -1;
							toplist.add(co.get(j));
						}
					}
				}
			}
		}

		//乗算器TOP・VTOP生成
		for(int i = 0; i < mult; i++){
			boolean ft = false;
			for(int j = 0; j < co.size(); j++){
				if(type[co.get(j)].equals("M") && !toplist.contains(co.get(j))){
					for(int k = 0; k < co.size(); k++){
						if(type[co.get(k)].equals("M") && !toplist.contains(co.get(k))){
							if(lifetime[co.get(k)] == lifetime[co.get(j)] + 1){
								multop[i][0] = co.get(j);
								multop[i][1] = co.get(k);
								toplist.add(co.get(j));
								toplist.add(co.get(k));
								ft = true;
								break;
							}
						}
					}
				}
				if(ft){
					break;
				}
			}
			if(!ft){
				for(int j = 0; j < co.size(); j++){
					if(type[co.get(j)].equals("M") && !toplist.contains(co.get(j))){
						if(lifetime[co.get(j)] == 1){
							multop[i][0] = co.get(j);
							multop[i][1] = -1;
							toplist.add(co.get(j));
						}
					}
				}
			}
		}

		//デバッグ
		for(int i = 0; i < addtop.length; i++){
			for(int j = 0; j < addtop[i].length; j++){
				System.out.println("addtop["+ i +"]["+ j + "]=" + addtop[i][j]);
			}
		}
		for(int i = 0; i < multop.length; i++){
			for(int j = 0; j < multop[i].length; j++){
				System.out.println("multop["+ i +"]["+ j + "]=" + multop[i][j]);
			}
		}
	}

}
