package app;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.ExecutionInfo;
import entity.Task;
import service.Scheduler;
import service.serviceImpl.FCFS_Scheduler;
import service.serviceImpl.PRIRR_Scheduler;
import service.serviceImpl.PRI_Scheduler;
import service.serviceImpl.RR_Scheduler;
import service.serviceImpl.SJF_Scheduler;

public class Main {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("Usage: java main <althogrithm> <schedule.txt>");
			System.exit(0);
		}

		String choice = args[0].toUpperCase();
		BufferedReader inputFile = new BufferedReader(new FileReader(args[1]));

		List<Task> tasks = new ArrayList<>();
		String line;

		line = inputFile.readLine();
		while (line != null) {
			String[] params = line.split(",\\s*");
			Task task = new Task(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2]));
			tasks.add(task);
			line = inputFile.readLine();

		}

		inputFile.close();

		Scheduler scheduler = null;
		switch (choice) {
		case "FCFS":
			scheduler = new FCFS_Scheduler();
			break;

		case "SJF":
			scheduler = new SJF_Scheduler();
			break;

		case "PRI":
			scheduler = new PRI_Scheduler();
			break;

		case "RR":
			scheduler = new RR_Scheduler(10);
			break;

		case "PRIRR":
			scheduler = new PRIRR_Scheduler(10);
			break;

		default:
			System.err.println("Invalid althogrithm");
			break;
		}

		List<ExecutionInfo> results = scheduler.schedule(tasks);
		for (ExecutionInfo info : results) {
			System.out.println(info);
		}
	}

}
