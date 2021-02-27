package es.urjc.code.ejem1.domain.command;

import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.ProductDTO;
import es.urjc.code.ejem1.infrastructure.entity.ProductEntity;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import org.modelmapper.ModelMapper;

public class ProductCommandServiceImpl implements ProductCommandService {

    private SpringDataJPAProductRepository repository;
    ModelMapper mapper = new ModelMapper();

    public ProductCommandServiceImpl(SpringDataJPAProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public FullProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity productEntity = mapper.map(productDTO, ProductEntity.class);
        FullProductDTO saveFullProductDTO = mapper.map(repository.save(productEntity), FullProductDTO.class);

        return saveFullProductDTO;
    }

    @Override
    public FullProductDTO deleteProduct(Long id) {
        FullProductDTO product = mapper.map(repository.findById(id), FullProductDTO.class);
        repository.deleteById(id);

        return product;
    }

}
