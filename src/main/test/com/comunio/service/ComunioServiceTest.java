package com.comunio.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.comunio.dao.ComunioDao;
import com.comunio.model.Comunio;
import com.comunio.service.impl.ComunioServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ComunioServiceTest {
	
	private static final long COMUNIO_ID = 1l;
	private static final String PASSWORD = "pass";
	private static final String COMUNIO_NAME = "Test";

	private ComunioServiceImpl comunioService = new ComunioServiceImpl();
	
	@Mock
	private ComunioDao comunioDao;
	
	@Before
	public void setUp(){
		comunioService.setComunioDao(comunioDao);
	}
	
	@Test
	public void createComunioCreatesANewComunio() throws Exception {
		comunioService.createComunio(COMUNIO_NAME, PASSWORD);
		verify(comunioDao).add(any(Comunio.class));
	}
}
