package a2;
import java.io.*;  
import java.util.*;  
public class q1 {

	
	
	public static void main(String[] args) throws FileNotFoundException{
			File dataset = new File("datasetQ1.csv");
			int result = fnKNN(dataset);
			System.out.println(result);
		}
		
	
	
	public static int fnKNN(File dataset) throws FileNotFoundException {
		
		//array list to store set of data
		List<point> set = new ArrayList<point>();
		//use a scanner to seperate each line seperately
		Scanner sc = new Scanner(dataset);
		sc.useDelimiter("\n");
	
		sc.nextLine();
		//for loop to populate arraylist with objects that represent points, splitting the input string by commas
		while(sc.hasNext()) {
			String input = sc.next();
			String data []= input.strip().split(",");
			double x = Double.valueOf(data[0]);
			double y = Double.valueOf(data[1]);
			int target = Integer.parseInt(data[2]);
			point placeHolder = new point();
			placeHolder.x = x;
			placeHolder.y = y;
			placeHolder.target = target;
			//System.out.println(x+" "+y+" "+" "+target);
			
			set.add(placeHolder);
			
	}
		
		//Initalization for the test point to be classified
		point test = new point();
		test.x = 1.2;
		test.y=.6;
		test.target = -1;//use negative 1 as placeholder value while point has not been classified
		//list used to store the distances from the test point and indexes of each respective point
		
		//list used to store just distances, will be subject to manipulation. Hence the requirement of the index field in the list neighbours.
		List<Double> distances = new ArrayList<>();
		//loop to initalize a list of distances as well as add distance property to each individual object
		for (int i =0;i<20;i++) {
			
			double distance = EDistance(test,set.get(i));
			set.get(i).distance = distance;
			distances.add(distance);
		}
		//sort distances from lowest to greatest
		Collections.sort(distances);
		//System.out.println(set.get(1).distance);
		
		//loop to determine when the first element of the sorted distance list(the smallest distance) is equivalent to the distance that pertains to an object.
		//The target value (0,1) of this object will be the returned result.
		//integer for target k values.
		
		//TODO: Manage multiple K Values, Create list to hold the target values of K results, mode of target values is final answer.
		int k = 1;
		double result = 0;
		for(int j = 0; j<k;j++) {
			for (int i =0;i<20;i++) {
			
				if(distances.get(0)==set.get(i).distance) {
					result += set.get(i).target;
				}
			}
		}
		
		System.out.println(result/2);
		if(result/2>=.5) {
			return 1;
		}
		else {
			return 0;
		}
		
}
	//Helper function used to calculate Euclidian distance between two points
	public static double EDistance(point test, point item) {
		double S = 0;
		double xdistance = Math.pow(test.x - item.x,2);
		//System.out.println(test.x+ " "+item.x);
		double ydistance = Math.pow(test.y - item.y,2);
		
		S+=xdistance +ydistance;
		
		
		
		
		return Math.sqrt(S);
		
		

	}

	}

	
