import java.util.LinkedList;
import java.util.List;

public class ContiguousAllocation {
	private int totalSize = 1000;	// memory size
	private List<Block> blocks = new LinkedList<>();
	
	
	public ContiguousAllocation() {	
		Block hole = new Block(0, totalSize, "", true);
		this.blocks.add(hole);
	}
	
	// command SIZE 1000
	public void size(int totalSize) {
		this.totalSize = totalSize;
		this.blocks.clear();
		
		Block hole = new Block(0, totalSize, "", true);
		this.blocks.add(hole);
	}
	
	// command RQ P0 200 W
	public void request(String processName, int size, String strategy) {
		
	}
	
	// command RL P0
	public void release(String processName) {
		
	}
	
	//command C
	public void compact() {
		
	}
	
	//command STAT
	public List<Block> getBlocks() {
		return this.blocks;
	}
	
	
}

class Block {
	private int address;
	private int size;
	private String name;	// process name
	private boolean isHole;
	
	public Block(int address, int size, String name, boolean isHole) {
		super();
		this.address = address;
		this.size = size;
		this.name = name;
		this.isHole = isHole;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHole() {
		return isHole;
	}

	public void setHole(boolean isHole) {
		this.isHole = isHole;
	}

	@Override
	public String toString() {
		if (this.isHole == false)
			return "Block [" + this.address + ":" + this.size + "] Process " + this.name;
		return "Block [" + this.address + ":" + this.size + "] Unused";			
	}
	
	
	
}