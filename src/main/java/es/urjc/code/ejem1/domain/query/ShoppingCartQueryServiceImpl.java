package es.urjc.code.ejem1.domain.query;

import es.urjc.code.ejem1.domain.dto.FullShoppingCartDTO;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAShoppingCartRepository;
import org.modelmapper.ModelMapper;

public class ShoppingCartQueryServiceImpl implements ShoppingCartQueryService {

    private SpringDataJPAShoppingCartRepository shoppingCartRepository;
    private ModelMapper mapper = new ModelMapper();

    public ShoppingCartQueryServiceImpl(SpringDataJPAShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public FullShoppingCartDTO getShoppingCart(Long id) {
        return mapper.map(shoppingCartRepository.findById(id), FullShoppingCartDTO.class);
    }
}
