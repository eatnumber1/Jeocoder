package com.eatnumber1.jeocoder.hibernate;

import com.eatnumber1.jeocoder.UsFormatAddress;
import com.eatnumber1.jeocoder.UsFormatAddressImpl;
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
public class TestHibernateUsFormatAddressFactory {
	@NotNull
	private static Log log = LogFactory.getLog(TestHibernateUsFormatAddressFactory.class);

	@NotNull
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory( @NotNull SessionFactory sessionFactory ) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Test
	public void saveAndLoad() {
		UsFormatAddress usFormatAddress = new UsFormatAddressImpl("CA", "Mountain View", null, null, "Amphitheatre Parkway", "1600");
		HibernateUsFormatAddressFactory factory = new HibernateUsFormatAddressFactory(usFormatAddress);
		Serializable id = hibernateTemplate.save(factory);
		log.debug("Saved " + factory + " to database");
		HibernateUsFormatAddressFactory savedFactory = (HibernateUsFormatAddressFactory) hibernateTemplate.get(HibernateUsFormatAddressFactory.class, id);
		log.debug("Read " + savedFactory + " from database");
		Assert.assertThat(usFormatAddress, is(savedFactory.produce()));
	}
}
