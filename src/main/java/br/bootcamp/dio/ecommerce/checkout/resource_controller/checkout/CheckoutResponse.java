package br.bootcamp.dio.ecommerce.checkout.resource_controller.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse {

    private String code;

}
