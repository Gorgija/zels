/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.org.zels.zelsystem.test;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import mk.org.zels.entity.Activity;
import mk.org.zels.entity.Priority;

/**
 *
 * @author georgy
 */
@Named(value = "testPriorityBean")
@SessionScoped
public class TestPriorityBean implements Serializable {
    
    @Inject
    PriorityFacade priorityFacade;


    public TestPriorityBean() {
    }
    
    
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(priorityFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(priorityFacade.findAll(), true);
    }
    
    public Priority getPriority(Integer id) {
        return priorityFacade.find(id);
    }
    
    @FacesConverter(forClass = Priority.class)
    public static class ActivityControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TestPriorityBean controller = (TestPriorityBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "testPriorityBean");
            return controller.getPriority(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Priority) {
                Priority o = (Priority) object;
                return getStringKey(o.getPriorityId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Priority.class.getName());
            }
        }

    }
}
