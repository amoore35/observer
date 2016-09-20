/**
 * 
 */
package headfirst.seniorstudent2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Alexia Moore
 *
 */
public class WeatherDataTest {
	
	private WeatherData weatherData;
	private CurrentConditionsDisplay display;
	private CurrentConditionsDisplay display2;
	private CurrentConditionsDisplay display3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		weatherData = new WeatherData();
		display = new CurrentConditionsDisplay(weatherData, 0, 0);
		display2 = new CurrentConditionsDisplay(weatherData, 0, 0);
		display3 = new CurrentConditionsDisplay(weatherData, 0, 0);
	}
	
	@Test
	public void testDeleteObserver() {
		int count = weatherData.countObservers();
		weatherData.deleteObserver(display);
		weatherData.deleteObserver(display3);
		assertEquals(count - 2, weatherData.countObservers());
	}
	
	@Test
	public void testSetMeasurements() {
		float temperature = 62;
		float humidity = 40;
		float pressure = 20;
		weatherData.setMeasurements(temperature, humidity, pressure);
		assertTrue(weatherData.getTemperature() == temperature && weatherData.getHumidity() == humidity 
				&& weatherData.getPressure() == pressure);
	}
	
	@Test
	public void testAddObserver() {
		weatherData.deleteObservers();
		int count = weatherData.countObservers();
		weatherData.addObserver(display);
		weatherData.addObserver(display2);
		assertEquals(count + 2, weatherData.countObservers());
	}
}
