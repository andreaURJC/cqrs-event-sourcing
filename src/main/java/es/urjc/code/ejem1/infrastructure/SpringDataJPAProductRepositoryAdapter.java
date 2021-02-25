package es.urjc.code.ejem1.infrastructure;

import java.util.Arrays;
import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import es.urjc.code.ejem1.domain.FullProductDTO;
import es.urjc.code.ejem1.domain.ProductRepository;

@Component
public class SpringDataJPAProductRepositoryAdapter implements ProductRepository {

	private SpringDataJPAProductRepository repository;
	private ModelMapper mapper = new ModelMapper();

	public SpringDataJPAProductRepositoryAdapter(SpringDataJPAProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public Collection<FullProductDTO> finAll() {
		return Arrays.asList(mapper.map(repository.findAll(), FullProductDTO[].class));
	}

	@Override
	public FullProductDTO findById(Long id) {
		return mapper.map(repository.findById(id).orElseThrow(ProductNotFoundException::new), FullProductDTO.class);
	}

	@Override
	public FullProductDTO save(FullProductDTO product) {
		ProductEntity productEntity = mapper.map(product, ProductEntity.class);
		repository.save(productEntity);

		return mapper.map(productEntity, FullProductDTO.class);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
