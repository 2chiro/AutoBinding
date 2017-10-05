package nishimoto.yoshiken;

import java.util.HashMap;
import java.util.Map;

public class OperationClique {
	private int[][] addops;
	private int[][] subops;
	private int[][] multops;
	private int[][] compops;

	public int[][] getAddops(){
		return addops;
	}
	public int[][] getSubops(){
		return subops;
	}
	public int[][] getMultops(){
		return multops;
	}
	public int[][] getCompops(){
		return compops;
	}

	public void Basic(int add, int sub, int mult, int comp, int[] vertexId, String[] type, int[] lifetime){
		Map<Integer, Integer> addmap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> submap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> multmap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> compmap = new HashMap<Integer, Integer>();
		int endtime = 0;
		for(int i = 0; i < vertexId.length; i++){
			if(type[i] == "A"){
				addmap.put(vertexId[i], lifetime[i]);
			}
			else if(type[i] == "S"){
				submap.put(vertexId[i], lifetime[i]);
			}
			else if(type[i] == "M"){
				multmap.put(vertexId[i], lifetime[i]);
			}
			else if(type[i] == "C"){
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
						addmap.remove(k);
						m = m + 1;
						if(add < m + 1){
							//エラー処理
						}
					}
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
					}
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
					}
				}
			}
		}

		//除算割当
		if(mult != 0){
			compops = new int[comp][endtime];
			for(int j = 1; j <= endtime; j++){
				int m = 0;
				for(int k : compmap.keySet()){
					if(j == compmap.get(k)){
						compops[m][j-1] = k;
						compmap.remove(k);
						m = m + 1;
					}
				}
			}
		}
	}
}
