package net.vksn.ecm.dao.impl;

import net.vksn.ecm.dao.FileDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateFileDAO implements FileDao {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public byte[] getFile(String fileName) {
		Session session = factory.openSession();
		
		return null;
	}

}
