package nishimoto.yoshiken;

import java.util.ArrayList;

public class LifetimeAnalysis {
	private static int[] start;
	private static int[] end;
	private static int[] kab;

	public static int[] getStart(){
		return start;
	}

	public static int[] getEnd(){
		return end;
	}

	public static int[] getKab(){
		return kab;
	}

	public static void Basic(int[] edgeId, int[] ver1, int[] ver2, int[] vertexId, String[] type, int[] lifetime){
		start = new int[edgeId.length]; end = new int[edgeId.length];
		ArrayList<Integer> kaburi = new ArrayList<Integer>();
		int x = 0;
		for(int i = 0; i < edgeId.length; i++){
			for(int k = i+1; k < edgeId.length; k++){
				if(ver1[i] == ver1[k]){
					if(lifetime[ver2[i]] >= lifetime[ver2[k]]){
						kaburi.add(edgeId[k]);
					}
				}
			}
			if(lifetime[ver1[i]] == -1){
				if(type[ver1[i]].equals("I")){
					lifetime[ver1[i]]= 0;
				}
			}
			if(lifetime[ver2[i]] == -1){
				if(type[ver2[i]].equals("O")){
					if(x == 0){
						for(int j = 0; j < lifetime.length; j++){
							if(x < lifetime[j]){
								x = lifetime[j];
							}
						}
						x = x + 1;
					}
					lifetime[ver2[i]] = x;
				}
			}
			end[i] = lifetime[ver2[i]];
			start[i] = lifetime[ver1[i]] + 1;
			System.out.println("start["+i+"]="+ start[i] + " end["+i+"]="+end[i]);
		}
		int ka = 0;
		kab = new int[kaburi.size()];
		for(int kav : kaburi){
			kab[ka] = kav;
			ka = ka + 1;
		}
	}
}
