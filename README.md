# Individual Assignment: Hash Tables Part #1   

## Hash Tables Part #1  
For this assignment you will building a basic hash  
table using separate chaining. In separate chaining  
tables collisions are managed by storing a linked  
list at each index of the table. Elements that collide  
in the table are appended to the list for that index,  
providing a simple mechanic to resolve collisions.  
Care must be taken to choose a proper load factor  
for the table otherwise each list will grow large  
enough to reduce the efficiency of the table.  

## Objectives  
### Course  
• Develop a set or map data structure based on  
hash tables or binary search trees and  
implement basic set operations.  
### Module  
• To use hash codes to quickly identify positions in a data structure for new or existing elements.  
• To resolve collisions in a data structure using either separate chaining or open addressing.  
• To write an iterator over a complex structure.  

## Separate Chaining  
As described above, a separate chaining table maintains a linked list at each position in the hash table.  
When an element is added to the table the hash code is first used to find a position in the table. This  
position never changes unlike open addressing algorithms. Instead each element that is placed to that  
position, including collisions, is added to the linked list at the position. This list grows to accommodate  
new elements.  
After adding the following elements with hash  
codes:  

C (23), B (111), A (17), D (3)  
After adding more elements:   
F (99), E (1), H (55), G (21), A (17)  

When removing elements from the table, a position is identified first through a hash code and then a  
search is made through the linked list at that position (if there is any elements). A similar routine is used  
to search for elements in the table (the contains() method). For example, trying to remove element G  
(21) would result in the following operation:  
Writing an iterator for this structure can be particularly challenging. The iterator should return the  
elements of the table from top-to-bottom. For example, given the table above iterator should return  
elements in the following order: B, E, G, C, D, H, A, F. Notice how the iterator must traverse linked lists  
when present and avoid missing lists (null pointers).  

## Writing the Hash Table  
Your job is to write the hash table described above. Your HashTable class must use the following  
interface: ICollection.java (https://egator.greenriver.edu/courses/2466854/files/245547834?wrap=1)   
(https://egator.greenriver.edu/courses/2466854/files/245547834/download?download_frd=1) (https://  
egator.greenriver.edu/courses/2466854/files/245547998?wrap=1) .  Below is a list of methods for the class  
and their Javadoc descriptions:  
Method  
Javadoc  
public void add(T element)  
/**
* Adds an element to the collection. No specific ordering
* is required. After the load factor has exceeded 250% the
* table should rehash all elements into a table that is 50%
* larger than the previous table size.
*
* @throws IllegalArgumentException thrown when a duplicate
* element is added to the table
* @param element the new element to put in the collection  
  */  
  public void remove(T element)  
  /**
* Finds and removes an element from the collection.
*
* @throws java.util.NoSuchElementException thrown when  
  the
* element is not found in the collection
* @param element the element to remove  
  */  
  public boolean contains(T  
  element)  
  /**
* Reports whether the collection contains an element.
*
* @param element the element to search for.
* @return true if the element is found, otherwise false  
  */  
  public int size()  
  /**
* Returns the number of elements in the collection.
*
* @return the number of elements  
  Individual Assignment: Hash Tables Part #1  
  https://egator.greenriver.edu/courses/2466854/assignments/3301860


*/  
public boolean isEmpty()  
/**
* Reports whether the collection is empty or not.
*
* @return true if the collection is empty, otherwise false  
  */  
  public void clear()  
  /**
* Removes all elements from the collection.  
  */  
  public T get(T element)  
  /**
* Returns an element in the collection that matches the
* input parameter according the equals() method of the
* parameter.
*
* Note: This method should not return a reference
* to the input parameter, but should instead search for the
* matching element in the table and return it. This method
* will be used directly in part #2 of the assignment.
*
* @param element an element to search for
* @return a matching element, or null if none are found  
  */  
  public Iterator<T> iterator()  
  /**
* Returns an iterator over the collection.
*
* @return an object using the Iterator<T> interface  
  */  

## Unit Tests  
Here is a group of unit tests that you can use to verify that your hash table is working correctly:  
HashTableTest.java (https://egator.greenriver.edu/courses/2466854/files/245547868?wrap=1)   
(https://egator.greenriver.edu/courses/2466854/files/245547868/download?download_frd=1) . The tests assume  
your class is called HashTable. I will use these tests when grading your work.  


## Expectation: Working Directly With Linked List Nodes  
Part of the learning experience on this assignment is using a non-traditional implementation of a Linked  
List. Our goal is to avoid relying directly upon pre-built classes, like the LinkedList class in the java.util  
package and to instead work with our own Node objects directly. This includes any Linked List classes  
that you have built as a student. This has the added effect of avoiding using the methods in a LinkedList  
class without being aware of their runtime cost. By writing the loops that manipulate Node objects  
directly inside of your HashTable class, you will be able to write these code segments as efficient as  
possible.  
Here is an example of how to do this correctly:  
The "wrong" way  
The "right" way  

## Requirements  
Any submissions ignoring the following requirements will be forced to resubmit their work.  
• Your hash table class is required to be a generic class.   
• Your hash table class must use the ICollection<T> interface. Your class must use the generic type of  
the ICollection<T> interface.  
• Your iterator should be a private inner classes. It should not be visible outside of your HashTable<T>  
class.  
• You must use separate chaining to resolve collisions in the hash table.  
    