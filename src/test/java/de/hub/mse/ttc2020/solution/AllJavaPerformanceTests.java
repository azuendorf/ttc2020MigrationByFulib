package de.hub.mse.ttc2020.solution;

import org.eclipse.core.runtime.CoreException;
import org.junit.BeforeClass;

import de.hub.mse.ttc2020.benchmark.AllFunctionalTests;
import de.hub.mse.ttc2020.benchmark.PerformanceTests;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AllJavaPerformanceTests extends PerformanceTests {

	@BeforeClass
	public static void init() throws CoreException {
		Logger.getGlobal().setLevel(Level.SEVERE);
		AllFunctionalTests.init();
		
		AllFunctionalTests.taskFactory = new JavaTaskFactory();
		
		AllFunctionalTests.pathScenario1 = "./data/scenario1/";
		AllFunctionalTests.pathScenario2 = "./data/scenario2/";
		AllFunctionalTests.pathScenario3 = "./data/scenario3/";
		AllFunctionalTests.pathScenario4 = "./data/scenario4/";
	}
}
