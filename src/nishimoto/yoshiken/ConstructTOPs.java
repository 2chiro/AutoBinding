package nishimoto.yoshiken;

import java.util.ArrayList;

public class ConstructTOPs {
	private static int[][] addtop;
	private static int[][] subtop;
	private static int[][] multop;
	private static int[][] divtop;
	private static ArrayList<Integer> toplist;
	private static ArrayList<Integer> topedge;

	private static ArrayList<Integer> addver;
	private static ArrayList<String> addtype;
	private static ArrayList<Integer> addlife;
	private static ArrayList<String> addport;

	private static ArrayList<Integer> addedge;
	private static ArrayList<Integer> addver1;
	private static ArrayList<Integer> addver2;

	private static boolean newsdfglistener = false;

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
	public static ArrayList<Integer> getTOPEdge(){
		return topedge;
	}

	public static ArrayList<Integer> getAddVer(){
		return addver;
	}
	public static ArrayList<String> getAddType(){
		return addtype;
	}
	public static ArrayList<Integer> getAddLife(){
		return addlife;
	}
	public static ArrayList<String> getAddPort(){
		return addport;
	}

	public static ArrayList<Integer> getAddEdge(){
		return addedge;
	}
	public static ArrayList<Integer> getAddVer1(){
		return addver1;
	}
	public static ArrayList<Integer> getAddVer2(){
		return addver2;
	}

	public static boolean getNewSDFGListener(){
		return newsdfglistener;
	}

	public static void Basic(ArrayList<Integer> co, int[] edgeId, String[] type, int[] lifetime, int add, int sub, int mult, int div, int max){
		int av = lifetime.length;
		int ve = 0;
		int ae = edgeId.length;

		int[] addrc = new int[max];
		int[] subrc = new int[max];
		int[] mulrc = new int[max];
		int[] divrc = new int[max];
		for(int i = 0; i < type.length; i++){
			if(type[i].equals("A")){
				addrc[lifetime[i]-1] = addrc[lifetime[i]-1] + 1;
			}
			else if(type[i].equals("S")){
				subrc[lifetime[i]-1] = subrc[lifetime[i]-1] + 1;
			}
			else if(type[i].equals("M")){
				mulrc[lifetime[i]-1] = mulrc[lifetime[i]-1] + 1;
			}
			else if(type[i].equals("D")){
				divrc[lifetime[i]-1] = divrc[lifetime[i]-1] + 1;
			}

		}

		addtop = new int[add][2];
		subtop = new int[sub][2];
		multop = new int[mult][2];
		divtop = new int[div][2];
		toplist = new ArrayList<Integer>();
		topedge = new ArrayList<Integer>();

		int a = 0;
		int m = 0;

		//phase 1 SDFGからTOPを生成
		for(int j = 0; j < co.size(); j++){
			//加算器
			if(type[co.get(j)].equals("A") && !toplist.contains(co.get(j)) && a < add){
				for(int k = 0; k < co.size(); k++){
					if(type[co.get(k)].equals("A") && !toplist.contains(co.get(k))){
						if(lifetime[co.get(k)] == lifetime[co.get(j)] + 1){
							addtop[a][0] = co.get(j);
							addtop[a][1] = co.get(k);
							toplist.add(co.get(j));
							toplist.add(co.get(k));
							topedge.add(co.get(k));
							a = a + 1;
							break;
						}
						else if(lifetime[co.get(k)] == lifetime[co.get(j)] - 1){
							addtop[a][0] = co.get(k);
							addtop[a][1] = co.get(j);
							toplist.add(co.get(k));
							toplist.add(co.get(j));
							topedge.add(co.get(j));
							a = a + 1;
							break;
						}
					}
				}
			}
			//乗算器
			if(type[co.get(j)].equals("M") && !toplist.contains(co.get(j)) && m < mult){
				for(int k = 0; k < co.size(); k++){
					if(type[co.get(k)].equals("M") && !toplist.contains(co.get(k))){
						if(lifetime[co.get(k)] == lifetime[co.get(j)] + 1){
							multop[m][0] = co.get(j);
							multop[m][1] = co.get(k);
							toplist.add(co.get(j));
							toplist.add(co.get(k));
							topedge.add(co.get(k));
							m = m + 1;
							break;
						}
						else if(lifetime[co.get(k)] == lifetime[co.get(j)] - 1){
							multop[m][0] = co.get(k);
							multop[m][1] = co.get(j);
							toplist.add(co.get(k));
							toplist.add(co.get(j));
							topedge.add(co.get(j));
							m = m + 1;
							break;
						}
					}
				}
			}
		}
		//phase1 SDFGからVTOPを生成
		if(toplist.size() <= co.size()){
			for(int j = 0; j < co.size(); j++){
				//加算器
				if(type[co.get(j)].equals("A") && !toplist.contains(co.get(j)) && a < add){
					if(lifetime[co.get(j)] == 1){
						addtop[a][0] = co.get(j);
						addtop[a][1] = -1;
						toplist.add(co.get(j));
						topedge.add(co.get(j));
					}
				}
				//乗算器
				if(type[co.get(j)].equals("M") && !toplist.contains(co.get(j)) && m < mult){
					if(lifetime[co.get(j)] == 1){
						multop[m][0] = co.get(j);
						multop[m][1] = -1;
						toplist.add(co.get(j));
						topedge.add(co.get(j));
					}
				}
			}
		}

		addver = new ArrayList<Integer>();
		addtype = new ArrayList<String>();
		addlife = new ArrayList<Integer>();
		addport = new ArrayList<String>();

		addedge = new ArrayList<Integer>();
		addver1 = new ArrayList<Integer>();
		addver2 = new ArrayList<Integer>();
		addport = new ArrayList<String>();

		//phase 2 テスト演算を追加してTOPを生成
		if(toplist.size() <= co.size()){
			for(int j = 0; j < co.size(); j++){
				//加算器
				if(type[co.get(j)].equals("A") && !toplist.contains(co.get(j)) && a < add){
					if(addrc[lifetime[co.get(j)] - 1] < addrc[lifetime[co.get(j)] - 1] + 1){
						for(int k = 0; k < 3; k++){
							addver.add(av);
							if(k == 2){
								addtype.add("A");
								addlife.add(lifetime[co.get(j)]-1);
								addtop[a][0] = addver.get(ve);
								addtop[a][1] = co.get(j);
								addver1.add(addver.get(ve - 2));
								addver1.add(addver.get(ve - 1));
								addver2.add(addver.get(ve));
								addver2.add(addver.get(ve));
								addport.add("l");
								addport.add("r");
								toplist.add(co.get(j));
								topedge.add(co.get(j));
							}
							else{
								addedge.add(ae);
								addtype.add("I");
								addlife.add(-1);
								ae = ae + 1;
							}
							ve = ve + 1;
							av = av + 1;
						}
					}
					else{
						for(int k = 0; k < 3; k++){
							addver.add(av);
							if(k == 2){
								addtype.add("A");
								addlife.add(lifetime[co.get(j)]+1);
								addtop[a][0] = co.get(j);
								addtop[a][1] = addver.get(ve);
								addver1.add(addver.get(ve - 2));
								addver1.add(addver.get(ve - 1));
								addver2.add(addver.get(ve));
								addver2.add(addver.get(ve));
								toplist.add(co.get(j));
								topedge.add(addver.get(ve));
							}
							else{
								addtype.add("I");
								addlife.add(-1);
								ae = ae + 1;
							}
							ve = ve + 1;
							av = av + 1;
						}
					}
					newsdfglistener = true;
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
