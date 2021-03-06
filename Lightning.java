package lightning;

import java.util.ArrayList;
import java.util.Collections;
import acm.graphics.GLine;
import acm.program.GraphicsProgram;
import java.util.concurrent.ThreadLocalRandom;

public class Lightning extends GraphicsProgram {
	
	ArrayList<Unit> passed = new ArrayList<Unit>();
	static boolean TEST = true;	//todo: encapsulate as function and get
	static boolean MAPTEST = false;	//rid of static variables
	static boolean JUMPTEST = false;
	static boolean ITERATIONTEST = false;
	static int RESISTANCE = 1000;
	static int WIDTH = 500;
	static int HEIGHT = 500;
	int x1,y1;
	
	public void run() {
		this.resize(WIDTH, HEIGHT);
		x1 = readInt("x coordinate: ");
		y1 = readInt("y coordinate: ");
		int x = x1;
		int y = y1;
		
		while(true) {
		int branch = 1;
		Unit[][] map = new Unit[HEIGHT][WIDTH];
		for (int j=0;j<map.length;j++) {
			for(int i=0;i<map[j].length;i++) {
				map[j][i] = new Unit(i,j);
				if(TEST) {System.out.print(map[j][i].num+" ");}
			}
		if(TEST) {System.out.print("\n");}
		}
		if (TEST){System.out.print(map[y][x].num+"\n");}
		int iterations = 0;
		System.out.print("x: "+x+"\n"+"y: "+y+"\n");
		while (true) {
			Unit start = map[y][x];
			start.passed = true;
            ArrayList<Unit> options = new ArrayList<Unit>();
            int s = 0;
            if(x==0&y==0) s = 1;
            if(x==WIDTH-1&y==0) s = 2;
            if(x==WIDTH-1&y==HEIGHT-1) s = 3;
            if(x==0&y==HEIGHT-1) s = 4;
            if(x==0) s = 5;
            if(y==0) s = 6;
            if(x==WIDTH-1) s = 7;
            if(y==HEIGHT-1) s = 8;
            
            switch (s) { //edge check
            case 1: {
            		options.add(map[y+1][x+1]);  
            		options.add(map[y][x+1]);
            		options.add(map[y+1][x]);
            		break;
            	}
            case 2: {
                	options.add(map[y][x-1]);
                	options.add(map[y+1][x-1]);
                	options.add(map[y+1][x]);
                	break;
            	}
            case 3: {
            		options.add(map[y-1][x]);
            		options.add(map[y-1][x-1]);
            		options.add(map[y][x-1]);
            		break;
            	}
            case 4: {
            		options.add(map[y-1][x]);
            		options.add(map[y-1][x+1]);
            		options.add(map[y][x+1]);
            		break;
            	}
            case 5: {
            		options.add(map[y-1][x]);
            		options.add(map[y-1][x+1]);
            		options.add(map[y][x+1]);
            		options.add(map[y+1][x+1]);
            		options.add(map[y+1][x]);
            		break;
            	}
            case 6: {
            		options.add(map[y][x-1]);
            		options.add(map[y+1][x-1]);
            		options.add(map[y+1][x]);
            		options.add(map[y+1][x+1]);
            		options.add(map[y][x+1]);
            		break;
            	}
            	
            case 7: {
            		options.add(map[y-1][x]);
            		options.add(map[y+1][x]);
            		options.add(map[y-1][x-1]);
            		options.add(map[y][x-1]);
            		options.add(map[y+1][x-1]);
            		break;
            	}
            case 8: {
            		options.add(map[y][x-1]);
            		options.add(map[y][x+1]);
            		options.add(map[y-1][x-1]);
            		options.add(map[y-1][x]);
            		options.add(map[y-1][x+1]);
            		break;
            	}
            default: options.add(map[y][x-1]);
        		options.add(map[y][x+1]);
        		options.add(map[y-1][x-1]);
        		options.add(map[y-1][x]);
        		options.add(map[y-1][x+1]);
        		options.add(map[y+1][x-1]);
        		options.add(map[y+1][x]);
        		options.add(map[y+1][x+1]);
        		break;
            }
            	if(TEST){for(int n=0;n<options.size();n++) {
            		System.out.print("Weights of all options: "+options.get(n).num+" ");}System.out.print("\n");if(MAPTEST){break;}} //map testing
            	
            	ArrayList<Integer> optionsnum = new ArrayList<Integer>();
        
            	int a = options.size(); 
            	for(int n=0;n<a;n++) { 
            		if (options.get(n).passed) {
            			passed.add(options.get(n));
            			options.remove(options.get(n));
            			a -= 1;
            		}else {
            	optionsnum.add(options.get(n).num);
            	}}
            	Collections.sort(optionsnum, Collections.reverseOrder());
            	if (TEST) {System.out.print("Sorted list of numbers of options: "+optionsnum+"\n");}
            	if (TEST) {System.out.print("Options: "+options+"\n");}
            	for(int n=0;n<a;n++) {  
            		if (optionsnum.size()==0) {
            			System.out.print("Ended with x:"+x+"y:"+y+"\n");
            			x = (int) passed.get(passed.size()-branch).getX();
            			y = (int) passed.get(passed.size()-branch).getY();
            			branch += 1;
            			System.out.print(branch);
                		break;
                	}
            		if (optionsnum.get(0)==options.get(n).num) {
            			Unit jumpto = options.get(n);
            			GLine line = new GLine(jumpto.getX(),jumpto.getY(),start.getX(),start.getY());
                    	add(line); 
                    	x = (int) jumpto.getX();
                    	if (TEST) {System.out.print("x coordinate of point to jump: "+x+"\n");}
                    	y = (int) jumpto.getY();
                    	if (TEST) {System.out.print("y coordinate of point to jump: "+y+"\n");} 
            			if (TEST) {System.out.print("Number of point to jump: "+jumpto.num+"\n");
            			break;
            		}
            	}
        
            }
				iterations +=1;
            	if(ITERATIONTEST) {break;} 
            	if(iterations == RESISTANCE) {
            		pause(ThreadLocalRandom.current().nextInt(500,750));
            		removeAll();
            		pause(ThreadLocalRandom.current().nextInt(5000,10000));
            		x = x1;
            		y = y1;
            		break;
            	}
            		
            	}
			}
		}
	}
		

