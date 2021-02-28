package es.urjc.code.ejem1.domain.service.command;

import es.urjc.code.ejem1.domain.dto.CreateProductDTO;
import es.urjc.code.ejem1.domain.dto.DeleteProductDTO;
import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.ProductDTO;
import es.urjc.code.ejem1.infrastructure.eventbus.ProductEventPublisher;
import es.urjc.code.ejem1.infrastructure.exception.ProductNotFoundException;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import org.modelmapper.ModelMapper;

public class ProductCommandServiceImpl implements ProductCommandService {

    private ProductEventPublisher publisher;
    private final SpringDataJPAProductRepository repository;

    ModelMapper mapper = new ModelMapper();
    long id = 4L;

    public ProductCommandServiceImpl(ProductEventPublisher publisher, SpringDataJPAProductRepository repository) {
        this.publisher = publisher;
        this.repository = repository;
    }

    @Override
    public FullProductDTO createProduct(ProductDTO productDTO) {

        //TODO convertir a UUID
        id += 1L;
        productDTO.setId(id);
        CreateProductDTO createProductDto = mapper.map(productDTO, CreateProductDTO.class);
        publisher.publish(createProductDto);

        return mapper.map(productDTO, FullProductDTO.class);
    }

    @Override
    public FullProductDTO deleteProduct(Long id) {
        DeleteProductDTO productDto = new DeleteProductDTO(id);
        FullProductDTO product = mapper.map(repository.findById(id).orElseThrow(() -> new ProductNotFoundException()), FullProductDTO.class);
        publisher.publish(productDto);

        return product;
    }

}
