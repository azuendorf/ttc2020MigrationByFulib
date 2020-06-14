package de.hub.mse.ttc2020.solution;

import de.hub.mse.ttc2020.benchmark.AbstractTask;
import de.hub.mse.ttc2020.benchmark.PrettyPrinter;
import de.hub.mse.ttc2020.benchmark.TaskInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.junit.BeforeClass;

import de.hub.mse.ttc2020.benchmark.AllFunctionalTests;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AllJavaFunctionalTests extends AllFunctionalTests {

	@BeforeClass
	public static void init() throws CoreException {
		AllFunctionalTests.init();
		
		AllFunctionalTests.taskFactory = new JavaTaskFactory();
		
		AllFunctionalTests.pathScenario1 = "./data/scenario1/";
		AllFunctionalTests.pathScenario2 = "./data/scenario2/";
		AllFunctionalTests.pathScenario3 = "./data/scenario3/";
		AllFunctionalTests.pathScenario4 = "./data/scenario4/";
	}

	@Test
	public void task_1_M1_M2_M1_a() throws IOException
	{
		System.out.println("\n= Task 1 M1->M2->M1 (a)");


		EPackage model1 = getModel(pathScenario1 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario1 + "models/V2.ecore");
		EObject input = getInstance(pathScenario1 + "instances/input/V1a.xmi");
		EObject expout = getInstance(pathScenario1 + "instances/expout/V1a.xmi");


		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printInstance(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_1_M1_M2_M1, model1, model2);

		EObject migrated = task.migrate(input);
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated " + PrettyPrinter.printInstance(migrated));
		System.out.println("Migrated Back " + PrettyPrinter.printInstance(migratedBack));


		System.out.println("Expected " + PrettyPrinter.printInstance(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}


	@Test
	public void task_1_M1_M2_M1_b() throws IOException {
		System.out.println("\n= Task 1 M1->M2->M1 (b)");

		EPackage model1 = getModel(pathScenario1 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario1 + "models/V2.ecore");
		EObject input = getInstance(pathScenario1 + "instances/input/V1b.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printInstance(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_1_M1_M2_M1, model1, model2);

		EObject migrated = task.migrate(input);
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated " + PrettyPrinter.printInstance(migrated));
		System.out.println("Migrated Back " + PrettyPrinter.printInstance(migratedBack));

		EObject expout = getInstance(pathScenario1 + "instances/expout/V1b.xmi");
		System.out.println("Expected " + PrettyPrinter.printInstance(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}

	@Test
	public void task_1_M2_M1_M2_a() throws IOException {
		System.out.println("\n= Task 1 M2->M1->M2 (a)");

		EPackage model1 = getModel(pathScenario1 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario1 + "models/V2.ecore");
		EObject input = getInstance(pathScenario1 + "instances/input/V2a.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printInstance(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_1_M2_M1_M2, model1, model2);

		EObject migrated = task.migrate(input);
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated " + PrettyPrinter.printInstance(migrated));
		System.out.println("Migrated Back " + PrettyPrinter.printInstance(migratedBack));

		EObject expout = getInstance(pathScenario1 + "instances/expout/V2a.xmi");
		System.out.println("Expected " + PrettyPrinter.printInstance(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}

	@Test
	public void task_1_M2_M1_M2_b() throws IOException {
		System.out.println("\n= Task 1 M2->M1->M2 (b)");

		EPackage model1 = getModel(pathScenario1 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario1 + "models/V2.ecore");
		EObject input = getInstance(pathScenario1 + "instances/input/V2b.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printInstance(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_1_M2_M1_M2, model1, model2);

		EObject migrated = task.migrate(input);
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated " + PrettyPrinter.printInstance(migrated));
		System.out.println("Migrated Back " + PrettyPrinter.printInstance(migratedBack));

		EObject expout = getInstance(pathScenario1 + "instances/expout/V2b.xmi");
		System.out.println("Expected " + PrettyPrinter.printInstance(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}

	@Test
	public void task_2_M1_M2_M1_a() throws IOException {
		System.out.println("\n= Task 2 M1->M2->M1 (a)");

		EPackage model1 = getModel(pathScenario2 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario2 + "models/V2.ecore");
		EObject input = getInstance(pathScenario2 + "instances/input/V1a.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printInstance(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_2_M1_M2_M1, model1, model2);

		EObject migrated = task.migrate(input);
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated " + PrettyPrinter.printInstance(migrated));
		System.out.println("Migrated Back " + PrettyPrinter.printInstance(migratedBack));

		EObject expout = getInstance(pathScenario2 + "instances/expout/V1a.xmi");
		System.out.println("Expected " + PrettyPrinter.printInstance(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}


	@Test
	public void task_2_M1_M2_M1_b() throws IOException {
		System.out.println("\n= Task 2 M1->M2->M1 (b)");

		EPackage model1 = getModel(pathScenario2 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario2 + "models/V2.ecore");
		EObject input = getInstance(pathScenario2 + "instances/input/V1b.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printInstance(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_2_M1_M2_M1, model1, model2);

		EObject migrated = task.migrate(input);
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated " + PrettyPrinter.printInstance(migrated));
		System.out.println("Migrated Back " + PrettyPrinter.printInstance(migratedBack));

		EObject expout = getInstance(pathScenario2 + "instances/expout/V1b.xmi");
		System.out.println("Expected " + PrettyPrinter.printInstance(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}


	@Test
	public void task_2_M2_M1_M2_a() throws IOException {
		System.out.println("\n= Task 2 M2->M1->M2 (a)");

		EPackage model1 = getModel(pathScenario2 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario2 + "models/V2.ecore");
		EObject input = getInstance(pathScenario2 + "instances/input/V2a.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printInstance(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_2_M2_M1_M2, model1, model2);

		EObject migrated = task.migrate(input);
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated " + PrettyPrinter.printInstance(migrated));
		System.out.println("Migrated Back " + PrettyPrinter.printInstance(migratedBack));

		EObject expout = getInstance(pathScenario2 + "instances/expout/V2a.xmi");
		System.out.println("Expected " + PrettyPrinter.printInstance(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}


	@Test
	public void task_2_M2_M1_M2_b() throws IOException {
		System.out.println("\n= Task 2 M2->M1->M2 (b)");

		EPackage model1 = getModel(pathScenario2 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario2 + "models/V2.ecore");
		EObject input = getInstance(pathScenario2 + "instances/input/V2b.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printInstance(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_2_M2_M1_M2, model1, model2);

		EObject migrated = task.migrate(input);
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated " + PrettyPrinter.printInstance(migrated));
		System.out.println("Migrated Back " + PrettyPrinter.printInstance(migratedBack));

		EObject expout = getInstance(pathScenario2 + "instances/expout/V2b.xmi");
		System.out.println("Expected " + PrettyPrinter.printInstance(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}


	@Test
	public void task_3_M1_M2_M1_a() throws IOException {
		System.out.println("\n= Task 3 M1->M2->M1 (a)");

		EPackage model1 = getModel(pathScenario3 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario3 + "models/V2.ecore");
		EObject input = getInstance(pathScenario3 + "instances/input/V1a.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printInstance(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_3_M1_M2_M1, model1, model2);

		EObject migrated = task.migrate(input);
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated " + PrettyPrinter.printInstance(migrated));
		System.out.println("Migrated Back " + PrettyPrinter.printInstance(migratedBack));

		EObject expout = getInstance(pathScenario3 + "instances/expout/V1a.xmi");
		System.out.println("Expected " + PrettyPrinter.printInstance(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}


	@Test
	public void task_3_M1_M2_M1_b() throws IOException {
		System.out.println("\n= Task 3 M1->M2->M1 (b)");

		EPackage model1 = getModel(pathScenario3 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario3 + "models/V2.ecore");
		EObject input = getInstance(pathScenario3 + "instances/input/V1b.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printInstance(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_3_M1_M2_M1, model1, model2);

		// Migrate
		EObject migrated = task.migrate(input);
		System.out.println("Migrated " + PrettyPrinter.printInstance(migrated));

		// Modify
		migrated.eSet(migrated.eClass().getEStructuralFeature("name"), null);
		System.out.println("Modified " + PrettyPrinter.printInstance(migrated));

		// Migrate Back
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated Back " + PrettyPrinter.printInstance(migratedBack));

		EObject expout = getInstance(pathScenario3 + "instances/expout/V1b.xmi");
		System.out.println("Expected " + PrettyPrinter.printInstance(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}

	@Test
	public void task_3_M2_M1_M2_a() throws IOException {
		System.out.println("\n= Task 3 M2->M1->M2 (a)");

		EPackage model1 = getModel(pathScenario3 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario3 + "models/V2.ecore");
		EObject input = getInstance(pathScenario3 + "instances/input/V2a.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printInstance(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_3_M2_M1_M2, model1, model2);

		EObject migrated = task.migrate(input);
		System.out.println("Migrated " + PrettyPrinter.printInstance(migrated));
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated Back " + PrettyPrinter.printInstance(migratedBack));

		EObject expout = getInstance(pathScenario3 + "instances/expout/V2a.xmi");
		System.out.println("Expected " + PrettyPrinter.printInstance(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}

	@Test
	public void task_3_M2_M1_M2_b() throws IOException {
		System.out.println("\n= Task 3 M2->M1->M2 (b)");

		EPackage model1 = getModel(pathScenario3 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario3 + "models/V2.ecore");
		EObject input = getInstance(pathScenario3 + "instances/input/V2b.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printInstance(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_3_M2_M1_M2, model1, model2);

		// Migrate
		EObject migrated = task.migrate(input);
		System.out.println("Migrated " + PrettyPrinter.printInstance(migrated));

		// Modify
		migrated.eSet(migrated.eClass().getEStructuralFeature("name"), "");
		System.out.println("Modified " + PrettyPrinter.printInstance(migrated));

		// Migrate back
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated Back " + PrettyPrinter.printInstance(migratedBack));

		EObject expout = getInstance(pathScenario3 + "instances/expout/V2b.xmi");
		System.out.println("Expected " + PrettyPrinter.printInstance(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}

	@Test
	public void task_4_M1_M2_M1_a() throws IOException {
		System.out.println("\n= Task 4 M1->M2->M1 (a)");

		EPackage model1 = getModel(pathScenario4 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario4 + "models/V2.ecore");
		EObject input = getInstance(pathScenario4 + "instances/input/V1a.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printContainer(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_4_M1_M2_M1, model1, model2);

		EObject migrated = task.migrate(input);
		System.out.println("Migrated " + PrettyPrinter.printContainer(migrated));

		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated Back " + PrettyPrinter.printContainer(migratedBack));

		EObject expout = getInstance(pathScenario4 + "instances/expout/V1a.xmi");
		System.out.println("Expected " + PrettyPrinter.printContainer(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}

	@Test
	public void task_4_M1_M2_M1_b() throws IOException {
		System.out.println("\n= Task 4 M1->M2->M1 (b)");

		EPackage model1 = getModel(pathScenario4 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario4 + "models/V2.ecore");
		EObject input = getInstance(pathScenario4 + "instances/input/V1b.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printContainer(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_4_M1_M2_M1, model1, model2);

		EObject migrated = task.migrate(input);
		System.out.println("Migrated " + PrettyPrinter.printContainer(migrated));

		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated Back " + PrettyPrinter.printContainer(migratedBack));

		EObject expout = getInstance(pathScenario4 + "instances/expout/V1b.xmi");
		System.out.println("Expected " + PrettyPrinter.printContainer(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}

	@Test
	public void task_4_M2_M1_M2_a() throws IOException {
		System.out.println("\n= Task 4 M2->M1->M2 (a)");

		EPackage model1 = getModel(pathScenario4 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario4 + "models/V2.ecore");
		EObject input = getInstance(pathScenario4 + "instances/input/V2a.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printContainer(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_4_M2_M1_M2, model1, model2);

		// Migrate
		EObject migrated = task.migrate(input);
		System.out.println("Migrated " + PrettyPrinter.printContainer(migrated));

		// Migrate back
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated Back " + PrettyPrinter.printContainer(migratedBack));

		EObject expout = getInstance(pathScenario4 + "instances/expout/V2a.xmi");
		System.out.println("Expected " + PrettyPrinter.printContainer(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}

	@Test
	public void task_4_M2_M1_M2_b() throws IOException {
		System.out.println("\n= Task 4 M2->M1->M2 (b)");

		EPackage model1 = getModel(pathScenario4 + "models/V1.ecore");
		EPackage model2 = getModel(pathScenario4 + "models/V2.ecore");
		EObject input = getInstance(pathScenario4 + "instances/input/V2b.xmi");
		System.out.println(PrettyPrinter.printModel(model1));
		System.out.println(PrettyPrinter.printModel(model2));
		System.out.println("Input " + PrettyPrinter.printContainer(input));

		AbstractTask task = taskFactory.createTask(TaskInfo.TASK_4_M2_M1_M2, model1, model2);

		// Migrate
		EObject migrated = task.migrate(input);
		System.out.println("Migrated " + PrettyPrinter.printContainer(migrated));

		// Migrate back
		EObject migratedBack = task.migrateBack(migrated);
		System.out.println("Migrated Back " + PrettyPrinter.printContainer(migratedBack));

		EObject expout = getInstance(pathScenario4 + "instances/expout/V2b.xmi");
		System.out.println("Expected " + PrettyPrinter.printContainer(expout));

		assertThat(PrettyPrinter.printInstance(migratedBack), equalTo(PrettyPrinter.printInstance(expout)));
	}

}
