package Assignment2;

public class Block {
	private String name;
	private int address;
	private int size;
	private boolean isHole;

	public Block(String name, int address, int size, boolean isHole) {
		this.name = name;
		this.address = address;
		this.size = size;
		this.isHole = isHole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isHole() {
		return isHole;
	}

	public void setHole(boolean isHole) {
		this.isHole = isHole;
	}

	@Override
	public String toString() {
		if (isHole)
			return "Addresses [" + address + ":" + size + "] Unused";
		return "Addresses [" + address + ":" + size + "] Process " + name;
	}

}
