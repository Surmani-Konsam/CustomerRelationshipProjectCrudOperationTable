package CustomerRelationshipProject;

public class PracticeClass {

	
	static int result = 1;
	public static int powerOfNumber(int power,int number) {
		final int fixedNumber = number;
		if(power==0) {
		return result;
		}else {
			result = result * number;
			System.out.println("number is : "+number*fixedNumber);
			return powerOfNumber(power-1,number);
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(powerOfNumber(3, 3));
		

	}

}
