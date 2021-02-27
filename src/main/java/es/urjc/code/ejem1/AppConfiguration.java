package es.urjc.code.ejem1;

import es.urjc.code.ejem1.domain.service.CartExpenditureService;
import es.urjc.code.ejem1.domain.service.CartExpenditureServiceImpl;
import es.urjc.code.ejem1.domain.command.ProductCommandService;
import es.urjc.code.ejem1.domain.command.ProductCommandServiceImpl;
import es.urjc.code.ejem1.domain.command.ShoppingCartCommandService;
import es.urjc.code.ejem1.domain.command.ShoppingCartCommandServiceImpl;
import es.urjc.code.ejem1.domain.query.ProductQueryService;
import es.urjc.code.ejem1.domain.query.ProductQueryServiceImpl;
import es.urjc.code.ejem1.domain.query.ShoppingCartQueryService;
import es.urjc.code.ejem1.domain.query.ShoppingCartQueryServiceImpl;
import es.urjc.code.ejem1.infrastructure.eventbus.CartExpenditureEventPublisher;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPACartExpenditureRepositoryAdapter;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAShoppingCartRepository;
import es.urjc.code.ejem1.service.ValidationServiceImpl;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

	@Bean
	public ShoppingCartCommandService shoppingCartCommandService(
			SpringDataJPAShoppingCartRepository shoppingCartRepository,
			SpringDataJPAProductRepository productRepository,
			CartExpenditureEventPublisher cartExpenditureEventPublisher) {
		return new ShoppingCartCommandServiceImpl(
				shoppingCartRepository,
				productRepository,
		        new ValidationServiceImpl(),
				cartExpenditureEventPublisher);
	}

	@Bean
	public ProductCommandService productCommandService(SpringDataJPAProductRepository repository) {
		return new ProductCommandServiceImpl(repository);
	}

	@Bean
	public ShoppingCartQueryService shoppingCartQueryService(SpringDataJPAShoppingCartRepository repository) {
		return new ShoppingCartQueryServiceImpl(repository);
	}

	@Bean
	public ProductQueryService productQueryService(SpringDataJPAProductRepository repository) {
		return new ProductQueryServiceImpl(repository);
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
