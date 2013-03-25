package homeworks;


public class Homeworks {
	protected double maxGrade=5.0;
	protected String result="this is the result";
	protected boolean capsSensitive = false; // If capsSensitive is false, Eren and eren is the same.
	protected boolean whitespaceSensitive = false; // If whitespaceSensitive is false, Eren Sezener and Eren   Sezener is the same.
	
	// Constructors
	
	public Homeworks() {
	}
	
	public Homeworks(int maxGrade){
		this.maxGrade = maxGrade;
	}
	
	public Homeworks(int maxGrade, boolean capsSensitive){
		this.maxGrade = maxGrade;
		this.capsSensitive = capsSensitive;
	}
	
	public Homeworks(int maxGrade, boolean capsSensitive, boolean whitespaceSensitive){
		this.maxGrade = maxGrade;
		this.capsSensitive = capsSensitive;
		this.whitespaceSensitive = whitespaceSensitive;
	}
	
	// Getters and Setters
	public double getMaxGrade() {
		return maxGrade;
	}

	public void setMaxGrade(double max) {
		maxGrade = max;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isCapsSensitive() {
		return capsSensitive;
	}

	public void setCapsSensitive(boolean capsSensitive) {
		this.capsSensitive = capsSensitive;
	}

	public boolean isWhitespaceSensitive() {
		return whitespaceSensitive;
	}

	public void setWhitespaceSensitive(boolean whitespaceSensitive) {
		this.whitespaceSensitive = whitespaceSensitive;
	}

}
