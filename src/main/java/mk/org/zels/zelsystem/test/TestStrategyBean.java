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
import mk.org.zels.entity.Priority;
import mk.org.zels.entity.Strategy;

/**
 *
 * @author georgy
 */
@Named(value = "testStrategyBean")
@SessionScoped
public class TestStrategyBean implements Serializable {
    
    @Inject
    private StrategyFacade strategyFacade;
    
    public TestStrategyBean() {
    }
    
    public List<Strategy> getStrategies() {
        return strategyFacade.findAll();
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(strategyFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(strategyFacade.findAll(), true);
    }
    
    
    public Strategy getStrategy(Integer id) {
        return strategyFacade.find(id);
    }
    
    @FacesConverter(forClass = Strategy.class)
    public static class ActivityControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TestStrategyBean controller = (TestStrategyBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "testStrategyBean");
            return controller.getStrategy(getKey(value));
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
            if (object instanceof Strategy) {
                Strategy o = (Strategy) object;
                return getStringKey(o.getStrategyId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Strategy.class.getName());
            }
        }

    }
}
