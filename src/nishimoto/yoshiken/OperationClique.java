package nishimoto.yoshiken;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OperationClique {
	private static int[][] addops;
	private static int[][] subops;
	private static int[][] multops;
	private static int[][] compops;

	public static int[][] getAddops(){
		return addops;
	}
	public static int[][] getSubops(){
		return subops;
	}
	public static int[][] getMultops(){
		return multops;
	}
	public static int[][] getCompops(){
		return compops;
	}

	public static void Basic(int add, int sub, int mult, int comp, int[] vertexId, String[] type, int[] lifetime){
		Map<Integer, Integer> addmap = Collections.synchronizedMap(new HashMap<Integer, Integer>());
		Map<Integer, Integer> submap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> multmap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> compmap = new HashMap<Integer, Integer>();
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
			else if("C".equals(type[i])){
				compmap.put(vertexId[i], lifetime[i]);
			}
			if(endtime < lifetime[i]){
				endtime = lifetime[i];
			}
		}

		int[] addlist = new int[addmap.size()]; int[] addtime = new int[addmap.size()];
		int[] sublist = new int[submap.size()]; int[] subtime = new int[submap.size()];
		int[] multlist = new int[multmap.size()]; int[]multtime = new int[multmap.size()];
		int[] complist = new int[compmap.size()]; int[] comptime = new int[compmap.size()];

		//加算割当-修正
		if(!addmap.isEmpty()){
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
		if(!submap.isEmpty()){
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
		if(!multmap.isEmpty()){
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
		if(!compmap.isEmpty()){
			compops = new int[comp][endtime];
			int k = 0;
			for(int key : compmap.keySet()){
				complist[k] = key;
				comptime[k] = compmap.get(key);
				k = k + 1;
			}
			for(int j = 1; j <= endtime; j++){
				int m = 0;
				for(int n = 0; n < complist.length; n++){
					if(comptime[n] == j){
						compops[m][j-1] = complist[n];
						complist[n] = -1; comptime[n] = -1;
						m = m + 1;
					}
					if(comp < m + 1){
						//エラー処理
					}
				}
				for(int c = m; c < compops.length; c++){
					compops[c][j-1] = -1;
				}
			}
		}
	}
}
