package br.bootcamp.dio.ecommerce.checkout.service;

import br.bootcamp.dio.ecommerce.checkout.entity.CheckoutEntity;
import br.bootcamp.dio.ecommerce.checkout.resource_controller.checkout.CheckoutResquest;

import java.util.Optional;

public interface CheckoutService {

    Optional<CheckoutEntity> create(CheckoutResquest checkoutResquest);

}
