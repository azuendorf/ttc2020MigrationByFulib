package de.hub.mse.ttc2020.solution;

import org.eclipse.core.runtime.CoreException;
import org.junit.BeforeClass;

import de.hub.mse.ttc2020.benchmark.AllFunctionalTests;

public class AllJavaFunctionalTests extends AllFunctionalTests {

	@BeforeClass
	public static void init() throws CoreException {
		AllFunctionalTests.init();
		
		AllFunctionalTests.taskFactory = new JavaTaskFactory();
		
		AllFunctionalTests.pathScenario1 = "../de.hub.mse.ttc2020.benchmark/data/scenario1/";
		AllFunctionalTests.pathScenario2 = "../de.hub.mse.ttc2020.benchmark/data/scenario2/";
		AllFunctionalTests.pathScenario3 = "../de.hub.mse.ttc2020.benchmark/data/scenario3/";
		AllFunctionalTests.pathScenario4 = "../de.hub.mse.ttc2020.benchmark/data/scenario4/";
	}
}
