package de.hub.mse.ttc2020.benchmark;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

public abstract class AbstractTask {

	private EPackage model1;
	private EPackage model2;

	public AbstractTask(EPackage model1, EPackage model2) {
		this.model1 = model1;
		this.model2 = model2;
	}

	protected EPackage getModel1() {
		return model1;
	}

	protected EPackage getModel2() {
		return model2;
	}

	public abstract EObject migrate(EObject instance);

	public abstract EObject migrateBack(EObject instance);
}
