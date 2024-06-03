import adts.ICollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * These test verify a HashTable structure using
 * separate chaining.
 *
 * @author Josh Archer
 * @version 1.0
 */
public class HashTableTest
{
    private ICollection<String> table;
    private String[] testValues = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"};

    /**
     * Creates a new empty table.
     */
    @BeforeEach
    public void setup()
    {
        table = getTable();
    }

    //instantiate an instance of your hashtable as the return value here...
    private ICollection<String> getTable()
    {
        return null;
    }

    //instantiate an instance of your hashtable as the return value here...
    private ICollection<TestClass> getTableTestClass()
    {
        return null;
    }

    private void addTestElements()
    {
        //add a few elements
        for (String element : testValues)
        {
            table.add(element);
        }
    }

    /**
     * Verifies that elements can be added and detected
     * in the table.
     */
    @Test
    public void addTest()
    {
        addTestElements();

        //testValues the size
        assertEquals(testValues.length, table.size(), "size() is incorrect after using add()");

        //verify each element is in the table
        for (String element : testValues)
        {
            assertTrue(table.contains(element), "element is missing after adding to hash table");
        }
    }

    /**
     * Checks whether duplicates are rejected by the hash table.
     */
    @Test
    public void addDuplicatesTest()
    {
        addTestElements();

        for (String element : testValues)
        {
            assertThrows(IllegalArgumentException.class, () -> table.add(element));
        }
    }

    /**
     * Tests the removal of existing elements in the table.
     */
    @Test
    public void removeTest()
    {
        addTestElements();

        //remove items that should be in the table
        List<String> removedElements = List.of("a", "m", "z");
        for (String element : removedElements)
        {
            table.remove(element);
        }

        //verify that they are removed
        assertEquals(testValues.length - 3, table.size(), "size() is incorrect after calling remove() 3 times");
        for (int i = 0; i < testValues.length; i++)
        {
            if (!removedElements.contains(testValues[i]))
            {
                assertTrue(table.contains(testValues[i]),
                    "element is missing after removing another element: " + testValues[i]);
            }
            else
            {
                assertFalse(table.contains(testValues[i]),
                    "element removed is still inside the table: " + testValues[i]);
            }
        }
    }

    /**
     * Tests that missing elements throw the expected exception.
     */
    @Test
    public void removeMissingElementTest()
    {
        addTestElements();
        assertThrows(NoSuchElementException.class, () -> table.remove("!"));
    }

    /**
     * Tests whether existing elements can be found in the table.
     */
    @Test
    public void containsExistsTest()
    {
        addTestElements();

        //look for present elements
        for (String element : testValues)
        {
            assertTrue(table.contains(element), "table does not contain elements that have been added");
        }
    }

    /**
     * Tests whether missing elements can be found in the table.
     */
    @Test
    public void containsMissingTest()
    {
        addTestElements();

        //look for missing elements
        assertEquals(testValues.length, table.size(), "size() is incorrect after adding elements");
        assertFalse(table.contains("!"), "table should not report missing elements as present in the table");
    }

    /**
     * Verifies that size() and isEmpty() report correctly with an empty table
     */
    @Test
    public void emptyTableTest()
    {
        assertTrue(table.isEmpty(), "table should be empty at first");
        assertEquals(0, table.size(), "table should have size zero with no elements");
    }

    /**
     * Verifies that size() and isEmpty() report correct as a table changes.
     */
    @Test
    public void resizingTableTest()
    {
        //add elements and see if size changes
        for (int i = 0; i < testValues.length; i++)
        {
            table.add(testValues[i]);
            assertEquals(i + 1, table.size(), "size() incorrect after calling add");
        }

        //remove elements and see if size changes
        for (int i = testValues.length - 1; i >= 0; i--)
        {
            table.remove(testValues[i]);
            assertEquals(i, table.size(), "size() incorrect after calling add");
        }
    }

    /**
     * Verifies that clear() actually removes all elements from the table
     */
    @Test
    public void clearTest()
    {
        addTestElements();
        table.clear();

        //the table should be empty now
        assertTrue(table.isEmpty(), "table should be empty after calling clear()");
        assertEquals(0, table.size(), "table should have size zero after calling clear()");

        //no elements should be present
        for (int i = 0; i < testValues.length; i++)
        {
            assertFalse(table.contains(testValues[i]), "table is reporting elements after clear() is called");
        }
    }

    /**
     * Verifies that an element can be retrieved from the table
     * using the get() method.
     */
    @Test
    public void getExistingTest()
    {
        //add a table with an element
        ICollection<TestClass> testTable = getTableTestClass();
        TestClass testElement = new TestClass(1, 2);
        testTable.add(testElement);

        assertEquals(testElement, testTable.get(testElement), "Element cannot be found with the get() method");
    }

    /**
     * Verifies that a missing element will return null when given to the get() method.
     */
    @Test
    public void getMissingTest()
    {
        //add a table without an element
        ICollection<TestClass> testTable = getTableTestClass();
        TestClass testElement = new TestClass(1, 2);
        TestClass otherElement = new TestClass(3, 4);

        testTable.add(otherElement);

        assertEquals(1, testTable.size(), "size() is incorrect after adding an element");
        assertNull(testTable.get(testElement), "get() does not return null for a missing element");
    }

    /**
     * Verifies that using the get() method will not just return
     * the input parameter reference.
     */
    @Test
    public void getNoReferenceTest()
    {
        //add a table
        ICollection<TestClass> testTable = getTableTestClass();

        //two elements that are equal according to equals()
        TestClass firstElement = new TestClass(1, 2);
        TestClass secondElement = new TestClass(1, 4);

        //first element is in the table, not second
        testTable.add(firstElement);

        assertNotNull(testTable.get(secondElement));
        assertNotSame(secondElement, testTable.get(secondElement),
                "Element in table is not returned when calling the get() method");
    }

    /**
     * Verifies that a new iterator can be retrieved on an empty table
     */
    @Test
    public void emptyTableIteratorTest()
    {
        //do we have an iterator?
        assertNotNull(table.iterator(), "iterator is returned null with no elements in the table");
    }

    /**
     * Verifies that all elements in the table can be returned (in any order).
     */
    @Test
    public void iteratorTest()
    {
        addTestElements();

        //store an array of flags - true: found, false: not-found
        boolean[] found = new boolean[testValues.length];
        for (String element : table)
        {
            //find the element, mark it as found
            for (int i = 0; i < testValues.length; i++)
            {
                if (testValues[i].equals(element))
                {
                    if (found[i])
                    {
                        fail("duplicate element found with iterator: " + element);
                    }
                    else
                    {
                        found[i] = true;
                    }
                }
            }
        }

        //check that each was found
        for (int i = 0; i < found.length; i++)
        {
            if (!found[i])
            {
                fail("element not found with iterator: " + testValues[i]);
            }
        }
    }

    /**
     * Verifies that you cannot call the add() method while using an iterator.
     */
    @Test
    public void concurrentAddTest()
    {
        addTestElements();

        try
        {
            for (String element : table)
            {
                table.add("!");
                assert element != null;
            }
            fail("ConcurrentModificationException not thrown while using add() concurrently with an iterator.");
        }
        catch (ConcurrentModificationException ex)
        {
            assert true; //expected
        }
    }

    /**
     * Verifies that you cannot call the remove() method while using an iterator.
     */
    @Test
    public void concurrentRemoveTest()
    {
        addTestElements();

        try
        {
            for (String element : table)
            {
                table.remove("e");
                assert element != null;
            }
            fail("ConcurrentModificationException not thrown while using remove() concurrently with an iterator.");
        }
        catch (ConcurrentModificationException ex)
        {
            assert true; //expected
        }
    }

    /**
     * Verifies that you cannot call the clear() method while using an iterator.
     */
    @Test
    public void concurrentClearTest()
    {
        addTestElements();

        try
        {
            for (String element : table)
            {
                table.clear();
                assert element != null;
            }
            fail("ConcurrentModificationException not thrown while using clear() concurrently with an iterator.");
        }
        catch (ConcurrentModificationException ex)
        {
            assert true; //expected
        }
    }

    private static class TestClass
    {
        private int identifier;
        private int value;

        public TestClass(int identifier, int value)
        {
            this.identifier = identifier;
            this.value = value;
        }

        @Override
        public boolean equals(Object other)
        {
            if (this == other)
            {
                return true;
            }

            if (other == null || getClass() != other.getClass())
            {
                return false;
            }

            TestClass testClass = (TestClass) other;
            return identifier == testClass.identifier;
        }

        @Override
        public int hashCode()
        {
            return identifier;
        }

        @Override
        public String toString()
        {
            return "TestClass{" +
                    "identifier=" + identifier +
                    ", value=" + value +
                    '}';
        }
    }
}
