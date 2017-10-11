package nishimoto.yoshiken;

import java.util.HashMap;
import java.util.Map;

public class OperationLEA {
	private static int[][] addops;
	private static int[][] subops;
	private static int[][] multops;
	private static int[][] divops;

	public static int[][] getAddops(){
		return addops;
	}
	public static int[][] getSubops(){
		return subops;
	}
	public static int[][] getMultops(){
		return multops;
	}
	public static int[][] getDivops(){
		return divops;
	}

	public static void Basic(int add, int sub, int mult, int div, int[] vertexId, String[] type, int[] lifetime){
		Map<Integer, Integer> addmap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> submap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> multmap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> divmap = new HashMap<Integer, Integer>();
		int endtime = 0;
		for(int i = 0; i < vertexId.length; i++){
			if("A".equals(type[i])){
				addmap.put(vertexId[i], lifetime[i]);
			}
			else if("S".equals(type[i])){
				submap.put(vertexId[i], lifetime[i]);
			}
			else if("M".equals(type[i])){
				multmap.put(vertexId[i], lifetime[i]);
			}
			else if("D".equals(type[i])){
				divmap.put(vertexId[i], lifetime[i]);
			}
			if(endtime < lifetime[i]){
				endtime = lifetime[i];
			}
		}

		int[] addlist = new int[addmap.size()]; int[] addtime = new int[addmap.size()];
		int[] sublist = new int[submap.size()]; int[] subtime = new int[submap.size()];
		int[] multlist = new int[multmap.size()]; int[]multtime = new int[multmap.size()];
		int[] divlist = new int[divmap.size()]; int[] divtime = new int[divmap.size()];

		//加算割当-修正
		if(add != 0){
			addops = new int[add][endtime];
			int k = 0;
			for(int key : addmap.keySet()){
				addlist[k] = key;
				addtime[k] = addmap.get(key);
				k = k + 1;
			}
			for(int j = 1; j <= endtime; j++){
				int m = 0;
				for(int n = 0; n < addlist.length; n++){
					if(addtime[n] == j){
						addops[m][j-1] = addlist[n];
						addlist[n] = -1; addtime[n] = -1;
						m = m + 1;
					}
					if(add < m + 1){
						//エラー処理
					}
				}
				for(int c = m; c < addops.length; c++){
					addops[c][j-1] = -1;
				}
			}
		}

		//減算割当-修正
		if(sub != 0){
			subops = new int[sub][endtime];
			int k = 0;
			for(int key : submap.keySet()){
				sublist[k] = key;
				subtime[k] = submap.get(key);
				k = k + 1;
			}
			for(int j = 1; j <= endtime; j++){
				int m = 0;
				for(int n = 0; n < sublist.length; n++){
					if(subtime[n] == j){
						subops[m][j-1] = sublist[n];
						sublist[n] = -1; subtime[n] = -1;
						m = m + 1;
					}
					if(sub < m + 1){
						//エラー処理
					}
				}
				for(int c = m; c < subops.length; c++){
					subops[c][j-1] = -1;
				}
			}
		}

		//乗算割当-修正
		if(mult != 0){
			multops = new int[mult][endtime];
			int k = 0;
			for(int key : multmap.keySet()){
				multlist[k] = key;
				multtime[k] = multmap.get(key);
				k = k + 1;
			}
			for(int j = 1; j <= endtime; j++){
				int m = 0;
				for(int n = 0; n < multlist.length; n++){
					if(multtime[n] == j){
						multops[m][j-1] = multlist[n];
						multlist[n] = -1; multtime[n] = -1;
						m = m + 1;
					}
					if(mult < m + 1){
						//エラー処理
					}
				}
				for(int c = m; c < multops.length; c++){
					multops[c][j-1] = -1;
				}
			}
		}

		//除算割当-修正
		if(div != 0){
			divops = new int[div][endtime];
			int k = 0;
			for(int key : divmap.keySet()){
				divlist[k] = key;
				divtime[k] = divmap.get(key);
				k = k + 1;
			}
			for(int j = 1; j <= endtime; j++){
				int m = 0;
				for(int n = 0; n < divlist.length; n++){
					if(divtime[n] == j){
						divops[m][j-1] = divlist[n];
						divlist[n] = -1; divtime[n] = -1;
						m = m + 1;
					}
					if(div < m + 1){
						//エラー処理
					}
				}
				for(int c = m; c < divops.length; c++){
					divops[c][j-1] = -1;
				}
			}
		}
	}
}
