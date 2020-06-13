package de.hub.mse.ttc2020.solution;

import org.eclipse.emf.ecore.EPackage;

import de.hub.mse.ttc2020.benchmark.AbstractTask;
import de.hub.mse.ttc2020.benchmark.AbstractTaskFactory;
import de.hub.mse.ttc2020.benchmark.TaskInfo;

public class JavaTaskFactory extends AbstractTaskFactory {

	public JavaTaskFactory() {
		super();
	}
	
	@Override
	public AbstractTask createTask(TaskInfo info, EPackage model1, EPackage model2) {
		switch (info) {
		case TASK_1_M1_M2_M1:
			return new Task_Fulib_M1_M2_M1(model1, model2);
		case TASK_1_M2_M1_M2:
			return new Task_Fulib_M2_M1_M2(model1, model2);
		case TASK_2_M1_M2_M1:
			return new Task_Fulib_M1_M2_M1(model1, model2);
		case TASK_2_M2_M1_M2:
			return new Task_Fulib_M2_M1_M2(model1, model2);
		case TASK_3_M1_M2_M1:
			return new Task_Fulib_M1_M2_M1(model1, model2);
		case TASK_3_M2_M1_M2:
			return new Task_Fulib_M2_M1_M2(model1, model2);
		case TASK_4_M1_M2_M1:
			return new Task_Fulib_M1_M2_M1(model1, model2);
		case TASK_4_M2_M1_M2:
			return new Task_Fulib_M2_M1_M2(model1, model2);
				
		default:
			return null;
		}
	}

}
