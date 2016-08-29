package com.acme.edu.systemtest03;

import com.acme.edu.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private Logger logger;

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
        logger = new Logger(new ConsoleDecorator(), new SequenceDataProcessor(), new ConsoleWriter());
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldLogIntegersOneDimensionalArray() throws IOException {
        //region when
        logger.logIntArray(new int[] {-1, 0, 1});
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutEquals(
            "primitives array: {-1, 0, 1}"+System.lineSeparator()
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersTwoDimensionalArray() throws IOException {
        //region when
        logger.logIntArray(new int[][] {{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutEquals(
            "primitives matrix: {"+System.lineSeparator() +
                "{-1, 0, 1}"+System.lineSeparator() +
                "{1, 2, 3}"+System.lineSeparator() +
                "{-1, -2, -3}"+System.lineSeparator() +
            "}"+System.lineSeparator()
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersThreeDimensionalArray() throws IOException {
        //region when
        logger.logIntArray(new int[][][] {{{-1, 0}, {1, 2}, {-1, -2}}});
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutEquals(
                "primitives multimatrix: {"+System.lineSeparator() +
                        "{" + System.lineSeparator() +
                        "{-1, 0}"+System.lineSeparator() +
                        "{1, 2}"+System.lineSeparator() +
                        "{-1, -2}"+System.lineSeparator() +
                        "}" + System.lineSeparator() +
                        "}"+System.lineSeparator()
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersFourDimensionalArray() throws IOException {
        //region when
        logger.logIntArray(new int[][][][] {{{{0}}}});
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutEquals(
            "primitives multimatrix: {"+System.lineSeparator() +
                "{"+System.lineSeparator() + "{"+System.lineSeparator() + "{" +
                    "0"
                + "}"+System.lineSeparator() + "}"+System.lineSeparator() + "}"+System.lineSeparator() +
            "}"+System.lineSeparator()
        );
        //endregion
    }

    @Test
    public void shouldLogStringsWithOneMethodCall() throws IOException {
        //region when
        logger.log("str1", "string 2", "str 3");
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("str1");
        assertSysoutContains("string 2");
        assertSysoutContains("str 3");
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException {
        //region when
        logger.log(-1, 0, 1, 3);
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("3");
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
        //region when
        logger.log(1);
        logger.log("str");
        logger.log(Integer.MAX_VALUE - 10);
        logger.log(11);
        logger.stopLogging();
        //endregion

        //region then
        assertSysoutContains("1");
        assertSysoutContains("str");
        assertSysoutContains(""+(Integer.MAX_VALUE - 10));
        assertSysoutContains("11");
        //endregion
    }
}