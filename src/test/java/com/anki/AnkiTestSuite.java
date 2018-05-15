package com.anki;

import com.anki.processors.CardsReaderTest;
import com.anki.processors.SaveRecoverGameTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author routarddev
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CardsReaderTest.class,
        SaveRecoverGameTest.class
})
public class AnkiTestSuite {
}