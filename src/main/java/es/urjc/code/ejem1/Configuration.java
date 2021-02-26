package es.urjc.code.ejem1;

import es.urjc.code.ejem1.domain.*;
import es.urjc.code.ejem1.infrastructure.CartExpenditureEventPublisher;
import es.urjc.code.ejem1.infrastructure.SpringDataJPACartExpenditureRepositoryAdapter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

import es.urjc.code.ejem1.infrastructure.SpringDataJPAProductRepositoryAdapter;
import es.urjc.code.ejem1.infrastructure.SpringDataJPAShoppingCartRepositoryAdapter;
import es.urjc.code.ejem1.service.ValidationServiceImpl;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public ShoppingCartService shoppingCartService(
	        SpringDataJPAShoppingCartRepositoryAdapter shoppingCartRepositoryAdapter,
	        SpringDataJPAProductRepositoryAdapter productRepositoryAdapter,
			CartExpenditureEventPublisher cartExpenditureEventPublisher) {
		return new ShoppingCartServiceImpl(
		        shoppingCartRepositoryAdapter,
		        productRepositoryAdapter,
		        new ValidationServiceImpl(),
				cartExpenditureEventPublisher);
	}

	@Bean
	public ProductService productService(SpringDataJPAProductRepositoryAdapter repositoryAdapter) {
		return new ProductServiceImpl(repositoryAdapter);
	}

	@Bean
	public CartExpenditureService cartExpenditureService(SpringDataJPACartExpenditureRepositoryAdapter repositoryAdapter) {
		return new CartExpenditureServiceImpl(repositoryAdapter);
	}

	@Bean
	public CartExpenditureEventPublisher applicationEventPublisher(ApplicationEventPublisher applicationEventPublisher){
		return new CartExpenditureEventPublisher(applicationEventPublisher);
	}

}
