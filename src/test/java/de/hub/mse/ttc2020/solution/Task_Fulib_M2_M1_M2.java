package de.hub.mse.ttc2020.solution;

import de.hub.mse.ttc2020.benchmark.AbstractTask;
import de.hub.mse.ttc2020.solution.M1.M1Editor;
import de.hub.mse.ttc2020.solution.M2.M2Editor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.fulib.yaml.Yaml;

public class Task_Fulib_M2_M1_M2 extends AbstractTask {

	private EObject trace;
	private M2Editor m2Editor;
	private M1Editor m1Editor;

	public Task_Fulib_M2_M1_M2(EPackage model1, EPackage model2) {
		super(model1, model2);
	}

	@Override
	public EObject migrate(EObject instance) {
		m2Editor = new M2Editor().setEmfModel(getModel2());
		m2Editor.parse(instance);
		String yaml = Yaml.encode(m2Editor.getActiveCommands().values());
		m1Editor = new M1Editor().setEmfModel(getModel1());
		m1Editor.apply(yaml);

		EObject result = m1Editor.getOrCreatePerson("obj0");
		if (m1Editor.getActiveCommands().size() > 1) {
			// we need a Container
			EObject person = m1Editor.getOrCreatePerson("obj0");
			EObject dog = m1Editor.getOrCreateDog("obj1");
			EClass containerClass = (EClass) getModel1().getEClassifier("Container");
			result = getModel1().getEFactoryInstance().create(containerClass);
			EStructuralFeature personFeature = containerClass.getEStructuralFeature("person");
			result.eSet(personFeature, person);
			EStructuralFeature dogFeature = containerClass.getEStructuralFeature("dog");
			result.eSet(dogFeature, dog);
		}
		return result;

	}

	@Override
	public EObject migrateBack(EObject instance) {
		m1Editor.parse(instance);
		String yaml = Yaml.encode(m1Editor.getActiveCommands().values());
		m2Editor.apply(yaml);

		EObject result = m2Editor.getOrCreatePerson("obj0");
		if (m2Editor.getActiveCommands().size() > 1) {
			// we need a Container
			EObject person = m2Editor.getOrCreatePerson("obj0");
			result = person.eContainer();

		}
		return result;
	}

}
