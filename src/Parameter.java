public class Parameter {

	private int kind;
	private float value;
	private float initValue;
	private int level;
    private int maxLevel;
	private float increment;

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Parameter(int kind, float initValue, int level,
			float increment,int maxLevel) {
		super();
		this.kind = kind;		
		this.initValue = initValue;
		this.level = level;
		this.increment = increment;
        this.maxLevel = maxLevel;
		this.value = initValue + (maxLevel-level)*increment;
        	}
	
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public float getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public float getInitValue() {
		return initValue;
	}
	public void setInitValue(int initValue) {
		this.initValue = initValue;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
        calculateCurrentValue();
	}
	public float getIncrement() {
		return increment;
	}
	public void setIncrement(float increment) {
		this.increment = increment;
	}
	
	public void calculateCurrentValue(){
		this.value = initValue + (maxLevel - level)*increment;

	}
	
	

	

}
