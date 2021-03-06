/**
 * 
 */
package com.ctlts.wfaas.util.consul;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import com.ctlts.wfaas.adapter.consul.ConsulAdapter;
import com.ctlts.wfaas.util.consul.Application.Command;
import com.ctlts.wfaas.util.consul.Application.ReadCommand;
import com.ctlts.wfaas.util.consul.Application.WriteCommand;

/**
 * @author mramach
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    @Mock
    private ConsulAdapter consulAdapter;
    @Mock
    private ApplicationContext ctx;
    @InjectMocks
	private Application app;
    @InjectMocks
    private ReadCommand readCommand;
    @InjectMocks
    private WriteCommand writeCommand;

    @Test
    public void testRead() throws Exception {
        
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(buf);
        System.setOut(out);
        
        when(ctx.getBean(eq("read"), eq(Command.class))).thenReturn(readCommand);
        when(consulAdapter.getProperties(eq("root"))).thenReturn(Collections.singletonMap("property", "value"));
        
        app.run("read", "root");

        assertEquals("Checking that the output buffer contains the expected output.", 
                String.format("property=value%s", System.getProperty("line.separator")), buf.toString());
        
    }
    
    @Test
    public void testWrite() throws Exception {
        
        when(ctx.getBean(eq("write"), eq(Command.class))).thenReturn(writeCommand);
        
        app.run("write", "src/test/resources/source.properties");

        verify(consulAdapter, atLeastOnce()).setProperty(eq("property"), eq("value"));
        
    }
	
}
