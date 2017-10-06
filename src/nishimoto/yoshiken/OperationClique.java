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

		//加算割当
		if(add != 0){
			addops = new int[add][endtime];
			for(int j = 1; j <= endtime; j++){
				int m = 0;
				for(int k : addmap.keySet()){
					if(j == addmap.get(k)){
						addops[m][j-1] = k;
						System.out.println("addops[" + m + "][" + (j-1) + "]" +addops[m][j-1]);
						addmap.remove(k);
						m = m + 1;
						if(add < m + 1){
							//エラー処理
						}
					}
				}
				for(int n = m; n < addops.length; n++){
					addops[n][j-1] = -1;
				}
			}
		}

		//減算割当
		if(sub != 0){
			subops = new int[sub][endtime];
			for(int j = 1; j <= endtime; j++){
				int m = 0;
				for(int k : submap.keySet()){
					if(j == submap.get(k)){
						subops[m][j-1] = k;
						submap.remove(k);
						m = m + 1;
						if(sub < m + 1){
							//エラー処理
						}
					}
				}
				for(int n = m; n < subops.length; n++){
					subops[n][j-1] = -1;
				}
			}
		}

		//乗算割当
		if(mult != 0){
			multops = new int[mult][endtime];
			for(int j = 1; j <= endtime; j++){
				int m = 0;
				for(int k : multmap.keySet()){
					if(j == multmap.get(k)){
						multops[m][j-1] = k;
						multmap.remove(k);
						m = m + 1;
						if(mult < m + 1){
							//エラー処理
						}
					}
				}
				for(int n = m; n < multops.length; n++){
					multops[n][j-1] = -1;
				}
			}
		}

		//除算割当
		if(comp != 0){
			compops = new int[comp][endtime];
			for(int j = 1; j <= endtime; j++){
				int m = 0;
				for(int k : compmap.keySet()){
					if(j == compmap.get(k)){
						compops[m][j-1] = k;
						compmap.remove(k);
						m = m + 1;
						if(comp < m + 1){
							//エラー処理
						}
					}
				}
				for(int n = m; n < compops.length; n++){
					compops[n][j-1] = -1;
				}
			}
		}
	}
}
