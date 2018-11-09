import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ZebraPuzzle extends CSP {
	static Set<Object> varColour = new HashSet<Object>(
		Arrays.asList(new String[]{"blue","green","ivory","red","yellow"}));
	static Set<Object> varDrinks = new HashSet<Object>(
		Arrays.asList(new String[]{"coffee","milk","orange-juice","tea","water"}));
	static Set<Object> varNationality = new HashSet<Object>(
		Arrays.asList(new String[]{"englishman","japanense","norwegian","spaniard","ukrainian"}));
	static Set<Object> varPet = new HashSet<Object>(
		Arrays.asList(new String[]{"dog","fox","horse","snails","zebra"}));
	static Set<Object> varCigarrette = new HashSet<Object>(
		Arrays.asList(new String[]{"chesterfield","kools","lucky-strike","old-gold","parliament"}));
	
	public boolean isGood(Object X, Object Y, Object x, Object y){
		//if X is not even mentioned in by the constraints, just return true
		//as nothing can be violated
		if(!C.containsKey(X))
			return true;
		
		//check to see if there is an arc between X and Y
		//if there isnt an arc, then no constraint, ie. it is good
		if(!C.get(X).contains(Y))
			return true;
		
		//Constraints
		//englishman lives in the red house
		if(X.equals("englishman")&&Y.equals("red")&&!x.equals(y))
			return false;
		
		//spaniard owns a dog
		if(X.equals("spaniard")&&Y.equals("dog")&&!x.equals(y))
			return false;
		
		//coffee is drunk in the green house
		if(X.equals("coffee")&&Y.equals("green")&&!x.equals(y))
			return false;
		
		//ukrainian drinks tea
		if(X.equals("ukrainian")&&Y.equals("tea")&&!x.equals(y))
			return false;
		
		//The green house is directly right of the ivory house
		if((X.equals("green")&& Y.equals("ivory") && !((Integer)x-(Integer)y==1)) ||
			(X.equals("ivory")&&Y.equals("green")&& !((Integer)x-(Integer)y==-1)))
				return false;
	
		//the old gold smoker owns snails
		if(X.equals("old-gold")&&Y.equals("snails")&&!x.equals(y))
			return false;
		
		//kools are being smoked in the yellow house
		if(X.equals("kools")&&Y.equals("yellow")&&!x.equals(y))
			return false;
		
		//Milk is drunk in the middle house
		/*if(X.equals("milk")&& !x.equals(new Integer(3)) || (Y.equals("milk")&& !y.equals(new Integer(3))))
			return false;
		
		//The Norweigian lives in the first house  on the left 
		if(X.equals("Norweigian")&& !x.equals(new Integer(1)) || (Y.equals("milk")&& !y.equals(new Integer(1))))
			return false;*/
		
		//The chesterfield smoker lives next to the fox owner
		if((X.equals("chesterfield")&& Y.equals("fox") && !((Integer)x-(Integer)y==1)) ||
			(X.equals("fox")&&Y.equals("chesterfield")&& !((Integer)x-(Integer)y==-1)))
				return false;
		
		//Kools are smoked in the house next to the house where the horse is kept
		if((X.equals("kools")&& Y.equals("horse") && !((Integer)x-(Integer)y==1)) ||
			(X.equals("horse")&&Y.equals("kools")&& !((Integer)x-(Integer)y==-1)))
				return false;
		
		//the lucky strike smoker drinks orange juice
		if(X.equals("lucky-strike")&&Y.equals("orange-juice")&&!x.equals(y))
			return false;
		
		//the japanese smokes parliament
		if(X.equals("japanense")&&Y.equals("parliament")&&!x.equals(y))
			return false;
		
		//The Norweigian lives next to the blue house
		if((X.equals("norweigian")&& Y.equals("blue") && !((Integer)x-(Integer)y==2)) ||
			(X.equals("blue")&&Y.equals("norwegian")&& !((Integer)x-(Integer)y==-2)))
				return false;
		
		//uniqueness constraints
		if(varColour.contains(X) && varColour.contains(Y) && !X.equals(Y)&&x.equals(y))
			return false;
		if(varDrinks.contains(X) && varDrinks.contains(Y) && !X.equals(Y)&&x.equals(y))
			return false;
		if(varNationality.contains(X) && varNationality.contains(Y) && !X.equals(Y)&&x.equals(y))
			return false;
		if(varPet.contains(X) && varPet.contains(Y) && !X.equals(Y)&&x.equals(y))
			return false;
		if(varCigarrette.contains(X) && varCigarrette.contains(Y) && !X.equals(Y)&&x.equals(y))
			return false;
		return true;
	}
	public static void main(String[]args)throws Exception{
		ZebraPuzzle csp = new ZebraPuzzle();
		Integer[] domain = {1,2,3,4,5};
		
		for(Object X: varColour)
			csp.addDomain(X,domain);
		
		for(Object X: varDrinks)
			csp.addDomain(X,domain);
		
		for(Object X: varNationality)
			csp.addDomain(X,domain);
		
		for(Object X: varPet)
			csp.addDomain(X,domain);
		
		for(Object X: varCigarrette)
			csp.addDomain(X,domain);
		
		//unary constraints: just remove values from domains
		//*TODO*
		//csp.addDomain("milk", new Integer[]{3});
		//csp.addDomain("norwegian",new Integer[]{1});
		
		//binary constraints: add constraint arcs
		//englishman lives in the red house
		csp.addBidirectionalArc("englishman","red");
		
		//spaniard owns a dog
		csp.addBidirectionalArc("spaniard","dog");
		
		//coffee is drunk in the green house
		csp.addBidirectionalArc("coffee","green");
		
		//ukrainian drinks tea
		csp.addBidirectionalArc("ukrainian","tea");
		
		//old gold smoker owns snails
		csp.addBidirectionalArc("old-gold","snails");
		
		//kools are being smoked in the yellow house
		csp.addBidirectionalArc("kools","yellow");
		
		//lucky-strike smoker drinks orange juice
		csp.addBidirectionalArc("lucky-strike","orange-juice");
		
		//japanese smokes parliament
		csp.addBidirectionalArc("japanense","parliament");
		
		//uniqueness constraints
		for(Object X: varColour)
			for(Object Y: varColour)
				csp.addBidirectionalArc(X,Y);
		
		for(Object X: varDrinks)
			for(Object Y: varDrinks)
				csp.addBidirectionalArc(X,Y);
		
		for(Object X: varNationality)
			for(Object Y: varNationality)
				csp.addBidirectionalArc(X,Y);
		
		for(Object X: varPet)
			for(Object Y: varPet)
				csp.addBidirectionalArc(X,Y);
		
		for(Object X: varCigarrette)
			for(Object Y: varCigarrette)
				csp.addBidirectionalArc(X,Y);
			
		//now lets search for a solution
		Search search = new Search(csp);
		System.out.println("Searched");
		System.out.println(search.BacktrackingSearch());
	}
		
}