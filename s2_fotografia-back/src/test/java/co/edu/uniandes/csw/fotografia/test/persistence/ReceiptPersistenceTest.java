/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.fotografia.test.persistence;

import co.edu.uniandes.csw.fotografia.entities.ReceiptEntity;
import co.edu.uniandes.csw.fotografia.persistence.ReceiptPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Valentina Duarte
 */

@RunWith (Arquillian.class)
public class ReceiptPersistenceTest 
{
    @Inject
    private ReceiptPersistence rp; 
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReceiptEntity.class.getPackage())
                .addPackage(ReceiptPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
        @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ReceiptEntity newEntity = factory.manufacturePojo(ReceiptEntity.class);
        ReceiptEntity result = rp.create(newEntity);
        Assert.assertNotNull(result);
        
        ReceiptEntity entity = em.find(ReceiptEntity.class, result.getId());
        Assert.assertEquals(newEntity.getNumero(), entity.getNumero());
        
    }
}
