package service;
import java.util.List;

import entity.ExecutionInfo;
import entity.Task;

public interface Scheduler {

	public List<ExecutionInfo> schedule(List<Task> tasks);
}
