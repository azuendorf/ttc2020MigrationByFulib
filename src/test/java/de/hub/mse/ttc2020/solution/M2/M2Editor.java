package de.hub.mse.ttc2020.solution.M2;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.fulib.yaml.Reflector;
import org.fulib.yaml.Yaml;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DateFormat;
import java.util.*;

public class M2Editor 
{

   public static final String PROPERTY_activeCommands = "activeCommands";

   private java.util.Map<String, ModelCommand> activeCommands = new java.util.LinkedHashMap<>();

   public java.util.Map<String, ModelCommand> getActiveCommands()
   {
      return activeCommands;
   }

   public M2Editor setActiveCommands(java.util.Map<String, ModelCommand> value)
   {
      if (value != this.activeCommands)
      {
         java.util.Map<String, ModelCommand> oldValue = this.activeCommands;
         this.activeCommands = value;
         firePropertyChange("activeCommands", oldValue, value);
      }
      return this;
   }

   public static final String PROPERTY_removeCommands = "removeCommands";

   private java.util.Map<String, RemoveCommand> removeCommands = new java.util.LinkedHashMap<>();

   public java.util.Map<String, RemoveCommand> getRemoveCommands()
   {
      return removeCommands;
   }

   public M2Editor setRemoveCommands(java.util.Map<String, RemoveCommand> value)
   {
      if (value != this.removeCommands)
      {
         java.util.Map<String, RemoveCommand> oldValue = this.removeCommands;
         this.removeCommands = value;
         firePropertyChange("removeCommands", oldValue, value);
      }
      return this;
   }

   public static final String PROPERTY_commandListeners = "commandListeners";

   private java.util.Map<String, ArrayList<CommandStream>> commandListeners = new java.util.LinkedHashMap<>();

   public java.util.Map<String, ArrayList<CommandStream>> getCommandListeners()
   {
      return commandListeners;
   }

   public M2Editor setCommandListeners(java.util.Map<String, ArrayList<CommandStream>> value)
   {
      if (value != this.commandListeners)
      {
         java.util.Map<String, ArrayList<CommandStream>> oldValue = this.commandListeners;
         this.commandListeners = value;
         firePropertyChange("commandListeners", oldValue, value);
      }
      return this;
   }

   public static final String PROPERTY_isoDateFormat = "isoDateFormat";

   private DateFormat isoDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

   public DateFormat getIsoDateFormat()
   {
      return isoDateFormat;
   }

   public M2Editor setIsoDateFormat(DateFormat value)
   {
      if (value != this.isoDateFormat)
      {
         DateFormat oldValue = this.isoDateFormat;
         this.isoDateFormat = value;
         firePropertyChange("isoDateFormat", oldValue, value);
      }
      return this;
   }

   public static final String PROPERTY_lastTime = "lastTime";

   private String lastTime = isoDateFormat.format(new Date());

   public String getLastTime()
   {
      return lastTime;
   }

   public M2Editor setLastTime(String value)
   {
      if (value == null ? this.lastTime != null : ! value.equals(this.lastTime))
      {
         String oldValue = this.lastTime;
         this.lastTime = value;
         firePropertyChange("lastTime", oldValue, value);
      }
      return this;
   }

   public static final String PROPERTY_timeDelta = "timeDelta";

   private long timeDelta = 1;

   public long getTimeDelta()
   {
      return timeDelta;
   }

   public M2Editor setTimeDelta(long value)
   {
      if (value != this.timeDelta)
      {
         long oldValue = this.timeDelta;
         this.timeDelta = value;
         firePropertyChange("timeDelta", oldValue, value);
      }
      return this;
   }

   public static final String PROPERTY_service = "service";

   private M2Service service = null;

   public M2Service getService()
   {
      return this.service;
   }

   public M2Editor setService(M2Service value)
   {
      if (this.service != value)
      {
         M2Service oldValue = this.service;
         if (this.service != null)
         {
            this.service = null;
            oldValue.setModelEditor(null);
         }
         this.service = value;
         if (value != null)
         {
            value.setModelEditor(this);
         }
         firePropertyChange("service", oldValue, value);
      }
      return this;
   }


   public String getTime() { 
      String newTime = isoDateFormat.format(new Date());
      if (newTime.compareTo(lastTime) <= 0) {
         try {
            Date lastDate = isoDateFormat.parse(lastTime);
            long millis = lastDate.getTime();
            millis += timeDelta;
            Date newDate = new Date(millis);
            newTime = isoDateFormat.format(newDate);
         }
         catch (Exception e) {
            e.printStackTrace();
         }
      }
      lastTime = newTime;
      return newTime;
   }

   public void fireCommandExecuted(ModelCommand command) { 
      String commandName = command.getClass().getSimpleName();
      ArrayList<CommandStream> listeners = commandListeners.computeIfAbsent(commandName, s -> new ArrayList<>());
      for (CommandStream stream : listeners) {
         stream.publish(command);
      }
   }

   public M2Editor addCommandListener(String commandName, CommandStream stream) {
      ArrayList<CommandStream> listeners = commandListeners.computeIfAbsent(commandName, s -> new ArrayList<>());
      listeners.add(stream);
      return this;
   }

   public void loadYaml(String yamlString) { 
      java.util.Map map = Yaml.forPackage("de.hub.mse.ttc2020.solution.M2").decode(yamlString);
      for (Object value : map.values()) {
         ModelCommand cmd = (ModelCommand) value;
         cmd.run(this);
      }
   }

   protected PropertyChangeSupport listeners = null;

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null)
      {
         listeners.firePropertyChange(propertyName, oldValue, newValue);
         return true;
      }
      return false;
   }

   public boolean addPropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(listener);
      return true;
   }

   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(propertyName, listener);
      return true;
   }

   public boolean removePropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(listener);
      }
      return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(propertyName, listener);
      }
      return true;
   }

   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getLastTime());


      return result.substring(1);
   }

   public void removeYou()
   {
      this.setService(null);

   }


   private EPackage emfModel;

   public M2Editor setEmfModel(EPackage emfModel)
   {
      this.emfModel = emfModel;
      return this;
   }

   public EPackage getEmfModel()
   {
      return emfModel;
   }

   Map<String, EObject> personMap = new LinkedHashMap<>();

   public void execute(ModelCommand command)
   {
      String id = command.getId();
      if (id == null) {
         id = "obj" + activeCommands.size();
         command.setId(id);
      }

      String time = command.getTime();
      if (time == null) {
         time = getTime();
         command.setTime(time);
      }

      ModelCommand oldCommand = activeCommands.get(id);

      if (oldCommand != null && oldCommand.getTime().compareTo(time) >= 0) {
         // already updated
         return;
      }

      if (oldCommand != null && oldCommand.equalsButTime(command)) {
         return;
      }

      command.run(this);

      activeCommands.put(id, command);
   }



   public EObject getOrCreatePerson(String id) {
      EObject eObject = personMap.get(id);

      if (eObject != null) {
         return eObject;
      }

      EClass person = (EClass) emfModel.getEClassifier("Person");
      eObject = emfModel.getEFactoryInstance().create(person);
      personMap.put(id, eObject);
      return eObject;
   }

   Map<String, EObject> dogMap = new LinkedHashMap<>();

   public EObject getOrCreateDog(String id) {
      EObject eObject = dogMap.get(id);

      if (eObject != null) {
         return eObject;
      }

      EClass dog = (EClass) emfModel.getEClassifier("Dog");
      eObject = emfModel.getEFactoryInstance().create(dog);
      dogMap.put(id, eObject);
      return eObject;
   }


   public M2Editor parse(EObject instance)
   {
      EObject person = instance;
      EObject dog = null;
      EClass eClass = instance.eClass();
      EStructuralFeature personFeature = eClass.getEStructuralFeature("person");
      if (personFeature != null) {
         person = (EObject) instance.eGet(personFeature);
      }
      EStructuralFeature dogFeature = eClass.getEStructuralFeature("dog");
      if (dogFeature != null) {
         dog = (EObject) instance.eGet(dogFeature);
      }

      parsePerson(person);

      parseDog(dog);

      return this;
   }

   private void parseDog(EObject dog)
   {
      if (dog == null) {
         return;
      }

      String id = getDogId(dog);
      HaveDog oldCommand = (HaveDog) activeCommands.get(id);

      EClass class1 = dog.eClass();
      String name = (String) dog.eGet(class1.getEStructuralFeature("name"));
      if ("".equals(name) && oldCommand != null && oldCommand.getName() == null) {
         name = null;
      }

      int age = -1;
      EStructuralFeature ageFeature = class1.getEStructuralFeature("age");
      if (ageFeature != null) {
         age = (Integer) dog.eGet(ageFeature);
      }
      else {
         if (oldCommand != null) {
            age = oldCommand.getAge();
         }
      }

      EStructuralFeature ownerFeature = class1.getEStructuralFeature("owner");
      EObject owner = (EObject) dog.eGet(ownerFeature);
      String ownerId = getPersonId(owner);

      HaveDog haveDog = new HaveDog()
            .setName(name)
            .setAge(age)
            .setOwner(ownerId);
      haveDog.setId(id);

      execute(haveDog);
   }

   private void parsePerson(EObject instance)
   {
      EClass eClass = instance.eClass();
      String name = (String) instance.eGet(eClass.getEStructuralFeature("name"));
      int age = -1;
      EStructuralFeature ageFeature = eClass.getEStructuralFeature("age");
      if (ageFeature != null) {
         age = (Integer) instance.eGet(ageFeature);
      }
      EStructuralFeature ybirthFeature = eClass.getEStructuralFeature("ybirth");
      if (ybirthFeature != null) {
         age = 2020 - (Integer) instance.eGet(ybirthFeature);
      }
      HavePerson havePerson = new HavePerson()
            .setName(name)
            .setAge(age);
      String id = getPersonId(instance);
      havePerson.setId(id);
      execute(havePerson);
   }

   public String getId(EObject instance)
   {
      for (Map.Entry<String, EObject> entry : personMap.entrySet()) {
         if (entry.getValue() == instance) {
            return entry.getKey();
         }
      }

      for (Map.Entry<String, EObject> entry : dogMap.entrySet()) {
         if (entry.getValue() == instance) {
            return entry.getKey();
         }
      }

      return "obj" + activeCommands.size();
   }

   public String getPersonId(EObject instance)
   {
      for (Map.Entry<String, EObject> entry : personMap.entrySet()) {
         if (entry.getValue() == instance) {
            return entry.getKey();
         }
      }

      String id = "obj" + activeCommands.size();
      personMap.put(id, instance);

      return id;
   }

   public String getDogId(EObject instance)
   {
      for (Map.Entry<String, EObject> entry : dogMap.entrySet()) {
         if (entry.getValue() == instance) {
            return entry.getKey();
         }
      }

      String id = "obj" + activeCommands.size();
      dogMap.put(id, instance);

      return id;
   }


   public void apply(String yaml)
   {
      LinkedHashMap<String, Object> commandList = Yaml.forPackage(this.getClass().getPackage().getName()).decode(yaml);
      for (Object m2Command : commandList.values()) {
         execute((de.hub.mse.ttc2020.solution.M2.ModelCommand) m2Command);
      }
   }
}
