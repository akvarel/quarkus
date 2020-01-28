package lv.toposoft.quarkus.microservices.health;

import lv.toposoft.quarkus.microservices.SearchResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class PingSearchResourceHealthCheck implements HealthCheck {

    @Inject
    SearchResource searchResource;

    @Override
    public HealthCheckResponse call() {
        searchResource.hello();
        return HealthCheckResponse.named("Ping Search REST Endpoint").up().build();
    }
}
