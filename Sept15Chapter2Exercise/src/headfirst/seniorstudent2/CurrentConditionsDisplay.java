/*
 * CurrentConditionsDisplay.java 1.0 Sept 10, 2016
 *
 * Copyright (c) 2016 HFDP
 */
package headfirst.seniorstudent2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplay extends JFrame implements Observer {

  private Observable observable;
  private JTextField humidityTextField;
  private JTextField pressureTextField;
  private JTextField temperatureTextField;

  public CurrentConditionsDisplay(Observable observable, int x, int y) {
    this.setTitle("Current Conditions");
    this.observable = observable;
    observable.addObserver(this);
    createGui();
    this.setLocation(x, y);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
      /**
       * Remove the observer from the Subject so no attempt is made by Subject to nofify a non
       * existing instance that is closed
       */
      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("Removed observer");
        observable.deleteObservers();
      }
    });
    this.pack();
    this.setVisible(true);
  }

  private void createGui() {
    Container container = this.getContentPane();
    JPanel holdGrid = new JPanel();
    JPanel leftGrid = new JPanel();
    JPanel rightGrid = new JPanel();

    leftGrid.setLayout(new GridLayout(3, 1));
    leftGrid.add(new JLabel("Current Temperature ", SwingConstants.RIGHT));
    leftGrid.add(new JLabel("Current Humidity ", SwingConstants.RIGHT));
    leftGrid.add(new JLabel("Current Pressure ", SwingConstants.RIGHT));

    rightGrid.setLayout(new GridLayout(3, 1));
    rightGrid.add(temperatureTextField = new JTextField("0", 10));
    rightGrid.add(humidityTextField = new JTextField("0", 10));
    rightGrid.add(pressureTextField = new JTextField("0", 10));

    holdGrid.setLayout(new BorderLayout(5, 0));
    holdGrid.add(leftGrid, BorderLayout.WEST);
    holdGrid.add(rightGrid, BorderLayout.CENTER);
    container.add(holdGrid, BorderLayout.CENTER);
  }

@Override
public void update(Observable o, Object arg) {
	if (o instanceof WeatherData) {
		WeatherData weatherData = (WeatherData)o;
		this.temperatureTextField.setText("" + weatherData.getTemperature());
		this.humidityTextField.setText("" + weatherData.getHumidity());
		this.pressureTextField.setText("" + weatherData.getPressure());
	}
	
}

}
