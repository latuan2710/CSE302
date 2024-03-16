package service.serviceImpl;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entity.ExecutionInfo;
import entity.Task;
import service.CPU;
import service.Scheduler;

public class FCFS_Scheduler implements Scheduler {
	private CPU cpu = new CPU();
	private LinkedList<Task> readyQuene = new LinkedList<>();

	@Override
	public List<ExecutionInfo> schedule(List<Task> tasks) {
		for (Task t : tasks) {
			this.readyQuene.add(t);
		}

		List<ExecutionInfo> result = new ArrayList<>();

		while (!readyQuene.isEmpty()) {
			Task task = readyQuene.remove();
			ExecutionInfo info = this.cpu.excute(task);
			result.add(info);
		}

		return result;
	}

}
