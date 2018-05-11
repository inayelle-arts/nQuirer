import com.inayelle.model.dao.DAOException;
import com.inayelle.model.dao.UserDAO;
import com.inayelle.model.entity.User;
import org.junit.Test;

public class Tester
{
	@Test
	public void testGetAll()
	{
		UserDAO dao = new UserDAO();
		try
		{
			var users = dao.getAll();
			for (var user : users)
				System.out.println(user);
		}
		catch (DAOException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetById()
	{
		UserDAO dao = new UserDAO();
		try
		{
			var user = dao.getById(2);
			System.out.println(user);
		}
		catch (DAOException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsert()
	{
		var user = new User(2, "keklol", "qwetrki", false);
		var dao = new UserDAO();
		try
		{
			dao.insert(user);
		}
		catch (DAOException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate()
	{
		var user = new User(2, "SOOQA", "qwetrki", false);
		var dao = new UserDAO();
		try
		{
			dao.update(user);
		}
		catch (DAOException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete()
	{
		var user = new User(2, "SOOQA", "qwetrki", false);
		var dao = new UserDAO();
		try
		{
			dao.delete(user);
		}
		catch (DAOException e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
