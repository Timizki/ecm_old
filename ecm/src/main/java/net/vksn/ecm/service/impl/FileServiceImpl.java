package net.vksn.ecm.service.impl;

import net.vksn.ecm.dao.FileDao;
import net.vksn.ecm.service.FileService;

public class FileServiceImpl implements FileService {

	private FileDao dao;
	@Override
	public byte[] getFile(String name) {
		
		return dao.getFile(name);
	}

}
