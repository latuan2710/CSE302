package entity;

public class Task {
	private String name;
	private int priority;
	private int burst;

	public Task(String name, int priority, int burst) {
		super();
		this.name = name.trim();
		this.priority = priority;
		this.burst = burst;
	}

	public int getBurst() {
		return burst;
	}

	public void setBurst(int burst) {
		this.burst = burst;
	}

	public String getName() {
		return name;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public String toString() {
		return "Task [name=" + name + ", priority=" + priority + ", burst=" + burst + "]";
	}

}
