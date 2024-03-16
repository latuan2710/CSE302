package entity;

public class ExecutionInfo {
	private long time;
	private Task task;
	private int burst;
	private int duration;

	public ExecutionInfo(long time, Task task, int burst, int duration) {
		super();
		this.time = time;
		this.task = task;
		this.burst = burst;
		this.duration = duration;
	}

	public long getTime() {
		return time;
	}

	public Task getTask() {
		return task;
	}

	public int getBurst() {
		return burst;
	}

	public int getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		return time + " : " + task.getName() + " - " + task.getPriority() + " - " + burst + " - " + duration;
	}

}
