/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.org.zels.zelsystem.test;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import mk.org.zels.entity.Activity;
import mk.org.zels.entity.StrategicActivity;

/**
 *
 * @author georgy
 */
@Named(value = "testStrategicActivityBean")
@SessionScoped
public class TestStrategicActivityBean implements Serializable {
    
    @Inject
    private StrategicActivityFacade strategicActivityFacade;
    
    boolean edit = false;
    StrategicActivity strategicActivity;

    public TestStrategicActivityBean() {
    }
    
    public List<StrategicActivity> getAllStrategicActivities() {
        return strategicActivityFacade.findAll();
    }
    
    public void  edit(StrategicActivity sa) {
        strategicActivity = sa;
        edit = true;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public StrategicActivity getStrategicActivity() {
        return strategicActivity;
    }

    public void setStrategicActivity(StrategicActivity strategicActivity) {
        this.strategicActivity = strategicActivity;
    }
    
    
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(strategicActivityFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(strategicActivityFacade.findAll(), true);
    }
    
    
    public StrategicActivity getStrategicActivity(Object id) {
        return strategicActivityFacade.find(id);
    }
    
    
        @FacesConverter(forClass = StrategicActivity.class)
    public static class ActivityControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TestStrategicActivityBean controller = (TestStrategicActivityBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "testStrategicActivityBean");
            return controller.getStrategicActivity(getKey(value));
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
            if (object instanceof StrategicActivity) {
                StrategicActivity o = (StrategicActivity) object;
                return getStringKey(o.getSaId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + StrategicActivity.class.getName());
            }
        }

    }
    
}
