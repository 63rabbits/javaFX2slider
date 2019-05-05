package javaFX2slider;

import java.math.BigDecimal;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class SliderController {

	@FXML
	private Slider sdr;
	@FXML
	private TextField txf;

	@FXML
	private Slider sdrHBind;
	@FXML
	private TextField txfHBind;

	@FXML
	private Slider sdrLBind;
	@FXML
	private TextField txfLBind;

	@FXML
	void initialize() {

		// Using Listener
		assert sdr != null : "fx:id=\"sdr\" was not injected: check your FXML file 'Slider.fxml'.";
		assert txf != null : "fx:id=\"txf\" was not injected: check your FXML file 'Slider.fxml'.";
		this.sdr.valueProperty().addListener(sliderListener);
		this.sdr.setValue(10);

		// Using Bind (High-level API)
		assert sdrHBind != null : "fx:id=\"sdrHBind\" was not injected: check your FXML file 'Slider.fxml'.";
		assert txfHBind != null : "fx:id=\"txfHBind\" was not injected: check your FXML file 'Slider.fxml'.";
		this.txfHBind.textProperty().bind(this.sdrHBind.valueProperty().asString());
		this.sdrHBind.setValue(10);

		// Using Bind (Low-level API)
		assert sdrLBind != null : "fx:id=\"sdrLBind\" was not injected: check your FXML file 'Slider.fxml'.";
		assert txfLBind != null : "fx:id=\"txfLBind\" was not injected: check your FXML file 'Slider.fxml'.";
		this.sdrLBind.setValue(10);
		this.txfLBind.textProperty().bind(this.observer(this.sdrLBind));
	}

	// Using Listener
	private ChangeListener<Number> sliderListener = new ChangeListener<Number>() {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			BigDecimal value = new BigDecimal(newValue.doubleValue());
			BigDecimal roundValue = value.setScale(0, BigDecimal.ROUND_DOWN);
			txf.setText(roundValue.toString());
		}
	};

	// Using Bind (High-level API)

	// Using Bind (Low-level API)
	private ObjectBinding<String> observer(Slider p) {
		final Slider sdr = p;
		ObjectBinding<String> sBinding = new ObjectBinding<String>() {
			{
				super.bind(sdr.valueProperty());
			}

			@Override
			protected String computeValue() {
				BigDecimal value = new BigDecimal(sdr.getValue());
				BigDecimal roundValue = value.setScale(0, BigDecimal.ROUND_DOWN);
				return roundValue.toString();
			}
		};
		return sBinding;
	}
}
