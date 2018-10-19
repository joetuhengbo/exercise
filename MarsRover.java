package resmed;

import java.util.Scanner;

public class MarsRover {
	
	
	static char getDirection(char cur, char rotate) {
		char[] orientation = {'W','N','E','S'};
		int i=0;
		while (i< orientation.length) {
			if (orientation[i] == cur) {
				if (rotate == 'L') {
					return orientation[Math.floorMod((i-1), 4)];
				} else {
					return orientation[Math.floorMod((i+1), 4)];
				}
			}
			i++;
		}
		return orientation[i];
	}
	
	private static class RoverPosition {
		
		int x;
		int y;
		char heading;
		
		public RoverPosition(int x, int y, char point) {
			super();
			this.x = x;
			this.y = y;
			this.heading = point;
		}
		
		@Override
		public String toString() {
			
			return x + " " + y + " " + heading;
		}
	}
	
	static RoverPosition moveRover(RoverPosition rp, String instruct, int[] plateauCoordinates) {
		
		for (int i=0; i< instruct.length(); i++) {
			
			if (instruct.charAt(i) == 'M') {
				switch (rp.heading) {
					case 'N':
						if (rp.y < plateauCoordinates[1]) rp.y +=1; break;
					case 'E': 
						if (rp.x < plateauCoordinates[0]) rp.x +=1; break;
					case 'S':
						if (rp.y > 0) rp.y -=1; break;
					case 'W': 
						if (rp.x > 0) rp.x -=1; break;
				}		
			} else if (instruct.charAt(i) == 'L' || instruct.charAt(i) == 'R') {	
				rp.heading = getDirection(rp.heading, instruct.charAt(i));
			}
		}
		return rp;
	}	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		String[] p = scanner.nextLine().split(" ");

        int[] plateauCoordinates = {Integer.parseInt(p[0]),Integer.parseInt(p[1])};
        
        while (scanner.hasNextLine()) {
        	
        	p = scanner.nextLine().split(" ");
        	RoverPosition rp = new RoverPosition(Integer.parseInt(p[0]),Integer.parseInt(p[1]), p[2].charAt(0));
        	String instruct = scanner.nextLine();
        	System.out.println(moveRover(rp, instruct, plateauCoordinates));
        	
        }
        scanner.close();
	}

}
