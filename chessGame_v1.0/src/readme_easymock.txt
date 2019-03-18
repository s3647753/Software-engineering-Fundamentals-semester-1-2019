
To mock Interfaces you only need easymock-xxx.jar
To mock Classes you also need cglib-xxx.jar and objenesis-xxx.jar


Add these to your Java build path
Project > Properties > Java Build Path > Add External Jars

example mocks

Mocking Classes
In the following example the ArrayList class is mocked.
import java.util.ArrayList;  
import static org.easymock.EasyMock.*;  

/**
 * This code is cut and paste from
 * http://2min2code.com/articles/easymock_intro/creating_mock_of_class
 */
  
public class MockingClass  
{  
    public static void main(String[] args)  
    {  
        ArrayList list = createMock(ArrayList.class);  
          
        expect(list.get(0)).andReturn("jwkus");  
          
        replay(list);  
          
        System.out.println(list.get(0));  
    }  
} 




// mocking an Interface almost identical code mocks a class
import java.util.List;  
import static org.easymock.EasyMock.*;  

/**
 * This code is cut and paste from
 * http://2min2code.com/articles/easymock_intro/creating_mock_of_interface
 *
 */
  
public class CreateMockOfInterface  
{  
    public static void main(String[] args)  
    {  
        List myList = createMock(List.class); 
        
        expect(myList.get(0)).andReturn("Mocked response");  
        
        replay(myList);  
          
        System.out.println(myList.get(0)); 
          
    }  
}  


The above are very simple yet effective examples of mocks
EasyMock can do a lot more if you are interested

Bernie
