package gef.tutorial.step.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class AbstractModel implements IPropertySource
{
    //the list of listeners
    private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        listeners.addPropertyChangeListener(listener);
    }
    //change of a model is notified
      public void firePropertyChange(String propName,Object oldValue,Object newValue)
      {
          listeners.firePropertyChange(propName, oldValue, newValue);
      }
      //deletion of listeners
      public void removePropertyChangeListener(PropertyChangeListener listener)
      {
          listeners.removePropertyChangeListener(listener);
      }
    @Override
    public Object getEditableValue()
    {
        // TODO Auto-generated method stub
        return this;
    }
    @Override
    public IPropertyDescriptor[] getPropertyDescriptors()
    {
        //如果在抽象模型中返回null会出现异常,因此这里返回一个长度为0的数组
        //后面将介绍IPropertyDescriptor数组
        return new IPropertyDescriptor[0];
    }
    @Override
    public Object getPropertyValue(Object id)
    {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public boolean isPropertySet(Object id)
    {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void resetPropertyValue(Object id)
    {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void setPropertyValue(Object id, Object value)
    {
        // TODO Auto-generated method stub
        
    }
}
