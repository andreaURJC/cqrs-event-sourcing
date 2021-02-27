package es.urjc.code.ejem1.domain.service.query;

import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.infrastructure.exception.ProductNotFoundException;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collection;

public class ProductQueryServiceImpl implements ProductQueryService {

    private SpringDataJPAProductRepository repository;
    ModelMapper mapper = new ModelMapper();

    public ProductQueryServiceImpl(SpringDataJPAProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<FullProductDTO> getProducts() {
        return Arrays.asList(mapper.map(repository.findAll(), FullProductDTO[].class));
    }

    @Override
    public FullProductDTO getProduct(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(() -> new ProductNotFoundException()), FullProductDTO.class);
    }
}
