package nishimoto.yoshiken;

import java.util.ArrayList;

public class RegisterLEA {
	private static int[][] regs;

	public static int[][] getRegs(){
		return regs;
	}

	public static void Basic(int[] edgeId, int[] start, int[] end){
		int t = 0;
		regs = null;
		regs = new int[edgeId.length][edgeId.length];
		boolean check = false;
		while(!check){
			int h = 0;
			int watermark = 0;

			while(true){
				ArrayList<Integer> xs = new ArrayList<Integer>();
				for(int i = 0; i < edgeId.length; i++){
					if(start[i] > watermark){
						xs.add(i);
					}
				}
				if(xs.isEmpty()){
					break;
				}
				int num = start[xs.get(0)];
				int y = xs.get(0);

				for(int i = 0; i < xs.size(); i++){
					if(num > start[xs.get(i)]){
						num = start[xs.get(i)];
						y = xs.get(i);
					}
				}

				watermark = end[y];
				regs[t][h] = y;
				System.out.println("regs["+t+"]"+"["+h+"]="+regs[t][h]);

				h = h + 1;

				edgeId[y] = -1;
				start[y] = -1;
				end[y] = -1;

				xs.clear();
			}

			t = t + 1;

			for(int i = 0; i < edgeId.length; i++){
				if(edgeId[i] != -1){
					check = false;
					break;
				}
				check = true;
			}
		}
	}
}
