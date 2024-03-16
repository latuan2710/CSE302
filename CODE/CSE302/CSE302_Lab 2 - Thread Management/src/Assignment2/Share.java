package Assignment2;

public class Share {
	double average = 0;
	int min = Integer.MAX_VALUE;
	int max = Integer.MIN_VALUE;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("The average value is " + average + "\n");
		buffer.append("The minimum value is " + min + "\n");
		buffer.append("The maximum value is " + max + "\n");
		return buffer.toString();
	}
}
