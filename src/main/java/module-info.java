module pl.pkrysztofiak.demo.fxlux {
    requires javafx.controls;
    requires javafx.fxml;
    requires reactor.core;
    requires org.reactivestreams;
    requires javafaker;


    opens pl.pkrysztofiak.demo.fxlux to javafx.fxml;
    exports pl.pkrysztofiak.demo.fxlux;
    exports pl.pkrysztofiak.demo.reactor;
    opens pl.pkrysztofiak.demo.reactor to javafx.fxml;
}