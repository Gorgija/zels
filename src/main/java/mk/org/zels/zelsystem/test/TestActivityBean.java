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
@Named(value = "testActivityBean")
@SessionScoped
public class TestActivityBean implements Serializable {
    
    @Inject
    ActivityFacade activityFacade;
    
    boolean edit = false;
    Activity activity;
    
    public TestActivityBean() {
    }
    
    public void editActivity(Activity a) {
        edit = true;
        this.activity = a;
    }
    
    public List<Activity> getAllActivitys() {
        return activityFacade.findAll();
    }
    
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(activityFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(activityFacade.findAll(), true);
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    
    
//    public Activity getActivity(Object id) {
//        return activityFacade.find(id);
//    }
    
//    @FacesConverter(forClass = Activity.class)
//    public static class ActivityControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            TestActivityBean controller = (TestActivityBean) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "testActivityBean");
//            return controller.getActivity(getKey(value));
//        }
//
//        java.lang.Integer getKey(String value) {
//            java.lang.Integer key;
//            key = Integer.valueOf(value);
//            return key;
//        }
//
//        String getStringKey(java.lang.Integer value) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(value);
//            return sb.toString();
//        }
//
//        @Override
//        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//            if (object == null) {
//                return null;
//            }
//            if (object instanceof Activity) {
//                Activity o = (Activity) object;
//                return getStringKey(o.getActivityId());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Activity.class.getName());
//            }
//        }
//
//    }
    
}
