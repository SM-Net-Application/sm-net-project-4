package com.smnet.core.task;

import com.smnet.core.dialog.AlertBuilder;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.HashMap;

public class TaskManager {

	public static void run(AlertBuilder alertBuilder, Stage stage, String waitMessage, TaskInterface operation) {

		Alert wait = alertBuilder.wait(stage, waitMessage);

		Task<HashMap<String, Object>> task = new Task<HashMap<String, Object>>() {

			{
				setOnSucceeded(value -> {
					wait.close();
					operation.feedback(getValue());
				});

				setOnCancelled(value -> {
					wait.close();

					HashMap<String, Object> hashMap = new HashMap<>();
					hashMap.put("error", "Cancelled");
					operation.feedback(hashMap);
				});

				setOnFailed(value -> {
					wait.close();

					HashMap<String, Object> hashMap = new HashMap<>();
					hashMap.put("error", "Failed");
					operation.feedback(hashMap);
				});
			}

			@Override
			protected HashMap<String, Object> call() {

				HashMap<String, Object> hashMap = new HashMap<>();
				operation.start(hashMap);
				return hashMap;
			}
		};

		wait.show();

		Thread thread = new Thread(task);
		thread.start();
	}
}
