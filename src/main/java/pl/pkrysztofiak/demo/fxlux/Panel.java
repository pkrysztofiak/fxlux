package pl.pkrysztofiak.demo.fxlux;

import com.github.javafaker.Faker;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import pl.pkrysztofiak.demo.reactor.Values;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.TimeUnit;

public class Panel {

    private static int idGenerator = 0;
    private final int id = idGenerator++;

    private final Property<Bounds> boundsProperty = new SimpleObjectProperty<>();
    public final Flux<Bounds> boundsFlux = Values.of(boundsProperty);

    private final Property<Bounds> ratioBoundsProperty = new SimpleObjectProperty<>();
    public final Flux<Bounds> ratioBoundsFlux = Values.of(ratioBoundsProperty);

    public Panel(Bounds ratioBounds) {
        ratioBoundsProperty.setValue(ratioBounds);
    }

    public Bounds calculateBounds(Bounds gridBounds, Bounds ratioBounds) {

        Integer sleep = Faker.instance().random().nextInt(1, 4);
        System.out.println("Panel id=" + id + " calculateBounds sleep=" + sleep + " Thread=" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double x = gridBounds.getX() * ratioBounds.getX();
        double y = gridBounds.getY() * ratioBounds.getY();
        double width = gridBounds.getWidth() * ratioBounds.getWidth();
        double height = gridBounds.getHeight() * ratioBounds.getHeight();
        System.out.println("Panel id=" + id + " calculateBounds done Thread=" + Thread.currentThread().getName());
        return new Bounds(x, y, width, height);
    }

    public Mono<Bounds> calculateBoundsAsync(Bounds gridBounds, Bounds ratioBounds) {
        return Mono.fromSupplier(() -> calculateBounds(gridBounds, ratioBounds)).subscribeOn(Schedulers.boundedElastic());
    }

    public void setBounds(Bounds bounds) {
        boundsProperty.setValue(bounds);
        System.out.println("setBounds()");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Panel{" +
                "id=" + id +
                ", ratioBounds=" + ratioBoundsProperty.getValue().toString() +
                ", bounds=" + boundsProperty.getValue().toString() +
                '}';
    }
}