/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.org.zels.zelsystem.test;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mk.org.zels.entity.Strategy;

/**
 *
 * @author georgy
 */
@Stateless
public class TestActivityService {

    @PersistenceContext(unitName = "mk.org.zels_ZELSystem_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    public List<Strategy> allStrategies() {
        return em.createNamedQuery("Strategy.findAll", Strategy.class).getResultList();
    }

    
}
