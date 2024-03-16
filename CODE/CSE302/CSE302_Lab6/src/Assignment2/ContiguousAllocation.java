package Assignment2;

import java.util.LinkedList;
import java.util.List;

public class ContiguousAllocation {
	private int totalSize = 1000;
	private LinkedList<Block> blocks = new LinkedList<>();

	public ContiguousAllocation() {
	}

	public void setSize(int size) {
		this.totalSize = size;
		this.blocks.clear();
		this.blocks.add(new Block(null, 0, size, true));
	}

	public void request(String processName, int size, String strategy) {
		for (Block block : blocks) {
			if (block.getName().equals(processName)) {
				return;
			}
		}

		if (strategy.equals("F")) {
			requestFF(processName, size);
		} else if (strategy.equals("B")) {
			requestBF(processName, size);
		} else if (strategy.equals("W")) {
			requestWF(processName, size);
		}
	}

	private void requestWF(String processName, int size) {
		Block blockLargest = new Block(null, -1, 0, true);
		for (Block block : blocks) {
			if (block.getSize() > blockLargest.getSize() && block.isHole() && block.getSize() >= size) {
				blockLargest = block;
			}
		}

		if (size == blockLargest.getSize()) {
			blockLargest.setName(processName);
			blockLargest.setHole(false);
			return;
		}

		int index = this.blocks.indexOf(blockLargest);
		this.blocks.add(index, new Block(processName, blockLargest.getAddress(), size, false));
		blockLargest.setAddress(blockLargest.getAddress() + size);
		blockLargest.setSize(blockLargest.getSize() - size);
	}

	private void requestBF(String processName, int size) {
		Block blockSmallest = new Block(null, -1, Integer.MAX_VALUE, true);
		for (Block block : blocks) {
			if (block.getSize() < blockSmallest.getSize() && block.isHole() && block.getSize() >= size) {
				blockSmallest = block;
			}
		}

		if (size == blockSmallest.getSize()) {
			blockSmallest.setName(processName);
			blockSmallest.setHole(false);
			return;
		}

		int index = this.blocks.indexOf(blockSmallest);
		this.blocks.add(index, new Block(processName, blockSmallest.getAddress(), size, false));
		blockSmallest.setAddress(blockSmallest.getAddress() + size);
		blockSmallest.setSize(blockSmallest.getSize() - size);
	}

	private void requestFF(String processName, int size) {
		for (Block block : blocks) {
			if (block.isHole() && block.getSize() >= size) {
				if (size == block.getSize()) {
					block.setName(processName);
					block.setHole(false);
					return;
				}

				int index = this.blocks.indexOf(block);
				this.blocks.add(index, new Block(processName, block.getAddress(), size, false));
				block.setAddress(block.getAddress() + size);
				block.setSize(block.getSize() - size);
				return;
			}
		}
	}

	public void release(String processName) {
		Block blockBefore = null;
		Block blockAfter = null;
		Block blockRelease = null;
		int index = -1;
		for (Block block : blocks) {
			if (!block.isHole() && block.getName().equals(processName)) {
				block.setHole(true);
				block.setName(null);
				index = blocks.indexOf(block);
				blockRelease = block;
				break;
			}
		}

		if (blockRelease != null) {
			blockBefore = (index - 1 >= 0) ? this.blocks.get(index - 1) : null;
			blockAfter = (index + 1 >= blocks.size() - 1) ? this.blocks.get(index + 1) : null;

			if (blockAfter != null && blockAfter.isHole()) {
				this.blocks.remove(blockAfter);
				blockRelease.setSize(blockRelease.getSize() + blockAfter.getSize());
			}

			if (blockBefore != null && blockBefore.isHole()) {
				this.blocks.remove(blockRelease);
				blockBefore.setSize(blockBefore.getSize() + blockRelease.getSize());
			}
		}
	}

	public void compact() {
		int totalHoleSize = 0;
		LinkedList<Block> blocksCopy = (LinkedList<Block>) this.blocks.clone();
		for (Block block : blocksCopy) {
			if (block.isHole()) {
				totalHoleSize += block.getSize();
				this.blocks.remove(block);
			}
		}

		blocks.addLast(new Block(null, 0, totalHoleSize, true));
		updateBlocks();
	}

	private void updateBlocks() {
		Block blockBefore = null;
		for (Block block : blocks) {
			if (blockBefore == null) {
				block.setAddress(0);
				blockBefore = block;
			} else {
				block.setAddress(blockBefore.getAddress() + blockBefore.getSize());
			}
		}
	}

	public List<Block> getBlocks() {
		return this.blocks;
	}
}
