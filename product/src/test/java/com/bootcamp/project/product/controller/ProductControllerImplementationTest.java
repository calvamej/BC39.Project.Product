package com.bootcamp.project.product.controller;

import com.bootcamp.project.product.entity.ProductEntity;
import com.bootcamp.project.product.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
public class ProductControllerImplementationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    @Test
    public void save()
    {
        ProductEntity OE = new ProductEntity();
        Mono<ProductEntity> MTest = Mono.just(OE);
        when(productService.save(OE)).thenReturn(MTest);
        webTestClient.post().uri("/Product/Save")
                .body(Mono.just(MTest),ProductEntity.class)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void update()
    {
        ProductEntity OE = new ProductEntity();
        Mono<ProductEntity> MTest = Mono.just(OE);
        when(productService.update("ABC",5)).thenReturn(MTest);
        webTestClient.put().uri("/Product/Update/ABC/5")
                .body(Mono.just(MTest),ProductEntity.class)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void delete()
    {
        given(productService.delete(any())).willReturn(Mono.empty());
        webTestClient.delete().uri("/Product/Delete/ABC")
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void getOne()
    {
        ProductEntity OE = new ProductEntity(null,"AAA","BBB",null,0,0,0,0,0,null,null);
        Mono<ProductEntity> MTest = Mono.just(OE);
        when(productService.getOne(any())).thenReturn(MTest);
        Flux<ProductEntity> responseBody = webTestClient.get().uri("/Product/FindOne/AAA")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductEntity.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(p -> p.getProductName().equals("AAA"))
                .verifyComplete();
    }
    @Test
    public void getAll()
    {
        ProductEntity OE = new ProductEntity(null,"AAA","BBB",null,0,0,0,0,0,null,null);
        ProductEntity OE2 = new ProductEntity(null,"BBB","CCC",null,0,0,0,0,0,null,null);
        Flux<ProductEntity> MTest = Flux.just(OE,OE2);
        when(productService.getAll()).thenReturn(MTest);
        Flux<ProductEntity> responseBody = webTestClient.get().uri("/Product/FindAll")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductEntity.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(OE)
                .expectNext(OE2)
                .verifyComplete();
    }
}
