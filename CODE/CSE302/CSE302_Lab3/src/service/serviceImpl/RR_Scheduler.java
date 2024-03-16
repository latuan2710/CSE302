package service.serviceImpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entity.ExecutionInfo;
import entity.Task;
import service.CPU;
import service.Interrupt;
import service.Scheduler;

public class RR_Scheduler implements Scheduler {
	private CPU cpu = new CPU();
	private LinkedList<Task> readyQuene = new LinkedList<>();
	private int quantum;

	public RR_Scheduler(int quantum) {
		this.quantum = quantum;
	}

	@Override
	public List<ExecutionInfo> schedule(List<Task> tasks) {
		for (Task t : tasks) {
			this.readyQuene.add(t);
		}

		List<ExecutionInfo> result = new ArrayList<>();

		while (!readyQuene.isEmpty()) {
			Task task = readyQuene.removeFirst();

			Interrupt interrupt = new Interrupt();
			interrupt.interruptAfter(this.cpu, this.quantum);
			ExecutionInfo info = this.cpu.excute(task);

			interrupt.stop();
			if (task.getBurst() > 0)
				readyQuene.addLast(task);

			result.add(info);
		}

		return result;
	}

}
