package com.eatnumber1.jeocoder.hibernate;

import com.eatnumber1.jeocoder.CoordinatesImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author Russell Harmon
 * @since Nov 10, 2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-config.xml")
public class TestHibernateCoordinatesFactory {
	@NotNull
	private static Log log = LogFactory.getLog(TestHibernateCoordinatesFactory.class);

	@NotNull
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory( @NotNull SessionFactory sessionFactory ) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Test
	public void saveAndLoad() {
		HibernateCoordinatesFactory coordinatesFactory = new HibernateCoordinatesFactory(new CoordinatesImpl(10.0, 10.0));
		Serializable id = hibernateTemplate.save(coordinatesFactory);
		log.debug("Saved " + coordinatesFactory + " to database");
		HibernateCoordinatesFactory coordinates = (HibernateCoordinatesFactory) hibernateTemplate.get(HibernateCoordinatesFactory.class, id);
		log.debug("Read " + coordinates + " from database");
		Assert.assertThat(coordinatesFactory, is(coordinates));
	}
}
