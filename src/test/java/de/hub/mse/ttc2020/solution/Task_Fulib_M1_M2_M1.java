package de.hub.mse.ttc2020.solution;

import de.hub.mse.ttc2020.benchmark.AbstractTask;
import de.hub.mse.ttc2020.solution.M1.M1Editor;
import de.hub.mse.ttc2020.solution.M2.M2Editor;
import org.eclipse.emf.ecore.*;
import org.fulib.yaml.Yaml;

public class Task_Fulib_M1_M2_M1 extends AbstractTask {

	private M2Editor m2Editor;
	private M1Editor m1Editor;

	public Task_Fulib_M1_M2_M1(EPackage model1, EPackage model2) {
		super(model1, model2);
	}

	@Override
	public EObject migrate(EObject instance) {
		// parse m1
		m1Editor = new M1Editor().setEmfModel(getModel1());
		m1Editor.parse(instance);

		// forward to m2
		String yaml = Yaml.encode(m1Editor.getActiveCommands().values());

		m2Editor = new M2Editor().setEmfModel(getModel2());
		m2Editor.apply(yaml);

		EObject result = m2Editor.getOrCreatePerson("obj0");
		if (m2Editor.getActiveCommands().size() > 1) {
			// we need a Container
			EObject person = m2Editor.getOrCreatePerson("obj0");
			EObject dog = m2Editor.getOrCreateDog("obj1");
			EClass containerClass = (EClass) getModel2().getEClassifier("Container");
			result = getModel2().getEFactoryInstance().create(containerClass);
			EStructuralFeature personFeature = containerClass.getEStructuralFeature("person");
			result.eSet(personFeature, person);
			EStructuralFeature dogFeature = containerClass.getEStructuralFeature("dog");
			result.eSet(dogFeature, dog);
		}
		return result;
	}

	@Override
	public EObject migrateBack(EObject instance) {
		m2Editor.parse(instance);
		String yaml = Yaml.encode(m2Editor.getActiveCommands().values());

		m1Editor.apply(yaml);

		EObject result = m1Editor.getOrCreatePerson("obj0");
		if (m1Editor.getActiveCommands().size() > 1) {
			// we need a Container
			EObject person = m1Editor.getOrCreatePerson("obj0");
			result = person.eContainer();
		}
		return result;
	}

}
