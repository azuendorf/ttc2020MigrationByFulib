package de.hub.mse.ttc2020.benchmark;

import org.eclipse.emf.ecore.EPackage;

public abstract class AbstractTaskFactory {

    public abstract AbstractTask createTask(TaskInfo info, EPackage model1, EPackage model2);

}
