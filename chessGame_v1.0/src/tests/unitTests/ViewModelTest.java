package tests.unitTests;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.GameEngineImpl;
import model_Interfaces.GameEngine;
import view.gui.OperationCancelledException;
import view.model.ViewModel;
import view.text.TextView;
import view_interfaces.View;
import view_interfaces.ViewType;

public class ViewModelTest {
   private GameEngine engine;
   private ViewType viewType;
   private View viewModel;

   @Before
   public void setUp() throws Exception {
      engine = createMock(GameEngineImpl.class);
      viewType = createMock(TextView.class);
      
      viewModel = new ViewModel(engine, viewType);
      
   }

   // TODO this is work in progress
   @Test
   public void testRegisterPlayer() throws OperationCancelledException {
      String name = "John";
      String pw = "1234";
      String message = "Success";
      
      expect(engine.register(name, pw)).andReturn(message);
      replay(engine);
      
//      expect(viewType.registerPlayer()).andReturn(new String[]{name, pw}); 
//      replay(viewType);
      
      assertEquals(message, viewModel.registerPlayer());
      
//      System.out.println(engine.register(name, pw));
//      System.out.println(viewType.registerPlayer()[0]);


      System.out.println(viewModel.registerPlayer());
      
   }

}
