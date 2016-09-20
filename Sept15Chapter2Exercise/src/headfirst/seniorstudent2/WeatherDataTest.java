/**
 * 
 */
package headfirst.seniorstudent2;

import static org.junit.Assert.*;

import java.util.Observer;
import java.util.Observable;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Alexia Moore
 *
 */
public class WeatherDataTest {
	
	private WeatherData weatherData;
	private TestObserver testObserver;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		weatherData = new WeatherData();
		testObserver = new TestObserver();
	}
	
	@Test
	public void testDeleteObserver() {
		weatherData.addObserver(testObserver);
		int count = weatherData.countObservers();
		weatherData.deleteObserver(testObserver);
		assertEquals(count - 1, weatherData.countObservers());
	}
	
	@Test
	public void testSetMeasurements() {
		weatherData.addObserver(testObserver);
		float temperature = 62;
		float humidity = 40;
		float pressure = 20;
		weatherData.setMeasurements(temperature, humidity, pressure);
		assertTrue(temperature == testObserver.getTemperature());
	}
	
	@Test
	public void testAddObserver() {
		int count = weatherData.countObservers();
		weatherData.addObserver(testObserver);
		assertEquals(count + 1, weatherData.countObservers());
	}
	
	private class TestObserver implements Observer {
		private float temperature;
		private float humidity;
		private float pressure;

		@Override
		public void update(Observable o, Object arg) {
			WeatherData weatherData = (WeatherData)o;
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			this.pressure = weatherData.getPressure();
		}
		
		public float getTemperature() {
			return temperature;
		}
	}
}
