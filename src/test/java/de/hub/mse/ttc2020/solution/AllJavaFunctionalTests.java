package de.hub.mse.ttc2020.solution;

import org.eclipse.core.runtime.CoreException;
import org.junit.BeforeClass;

import de.hub.mse.ttc2020.benchmark.AllFunctionalTests;

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
}
