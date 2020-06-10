package de.hub.mse.ttc2020.benchmark;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

public class PrettyPrinter {

	public static String printModel(EPackage model) {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("Model [" + model.getNsURI() + "]\n");
		for (Iterator<EClassifier> itC = model.getEClassifiers().iterator(); itC.hasNext();) {
			EClass clazz = (EClass) itC.next();
			strBuf.append("\t" + clazz.getName() + "(");
			for (Iterator<EAttribute> itA = clazz.getEAttributes().iterator(); itA.hasNext();) {
				EAttribute attribute = itA.next();
				strBuf.append(attribute.getName() + ":" + attribute.getEType().getName());
				if (itA.hasNext()) {
					strBuf.append(", ");
				}
			}
			strBuf.append(")");
			if (itC.hasNext()) {
				strBuf.append("\n");
			}
		}

		return strBuf.toString();
	}

	public static String printInstance(EObject instance) {
		StringBuffer strBuf = new StringBuffer();
		EClass clazz = instance.eClass();
		strBuf.append("Instance [" + clazz.getEPackage().getNsURI() + "]\n");
		strBuf.append("\t:" + clazz.getName() + "(");
		for (Iterator<EAttribute> itA = clazz.getEAttributes().iterator(); itA.hasNext();) {
			EAttribute attribute = itA.next();
			strBuf.append(attribute.getName() + "=" + instance.eGet(attribute));
			if (itA.hasNext()) {
				strBuf.append(", ");
			}
		}
		strBuf.append(")");

		return strBuf.toString();
	}

	public static String printContainer(EObject container) {
		StringBuffer strBuf = new StringBuffer();
		EClass cClazz = container.eClass();
		strBuf.append("Instance [" + cClazz.getEPackage().getNsURI() + "]\n");

		for (Iterator<EObject> itInst = container.eAllContents(); itInst.hasNext();) {
			EObject instance = itInst.next();
			EClass clazz = instance.eClass();

			strBuf.append("\t:" + clazz.getName() + "(");
			for (Iterator<EAttribute> itA = clazz.getEAttributes().iterator(); itA.hasNext();) {
				EAttribute attribute = itA.next();
				strBuf.append(attribute.getName() + "=" + instance.eGet(attribute));
				if (itA.hasNext()) {
					strBuf.append(", ");
				}
			}
			strBuf.append(")");
			if (itInst.hasNext()) {
				strBuf.append("\n");
			}
		}
		
		return strBuf.toString();
	}
}
