package br.com.organicxpto.estoque;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProdutoRepository extends MongoRepository<Produto, Long> {

    @Query()
    Produto test();
}
