package pl.pkrysztofiak.demo.fxlux;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.demo.reactor.Additions;
import pl.pkrysztofiak.demo.reactor.Values;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

public class Grid {

    private final ObservableList<Panel> panels = FXCollections.observableArrayList();
    private final Flux<Panel> panelAddedFlux = Additions.of(panels).startWith(panels);

    private final Property<Bounds> boundsProperty = new SimpleObjectProperty<>(new Bounds(0, 0, 100, 100));
    private final Flux<Bounds> boundsFlux = Values.of(boundsProperty);

    public Grid(Panel... panels) {
        this.panels.setAll(panels);
        panelAddedFlux.startWith(panels).subscribe(this::onPanelAdded);
    }

    private void onPanelAdded(Panel panel) {
//        boundsFlux.withLatestFrom(panel.ratioBoundsFlux, Tuples::of)
//                .map(tuple -> panel.calculateBounds(tuple.getT1(), tuple.getT2()))
//                .subscribe(panel::setBounds);
        boundsFlux.withLatestFrom(panel.ratioBoundsFlux, Tuples::of)
                .flatMap(tuple -> panel.calculateBoundsAsync(tuple.getT1(), tuple.getT2()))
                .subscribe(panel::setBounds);
    }


}