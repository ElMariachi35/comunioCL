package com.comunio.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    private static final long COMUNIO_ID = 1l;
    private static final String COMUNIO_NAME = "MyComunio";
    private static final String PASSWORD = "pass";

    private Comunio comunio = new Comunio();
    
    private ComunioServiceImpl comunioService = new ComunioServiceImpl();
    
    @Mock
    ComunioDao comunioDao;
    @Mock
    GroupService groupService;
    
    @Before
    public void setUp(){
        comunioService.comunioDao = comunioDao;
        comunioService.groupService = groupService;
    }
    
    @Test
    public void createComunioAddsComunioAndReturnsId() throws Exception {
        comunioService.createComunio(COMUNIO_NAME, PASSWORD);
        verify(comunioDao).add(any(Comunio.class));
    }
    
    @Test
    public void getComunioReturnsCorrectComunio() throws Exception {
        Comunio comunio = new Comunio();
        comunio.setComunioId(COMUNIO_ID);
        when(comunioDao.getComunio(COMUNIO_ID)).thenReturn(comunio);
        assertEquals(COMUNIO_ID, comunioService.getComunio(COMUNIO_ID).getComunioId());
    }
    
    @Test
    public void addComunioCallsCorrectMehtod() throws Exception {
        comunioService.add(comunio);
        verify(comunioDao).add(comunio);
    }
}
