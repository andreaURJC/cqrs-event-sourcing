package es.urjc.code.ejem1.infrastructure;

import es.urjc.code.ejem1.domain.CartExpenditureRepository;
import es.urjc.code.ejem1.domain.FullCartExpenditureDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class SpringDataJPACartExpenditureRepositoryAdapter implements CartExpenditureRepository {

    private final SpringDataJPACartExpenditureRepository cartExpenditureRepository;

    private ModelMapper mapper = new ModelMapper();

    public SpringDataJPACartExpenditureRepositoryAdapter(SpringDataJPACartExpenditureRepository cartExpenditureRepository) {
        this.cartExpenditureRepository = cartExpenditureRepository;
    }

    @Override
    public Collection<FullCartExpenditureDTO> findAll() {
        Collection<CartExpenditureEntity> cartExpenditureEntities = this.cartExpenditureRepository.findAll();
        return Arrays.asList(mapper.map(cartExpenditureEntities, FullCartExpenditureDTO[].class));
    }

    @Override
    public FullCartExpenditureDTO save(FullCartExpenditureDTO fullCartExpenditureDTO) {
        CartExpenditureEntity entity = mapper.map(fullCartExpenditureDTO, CartExpenditureEntity.class);
        return mapper.map(this.cartExpenditureRepository.save(entity), FullCartExpenditureDTO.class);
    }
}
