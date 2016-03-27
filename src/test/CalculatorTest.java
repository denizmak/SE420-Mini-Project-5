package test;

import static org.junit.Assert.*;

//import org.junit.After;
//import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import app.CommissionCalculator;
import app.SalesTransaction;
import app.iCommissionCalculator;

/**
 * This class tests methods in CommissionCalculator and SalesTransaction classes. 
 * Also, constants from iCommissionCalculator are tested as well.
 * @author DZ & FA
 *
 */
public class CalculatorTest 
{
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{	
		System.out.println("Starting testing! :/");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
		System.out.println("Testing Finished! :)");
	}

	/*
	@Before
	public void setUp() throws Exception 
	{
		System.out.println("Before single test");
	}
	@After
	public void tearDown() throws Exception 
	{
		System.out.println("After single test");
	}
	*/
	
	
	//--------------------------------------------- F A R E S ----------------------------------------------------------------------
	
	/**
	 * This tests if SalesTransaction object is created with valid input of Transaction types
	 * @throws Exception
	 */
	@Test
	public void testingSalesTransactionTypes() throws Exception 
	{
		//create SalesTransaction object with valid input
		SalesTransaction stM =  new SalesTransaction(iCommissionCalculator.MAINTENANCE_ITEM, 22.22);
		SalesTransaction stB =  new SalesTransaction(iCommissionCalculator.BASIC_ITEM, 22.22);
		SalesTransaction stR =  new SalesTransaction(iCommissionCalculator.REPLACEMNET_ITEM, 22.22);
		
		//check if methods return proper transaction type
		assertEquals(1,stM.getTransactionType());
		assertEquals(0,stB.getTransactionType());
		assertEquals(2,stR.getTransactionType());
		
	}
	
	/**
	 * This test checks if commission is properly calculated for Experienced level seller
	 * when minimum sales is not reached
	 * @throws Exception
	 */
	@Test
	public void testingExperiencedExperienceCommissionBelowMinimum() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Fares", iCommissionCalculator.EXPERIENCED);
		
		//add sales to cc object to be below minimum required for commission, below 5000
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 4000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 999.00);
		
		//test if minimum sale constant is correct for probationary level commission
		assertEquals(5000.00, cc.getMinimumSales(), 0.01);
		
		//check if proper employee name is saved
		assertEquals("Fares", cc.getName());
		
		//check if sales are adding correctly
		assertEquals(4999.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals(0.00, cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals(0.00, cc.calculateBonusCommission(), 0.01);
	}
	
	/**
	 * This test checks if commission is properly calculated for Experienced level seller
	 * when minimum sales is matched exactly
	 * @throws Exception
	 */
	@Test
	public void testingExperiencedExperienceCommissionMinimum() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Fares", iCommissionCalculator.EXPERIENCED);
		
		//add sales to cc object to be minimum required for commission which is 5000
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 4000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 1000.00);
		
		//check if sales are adding correctly
		assertEquals(5000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals(0.00, cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals(0.00, cc.calculateBonusCommission(), 0.01);
	}
	
	/**
	 * This test checks if commission is properly calculated for Experienced level seller
	 * and for basic item
	 * @throws Exception
	 */
	@Test
	public void testingExperiencedExperienceCommissionBasic() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Fares", iCommissionCalculator.EXPERIENCED);
		
		//add sales to cc object to be 1500 over the minimum required for experienced
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 5000.00);
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 1500.00);
		
		//check if sales are adding correctly
		assertEquals(6500.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((1500 * 0.04), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals(0.00, cc.calculateBonusCommission(), 0.01);
	}
	
	/**
	 * This test checks if commission is properly calculated for Experienced level seller
	 * and for replacement item
	 * @throws Exception
	 */
	@Test
	public void testingExperiencedExperienceCommissionReplacement() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Fares", iCommissionCalculator.EXPERIENCED);
		
		//add sales to cc object to be below minimum required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 5000.00);
		cc.addSale(iCommissionCalculator.REPLACEMNET_ITEM, 1000.00);
		
		//check if sales are adding correctly
		assertEquals(6000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((1000 * 0.015), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals(0.00, cc.calculateBonusCommission(), 0.01);
	}
	
	/**
	 * This test checks if commission is properly calculated for Experienced level seller
	 * and for maintenance item
	 * @throws Exception
	 */
	@Test
	public void testingExperiencedExperienceCommissionMaintenance() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Fares", iCommissionCalculator.EXPERIENCED);
		
		//add sales to cc object to be below minimum required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 5000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 10000.00);
		
		//check if sales are adding correctly
		assertEquals(15000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((10000 * 0.06), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals(0.00, cc.calculateBonusCommission(), 0.01);
	}
	
	/**
	 * This test checks if commission is properly calculated for Experienced level seller
	 * and for consulting services
	 * @throws Exception
	 */
	@Test
	public void testingExperiencedExperienceCommissionConsulting() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Fares", iCommissionCalculator.EXPERIENCED);
		
		//add sales to cc object to be above 5000 required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 5000.00);
		cc.addSale(iCommissionCalculator.CONSULTING_ITEM, 10000.00);
		
		//check if sales are adding correctly
		assertEquals(15000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((10000 * 0.08), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals(0.00, cc.calculateBonusCommission(), 0.01);
	}
	
	
	/**
	 * This test checks if bonus commission is properly calculated for Experienced level seller
	 * and for basic item
	 * @throws Exception
	 */
	@Test
	public void testingExperiencedExperienceBonusCommissionBasic() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Fares", iCommissionCalculator.EXPERIENCED);
		
		//add sales to cc object to be above 5000 required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 5000.00);
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 100000.00);
		
		//check if sales are adding correctly
		assertEquals(105000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((100000 * 0.04), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals((5000 * 0.015), cc.calculateBonusCommission(), 0.01);
	}
	
	/**
	 * This test checks if bonus commission is properly calculated for Experienced level seller
	 * and for maintenance item
	 * @throws Exception
	 */
	@Test
	public void testingExperiencedExperienceBonusCommissionMaintenance() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Fares", iCommissionCalculator.EXPERIENCED);
		
		//add sales to cc object to be above required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 5000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 100000.00);
		
		//check if sales are adding correctly
		assertEquals(105000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((100000 * 0.06), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals((5000 * 0.015), cc.calculateBonusCommission(), 0.01);
	}
	
	
	/**
	 * This test checks if bonus commission is properly calculated for Experienced level seller
	 * and for replacement item
	 * @throws Exception
	 */
	@Test
	public void testingExperiencedExperienceBonusCommissionReplacement() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Fares", iCommissionCalculator.EXPERIENCED);
		
		//add sales to cc object to be above 5000 required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 5000.00);
		cc.addSale(iCommissionCalculator.REPLACEMNET_ITEM, 200000.00);
		
		//check if sales are adding correctly
		assertEquals(205000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((200000 * 0.015), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals((105000 * 0.015), cc.calculateBonusCommission(), 0.01);
	}
	
	
	/**
	 * This test checks if bonus commission is properly calculated for Experienced level seller
	 * and for consulting services
	 * @throws Exception
	 */
	@Test
	public void testingExperiencedExperienceBonusCommissionConsulting() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Fares", iCommissionCalculator.EXPERIENCED);
		
		//add sales to cc object to be above 5000 required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 5000.00);
		cc.addSale(iCommissionCalculator.CONSULTING_ITEM, 400000.00);
		cc.addSale(iCommissionCalculator.CONSULTING_ITEM, 50000.00);
		cc.addSale(iCommissionCalculator.CONSULTING_ITEM, 50000.00);
		
		//check if sales are adding correctly
		assertEquals(505000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((500000 * 0.08), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals((405000 * 0.015), cc.calculateBonusCommission(), 0.01);
	}
	
	
	//----------------------------------------------------- D E N I -------------------------------------------------------
	/**
	 * This test checks if Exception is thrown when user tries to create SalesTransaction object 
	 * with sales amount less than zero (negative number)
	 * @throws Exception
	 */
	@Test (expected = Exception.class)
	public void testingSalesTransactionException() throws Exception 
	{
		//create SalesTransaction object with sales amount less than 0 (Should throw exception)
		SalesTransaction st =  new SalesTransaction(iCommissionCalculator.BASIC_ITEM, -2);
	}
	
	/**
	 * This test checks if Exception is thrown when user tries to create SalesTransaction object 
	 * with invalid transaction type (not between 0 and 3)
	 * @throws Exception
	 */
	@Test (expected = Exception.class)
	public void testingSalesTransactionException2() throws Exception 
	{
		//create SalesTransaction object with invalid transaction type (Should throw exception)
		SalesTransaction st =  new SalesTransaction(11, 22.22);
	}
	
	/**
	 * This test checks if SalesTransaction object is properly created with valid input
	 * @throws Exception
	 */
	@Test
	public void testingSalesTransactionConstructor() throws Exception 
	{
		//create SalesTransaction object with valid input
		SalesTransaction st =  new SalesTransaction(iCommissionCalculator.REPLACEMNET_ITEM, 22.22);
		
		//check if methods return proper transaction amount and type
		assertEquals(st.getTransactionAmount(),22.22, 0.01);
		assertEquals(2,st.getTransactionType());
	}
	
	/**
	 * This test checks if commission is properly calculated for probationary level seller
	 * when minimum sales is not reached
	 * @throws Exception
	 */
	@Test
	public void testingProbationaryExperienceCommissionBelowMinimum() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Deni", iCommissionCalculator.PROBATIONARY);
		
		//add sales to cc object to be below minimum required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 999.00);
		
		//test if minimum sale constant is correct for probationary level commission
		assertEquals(2000.00, cc.getMinimumSales(), 0.01);
		
		//check if proper employee name is saved
		assertEquals("Deni", cc.getName());
		
		//check if sales are adding correctly
		assertEquals(1999.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals(0.00, cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals(0.00, cc.calculateBonusCommission(), 0.01);
	}
	
	/**
	 * This test checks if commission is properly calculated for probationary level seller
	 * when minimum sales is matched exactly
	 * @throws Exception
	 */
	@Test
	public void testingProbationaryExperienceCommissionMinimum() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Deni", iCommissionCalculator.PROBATIONARY);
		
		//add sales to cc object to be below minimum required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 1000.00);
		
		//check if sales are adding correctly
		assertEquals(2000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals(0.00, cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals(0.00, cc.calculateBonusCommission(), 0.01);
	}
	
	/**
	 * This test checks if commission is properly calculated for probationary level seller
	 * and for basic item
	 * @throws Exception
	 */
	@Test
	public void testingProbationaryExperienceCommissionBasic() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Deni", iCommissionCalculator.PROBATIONARY);
		
		//add sales to cc object to be below minimum required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 1000.00);
		
		//check if sales are adding correctly
		assertEquals(3000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((1000 * 0.02), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals(0.00, cc.calculateBonusCommission(), 0.01);
	}
	
	
	/**
	 * This test checks if commission is properly calculated for probationary level seller
	 * and for maintenance item
	 * @throws Exception
	 */
	@Test
	public void testingProbationaryExperienceCommissionMaintenance() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Deni", iCommissionCalculator.PROBATIONARY);
		
		//add sales to cc object to be below minimum required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 1000.00);
		
		//check if sales are adding correctly
		assertEquals(3000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((1000 * 0.03), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals(0.00, cc.calculateBonusCommission(), 0.01);
	}
	
	
	/**
	 * This test checks if commission is properly calculated for probationary level seller
	 * and for replacement item
	 * @throws Exception
	 */
	@Test
	public void testingProbationaryExperienceCommissionReplacement() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Deni", iCommissionCalculator.PROBATIONARY);
		
		//add sales to cc object to be below minimum required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.REPLACEMNET_ITEM, 1000.00);
		
		//check if sales are adding correctly
		assertEquals(3000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((1000 * 0.01), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals(0.00, cc.calculateBonusCommission(), 0.01);
	}
	
	
	/**
	 * This test checks if commission is properly calculated for probationary level seller
	 * and for consulting services
	 * @throws Exception
	 */
	@Test
	public void testingProbationaryExperienceCommissionConsulting() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Deni", iCommissionCalculator.PROBATIONARY);
		
		//add sales to cc object to be below minimum required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.CONSULTING_ITEM, 1000.00);
		
		//check if sales are adding correctly
		assertEquals(3000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((1000 * 0.03), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals(0.00, cc.calculateBonusCommission(), 0.01);
	}
	
	
	/**
	 * This test checks if bonus commission is properly calculated for probationary level seller
	 * and for basic item
	 * @throws Exception
	 */
	@Test
	public void testingProbationaryExperienceBonusCommissionBasic() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Deni", iCommissionCalculator.PROBATIONARY);
		
		//add sales to cc object to be below minimum required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 58000.00);
		
		//check if sales are adding correctly
		assertEquals(60000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((58000 * 0.02), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals((10000 * 0.005), cc.calculateBonusCommission(), 0.01);
	}
	
	
	/**
	 * This test checks if bonus commission is properly calculated for probationary level seller
	 * and for maintenance item
	 * @throws Exception
	 */
	@Test
	public void testingProbationaryExperienceBonusCommissionMaintenance() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Deni", iCommissionCalculator.PROBATIONARY);
		
		//add sales to cc object to be below minimum required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 68000.00);
		
		//check if sales are adding correctly
		assertEquals(70000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((68000 * 0.03), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals((20000 * 0.005), cc.calculateBonusCommission(), 0.01);
	}
	
	
	/**
	 * This test checks if bonus commission is properly calculated for probationary level seller
	 * and for replacement item
	 * @throws Exception
	 */
	@Test
	public void testingProbationaryExperienceBonusCommissionReplacement() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Deni", iCommissionCalculator.PROBATIONARY);
		
		//add sales to cc object to be below minimum required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.MAINTENANCE_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.REPLACEMNET_ITEM, 78000.00);
		
		//check if sales are adding correctly
		assertEquals(80000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((78000 * 0.01), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals((30000 * 0.005), cc.calculateBonusCommission(), 0.01);
	}
	
	
	/**
	 * This test checks if bonus commission is properly calculated for probationary level seller
	 * and for consulting services
	 * @throws Exception
	 */
	@Test
	public void testingProbationaryExperienceBonusCommissionConsulting() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Deni", iCommissionCalculator.PROBATIONARY);
		
		//add sales to cc object to be below minimum required for commission
		cc.addSale(iCommissionCalculator.BASIC_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.CONSULTING_ITEM, 1000.00);
		cc.addSale(iCommissionCalculator.CONSULTING_ITEM, 48000.00);
		cc.addSale(iCommissionCalculator.CONSULTING_ITEM, 40000.00);
		
		//check if sales are adding correctly
		assertEquals(90000.00, cc.getTotalSales(), 0.01);
		
		//check if proper commission amount is returned
		assertEquals((88000 * 0.03), cc.calculateCommission(), 0.01);
		
		//check if proper bonus commission amount is returned
		assertEquals((40000 * 0.005), cc.calculateBonusCommission(), 0.01);
	}
	
	
	/**
	 * This test checks if bonus commission is properly calculated for probationary level seller
	 * and for consulting services
	 * @throws Exception
	 */
	@Test
	public void testingSetEmployeeExperience() throws Exception 
	{
		//create CommissionCalculator object with valid input
		CommissionCalculator cc =  new CommissionCalculator("Deni", 22);
		
		//test if minimum sale constant is correct for probationary level commission
		cc.setEmployeeExperience(iCommissionCalculator.PROBATIONARY);
		assertEquals(2000.00, cc.getMinimumSales(), 0.01);
		
		//test if minimum sale constant is correct for experienced level commission
		cc.setEmployeeExperience(iCommissionCalculator.EXPERIENCED);
		assertEquals(5000.00, cc.getMinimumSales(), 0.01);
	}
}