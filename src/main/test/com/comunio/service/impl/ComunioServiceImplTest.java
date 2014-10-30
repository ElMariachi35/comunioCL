package com.comunio.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.comunio.dao.ComunioDao;
import com.comunio.model.Comunio;
import com.comunio.service.GroupService;

@RunWith(MockitoJUnitRunner.class)
public class ComunioServiceImplTest {

    private static final String COMUNIO_NAME = "MyComunio";
    private static final String PASSWORD = "pass";

    private Comunio comunio = new Comunio();

    private ComunioServiceImpl comunioService = new ComunioServiceImpl();

    @Mock
    ComunioDao comunioDao;
    @Mock
    GroupService groupService;

    @Before
    public void setUp() {
        comunioService.comunioDao = comunioDao;
        comunioService.groupService = groupService;
    }

    @Test
    public void createComunioAddsComunioAndReturnsId() throws Exception {
        comunioService.createComunio(COMUNIO_NAME, PASSWORD);
        verify(comunioDao).add(any(Comunio.class));
    }

    @Test
    public void addComunioCallsCorrectMehtod() throws Exception {
        comunioService.add(comunio);
        verify(comunioDao).add(comunio);
    }
    
    @Test
    public void testName() throws Exception {
	for(int i=0;i<3;i++){
	    for(int j=1;j<4;j++){
		int result = (i+1)*(j+i+1)-(i+1);
	    }
	}
    }
}
