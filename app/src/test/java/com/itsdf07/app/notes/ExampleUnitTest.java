package com.itsdf07.app.notes;

import com.itsdf07.module.common.Module2Common;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testModuleData() throws Exception {
        System.out.println(Module2Common.onTestModule2Common());
    }
}