package de.hub.mse.ttc2020.benchmark;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.match.DefaultComparisonFactory;
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.BeforeClass;

/**
 * Common superclass of benchmark tests.
 * 
 * Configured via {@link #taskFactory} and {@link #pathScenario1},
 * {@link #pathScenario2}, {@link #pathScenario3}, {@link #pathScenario4}.
 *
 */
public abstract class AbstractBenchmarkTests {
	protected static AbstractTaskFactory taskFactory;

	protected static String pathScenario1 = "data/scenario1/";
	protected static String pathScenario2 = "data/scenario2/";
	protected static String pathScenario3 = "data/scenario3/";
	protected static String pathScenario4 = "data/scenario4/";
	
	protected static ResourceSet resSet;
	
	@BeforeClass
	public static void init() throws CoreException {
		System.out.println("Start running tests...");

		// IExtensionRegistry reg = Platform.getExtensionRegistry();
		if (Platform.getExtensionRegistry() != null) {
			IConfigurationElement[] elements = Platform.getExtensionRegistry()
					.getConfigurationElementsFor("de.hub.mse.ttc2020.benchmark.concretetaskfactory");
			if (elements.length > 0) {
				taskFactory = (AbstractTaskFactory) elements[0].createExecutableExtension("concrete_task_factory");
			}
		}

		resSet = new ResourceSetImpl();

		// Initialize EMF resource factories
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("ecore", new EcoreResourceFactoryImpl());
		m.put("xmi", new XMIResourceFactoryImpl());

		// Suppress Log4J warnings (used by EMFCompare)
		Logger.getRootLogger().setLevel(Level.OFF);
	}
	
	protected EPackage getModel(String path) throws IOException {
		Resource resource = resSet.getResource(URI.createURI(path), true);
		resource.load(Collections.EMPTY_MAP);
		EPackage model = (EPackage) resource.getContents().get(0);
		resSet.getPackageRegistry().put(model.getNsURI(), model);
		return model;
	}

	protected EObject getInstance(String path) throws IOException {
		Resource resource = resSet.getResource(URI.createURI(path), true);
		resource.load(Collections.EMPTY_MAP);
		return resource.getContents().get(0);
	}

	protected boolean isEqual(EObject out, EObject expout) {
		// Configure EMF Compare
		IEObjectMatcher matcher = DefaultMatchEngine.createDefaultEObjectMatcher(UseIdentifiers.NEVER);
		IComparisonFactory comparisonFactory = new DefaultComparisonFactory(new DefaultEqualityHelperFactory());
		@SuppressWarnings("deprecation")
		IMatchEngine.Factory matchEngineFactory = new MatchEngineFactoryImpl(matcher, comparisonFactory);
		matchEngineFactory.setRanking(20);
		IMatchEngine.Factory.Registry matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
		matchEngineRegistry.add(matchEngineFactory);
		EMFCompare comparator = EMFCompare.builder().setMatchEngineFactoryRegistry(matchEngineRegistry).build();

		// Perform comparison
		@SuppressWarnings("deprecation")
		IComparisonScope scope = EMFCompare.createDefaultScope(out, expout);
		Comparison comparison = comparator.compare(scope);

		// Equal if diff list is empty
		return comparison.getDifferences().isEmpty();
	}
}
