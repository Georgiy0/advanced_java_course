package ru.mail.LFUtests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.mail.LFU.LFUcache;

@RunWith(value = Parameterized.class)
public class generalTests {

    private int size;
    public LFUcache<Integer, Integer> testCache = new LFUcache<>(size);

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(final Description description) {
            System.err.println("=== Running " + description.getMethodName());
        }
    };


    public generalTests(int size){
        this.size = size;
    }

    @Before
    public void set(){
        testCache = new LFUcache<>(size);
        testCache.clear();
    }

    @Parameterized.Parameters(name = "{index}")
    public static Collection<Object[]> generateData(){

        return Arrays.asList(new Object[][]{
                {2},
                {100},
        });
    }

    // работает ли
    @Test
    public void test_basic() {
        testCache.put(0, 100500);
        Assert.assertTrue(testCache.get(0) == 100500);
    }

    // проверка соблюдения ограниченности размера кэша
    @Test
    public void test_clearing() {
        for(int i = 0; i < size; i++){
            Assert.assertTrue(testCache.put(i, i) == null);
        }
        Assert.assertTrue(testCache.put(size+1, size+1)==0);
    }

    // тестирование удаления LFU элемента
    @Test
    public void test_LFU_find() {
        for(int i = 0; i < size; i++){
            Assert.assertTrue(testCache.put(i, i) == null);
        }
        for(int i = 0; i<10; i++) testCache.get(0);
        Assert.assertTrue(testCache.put(size+1, size+1) == 1
                && testCache.get(0) != null);
    }

    // Тестирование удаления элемента при одинаковой частоте использования
    @Test
    public void test_equal_frequency() {
        for(int i = 0; i < size; i++){
            Assert.assertTrue(testCache.put(i, i) == null);
            testCache.get(i);
            testCache.get(i);
        }
        Assert.assertTrue(testCache.put(size+1, size+1) == 0);
        Assert.assertTrue(testCache.get(0) == null
                && testCache.get(1) == 1);
    }
}