
public class Item {
	private int placement;
	private Parameter par1;
	private Parameter par2;
	private Parameter par3;
	private String name;
	private float[] pars = {0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private boolean equipped;

    public boolean isEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public Item(int placement, Parameter par1, Parameter par2, Parameter par3,
			String name) {
		super();
		this.placement = placement;

		this.par1 = par1;
		this.par2 = par2;
		this.par3 = par3;
		this.name = name;
        this.equipped = false;
		update();
	}

    public Item(int placement, Parameter par1, Parameter par2, Parameter par3,
                String name, boolean equipped) {
        super();
        this.placement = placement;

        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
        this.name = name;
        this.equipped = equipped;
        update();
    }

    public Item(int placement, int level, Parameter par1, Parameter par2, Parameter par3,
                String name, boolean equipped) {
        super();
        this.placement = placement;
        par1.setLevel(level);
        par2.setLevel(level);
        par3.setLevel(level);

        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
        this.name = name;
        this.equipped = equipped;
        update();
    }
	
	private void update(){
		int i = this.par1.getKind();
		pars[i] = pars[i] + this.par1.getValue();
		i = this.par2.getKind();
		pars[i] = pars[i] + this.par2.getValue();
		i = this.par3.getKind();
		pars[i] = pars[i] + this.par3.getValue();
	}
	
	public int getPlacement() {
		return placement;
	}
	public void setPlacement(int placement) {
		this.placement = placement;
	}
	public Parameter getPar1() {		
		return par1;
	}
	public void setPar1(Parameter par1) {
		this.par1 = par1;
		update();
	}
	public Parameter getPar2() {
		return par2;
	}
	public void setPar2(Parameter par2) {
		this.par2 = par2;
		update();
	}
	public Parameter getPar3() {		
		return par3;
	}
	public float[] getPars() {
		return pars;
	}

	public void setPars(float[] pars) {
		this.pars = pars;
	}

	public void setPar3(Parameter par3) {
		this.par3 = par3;
		update();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
